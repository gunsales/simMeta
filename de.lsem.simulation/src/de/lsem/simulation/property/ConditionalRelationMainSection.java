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
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import de.lsem.repository.model.simulation.IConditionalRelation;
import de.lsem.repository.model.simulation.IRelation;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.util.ElementConstants;
import de.lsem.simulation.util.LSEMElementHelper;
import de.lsem.simulation.util.NameValidator;

public class ConditionalRelationMainSection extends GFPropertySection implements
		ITabbedPropertyConstants {

	private Text probText;
	private Text condText;
	private Text nameText;

	private CLabel condLabel;
	private CLabel probLabel;

	private Button buttonBedingung;
	private Button buttonWahrscheinlichkeit;
	private TabbedPropertySheetWidgetFactory factory;
	private Composite buttonCombo;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		factory = getWidgetFactory();
		Composite composite = factory.createFlatFormComposite(parent);

		createNameSection(composite);
		buttonCombo = createChoiseComposite(composite);
		createChoosenSection(composite);

		addModifyListeners();
	}

	private void createChoosenSection(Composite composite) {
		condText = factory.createText(composite, "");
		FormData data = new FormData();
		data.left = new FormAttachment(0, 130);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(buttonCombo, VSPACE);
		condText.setLayoutData(data);

		condLabel = factory.createCLabel(composite,
				ElementConstants.LABEL_CONDITION);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(condText, -HSPACE);
		data.top = new FormAttachment(condText, 0, SWT.CENTER);
		condLabel.setLayoutData(data);

		probText = factory.createText(composite, "");
		data = new FormData();
		data.left = new FormAttachment(0, 130);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(buttonCombo, VSPACE);
		probText.setLayoutData(data);

		probLabel = factory.createCLabel(composite,
				ElementConstants.LABEL_PROBABILITY);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(probText, -HSPACE);
		data.top = new FormAttachment(probText, 0, SWT.CENTER);
		probLabel.setLayoutData(data);

	}

	private void createNameSection(Composite composite) {
		nameText = factory.createText(composite, "");
		FormData data = new FormData();
		data.left = new FormAttachment(0, 130);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, VSPACE);
		nameText.setLayoutData(data);

		CLabel valueLabel = factory.createCLabel(composite,
				ElementConstants.LABEL_DESCRIPTION);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(nameText, -HSPACE);
		data.top = new FormAttachment(nameText, 0, SWT.CENTER);
		valueLabel.setLayoutData(data);
	}

	private Composite createChoiseComposite(Composite parent) {

		Composite buttonCombo = factory.createComposite(parent);
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(400, 0);
		data.top = new FormAttachment(nameText, VSPACE);
		buttonCombo.setLayoutData(data);

		buttonBedingung = factory.createButton(buttonCombo,
				ElementConstants.LABEL_CONDITION, SWT.RADIO);
		buttonBedingung.setBounds(130, 0, 75, 30);
		buttonBedingung.addSelectionListener(buttonSelectionListener);
		buttonBedingung.setSelection(true);

		buttonWahrscheinlichkeit = factory.createButton(buttonCombo,
				ElementConstants.LABEL_PROBABILITY, SWT.RADIO);
		buttonWahrscheinlichkeit.setBounds(210, 0, 150, 30);
		buttonWahrscheinlichkeit.addSelectionListener(buttonSelectionListener);

		CLabel cLabel = factory.createCLabel(buttonCombo,
				ElementConstants.LABEL_BASED_ON);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(buttonBedingung, -HSPACE);
		data.top = new FormAttachment(buttonBedingung, 0, SWT.CENTER);
		cLabel.setLayoutData(data);

		return buttonCombo;
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

			IConditionalRelation cr = (IConditionalRelation) bo;
			String cond = cr.getCondition() == null ? "" : cr.getCondition();
			String name = cr.getName() == null ? "" : cr.getName();

			double roundedProbability = (double) Math
					.round(cr.getProbability() * 100) / 100;
			String prop = String.valueOf(cr.getProbability() == -1 ? 0
					: roundedProbability);

			if (cond.isEmpty()) {
				buttonBedingung.setSelection(false);
				buttonWahrscheinlichkeit.setSelection(true);
				probText.setVisible(true);
				probLabel.setVisible(true);
				condLabel.setVisible(false);
				condText.setVisible(false);
			} else {
				buttonBedingung.setSelection(true);
				buttonWahrscheinlichkeit.setSelection(false);
				probText.setVisible(false);
				probLabel.setVisible(false);
				condLabel.setVisible(true);
				condText.setVisible(true);
			}

			if (nameText != null)
				nameText.setText(name);
			if (condText != null)
				condText.setText(cond == null ? "" : cond);
			if (probText != null)
				probText.setText(prop == null ? "" : prop);
		}

		addModifyListeners();
	}

	private InputDialog createUniqueNameInputDialog(IConditionalRelation dummy) {
		Shell ac = Display.getDefault().getActiveShell();
		InputDialog dialog = new InputDialog(ac, "Unique name needed.",
				"Please insert a unique name", dummy.getName() + "_new",
				new NameValidator(dummy, getDiagram()));
		return dialog;
	}

	private boolean checkNameUnique(String name) {

		EList<EObject> contents = getDiagram().eResource().getContents();

		List<ISimulationElement> elements = LSEMElementHelper
				.getElementsFromDiagram(contents);
		for (ISimulationElement element : elements) {
			if (element.getName().equals(name)) {
				return false;
			}
		}
		List<IRelation> relations = LSEMElementHelper
				.getRelationsFromDiagram(contents);
		for (IRelation element : relations) {
			if (element.getName() == null || element.getName().equals(name)) {
				return false;
			}
		}

		return true;
	}

	private void addModifyListeners() {
		if (condText != null)
			condText.addModifyListener(modifyCondRelListener);
		if (probText != null)
			probText.addModifyListener(modifyCondRelListener);
		if (nameText != null)
			nameText.addModifyListener(modifyCondRelListener);
		// typeText.addModifyListener(modifyActivityListener);
		// descText.addModifyListener(modifyActivityListener);
	}

	private void removeModifyListeners() {
		if (condText != null)
			condText.removeModifyListener(modifyCondRelListener);
		if (probText != null)
			probText.removeModifyListener(modifyCondRelListener);
		if (nameText != null)
			nameText.removeModifyListener(modifyCondRelListener);
		// typeText.removeModifyListener(modifyActivityListener);
		// descText.removeModifyListener(modifyActivityListener);
	}

	private ModifyListener modifyCondRelListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					IConditionalRelation cr = getConditionalRelationFromSelection();

					if (!cr.getCondition().equals(condText.getText())) {
						setCondition(cr);
					}
					if (!cr.getName().equals(nameText.getText())) {
						setName(cr);
					}
					if (cr.getProbability() != Double.parseDouble(probText
							.getText())) {
						setProbability(cr);
					}
				}

				private IConditionalRelation getConditionalRelationFromSelection() {
					PictogramElement pe = getSelectedPictogramElement();
					IConditionalRelation cr = (IConditionalRelation) Graphiti
							.getLinkService()
							.getBusinessObjectForLinkedPictogramElement(pe);
					return cr;
				}

				private void setProbability(IConditionalRelation cr) {
					try {
						cr.setProbability(Float.parseFloat(probText.getText()));
					} catch (Exception e) {
						probText.setText("0");
						MessageBox msg = new MessageBox(probText.getShell(),
								SWT.ICON_ERROR);
						msg.setText("Error");
						msg.setMessage("Wrong input");
						msg.open();
					}
				}

				private void setName(IConditionalRelation cr) {
					String text = nameText.getText() == null ? "" : nameText
							.getText();
					if (text != cr.getName()) {
						if (!checkNameUnique(text)) {
							InputDialog inputDialog = createUniqueNameInputDialog(cr);
							inputDialog.open();
							text = inputDialog.getValue();
							if (text != null)
								nameText.setText(text);
						}
						cr.setName(text);
					}
				}

				private void setCondition(IConditionalRelation cr) {
					cr.setCondition(condText.getText() == null ? "" : condText
							.getText());
				}
			});
		}
	};

	private SelectionListener buttonSelectionListener = new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (buttonWahrscheinlichkeit.getSelection()) {
				probText.setVisible(true);
				probLabel.setVisible(true);

				condText.setVisible(false);
				condLabel.setVisible(false);

			} else {
				probText.setVisible(false);
				probLabel.setVisible(false);

				condText.setVisible(true);
				condLabel.setVisible(true);
			}

		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {

		}
	};

}
