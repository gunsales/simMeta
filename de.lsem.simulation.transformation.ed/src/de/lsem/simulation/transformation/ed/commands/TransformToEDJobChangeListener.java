package de.lsem.simulation.transformation.ed.commands;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;

public class TransformToEDJobChangeListener implements IJobChangeListener {

	private final IWorkbenchWindow workbench;

	public TransformToEDJobChangeListener(final IWorkbenchWindow workbench) {
		this.workbench = workbench;
	}

	@Override
	public void done(final IJobChangeEvent event) {

		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if(event.getResult().isOK()) {
					createMessageBox();
				}
			}

			private void createMessageBox() {
				MessageBox msg = new MessageBox(workbench.getShell(), 
						SWT.ICON_INFORMATION);
				msg.setText(Messages.TransformToED_11);
				msg.setMessage(Messages.TransformToED_12);
				msg.open();
			}
		});
	}

	@Override
	public void awake(IJobChangeEvent event) {}

	@Override
	public void aboutToRun(IJobChangeEvent event) {}

	@Override
	public void sleeping(IJobChangeEvent event) {}

	@Override
	public void scheduled(IJobChangeEvent event) {}

	@Override
	public void running(IJobChangeEvent event) {}
}
