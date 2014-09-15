package de.lsem.simulation.property;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.jface.viewers.ComboViewer;
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

import de.lsem.repository.model.simulation.Activity;
import de.lsem.repository.model.simulation.DistributionFunction;
import de.lsem.repository.model.simulation.ICapacity;
import de.lsem.repository.model.simulation.IConstant;
import de.lsem.repository.model.simulation.IDistribution;
import de.lsem.repository.model.simulation.IDistributionFunction;
import de.lsem.repository.model.simulation.ITime;
import de.lsem.repository.model.simulation.QueuingStrategy;
import de.lsem.repository.model.simulation.ServiceType;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.repository.model.simulation.UnitOfTime;
import de.lsem.simulation.DistributionFunctionProvider;

/**
 * Even though generating origin, destination, startDate and endDate values are
 * not set because they are not needed in simulation. Same with values at
 * "Picking".
 * 
 * @author Lewin
 * 
 *         Need to reset element contents when refreshing values -->
 *         resetValuesOfElements
 */
public class ActivityServiceSection extends LSEMElementGeneralPropertySection {

	private static final Logger logger = Logger
			.getLogger(ActivityServiceSection.class.getSimpleName());

	private ComboViewer serviceCV;
	private ComboViewer queueCV;
	private ComboViewer timeCV;
	private CCombo distCV;

	// private CLabel serviceLabel;
	private CLabel durationLabel;
	private CLabel amountLabel;
	private CLabel maxCapacityLabel;
	private CLabel queueLabel;
	private CLabel constTextLabel;
	private CLabel distCVLabel;
	private CLabel timeCVLabel;

	private Text durationText;
	private Text amountText;
	private Text maxCapacityText;
	private Text constText;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		createServiceComboAndLabel();
		createKommisionierungSection();
		createStorageSection();
		createTransportSection();

