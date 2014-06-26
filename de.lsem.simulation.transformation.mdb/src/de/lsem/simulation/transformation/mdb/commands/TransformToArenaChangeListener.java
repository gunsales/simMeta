package de.lsem.simulation.transformation.mdb.commands;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;

public class TransformToArenaChangeListener implements IJobChangeListener {

	private IWorkbenchWindow workbench;

	public TransformToArenaChangeListener(final IWorkbenchWindow workbench) {
		this.workbench = workbench;
	}
	
	@Override
	public void sleeping(IJobChangeEvent event) {}

	@Override
	public void scheduled(IJobChangeEvent event) {}

	@Override
	public void running(IJobChangeEvent event) {}

	@Override
	public void done(final IJobChangeEvent event) {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if(event.getResult().isOK()) {					
					MessageBox msg = new MessageBox(workbench.getShell(), 
							SWT.ICON_INFORMATION);
					msg.setText(Messages.TransformToArena_10);
					msg.setMessage(Messages.TransformToArena_11);
					msg.open();
				}
			}
		});
	}

	@Override
	public void awake(IJobChangeEvent event) {}

	@Override
	public void aboutToRun(IJobChangeEvent event) {}

}
