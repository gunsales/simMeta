package de.lsem.simulation.property;

import org.eclipse.emf.common.command.CommandStack;
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

import de.lsem.repository.model.simulation.IGood;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.simulation.util.ElementConstants;

public class SourceGoodSection extends GFPropertySection implements
		ITabbedPropertyConstants, ElementConstants {

	private Text nameText;
	private Text typeText;
	private Text descText;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

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

		typeText = factory.createText(composite, LABEL_EMPTY);
		data = new FormData();
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(nameText, VSPACE);
		typeText.setLayoutData(data);

		valueLabel = factory.createCLabel(composite, LABEL_TYPE);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(typeText, -HSPACE);
		data.top = new FormAttachment(typeText, 0, SWT.CENTER);
		valueLabel.setLayoutData(data);

		descText = factory.createText(composite, LABEL_EMPTY, SWT.MULTI
				| SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		data = new FormData(SWT.DEFAULT, 100);
		data.left = new FormAttachment(0, 120);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(typeText, VSPACE);
		// data.bottom = new FormAttachment(typeText, 0, SWT.BOTTOM);
		descText.setLayoutData(data);

		valueLabel = factory.createCLabel(composite, LABEL_DESCRIPTION);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(descText, -HSPACE);
		data.top = new FormAttachment(descText, 0, SWT.CENTER);
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