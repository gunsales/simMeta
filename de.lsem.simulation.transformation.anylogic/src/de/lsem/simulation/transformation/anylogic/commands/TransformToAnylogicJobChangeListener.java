package de.lsem.simulation.transformation.anylogic.commands;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import de.lsem.simulation.transformation.anylogic.Activator;
import de.lsem.simulation.transformation.anylogic.generator.persistant.AnyLogicWorkspace;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Connector;
import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject;
import de.lsem.simulation.transformation.anylogic.transform.helper.ClassGenerator;

public class TransformToAnylogicJobChangeListener implements IJobChangeListener {

	private TransformToAnylogicJob transformerJob;
	private XMIResource xmiResource;

	public TransformToAnylogicJobChangeListener(TransformToAnylogicJob transformJob, XMIResource resource) {
		this.transformerJob = transformJob;
		this.xmiResource = resource;
		
		createJobChangeListener(transformJob, resource);
	}
	
	@Override
	public void aboutToRun(IJobChangeEvent event) {
	}

	@Override
	public void awake(IJobChangeEvent event) {
	}

	@Override
	public void done(IJobChangeEvent event) {
		if (event.getResult().isOK()
				|| event.getResult().matches(Status.WARNING)) {
			Job job = createInitSaveFileMarshalAndOpenAnylogicJob(
					transformerJob, xmiResource);
			job.addJobChangeListener(createJobDoneChangeListener());
			job.setUser(true);
			job.schedule();

		}
	}
	
	private void marshallDatShit(AnyLogicWorkspace anyLogicWorkspace,
			IFile targetFile) throws JAXBException, IOException {

		// Create and initialize the marshaller
		Marshaller m = initMarshaller();

		// Write to System.out
//		m.marshal(anyLogicWorkspace, System.out);

		// Write to File
		File dummyFile = targetFile.getRawLocation().makeAbsolute().toFile();

		FileWriter writer = new FileWriter(dummyFile);
		m.marshal(anyLogicWorkspace, writer);
		writer.flush();
		writer.close();
	}
	
	private IJobChangeListener createJobChangeListener(
//			final ExecutionEvent executionEvent,
			final TransformToAnylogicJob transformerJob,
			final XMIResource xmiResource) {
		return new IJobChangeListener() {

			@Override
			public void sleeping(IJobChangeEvent event) {
			}

			@Override
			public void scheduled(IJobChangeEvent event) {
			}

			@Override
			public void running(IJobChangeEvent event) {
			}

			@Override
			public void done(final IJobChangeEvent event) {

				if (event.getResult().isOK()
						|| event.getResult().matches(Status.WARNING)) {
					Job job = createInitSaveFileMarshalAndOpenAnylogicJob(
							transformerJob, xmiResource);
					job.addJobChangeListener(createJobDoneChangeListener());
					job.setUser(true);
					job.schedule();

				}
			}

			private IJobChangeListener createJobDoneChangeListener(
					) {
				return new IJobChangeListener() {

					@Override
					public void sleeping(IJobChangeEvent event) {
					}

					@Override
					public void scheduled(IJobChangeEvent event) {
					}

					@Override
					public void running(IJobChangeEvent event) {
					}

					@Override
					public void done(final IJobChangeEvent event) {

						Display.getDefault().syncExec(new Runnable() {
							public void run() {
								if (event.getResult().isOK()) {
									createMessageBox();
								}
							}
						});

					}

					private void createMessageBox() {
						MessageBox msg = new MessageBox(
								getActiveEditor().getEditorSite().getShell(),
								SWT.ICON_INFORMATION);
						msg.setText("Erfolg");
						msg.setMessage("Transformation zu Anylogic erfolgreich abgeschlossen.");
						msg.open();
					}

					@Override
					public void awake(IJobChangeEvent event) {
					}

					@Override
					public void aboutToRun(IJobChangeEvent event) {
					}
				};
			}

			

			@Override
			public void awake(IJobChangeEvent event) {
			}

			@Override
			public void aboutToRun(IJobChangeEvent event) {
			}
		};
	}
	