		addAllSelectionListeners();
	}

	private void addAllSelectionListeners() {
		serviceCV.addSelectionChangedListener(comboServiceSelectionListener);
		queueCV.addSelectionChangedListener(comboQueueStrategySelectionListener);
		distCV.addSelectionListener(comboDistributionFunctionSelectionChangedListener);
		timeCV.addSelectionChangedListener(comboTimeSelectionListener);
	}

	private void addModifyListeners() {
		if (maxCapacityText != null)
			maxCapacityText.addModifyListener(modifyStorageListener);
		if (constText != null)
			constText.addModifyListener(modifyConstantListener);
		if (distCV != null)
			distCV.addModifyListener(modifyComboDistributionListener);
		if (timeCV != null)
			timeCV.addSelectionChangedListener(comboTimeSelectionListener);
		if (serviceCV != null)
			serviceCV
					.addSelectionChangedListener(comboServiceSelectionListener);
		if (queueCV != null)
			queueCV.addSelectionChangedListener(comboQueueStrategySelectionListener);
		if (distCV != null)
			distCV.addSelectionListener(comboDistributionFunctionSelectionChangedListener);
		if (timeCV != null)
			timeCV.addSelectionChangedListener(comboTimeSelectionListener);

	}

	private void removeModifyListeners() {
		if (maxCapacityText != null)
			maxCapacityText.removeModifyListener(modifyStorageListener);
		if (constText != null)
			constText.removeModifyListener(modifyConstantListener);
		if (distCV != null)
			distCV.removeModifyListener(modifyComboDistributionListener);
		if (serviceCV != null)
			serviceCV
					.removeSelectionChangedListener(comboServiceSelectionListener);
		if (queueCV != null)
			queueCV.removeSelectionChangedListener(comboQueueStrategySelectionListener);
		if (distCV != null)
			distCV.removeSelectionListener(comboDistributionFunctionSelectionChangedListener);
		if (timeCV != null)
			timeCV.removeSelectionChangedListener(comboTimeSelectionListener);
	}

	@Override
	public void refresh() {
		removeModifyListeners();
		resetValuesOfElements();

		if (getSelectedPictogramElement() != null) {
			final Object bo = getSelectedBusinessObject();

			if (bo == null && !(bo instanceof Activity))
				return;

			Activity activity = ((Activity) bo);

			// ServiceType Combo
			if (serviceCV != null) {

				// Set service type
				ServiceType serviceType = activity.getServiceType();
				if (serviceType == null) {
					setDefaultServiceType();
					return;
				}
				// logger.log(Level.INFO,
				// serviceType.getClass().getSimpleName());

				// set default service
				StructuredSelection sl = new StructuredSelection(
						(serviceType == null) ? ServiceType.DEFAULT
								: serviceType);

				serviceCV.setSelection(sl, true);
				changeServiceVisibility(serviceType);

				// Transport selection
				// if(serviceType.getName().equals(TRANSPORT)){

				// Time period
				if (activity.getTimePeriod() == null) {
					createDefaultTimePeriod();
				}

				// DeviationType Combo (Constant, Uniform, DeviationFunction)
				// System.out.println("distcv != null");
				IDistribution period = activity.getTimePeriod().getPeriod();
				// logger.log(Level.INFO, period.toString());

				// DistribtionFnct
				if (period instanceof IDistributionFunction) {
					try {
						if (distCV != null) {
							for (String s : distCV.getItems()) {
								// System.out.println(s + " vs. " +
								// period.getClass().getSimpleName());
								if (s.contains(period.getClass()
										.getSimpleName())) {
									String label = getLabelGenerator()
											.getDistributionFunctionFor(
													(DistributionFunction) period);
									distCV.add(label, distCV.indexOf(s));
									distCV.setData(label, period);
									distCV.remove(s);
									distCV.select(distCV.indexOf(label));
									break;
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (period instanceof IConstant) {
					try {
						// logger.log(Level.INFO,
						// " Period INSTANCE OF CONSTANT");
						constText.setText("" + ((IConstant) period).getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// Timeunit
				String unit = activity.getTimePeriod().getUnit() == null ? TIME_VALUES.get(0)
						: activity.getTimePeriod().getUnit().getLiteral();
				StructuredSelection ss = new StructuredSelection(unit);
				if (timeCV != null)
					timeCV.setSelection(ss, true);

			}
			ICapacity capacity = activity.getCapacity();
			if (capacity != null) {

				// Storage
				// else if( serviceType.getName().equals(STORAGE)){
				// Queue-Strategy
				String queueValue = activity.getCapacity().getQueueStrategy()
						.getLiteral();
				if (queueValue == null)
					queueValue = QueuingStrategy.FIFO.getLiteral();

				QueuingStrategy qs = QueuingStrategy.get(queueValue);

				StructuredSelection selection = new StructuredSelection(qs);
				queueCV.setSelection(selection, true);
				// Max. Capacity
				String maxCapacityString = String.valueOf(activity
						.getCapacity().getMaxCapacity());
				maxCapacityText.setText(maxCapacityString);
				// }//End Storage selection
				// else if(serviceType.getName().equals(KOMMISSIONIERUNG)){
				// TODO Implementieren, sobald service typ angepasst
				// String menge = activity.get...
				// String dauer = activity.get...
				// }
			}
		}

		addModifyListeners();

		// oldObject = getSelectedPictogramElement();

	}

	private void createDefaultTimePeriod() {
		TransactionalEditingDomain ted = getEditDomain();
		ted.getCommandStack().execute(new RecordingCommand(ted) {

			@Override
			protected void doExecute() {

				Activity activity = (Activity) getSelectedBusinessObject();

				ITime time = createTime();
				IConstant timePeriod = createConstant();
				time.setPeriod(timePeriod);
				time.setUnit(UnitOfTime.HOUR);

				getDiagramContents().add(time);
				getDiagramContents().add(timePeriod);
				activity.setTimePeriod(time);

			}

			private EList<EObject> getDiagramContents() {
				return getDiagram().eResource().getContents();
			}

			private IConstant createConstant() {
				return SimulationFactory.eINSTANCE.createConstant();
			}

			private ITime createTime() {
				return SimulationFactory.eINSTANCE.createTime();
			}
		});
	}

	private void setDefaultServiceType() {
		TransactionalEditingDomain ted = getEditDomain();
		ted.getCommandStack().execute(new RecordingCommand(ted) {

			@Override
			protected void doExecute() {

				Activity activity = (Activity) getSelectedBusinessObject();

				// IService service =
				// MetamodelFactory.eINSTANCE.createService();
				// //TODO Kann knallen... test!
				// service.setName(ElementConstants.DEFAULT_SERVICE);
				// getDiagram().eResource().getContents().add(service);
				activity.setServiceType(ServiceType.DEFAULT);
			}
		});
	}

	private TransactionalEditingDomain getEditDomain() {
		return getDiagramContainer().getDiagramBehavior().getEditingDomain();
	}

	private EObject getSelectedBusinessObject() {
		return Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(
						getSelectedPictogramElement());
	}

	private void resetValuesOfElements() {
		try {
			serviceCV.setSelection(new StructuredSelection());
			timeCV.setSelection(new StructuredSelection());
			queueCV.setSelection(new StructuredSelection());
			distCV.select(-1);
			durationText.setText("");
			amountText.setText("");
			maxCapacityText.setText("");
			constText.setText("");
		} catch (Exception e) {
		}
	}

	private ModifyListener modifyConstantListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			TransactionalEditingDomain ted = getEditDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					PictogramElement pe = getSelectedPictogramElement();
					Activity activity = (Activity) Graphiti.getLinkService()
							.getBusinessObjectForLinkedPictogramElement(pe);

					if (constText.getText() != null
							&& !constText.getText().equals("")) {
						// Before it was a constant
						IConstant constant = null;
						if (activity.getTimePeriod().getPeriod() instanceof IConstant) {
							constant = ((IConstant) activity.getTimePeriod()
									.getPeriod());
						}
						// Before it was a DistributionFunction
						else if (activity.getTimePeriod().getPeriod() instanceof IDistributionFunction) {
							constant = SimulationFactory.eINSTANCE
									.createConstant();
							activity.setTimePeriod(switchDistributionItem(
									activity.getTimePeriod(), constant));
						}
						if (constant != null) {
							try {
								float l = Float.parseFloat(constText.getText());
								constant.setValue(l);
								activity.getTimePeriod().setPeriod(constant);
							} catch (Exception e) {
							}// Empty String or not a figure
						}
					}
				}
			});
		}
	};

	private ModifyListener modifyStorageListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			TransactionalEditingDomain ted = getEditDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					PictogramElement pe = getSelectedPictogramElement();
					Activity activity = (Activity) Graphiti.getLinkService()
							.getBusinessObjectForLinkedPictogramElement(pe);

					String maxCapString = maxCapacityText.getText();
					try {
						int value = Integer.parseInt(maxCapString);
						activity.getCapacity().setMaxCapacity(value);
					} catch (Exception e1) {
					}
				}
			});
		}
	};

	private ModifyListener modifyPickingListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent e) {
			try {
				TransactionalEditingDomain ted = getEditDomain();
				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {
						// Activity activity =
						// (Activity)Graphiti.getLinkService()
						// .getBusinessObjectForLinkedPictogramElement(
						// getSelectedPictogramElement());
					}
				});
			} catch (Exception e1) {
			}

		}
	};

	private ISelectionChangedListener comboServiceSelectionListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			TransactionalEditingDomain ted = getEditDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					Activity activity = (Activity) getSelectedBusinessObject();

					// ############# Function Type (Picking, Transport ...)
					IStructuredSelection sel = (IStructuredSelection) serviceCV
							.getSelection();
					Object firstElement = sel.getFirstElement();
					// logger.log(Level.INFO, ""+firstElement);
					if (firstElement == null)
						return;

					if (!activity.getServiceType().equals(firstElement)) {

						if /* Picking */(firstElement
								.equals(ServiceType.PICKING)) {
							activity.setServiceType(ServiceType.PICKING);
							changeServiceVisibility(ServiceType.PICKING);
						} /* Value added */else if (firstElement
								.equals(ServiceType.VALUE_ADDED)) {
							activity.setServiceType(ServiceType.VALUE_ADDED);
							changeServiceVisibility(ServiceType.VALUE_ADDED);
						} /* Handling */else if (firstElement
								.equals(ServiceType.HANDLING)) {
							activity.setServiceType(ServiceType.HANDLING);
							changeServiceVisibility(ServiceType.HANDLING);
						} /* Storage */else if (firstElement
								.equals(ServiceType.STORAGE)) {
							activity.setServiceType(ServiceType.STORAGE);
							changeServiceVisibility(ServiceType.STORAGE);
						} /* Transport */else if (firstElement
								.equals(ServiceType.TRANSPORT)) {
							activity.setServiceType(ServiceType.TRANSPORT);
							changeServiceVisibility(ServiceType.TRANSPORT);
						} /* Default Service-Type */else if (firstElement
								.equals(ServiceType.DEFAULT)) {
							activity.setServiceType(ServiceType.DEFAULT);
							changeServiceVisibility(ServiceType.DEFAULT);
						} /* Default */else {
							changeServiceVisibility(ServiceType.DEFAULT);
						}

					}// IF Equals
				}
				// }//End IF
				// }//End For
			});
		}
	};

	private ITime switchDistributionItem(ITime time, IDistribution itemToAdd) {
		// Remove from diagram
		getDiagram().eResource().getContents().remove(time.getPeriod());
		// add IDistribution to BO
		time.setPeriod(itemToAdd);
		// Add new IDistribution to diagram
		getDiagram().eResource().getContents().add(itemToAdd);

		return time;
	}

	private ModifyListener modifyComboDistributionListener = new ModifyListener() {

		@Override
		public void modifyText(ModifyEvent arg0) {
			TransactionalEditingDomain ted = getEditDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {

					Activity activity = (Activity) getSelectedBusinessObject();

					String text = distCV.getText().trim();
					logger.log(Level.INFO, text);
					if (text != null && !text.isEmpty()) {
						IDistributionFunction fct = getLabelGenerator()
								.getDistributionFunctionForLSEMElement(text);
						logger.log(Level.INFO, fct.toString());
						if (fct != null) {
							ITime distributionItem = switchDistributionItem(
									activity.getTimePeriod(), fct);
							activity.setTimePeriod(distributionItem);
							logger.log(Level.INFO,
									"distribution function switched");
						}
					}
				}
			});
		}
	};

	private SelectionListener comboDistributionFunctionSelectionChangedListener = new SelectionListener() {

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			TransactionalEditingDomain ted = getEditDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {

					Activity activity = (Activity) getSelectedBusinessObject();

					String text = distCV.getText().trim();
					logger.log(Level.INFO, text);
					if (text != null && !text.isEmpty()) {
						IDistributionFunction fct = getLabelGenerator()
								.getDistributionFunctionForLSEMElement(text);
						if (fct != null) {
							activity.setTimePeriod(switchDistributionItem(
									activity.getTimePeriod(), fct));
						}
					}

				}
			});
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
		}
	};

	private ISelectionChangedListener comboTimeSelectionListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			TransactionalEditingDomain ted = getEditDomain();

			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					Activity activity = (Activity) getSelectedBusinessObject();

					// System.out.println("********** " + timeCV.getSelection()
					// + " ********* ");
					// ################### Time-Values (Hour, Minute ...)
					// ############
					IStructuredSelection sel = (IStructuredSelection) timeCV
							.getSelection();

					Object firstElement = sel.getFirstElement();

					if (firstElement == null)
						return;

					if (activity.getTimePeriod() == null) {
						ITime time = SimulationFactory.eINSTANCE.createTime();
						getDiagram().eResource().getContents().add(time);
						activity.setTimePeriod(time);
					}

					String textString = firstElement.toString();
					if (textString.equals(TIME_SECOND)) {
						activity.getTimePeriod().setUnit(
								UnitOfTime.get(TIME_SECOND));
					} else if (textString.equals(TIME_MINUTE)) {
						activity.getTimePeriod().setUnit(
								UnitOfTime.get(TIME_MINUTE));
					} else if (textString.equals(TIME_HOUR)) {
						activity.getTimePeriod().setUnit(
								UnitOfTime.get(TIME_HOUR));
					} else if (textString.equals(TIME_DAY)) {
						activity.getTimePeriod().setUnit(
								UnitOfTime.get(TIME_DAY));
					}
				}
			});

		}
	};

	private ISelectionChangedListener comboQueueStrategySelectionListener = new ISelectionChangedListener() {

		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			TransactionalEditingDomain ted = getEditDomain();
			ted.getCommandStack().execute(new RecordingCommand(ted) {

				@Override
				protected void doExecute() {
					ILinkService linkService = Graphiti.getLinkService();
					EObject eObject = linkService
							.getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());

					Activity activity = (Activity) eObject;

					IStructuredSelection sel = (IStructuredSelection) queueCV
							.getSelection();
					Object firstElement = sel.getFirstElement();
					if (firstElement == null)
						return;

					String textString = firstElement.toString();

					for (QueuingStrategy qs : QueuingStrategy.VALUES) {
						if (qs.getLiteral().equals(textString)) {
							activity.getCapacity().setQueueStrategy(qs);
							break;
						}
					}

				}
			});

		}
	};

	private void createServiceComboAndLabel() {
		serviceCV = createComboViewer(ServiceType.values(), SWT.READ_ONLY);
		createCLabel(serviceCV.getControl(), SERVICE_TYPE);
	}

	private void createKommisionierungSection() {
		durationText = createText("");
		durationText.addModifyListener(modifyPickingListener);
		durationLabel = createCLabel(durationText, DAUER_LABEL);
		amountText = createText(durationText, "");
		amountLabel = createCLabel(amountText, MENGE_LABEL);
	}

	private void createStorageSection() {
		queueCV = createComboViewer(QueuingStrategy.values(),
				serviceCV.getControl(), SWT.READ_ONLY);
		queueLabel = createCLabel(queueCV.getControl(), QUEUETYPE_STRING);

		maxCapacityText = createText(queueCV.getControl(), "");
		maxCapacityLabel = createCLabel(maxCapacityText, MAX_CAPACITY);
	}

	private void createTransportSection() {

		// Time section when Transport is chosen
		distCV = createCCombo(DistributionFunctionProvider.INSTANCE
				.getDistributionFunctions().toArray(), serviceCV.getControl(),
				SWT.NONE);
		distCVLabel = createCLabel(distCV, DEVIATION_TYPE);

		// 2.1 const-value
		constText = createText(distCV, "");
		// 2.2 Label
		constTextLabel = createCLabel(constText, CONSTANT_CONSTANT);

		timeCV = createComboViewer(TIME_VALUES.toArray(), constText, SWT.READ_ONLY);

		timeCVLabel = createCLabel(timeCV.getControl(), ZEITEINHEIT_CONSTANT);
	}

	private void changeServiceVisibility(ServiceType selectedFunction) {
		setAllSubElementsToInvicible();
		try {
			if (selectedFunction.equals(ServiceType.PICKING)) {
				setPickingSelected();
			} else if (selectedFunction.equals(ServiceType.VALUE_ADDED)) {
			} else if (selectedFunction.equals(ServiceType.HANDLING)) {
			} else if (selectedFunction.equals(ServiceType.STORAGE)) {
				setStorageSelected();
			} else if (selectedFunction.equals(ServiceType.TRANSPORT)) {
				setTransportSelected();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void setAllSubElementsToInvicible() {
		try {
			durationLabel.setVisible(false);
			amountLabel.setVisible(false);
			maxCapacityLabel.setVisible(false);
			queueLabel.setVisible(false);

			durationText.setVisible(false);
			amountText.setVisible(false);
			maxCapacityText.setVisible(false);
			queueCV.getControl().setVisible(false);

			constText.setVisible(false);
			constTextLabel.setVisible(false);
			distCVLabel.setVisible(false);
			timeCVLabel.setVisible(false);

			// okTODO Test whether reactivation needs to be done
			timeCV.getControl().setVisible(false);
			distCV.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setPickingSelected() {
		durationText.setVisible(true);
		amountText.setVisible(true);
		durationLabel.setVisible(true);
		amountLabel.setVisible(true);
	}

	private void setStorageSelected() {
		queueCV.getControl().setVisible(true);
		queueLabel.setVisible(true);

		maxCapacityText.setVisible(true);
		maxCapacityLabel.setVisible(true);
	}

	private void setTransportSelected() throws Exception {
		distCV.setVisible(true);
		distCVLabel.setVisible(true);

		constText.setVisible(true);
		constTextLabel.setVisible(true);

		timeCV.getControl().setVisible(true);
		timeCVLabel.setVisible(true);
	}

	class Listeners {
		
		void addModifyListeners() {
			if (maxCapacityText != null)
				maxCapacityText.addModifyListener(modifyStorageListener);
			if (constText != null)
				constText.addModifyListener(modifyConstantListener);
			if (distCV != null)
				distCV.addModifyListener(modifyComboDistributionListener);
			if (timeCV != null)
				timeCV.addSelectionChangedListener(comboTimeSelectionListener);
			if (serviceCV != null)
				serviceCV
						.addSelectionChangedListener(comboServiceSelectionListener);
			if (queueCV != null)
				queueCV.addSelectionChangedListener(comboQueueStrategySelectionListener);
			if (distCV != null)
				distCV.addSelectionListener(comboDistributionFunctionSelectionChangedListener);
			if (timeCV != null)
				timeCV.addSelectionChangedListener(comboTimeSelectionListener);
			if (durationText != null) {
				durationText.addModifyListener(modifyPickingListener);
			}

		}

		void removeModifyListeners() {
			if (maxCapacityText != null)
				maxCapacityText.removeModifyListener(modifyStorageListener);
			if (constText != null)
				constText.removeModifyListener(modifyConstantListener);
			if (distCV != null)
				distCV.removeModifyListener(modifyComboDistributionListener);
			if (durationText != null) {
				durationText.removeModifyListener(modifyPickingListener);
			}
			if (serviceCV != null)
				serviceCV
						.removeSelectionChangedListener(comboServiceSelectionListener);
			if (queueCV != null)
				queueCV.removeSelectionChangedListener(comboQueueStrategySelectionListener);
			if (distCV != null)
				distCV.removeSelectionListener(comboDistributionFunctionSelectionChangedListener);
			if (timeCV != null)
				timeCV.removeSelectionChangedListener(comboTimeSelectionListener);

		}
		
		protected ISelectionChangedListener comboQueueStrategySelectionListener = new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				TransactionalEditingDomain ted = getEditDomain();
				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {
						ILinkService linkService = Graphiti.getLinkService();
						EObject eObject = linkService
								.getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());

						Activity activity = (Activity) eObject;

						IStructuredSelection sel = (IStructuredSelection) queueCV
								.getSelection();
						Object firstElement = sel.getFirstElement();
						if (firstElement == null)
							return;

						String textString = firstElement.toString();

						for (QueuingStrategy qs : QueuingStrategy.VALUES) {
							if (qs.getLiteral().equals(textString)) {
								activity.getCapacity().setQueueStrategy(qs);
								break;
							}
						}

					}
				});

			}
		};

		protected ModifyListener modifyComboDistributionListener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				TransactionalEditingDomain ted = getEditDomain();
				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {

						Activity activity = (Activity) getSelectedBusinessObject();

						String text = distCV.getText().trim();
						logger.log(Level.INFO, text);
						if (text != null && !text.isEmpty()) {
							IDistributionFunction fct = getLabelGenerator()
									.getDistributionFunctionForLSEMElement(text);
							logger.log(Level.INFO, fct.toString());
							if (fct != null) {
								ITime distributionItem = switchDistributionItem(
										activity.getTimePeriod(), fct);
								activity.setTimePeriod(distributionItem);
								logger.log(Level.INFO,
										"distribution function switched");
							}
						}
					}
				});
			}
		};

		protected SelectionListener comboDistributionFunctionSelectionChangedListener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TransactionalEditingDomain ted = getEditDomain();
				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {

						Activity activity = (Activity) getSelectedBusinessObject();

						String text = distCV.getText().trim();
						logger.log(Level.INFO, text);
						if (text != null && !text.isEmpty()) {
							IDistributionFunction fct = getLabelGenerator()
									.getDistributionFunctionForLSEMElement(text);
							if (fct != null) {
								activity.setTimePeriod(switchDistributionItem(
										activity.getTimePeriod(), fct));
							}
						}

					}
				});
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		};

		protected ISelectionChangedListener comboTimeSelectionListener = new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				TransactionalEditingDomain ted = getEditDomain();

				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {
						Activity activity = (Activity) getSelectedBusinessObject();

						// System.out.println("********** " +
						// timeCV.getSelection()
						// + " ********* ");
						// ################### Time-Values (Hour, Minute ...)
						// ############
						IStructuredSelection sel = (IStructuredSelection) timeCV
								.getSelection();

						Object firstElement = sel.getFirstElement();

						if (firstElement == null)
							return;

						if (activity.getTimePeriod() == null) {
							ITime time = SimulationFactory.eINSTANCE
									.createTime();
							getDiagram().eResource().getContents().add(time);
							activity.setTimePeriod(time);
						}

						String textString = firstElement.toString();
						if (textString.equals(TIME_SECOND)) {
							activity.getTimePeriod().setUnit(
									UnitOfTime.get(TIME_SECOND));
						} else if (textString.equals(TIME_MINUTE)) {
							activity.getTimePeriod().setUnit(
									UnitOfTime.get(TIME_MINUTE));
						} else if (textString.equals(TIME_HOUR)) {
							activity.getTimePeriod().setUnit(
									UnitOfTime.get(TIME_HOUR));
						} else if (textString.equals(TIME_DAY)) {
							activity.getTimePeriod().setUnit(
									UnitOfTime.get(TIME_DAY));
						}
					}
				});

			}
		};

		protected ModifyListener modifyConstantListener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				TransactionalEditingDomain ted = getEditDomain();
				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {
						PictogramElement pe = getSelectedPictogramElement();
						Activity activity = (Activity) Graphiti
								.getLinkService()
								.getBusinessObjectForLinkedPictogramElement(pe);

						if (constText.getText() != null
								&& !constText.getText().equals("")) {
							// Before it was a constant
							IConstant constant = null;
							if (activity.getTimePeriod().getPeriod() instanceof IConstant) {
								constant = ((IConstant) activity
										.getTimePeriod().getPeriod());
							}
							// Before it was a DistributionFunction
							else if (activity.getTimePeriod().getPeriod() instanceof IDistributionFunction) {
								constant = SimulationFactory.eINSTANCE
										.createConstant();
								activity.setTimePeriod(switchDistributionItem(
										activity.getTimePeriod(), constant));
							}
							if (constant != null) {
								try {
									float l = Float.parseFloat(constText
											.getText());
									constant.setValue(l);
									activity.getTimePeriod()
											.setPeriod(constant);
								} catch (Exception e) {
								}// Empty String or not a figure
							}
						}
					}
				});
			}
		};

		protected ModifyListener modifyStorageListener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				TransactionalEditingDomain ted = getEditDomain();
				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {
						PictogramElement pe = getSelectedPictogramElement();
						Activity activity = (Activity) Graphiti
								.getLinkService()
								.getBusinessObjectForLinkedPictogramElement(pe);

						String maxCapString = maxCapacityText.getText();
						try {
							int value = Integer.parseInt(maxCapString);
							activity.getCapacity().setMaxCapacity(value);
						} catch (Exception e1) {
						}
					}
				});
			}
		};

		protected ModifyListener modifyPickingListener = new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				try {
					TransactionalEditingDomain ted = getEditDomain();
					ted.getCommandStack().execute(new RecordingCommand(ted) {

						@Override
						protected void doExecute() {
							// Activity activity =
							// (Activity)Graphiti.getLinkService()
							// .getBusinessObjectForLinkedPictogramElement(
							// getSelectedPictogramElement());
						}
					});
				} catch (Exception e1) {
				}

			}
		};

		protected ISelectionChangedListener comboServiceSelectionListener = new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				TransactionalEditingDomain ted = getEditDomain();
				ted.getCommandStack().execute(new RecordingCommand(ted) {

					@Override
					protected void doExecute() {
						Activity activity = (Activity) getSelectedBusinessObject();

						// ############# Function Type (Picking, Transport ...)
						IStructuredSelection sel = (IStructuredSelection) serviceCV
								.getSelection();
						Object firstElement = sel.getFirstElement();
						// logger.log(Level.INFO, ""+firstElement);
						if (firstElement == null)
							return;

						if (!activity.getServiceType().equals(firstElement)) {

							if /* Picking */(firstElement
									.equals(ServiceType.PICKING)) {
								activity.setServiceType(ServiceType.PICKING);
								changeServiceVisibility(ServiceType.PICKING);
							} /* Value added */else if (firstElement
									.equals(ServiceType.VALUE_ADDED)) {
								activity.setServiceType(ServiceType.VALUE_ADDED);
								changeServiceVisibility(ServiceType.VALUE_ADDED);
							} /* Handling */else if (firstElement
									.equals(ServiceType.HANDLING)) {
								activity.setServiceType(ServiceType.HANDLING);
								changeServiceVisibility(ServiceType.HANDLING);
							} /* Storage */else if (firstElement
									.equals(ServiceType.STORAGE)) {
								activity.setServiceType(ServiceType.STORAGE);
								changeServiceVisibility(ServiceType.STORAGE);
							} /* Transport */else if (firstElement
									.equals(ServiceType.TRANSPORT)) {
								activity.setServiceType(ServiceType.TRANSPORT);
								changeServiceVisibility(ServiceType.TRANSPORT);
							} /* Default Service-Type */else if (firstElement
									.equals(ServiceType.DEFAULT)) {
								activity.setServiceType(ServiceType.DEFAULT);
								changeServiceVisibility(ServiceType.DEFAULT);
							} /* Default */else {
								changeServiceVisibility(ServiceType.DEFAULT);
							}

						}// IF Equals
					}
					// }//End IF
					// }//End For
				});
			}
		};
	} // End class Listeners
}
