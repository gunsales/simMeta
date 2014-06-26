package de.lsem.simulation.property;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import de.lsem.repository.model.simulation.Activity;
import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.util.ElementConstants;

public class SinkMainSection extends GFPropertySection implements ITabbedPropertyConstants {

	private Text nameText;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		Composite composite = factory.createFlatFormComposite(parent);
		FormData data;

		nameText = factory.createText(composite, ElementConstants.LABEL_EMPTY);
		data = new FormData();
		data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, VSPACE);
		nameText.setLayoutData(data);
		nameText.addModifyListener(modifyActivityListener);

		CLabel valueLabel = factory.createCLabel(composite, 
				ElementConstants.LABEL_NAME);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(nameText, -HSPACE);
		data.top = new FormAttachment(nameText, 0, SWT.CENTER);
		valueLabel.setLayoutData(data);
	}

	@Override
	public void refresh() {
		PictogramElement pe = getSelectedPictogramElement();
		nameText.removeModifyListener(modifyActivityListener);
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);

			if (bo == null)
				return;
			final IActivity act = (IActivity)bo;
			//			TransactionalEditingDomain ted = TransactionUtil.getEditingDomain(getDiagram());
			String name = act.getName();
			nameText.setText(name == null ? ElementConstants.LABEL_EMPTY : name);
		}
		nameText.addModifyListener(modifyActivityListener);
	}

	private ModifyListener modifyActivityListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			TransactionalEditingDomain ted = 
					getDiagramContainer().getDiagramBehavior().getEditingDomain();
			
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					Activity activity = (Activity)Graphiti.getLinkService()
							.getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
					activity.setName(nameText.getText());					
				}
			});			
		}		
	};
}