	private Marshaller initMarshaller() throws JAXBException, PropertyException {
		JAXBContext context = JAXBContext.newInstance(AnyLogicWorkspace.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		// Unescape all characters such as "<" or ">", which usually are
		// presented as "&lgt;" or "&rgt;"
		m.setProperty(CharacterEscapeHandler.class.getName(),
				new CharacterEscapeHandler() {
					@Override
					public void escape(char[] ch, int start, int length,
							boolean isAttVal, Writer out) throws IOException {
						out.write(ch, start, length);
					}
				});
		return m;
	}
	
	private Job createInitSaveFileMarshalAndOpenAnylogicJob(
			final TransformToAnylogicJob transformerJob,
			final XMIResource xmiResource) {
		Job job = new Job("Initializing and opening Anylogic.") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// Get transformed objects from job
				List<EmbeddedObject> objects = transformerJob
						.getEmbeddedObjects();

				Set<Connector> connections = transformerJob
						.getConnections();

				try {

					// Create and init the targetFile
					IFile targetFile = initTargetFile(xmiResource);

					// Build root-object and insert embedded objects
					// recursively
					String targetFileName = targetFile.getName()
							.replace(targetFile.getFileExtension(), "")
							.replace(".", "");
					AnyLogicWorkspace anyLogicWorkspace = ClassGenerator
							.createAnylogicWorkspace(targetFileName,
									objects, connections);

					// Write to sysout and targetFile
					marshallDatShit(anyLogicWorkspace, targetFile);

					// Refresh project-view
					targetFile.getProject().refreshLocal(
							IProject.DEPTH_INFINITE,
							new NullProgressMonitor());

					// Open generated file
					Desktop.getDesktop().open(
							targetFile.getRawLocation().toFile());
				} catch (JAXBException e) {
					e.printStackTrace();
					return createTransformToAnylogicStatusWithException(e);
				} catch (CoreException e) {
					e.printStackTrace();

					Display.getDefault().syncExec(new Runnable() {

						@Override
						public void run() {
							MessageDialog
									.openError(
											Display.getDefault()
													.getActiveShell(),
											"Error when transforming",
											"Could not overwrite target-file. Please close the target-file in any other program.");
						}
					});
					return createTransformToAnylogicStatusWithException(e);
				} catch (IOException e) {
					e.printStackTrace();
					return createTransformToAnylogicStatusWithException(e);
				}
				return new Status(Status.OK, Activator.PLUGIN_ID,
						"Initialized and opened transformed model in anylogic.");

			}

			private IStatus createTransformToAnylogicStatusWithException(
					Exception e) {
				return new Status(Status.ERROR, Activator.PLUGIN_ID,
						"Failed to transform to anylogic.", e);
			}
		};
		return job;
	}

	private IFolder createTargetFolderFor(IProject project) {
		try {
			project.open(new NullProgressMonitor());
			IFolder srcGenFolder = project.getFolder("anylogic-gen");
			if (!srcGenFolder.exists()) {
				srcGenFolder.create(true, true, new NullProgressMonitor());
			}
			return srcGenFolder;
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return null;
	}
	private IFile getSourceFileFrom(XMIResource xmiResource) {
		URI uri = xmiResource.getURI();
		URI resolvedFile = CommonPlugin.resolve(uri);

		String projectNameString = getProjectNameFor(uri);
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectNameString);

		Path path = new Path(resolvedFile.toFileString());
		return project.getFile(path);
	}

	private IFile initTargetFile(final XMIResource xmiResource)
			throws CoreException {
		IFile sourceFile = getSourceFileFrom(xmiResource);
		IFolder targetFolder = createTargetFolderFor(sourceFile.getProject());

		IFile targetFile = createTargetFile(sourceFile, targetFolder);
		return targetFile;
	}

	


	@Override
	public void running(IJobChangeEvent event) {
	}

	@Override
	public void scheduled(IJobChangeEvent event) {
	}

	@Override
	public void sleeping(IJobChangeEvent event) {
	}
	
	private IJobChangeListener createJobDoneChangeListener() {
		return new IJobChangeListener() {

			@Override
			public void sleeping(IJobChangeEvent event) {
			}

			@Override
			public void scheduled(IJobChangeEvent event) {
			}

			@Override
			public void running(IJobChangeEvent event) {
			}

			@Override
			public void done(final IJobChangeEvent event) {

				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						if (event.getResult().isOK()) {
							createMessageBox();
						}
					}
				});

			}

			private void createMessageBox() {
				MessageBox msg = new MessageBox(
						getActiveEditor().getEditorSite().getShell(),
						SWT.ICON_INFORMATION);
				msg.setText("Erfolg");
				msg.setMessage("Transformation zu Anylogic erfolgreich abgeschlossen.");
				msg.open();
			}

			@Override
			public void awake(IJobChangeEvent event) {
			}

			@Override
			public void aboutToRun(IJobChangeEvent event) {
			}
		};
		
		
	}

	private String getProjectNameFor(URI uri) {
		String uriString = uri.toString();
		String trimmedFirst = uriString.substring(19, uriString.length());
		trimmedFirst = trimmedFirst.replaceAll("%20", " ");
		return trimmedFirst.substring(0, trimmedFirst.indexOf("/"));
	}


	private IFile createTargetFile(IFile sourceFile, IFolder targetFolder)
			throws CoreException {

		IFile targetFile = targetFolder.getFile(sourceFile.getName().replace(
				".diagram", ".alp"));
		int counter = 1;
		while (targetFile.exists() && !targetFile.isAccessible()) {

			targetFile = targetFolder.getFile("(" + counter + ")"
					+ sourceFile.getName().replace(".diagram", ".alp"));
			counter++;
		}

		// if (targetFile.exists()) {
		// targetFile.delete(true, new NullProgressMonitor());
		// }

		return targetFile;
	}
	
	private IEditorPart getActiveEditor() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor();
	}
	
	
	


}
