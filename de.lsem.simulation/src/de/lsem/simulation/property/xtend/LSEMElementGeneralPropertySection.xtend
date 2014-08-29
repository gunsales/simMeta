package de.lsem.simulation.property.xtend

import de.lsem.repository.model.simulation.IDistribution
import de.lsem.repository.model.simulation.IDistributionFunction
import de.lsem.repository.model.simulation.ISource
import de.lsem.simulation.util.ElementConstants
import java.util.logging.Level
import java.util.logging.Logger
import org.eclipse.graphiti.ui.platform.GFPropertySection
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.ComboViewer
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.jface.window.DefaultToolTip
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.CCombo
import org.eclipse.swt.layout.FormAttachment
import org.eclipse.swt.layout.FormData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage

class LSEMElementGeneralPropertySection extends GFPropertySection implements ITabbedPropertyConstants, ElementConstants {

	val Logger logger = Logger.getLogger(typeof(LSEMElementGeneralPropertySection).simpleName)

	var DistributionFunctionLabelGenerator labelGenerator

	var Composite composite
	var DefaultToolTip cComboToolTip
	
	override createControls(Composite it, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(it, aTabbedPropertySheetPage)
		composite = widgetFactory.createFlatFormComposite(it)
	}
	
	def getComposite(){
		composite
	}
	
	def getLabelGenerator(){
		labelGenerator = labelGenerator ?: new DistributionFunctionLabelGenerator
	}

	def create cv:new ComboViewer(composite, readOnly) createComboViewer(Composite composite, Object[] valueSet,
		Control aboveObject, int readOnly) {
		cv.contentProvider = ArrayContentProvider.instance
		val labelProvider = new LabelProvider
		labelProvider.getText() [ Object element |
			if (element instanceof IDistributionFunction) {
				getLabelGenerator.getDistributionFunctionFor(element as IDistributionFunction)
			} else if (element instanceof String) {
				element.toString
			}
			labelProvider.getText(element)
		]
		
		// TODO Test nullpointer
		cv.labelProvider = labelProvider
		cv.input = valueSet
		for (i : valueSet) {
			logger.log(Level.INFO, "Adding value: \"" + i + "\" to combo-viewer" + cv.toString)
		}

		var data = new FormData
		data.left = new FormAttachment(0, 130)
		data.right = new FormAttachment(100, 0)
		data.top = new FormAttachment(aboveObject, VSPACE)
		
		cv.control.layoutData = data
	}

	def create cc:new CCombo(composite, SWT.BORDER)	createCCombo(Composite composite, Object[] valueSet,
		Control topObject, int readOnly) {
		cComboToolTip = new DefaultToolTip(cc)

		for (v : valueSet) {
			if (v instanceof IDistributionFunction) {
				val d = v as IDistributionFunction
				val label = getLabelGenerator.getDistributionFunctionFor(d)
				cc.add(label.toString)
				cc.setData(label.toString, d)
			} else {
				cc.add(v.toString)
				cc.setData(v.toString, v)
			}
			logger.log(Level.INFO, "Adding value : \"" + v + "\" to combo viewer " + cc.toString)
		}

		val listener = new CComboToolTipSelectionListener(cComboToolTip)
		cc.addSelectionListener(listener)

		var data = new FormData
		data.left = new FormAttachment(0, 130)
		data.right = new FormAttachment(100, 0)
		data.top = new FormAttachment(topObject, VSPACE)
		cc.layoutData = data
	}


	def create label: widgetFactory.createCLabel(composite, labelText) createCLabel(Control control, String labelText) {
		var data = new FormData
		data.left = new FormAttachment(0, 0)
		data.right = new FormAttachment(control, -HSPACE)
		data.top = new FormAttachment(control, 0, SWT.CENTER)
		label.layoutData = data
	}

	def switchSourceDistributionItem(ISource source, IDistribution item) {
		val asd = diagram.eResource.contents.remove(source.firstEntity.period)
		logger.log(Level.INFO, "Former item removed?" + asd)

		diagram.eResource.contents.add(item)
		source.firstEntity.period = item

		source
	}

	def create text:widgetFactory.createText(composite, "") createText(Control controlElementAbove) {
		val data = new FormData
		data.left = new FormAttachment(0, 130)
		data.right = new FormAttachment(100, 0)
		data.top = new FormAttachment(controlElementAbove, VSPACE)
		text.layoutData = data
		
		logger.log(Level.INFO, "Text below control: " + controlElementAbove.class.simpleName + " created.")
	}

}
