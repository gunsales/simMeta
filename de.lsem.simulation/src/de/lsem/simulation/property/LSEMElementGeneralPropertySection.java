package de.lsem.simulation.property;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.DefaultToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import de.lsem.repository.model.simulation.DistributionFunction;
import de.lsem.repository.model.simulation.IBeta;
import de.lsem.repository.model.simulation.IDistribution;
import de.lsem.repository.model.simulation.IDistributionFunction;
import de.lsem.repository.model.simulation.IErlang;
import de.lsem.repository.model.simulation.IGamma;
import de.lsem.repository.model.simulation.ILogNormal;
import de.lsem.repository.model.simulation.INegExp;
import de.lsem.repository.model.simulation.INormal;
import de.lsem.repository.model.simulation.IPoisson;
import de.lsem.repository.model.simulation.ISource;
import de.lsem.repository.model.simulation.ITriangular;
import de.lsem.repository.model.simulation.IUniform;
import de.lsem.repository.model.simulation.IWeibull;
import de.lsem.repository.model.simulation.SimulationFactory;
import de.lsem.simulation.util.ElementConstants;

public class LSEMElementGeneralPropertySection extends GFPropertySection
		implements ITabbedPropertyConstants, ElementConstants {

	private static final Logger logger = Logger
			.getLogger(LSEMElementGeneralPropertySection.class.getSimpleName());

	TabbedPropertySheetWidgetFactory factory;
	Composite composite;

	private DefaultToolTip cComboToolTip;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		factory = getWidgetFactory();
		composite = factory.createFlatFormComposite(parent);
	}

	public ComboViewer createComboViewer(Composite composite, ComboViewer cv,
			Object[] valueSet, Object topObject,
			TabbedPropertySheetWidgetFactory factory, int readOnly) {

		cv = new ComboViewer(composite, readOnly);
		cv.setContentProvider(ArrayContentProvider.getInstance());
		cv.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof IDistributionFunction)
					return getDistributionFunctionLabelForComboViewer((IDistributionFunction) element);
				else if (element instanceof String)
					return element.toString();
				return super.getText(element);
			}
		});
		cv.setInput(valueSet);

		for (int i = 0; i < valueSet.length; i++) {
			// cv.add(valueSet[i].toString());
			// cv.setData(valueSet[i].toString(), valueSet[i]);
			logger.log(Level.INFO, "Adding value : \"" + valueSet[i]
					+ "\" to combo viewer " + cv.toString());
		}

		FormData data = new FormData();
		data.left = new FormAttachment(0, 130);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment((Control) topObject, VSPACE);
		cv.getControl().setLayoutData(data);

		return cv;
	}

	CCombo createCCombo(Composite composite, CCombo cv, Object[] valueSet,
			Object topObject, TabbedPropertySheetWidgetFactory factory,
			int readOnly) {

		cv = new CCombo(composite, SWT.NONE | SWT.BORDER);
		cComboToolTip = new DefaultToolTip(cv);
		for (int i = 0; i < valueSet.length; i++) {
			if (valueSet[i] instanceof IDistributionFunction) {
				IDistributionFunction distFunc = (IDistributionFunction) valueSet[i];
				String distLabel = getDistributionFunctionLabelForComboViewer(distFunc);
				cv.add(distLabel);
				
				cv.setData(distLabel, distFunc);
				cComboToolTip.setData(distLabel, distFunc);
			} else {
				cv.add(valueSet[i].toString());
				cv.setData(valueSet[i].toString(), valueSet[i]);
			}
			logger.log(
					Level.INFO,
					"Adding value : \""
							+ getDistributionFunctionLabelForComboViewer((DistributionFunction) valueSet[i])
							+ "\" to combo viewer " + cv.toString());
		}
		cv.addSelectionListener(createCComboToolTipListener(cv));

		FormData data = new FormData();
		data.left = new FormAttachment(0, 130);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment((Control) topObject, VSPACE);
		cv.setLayoutData(data);

		return cv;
	}

	private SelectionListener createCComboToolTipListener(final CCombo cv) {
		return new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {

				Object data = cv.getData(cv.getItem(cv.getSelectionIndex()));
				if (data != null) {
					cComboToolTip.setText(data.toString());
					cComboToolTip.activate();
					cComboToolTip.show(new Point(20, 20));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		};
	}

	CLabel createCLabel(Composite composite,
			TabbedPropertySheetWidgetFactory factory, Control control,
			CLabel label, String labelText) {
		label = factory.createCLabel(composite, labelText);
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(control, -HSPACE);
		data.top = new FormAttachment(control, 0, SWT.CENTER);
		label.setLayoutData(data);
		return label;
	}

	String getDistributionFunctionLabelForComboViewer(
			IDistributionFunction distFunc) {

		StringBuffer b = new StringBuffer();

		if (distFunc instanceof IWeibull) {
			IWeibull w = (IWeibull) distFunc;
			b.append(w.getClass().getSimpleName());
			b.append("( ");
			b.append(w.getBeta());
			b.append(", ");
			b.append(w.getAlpha());
			b.append(" )");
		} else if (distFunc instanceof IBeta) {
			IBeta beta = (IBeta) distFunc;
			b.append(beta.getClass().getSimpleName());
			b.append("( ");
			b.append(beta.getBeta());
			b.append(", ");
			b.append(beta.getAlpha());
			b.append(" )");
		} else if (distFunc instanceof INormal) {
			INormal norm = (INormal) distFunc;
			b.append(norm.getClass().getSimpleName());
			b.append("( ");
			b.append(norm.getMean());
			b.append(", ");
			b.append(norm.getStdDev());
			b.append(" )");
		} else if (distFunc instanceof ITriangular) {
			ITriangular tria = (ITriangular) distFunc;
			b.append(tria.getClass().getSimpleName());
			b.append("( ");
			b.append(tria.getMin());
			b.append(", ");
			b.append(tria.getMode());
			b.append(", ");
			b.append(tria.getMax());
			b.append(" )");
		} else if (distFunc instanceof ILogNormal) {
			ILogNormal lnorm = (ILogNormal) distFunc;
			b.append(lnorm.getClass().getSimpleName());
			b.append("( ");
			b.append(lnorm.getLogMean());
			b.append(", ");
			b.append(lnorm.getLogStd());
			b.append(" )");
		} else if (distFunc instanceof IUniform) {
			IUniform unif = (IUniform) distFunc;
			b.append(unif.getClass().getSimpleName());
			b.append("( ");
			b.append(unif.getMin());
			b.append(", ");
			b.append(unif.getMax());
			b.append(" )");
		} else if (distFunc instanceof IPoisson) {
			IPoisson pois = (IPoisson) distFunc;
			b.append(pois.getClass().getSimpleName());
			b.append("( ");
			b.append(pois.getMean());
			b.append(" )");
		} else if (distFunc instanceof INegExp) {
			INegExp negE = (INegExp) distFunc;
			b.append(negE.getClass().getSimpleName());
			b.append("( ");
			b.append(negE.getMean());
			b.append(" )");
		} else if (distFunc instanceof IErlang) {
			IErlang erla = (IErlang) distFunc;
			b.append(erla.getClass().getSimpleName());
			b.append("( ");
			b.append(erla.getExpMean());
			b.append(", ");
			b.append(erla.getK());
			b.append(" )");
		} else if (distFunc instanceof IGamma) {
			IGamma gamm = (IGamma) distFunc;
			b.append(gamm.getClass().getSimpleName());
			b.append("( ");
			b.append(gamm.getBeta());
			b.append(", ");
			b.append(gamm.getAlpha());
			b.append(" )");
		}
		return b.toString();
	}

	IDistributionFunction getDistributionFunctionForLSEMElement(String text) {
		try {
			if (text == null)
				return null;
			String[] attributeArray = generateAttributeValueArray(text);

			// Switch between all distribution classes
			if (text.startsWith("Weibull") && attributeArray.length == 2) {
				IWeibull fct = SimulationFactory.eINSTANCE.createWeibull();
				// logger.log(Level.INFO,
				// "Generating weibull-distributionfunction... ");
				fct.setBeta(Double.parseDouble(attributeArray[0]));
				fct.setAlpha(Double.parseDouble(attributeArray[1]));
				// logger.log(Level.INFO, "Beta: " + fct.getBeta() + ", Alpha: "
				// + fct.getAlpha());
				return fct;
			} else if (text.startsWith("Beta") && attributeArray.length == 2) {
				IBeta fct = SimulationFactory.eINSTANCE.createBeta();
				// logger.log(Level.INFO,
				// "Generating beta-distributionfunction");
				fct.setBeta(Double.parseDouble(attributeArray[0]));
				fct.setAlpha(Double.parseDouble(attributeArray[1]));
				return fct;
			} else if (text.startsWith("Normal") && attributeArray.length == 2) {
				INormal fct = SimulationFactory.eINSTANCE.createNormal();
				// logger.log(Level.INFO,
				// "Generating normal-distributionfunction");
				fct.setMean(Double.parseDouble(attributeArray[0]));
				fct.setStdDev(Double.parseDouble(attributeArray[1]));
				return fct;
			} else if (text.startsWith("Triangular")
					&& attributeArray.length == 3) {
				ITriangular fct = SimulationFactory.eINSTANCE
						.createTriangular();
				// logger.log(Level.INFO,
				// "Generating triangular-distributionfunction");
				fct.setMin(Double.parseDouble(attributeArray[0]));
				fct.setMode(Double.parseDouble(attributeArray[1]));
				fct.setMax(Double.parseDouble(attributeArray[2]));
				return fct;
			} else if (text.startsWith("LogNormal")
					&& attributeArray.length == 2) {
				ILogNormal fct = SimulationFactory.eINSTANCE.createLogNormal();
				// logger.log(Level.INFO,
				// "Generating lognormal-distributionfunction");
				fct.setLogMean(Double.parseDouble(attributeArray[0]));
				fct.setLogStd(Double.parseDouble(attributeArray[1]));
				return fct;
			} else if (text.startsWith("Uniform") && attributeArray.length == 2) {
				IUniform fct = SimulationFactory.eINSTANCE.createUniform();
				// logger.log(Level.INFO,
				// "Generating uniform-distributionfunction");
				fct.setMin(Double.parseDouble(attributeArray[0]));
				fct.setMax(Double.parseDouble(attributeArray[1]));
				return fct;
			} else if (text.startsWith("Poisson") && attributeArray.length == 1) {
				IPoisson fct = SimulationFactory.eINSTANCE.createPoisson();
				// logger.log(Level.INFO,
				// "Generating poisson-distributionfunction");
				fct.setMean(Double.parseDouble(attributeArray[0]));
				return fct;
			} else if (text.startsWith("NegExp") && attributeArray.length == 1) {
				INegExp fct = SimulationFactory.eINSTANCE.createNegExp();
				// logger.log(Level.INFO,
				// "Generating negExpression-distributionfunction");
				fct.setMean(Double.parseDouble(attributeArray[0]));
				return fct;
			} else if (text.startsWith("Erlang") && attributeArray.length == 2) {
				IErlang fct = SimulationFactory.eINSTANCE.createErlang();
				// logger.log(Level.INFO,
				// "Generating normal-distributionfunction");
				fct.setExpMean(Double.parseDouble(attributeArray[0]));
				fct.setK(Double.parseDouble(attributeArray[1]));
				return fct;
			} else if (text.startsWith("Gamma") && attributeArray.length == 2) {
				IGamma fct = SimulationFactory.eINSTANCE.createGamma();
				// logger.log(Level.INFO,
				// "Generating normal-distributionfunction");
				fct.setBeta(Double.parseDouble(attributeArray[0]));
				fct.setAlpha(Double.parseDouble(attributeArray[1]));
				return fct;
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generates a cleared attribute-array only containing values, so that:
	 * 
	 * <function-name> ([<attribute-name1>" "<attribute-value1>,
	 * <attribute-name2>" "<attribute-value2>, <...> ]) becomes
	 * [<attribute-value1>, <attribute-value2>, ... , <attribute-valueN>]
	 * 
	 * @param key
	 *            The displayed function in form <function-name>
	 *            ([<attribute-name1>" "<attribute-value1>,
	 *            <attribute-name2>" "<attribute-value2>, <...> ])
	 * @return string array in form [<attribute-value1>, <attribute-value2>, ...
	 *         , <attribute-valueN>]
	 */
	private String[] generateAttributeValueArray(String key) throws Exception {

		// Take only attributes in brackets
		String attributes = key.split("\\(")[1];
		// Clear last bracket
		attributes = attributes.replace(")", "");
		// Get attribute-value-pairs
		String[] attributeArray = attributes.split(", ");

		// for(int i = 0; i < attributeWithNameArray.length; i++){
		// // Next split because only value is needed of form: <attribute-name>
		// + " " + <value>
		// attributeWithNameArray[i] = attributeWithNameArray[i].split(" ")[1];
		// logger.log(Level.INFO, attributeWithNameArray[i]);
		// }

		return attributeArray;
	}

	ISource switchSourceDistributionItem(ISource source, IDistribution item) {
		// Remove from diagram
		boolean asd = getDiagram().eResource().getContents()
				.remove(source.getFirstEntity().getPeriod());
		logger.log(Level.INFO, "Former item removed? " + asd);
		// Add new to diagram
		getDiagram().eResource().getContents().add(item);
		// Change deviationType to IUniform and add it
		source.getFirstEntity().setPeriod(item);

		return source;
	}

	Text createText(Composite composite,
			TabbedPropertySheetWidgetFactory factory,
			Object controlElementAbove, Text text) {

		text = factory.createText(composite, "");
		FormData data = new FormData();
		data.left = new FormAttachment(0, 130);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment((Control) controlElementAbove, VSPACE);
		text.setLayoutData(data);

		return text;

	}
}
