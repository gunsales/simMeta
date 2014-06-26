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

import de.lsem.repository.model.simulation.ISource;
import de.lsem.simulation.util.ElementConstants;

public class SourceMainSection extends GFPropertySection implements
		ITabbedPropertyConstants, ElementConstants {

	private Text maxNewEntitiesText;
	private Text nameText;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
		Composite composite = factory.createFlatFormComposite(parent);
		FormData data;

		nameText = factory.createText(composite, LABEL_EMPTY);
		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, VSPACE);
		nameText.setLayoutData(data);

		CLabel valueLabel = factory.createCLabel(composite, LABEL_NAME);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(nameText, -HSPACE);
		data.top = new FormAttachment(nameText, 0, SWT.CENTER);
		valueLabel.setLayoutData(data);

		maxNewEntitiesText = factory.createText(composite, LABEL_EMPTY);
		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(nameText, VSPACE);
		maxNewEntitiesText.setLayoutData(data);

		valueLabel = factory.createCLabel(composite, LABEL_MAX_ENTITIES);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(maxNewEntitiesText, -HSPACE);
		data.top = new FormAttachment(maxNewEntitiesText, 0, SWT.CENTER);
		valueLabel.setLayoutData(data);

		addModifyListeners();
	}

	@Override
	public void refresh() {
		removeModifyListeners();

		PictogramElement pe = getSelectedPictogramElement();
		if (pe != null) {
			Object bo = Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);

			if (bo == null)
				return;
			ISource source = (ISource) bo;
			String name = source.getName();
			String desc = String.valueOf(source.getMaxNewEntities());

			nameText.setText(name == null ? LABEL_EMPTY : name);
			maxNewEntitiesText.setText(desc == null ? LABEL_EMPTY : desc);
		}
		addModifyListeners();
	}

	private ModifyListener modifySourceListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			PictogramElement pe = getSelectedPictogramElement();
			final ISource source = (ISource) Graphiti.getLinkService()
					.getBusinessObjectForLinkedPictogramElement(pe);

			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();

			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					source.setName(nameText.getText());
					try {
						int nr = Integer.parseInt(maxNewEntitiesText.getText());
						source.setMaxNewEntities(nr);
					} catch (Exception e2) {
					}
				}
			});
		}
	};

	private void addModifyListeners() {
		nameText.addModifyListener(modifySourceListener);
		maxNewEntitiesText.addModifyListener(modifySourceListener);
	}

	private void removeModifyListeners() {
		nameText.removeModifyListener(modifySourceListener);
		maxNewEntitiesText.removeModifyListener(modifySourceListener);
	}

}
