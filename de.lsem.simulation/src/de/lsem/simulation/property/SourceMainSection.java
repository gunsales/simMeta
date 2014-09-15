package de.lsem.simulation.property;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import de.lsem.repository.model.simulation.ISource;
import de.lsem.simulation.util.ElementConstants;

public class SourceMainSection extends LSEMElementGeneralPropertySection implements
		ITabbedPropertyConstants, ElementConstants {

	private Text maxNewEntitiesText;
	private Text nameText;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		nameText = createText(LABEL_EMPTY);
		nameText.setLayoutData(getElementFormData());
		createCLabel(nameText, LABEL_NAME).setLayoutData(getLabelFormData(nameText));

		maxNewEntitiesText = createText(nameText, LABEL_EMPTY);
		maxNewEntitiesText.setLayoutData(getElementFormData(nameText));
		createCLabel(maxNewEntitiesText, LABEL_MAX_ENTITIES).setLayoutData(getLabelFormData(maxNewEntitiesText));

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
