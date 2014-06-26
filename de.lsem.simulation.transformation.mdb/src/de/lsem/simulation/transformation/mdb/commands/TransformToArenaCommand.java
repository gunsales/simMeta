package de.lsem.simulation.transformation.mdb.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.graphiti.platform.IDiagramContainer;
import org.eclipse.graphiti.ui.editor.DiagramEditorInput;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class TransformToArenaCommand implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow workbench = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();

		IEditorPart editor = getActiveEditor(event);
		if (editor.isDirty()) {
			int open = createSaveMessageBox(event);
			if (open == SWT.YES) {
				editor.doSave(new NullProgressMonitor());
			} else {
				return null;
			}
		}
		
		createAndExecuteTransformationJob(workbench);

		return null;
	}

	private void createAndExecuteTransformationJob(IWorkbenchWindow workbench) {
		Job job = new TransformToArenaJob("Transform to ARENA", workbench);
		job.setUser(true);
		job.addJobChangeListener(createIJobChangeListener(workbench));
		job.schedule();
	}
	
	protected int createSaveMessageBox(ExecutionEvent event) {
		MessageBox box = new MessageBox(HandlerUtil.getActiveShell(event),
				SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		box.setText("Save the editor?");
		box.setMessage("The transformation will begin as soon as you save the diagram. Should the editor-state be saved?");
		return box.open();
	}

	private IJobChangeListener createIJobChangeListener(
			final IWorkbenchWindow workbench) {
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
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						if (event.getResult().isOK()) {
							MessageBox msg = new MessageBox(workbench
									.getShell(), SWT.ICON_INFORMATION);
							msg.setText(Messages.TransformToArena_10);
							msg.setMessage(Messages.TransformToArena_11);
							msg.open();
						}
					}
				});
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
	public boolean isEnabled() {
		try {
			IEditorPart editor = getActiveEditor();
			DiagramEditorInput ei = getEditorInput(editor);

			return (editor instanceof IDiagramContainer && ei.getUri()
					.fileExtension().equals(Messages.TransformToArena_9));
		} catch (Exception e) {
		}
		return false;
	}

	private DiagramEditorInput getEditorInput(IEditorPart editor) {
		DiagramEditorInput ei = (DiagramEditorInput) editor
				.getEditorInput();
		return ei;
	}

	private IEditorPart getActiveEditor() {
		IEditorPart editor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		return editor;
	}

	private IEditorPart getActiveEditor(ExecutionEvent event) {
		return HandlerUtil.getActiveEditor(event);
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

}
