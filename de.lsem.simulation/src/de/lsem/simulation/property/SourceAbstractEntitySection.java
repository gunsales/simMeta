package de.lsem.simulation.property;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IDistribution;
import de.lsem.repository.model.simulation.IDistributionFunction;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.repository.model.simulation.UnitOfTime;
import de.lsem.simulation.DistributionFunctionProvider;
import de.lsem.simulation.property.xtend.LSEMElementGeneralPropertySection;
import de.lsem.simulation.util.ElementConstants;

public class SourceAbstractEntitySection extends
LSEMElementGeneralPropertySection {

	private static final Logger logger = Logger
			.getLogger(SourceAbstractEntitySection.class.getSimpleName());

	ITime entity;

	CCombo distCC;
	ComboViewer timeCV;

	Text constText;

	CLabel constantLabel;
	CLabel timeLabel;
	CLabel distComboLabel;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		distCC = createCCombo(getComposite(), DistributionFunctionProvider.INSTANCE
				.getDistributionFunctions().toArray(), getComposite(), SWT.NONE);
		distComboLabel = createCLabel(distCC, ElementConstants.DEVIATION_TYPE);

		createDistSection_Constant();

		timeCV = createComboViewer(getComposite(), UnitOfTime.values(), getComposite(), SWT.READ_ONLY);
		timeLabel = createCLabel(timeCV.getControl(), ElementConstants.ZEITEINHEIT_CONSTANT);

		addListeners();

	}

	private void createDistSection_Constant() {
		// ************** 2.Constant
		// 2.1 value
		constText = createText(distCC);
		// 2.2 Label
		constantLabel = createCLabel(constText, ElementConstants.CONSTANT_CONSTANT);
	}

	@Override
	public void refresh() {

		try {
			resetValuesOfElements();
		} catch (Exception e) {
		}

		removeListeners();

		if (entity == null) {
			createEntity();
		}

		IDistribution period = entity.getPeriod();
		// DistribtionFnct
		if (period instanceof IDistributionFunction) {
			try {
				logger.log(Level.INFO, "Period INSTANCE OF DISTFUNC");
				if (distCC != null) {
					for (String s : distCC.getItems()) {
						// System.out.println(s + " vs. " +
						// period.getClass().getSimpleName());
						if (s.startsWith(period.getClass().getSimpleName())) {
							String label = getLabelGenerator().getDistributionFunctionLabelForComboViewer((IDistributionFunction) period);
							distCC.add(label, distCC.indexOf(s));
							distCC.setData(label, period);
							distCC.remove(s);
							distCC.select(distCC.indexOf(label));
							// System.out.println("-->" + s);
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (period instanceof IConstant) {
			try {
				logger.log(Level.INFO, " Period INSTANCE OF CONSTANT");
				IConstant constant = (IConstant) period;
				constText.setText(String.valueOf(constant.getValue()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		// Time Period
		// periodText.setText(String.valueOf(activity.getTimePeriod().getPeriod()));

		// Timeunit
		UnitOfTime unit = entity == null ? UnitOfTime.DAY : entity.getUnit();
		StructuredSelection ss = new StructuredSelection(unit);
		if (timeCV != null)
			timeCV.setSelection(ss, true);

		addListeners();
	}

	private void createEntity() {
		getDiagramContainer()
		.getDiagramBehavior()
		.getEditingDomain()
		.getCommandStack()
		.execute(
				new RecordingCommand(getDiagramContainer()
						.getDiagramBehavior().getEditingDomain()) {

					@Override
					protected void doExecute() {
						entity = SimulationFactory.eINSTANCE
								.createTime();
						IConstant constantDummy = SimulationFactory.eINSTANCE
								.createConstant();

						constantDummy.setValue(0.0);

						entity.setUnit(UnitOfTime.HOUR);
						entity.setPeriod(constantDummy);

						getDiagram().eResource().getContents()
						.add(constantDummy);
						getDiagram().eResource().getContents()
						.add(entity);
					}
				});
	}

	private void resetValuesOfElements() {
		constText.setText(LABEL_EMPTY);
		distCC.select(-1);
		timeCV.setSelection(new StructuredSelection());
	}

	protected ModifyListener modifyComboDistributionListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent arg0) {
			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {

					String text = distCC.getText().trim();
					logger.log(Level.INFO, text);
					if (text != null && !text.isEmpty()) {
						IDistributionFunction fct = getLabelGenerator().getDistributionFunctionForLSEMElement(text);
						if (fct != null) {
							logger.log(Level.INFO, fct.toString());
							entity = switchDistributionItem(entity, fct);
							logger.log(Level.INFO,
									"distribution function switched");
						}
					}
				}
			});
		}
	};

	private ITime switchDistributionItem(ITime time, IDistribution itemToAdd) {
		assert (time != null);
		assert (itemToAdd != null);
		// Remove from diagram
		getDiagram().eResource().getContents().remove(time.getPeriod());

		// Change deviationType to IUniform and add it
		time.setPeriod(itemToAdd);
		// Add new to diagram
		getDiagram().eResource().getContents().add(itemToAdd);

		return time;
	}

	protected SelectionListener comboDistributionFunctionSelectionChangedListener = new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {

					String text = distCC.getText().trim();
					logger.log(Level.INFO, text);
					if (text != null && !text.isEmpty()) {
						IDistributionFunction fct = getLabelGenerator().getDistributionFunctionForLSEMElement(text);
						logger.log(Level.INFO, fct.toString());
						if (fct != null) {
							entity = switchDistributionItem(entity, fct);
							logger.log(Level.INFO,
									"distribution function switched");
						}
					}

				}
			});
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
		}
	};

	private void addListeners() {
		if (timeCV != null)
			timeCV.addSelectionChangedListener(timeComboSelectionListener);
		if (distCC != null) {
			distCC.addSelectionListener(comboDistributionFunctionSelectionChangedListener);
			distCC.addModifyListener(modifyComboDistributionListener);
		}

		addModifyTextListeners();
	}

	private void removeListeners() {
		if (timeCV != null)
			timeCV.removeSelectionChangedListener(timeComboSelectionListener);
		if (distCC != null) {
			distCC.removeSelectionListener(comboDistributionFunctionSelectionChangedListener);
			distCC.removeModifyListener(modifyComboDistributionListener);
		}

		removeModifyTextListeners();
	}

	private void addModifyTextListeners() {
		if (constText != null)
			constText.addModifyListener(modifyConstantListener);

	}

	private void removeModifyTextListeners() {
		if (constText != null)
			constText.removeModifyListener(modifyConstantListener);
	}

	private ModifyListener modifyConstantListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {

			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();

			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					if (entity != null) {

						String constTextString = constText.getText();
						try {
							float f = Float.parseFloat(constTextString);

							if (entity.getPeriod() == null) {
								entity.setPeriod(SimulationFactory.eINSTANCE
										.createConstant());
								getDiagram().eResource().getContents()
								.add(entity.getPeriod());
							}
							logger.log(Level.INFO, entity.getPeriod()
									.toString());

							// IF old value is a constant --> Do nothing
							if (entity.getPeriod() instanceof IConstant) {

							}
							// Else if old value is a distribution-function
							else if (entity.getPeriod() instanceof IDistributionFunction) {
								entity = switchDistributionItem(entity,
										SimulationFactory.eINSTANCE
										.createConstant());
							}

							((IConstant) entity.getPeriod()).setValue(f);

						} catch (NumberFormatException e) {
							return;
						}
					}
				}
			});
		}
	};

	private ISelectionChangedListener timeComboSelectionListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			TransactionalEditingDomain ted = getDiagramContainer()
					.getDiagramBehavior().getEditingDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					ISelection selection = timeCV.getSelection();
					if (selection instanceof IStructuredSelection
							&& !selection.isEmpty() && entity != null) {
						IStructuredSelection strucSel = (IStructuredSelection) selection;

						entity.setUnit(UnitOfTime.get(strucSel
								.getFirstElement().toString()));
					}
				}
			});

		}
	};

}
