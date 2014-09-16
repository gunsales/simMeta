package de.lsem.simulation.property;

import static de.lsem.simulation.util.LSEMElementHelper.nameExists;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.property.validators.NameValidator;
import de.lsem.simulation.util.ElementConstants;

public class ActivityAndSinkMainSection extends LSEMElementGeneralPropertySection implements
		ITabbedPropertyConstants {

	private Text nameText;

	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		nameText = createText(ElementConstants.LABEL_EMPTY);
		nameText.setLayoutData(getElementFormData());
		nameText.addModifyListener(modifyActivityListener);

		createCLabel(nameText,
				ElementConstants.LABEL_NAME).setLayoutData(getLabelFormData(nameText));
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
			final ISimulationElement act = (ISimulationElement) bo;
			// TransactionalEditingDomain ted =
			// TransactionUtil.getEditingDomain(getDiagram());
			String name = act.getName();
			nameText.setText(name == null ? ElementConstants.LABEL_EMPTY : name);
		}
		nameText.addModifyListener(modifyActivityListener);
	}

	private final ModifyListener modifyActivityListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();

			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {

					EObject selectedPicto = getPictogramElement();

					if (selectedPicto instanceof ISimulationElement) {
						ISimulationElement sim = (ISimulationElement) selectedPicto;
						String dummy = nameText.getText();

						if (nameExists(getContents(), dummy)) {
							InputDialog dialog = createUniqueNameInputDialog(dummy, (ISimulationElement) selectedPicto);
							int asd = dialog.open();
							if (asd == Window.OK) {
								dummy = dialog.getValue();
								nameText.setText(dummy);
							} else {
								nameText.setText(sim.getName());
								dummy = sim.getName();
							}
						}

						sim.setName(dummy);
					}

				}

				private EObject getPictogramElement() {
					EObject selectedPicto = Graphiti.getLinkService()
							.getBusinessObjectForLinkedPictogramElement(
									getSelectedPictogramElement());
					return selectedPicto;
				}

				private InputDialog createUniqueNameInputDialog(
						String currentValue, ISimulationElement dummy) {
					Shell ac = Display.getDefault().getActiveShell();
					InputDialog dialog = new InputDialog(ac,
							"Unique name needed.",
							"Please insert a unique name", currentValue,
									//+ "_XX"
									new NameValidator(dummy,
									getContents()));
					return dialog;
				}

			
			});
		}
	};
	private EList<EObject> getContents() {
		return getDiagram().eResource().getContents();
	}
}