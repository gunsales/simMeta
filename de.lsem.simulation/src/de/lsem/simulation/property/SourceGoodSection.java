package de.lsem.simulation.property;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.simulation.util.ElementConstants;

public class SourceGoodSection extends LSEMElementGeneralPropertySection implements
		ITabbedPropertyConstants, ElementConstants {

	private Text nameText;
	private Text typeText;
	private Text descText;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		nameText = createText(LABEL_EMPTY);
		nameText.setLayoutData(getElementFormData());
		createCLabel(nameText, LABEL_NAME).setLayoutData(getLabelFormData(nameText));

		typeText = createText(nameText, LABEL_EMPTY);
		typeText.setLayoutData(getElementFormData(nameText));
		createCLabel(typeText, LABEL_TYPE).setLayoutData(getLabelFormData(typeText));

		descText = createText(LABEL_EMPTY, SWT.MULTI
				| SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		descText.setLayoutData(getElementFormData(typeText));

		createCLabel(descText, LABEL_DESCRIPTION).setLayoutData(getLabelFormData(descText));

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
			ISource src = (ISource) bo;
			IGood good = src.getProcessedObject();

			if (good == null) {
				src = createGood(src);
			}
			String name = src.getProcessedObject().getName();
			String desc = src.getProcessedObject().getDescription();
			String type = src.getProcessedObject().getType();

			nameText.setText(name == null ? LABEL_EMPTY : name);
			typeText.setText(type == null ? LABEL_EMPTY : type);
			descText.setText(desc == null ? LABEL_EMPTY : desc);
		}
		addModifyListeners();
	}

	private ISource createGood(final ISource src) {
		getCommandStackForTransactionalEditingDomain().execute(
				new RecordingCommand(getTransactionalEditingDomain()) {

					@Override
					protected void doExecute() {
						IGood good = SimulationFactory.eINSTANCE.createGood();
						good.setDescription("");
						good.setName("");
						good.setType("");
						getDiagram().eResource().getContents().add(good);
						src.setProcessedObject(good);
					}
				});
		return src;
	}

	private CommandStack getCommandStackForTransactionalEditingDomain() {
		return getTransactionalEditingDomain().getCommandStack();
	}

	private TransactionalEditingDomain getTransactionalEditingDomain() {
		return getDiagramContainer().getDiagramBehavior().getEditingDomain();
	}

	private ModifyListener modifyActivityListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					PictogramElement pe = getSelectedPictogramElement();
					ISource activity = (ISource) Graphiti.getLinkService()
							.getBusinessObjectForLinkedPictogramElement(pe);

					activity.getProcessedObject().setName(nameText.getText());
					activity.getProcessedObject().setType(typeText.getText());
					activity.getProcessedObject().setDescription(
							descText.getText());
				}
			});
		}
	};

	private void addModifyListeners() {
		nameText.addModifyListener(modifyActivityListener);
		typeText.addModifyListener(modifyActivityListener);
		descText.addModifyListener(modifyActivityListener);
	}

	private void removeModifyListeners() {
		nameText.removeModifyListener(modifyActivityListener);
		typeText.removeModifyListener(modifyActivityListener);
		descText.removeModifyListener(modifyActivityListener);
	}
}