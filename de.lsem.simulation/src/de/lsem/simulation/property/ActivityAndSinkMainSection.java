package de.lsem.simulation.property;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.util.ElementConstants;
import de.lsem.simulation.util.LSEMElementHelper;
import de.lsem.simulation.util.NameValidator;

public class ActivityAndSinkMainSection extends GFPropertySection implements
		ITabbedPropertyConstants {

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
			final ISimulationElement act = (ISimulationElement) bo;
			// TransactionalEditingDomain ted =
			// TransactionUtil.getEditingDomain(getDiagram());
			String name = act.getName();
			nameText.setText(name == null ? ElementConstants.LABEL_EMPTY : name);
		}
		nameText.addModifyListener(modifyActivityListener);
	}

	private ModifyListener modifyActivityListener = new ModifyListener() {

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

						if (!preCheck(dummy)) {
							InputDialog dialog = createUniqueNameInputDialog(dummy, (ISimulationElement) selectedPicto);
							int asd = dialog.open();
							System.out.println(asd + " " + Window.OK);
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
									getDiagram()));
					return dialog;
				}
			});
		}
	};

	/*
	 * Check if name equals another object in editor --> Do not allow, as names
	 * are treated like IDs
	 */
	private boolean preCheck(String newElementName) {
		EList<EObject> contents = getDiagram().eResource().getContents();
		List<ISimulationElement> filteredContents = LSEMElementHelper
				.getElementsFromDiagram(contents);

		for (ISimulationElement e : filteredContents) {
			if (e.getName().equals(newElementName)) {
				return false;
			}

			for (IRelation r : e.getOutgoing()) {
				if (r.getName().equals(newElementName)) {
					return false;
				}
			}
		}

		return true;
	}

}