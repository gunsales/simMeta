package de.lsem.simulation.property

import de.lsem.repository.model.simulation.IDistributionFunction
import de.lsem.simulation.property.labelProvider.CComboLabelProvider
import de.lsem.simulation.property.modifyListener.CComboToolTipSelectionListener
import de.lsem.simulation.util.DistributionFunctionLabelGenerator
import de.lsem.simulation.util.ElementConstants
import org.eclipse.graphiti.ui.platform.GFPropertySection
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.ComboViewer
import org.eclipse.jface.window.DefaultToolTip
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.CCombo
import org.eclipse.swt.layout.FormAttachment
import org.eclipse.swt.layout.FormData
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory

class LSEMElementGeneralPropertySection extends GFPropertySection implements ITabbedPropertyConstants, ElementConstants {

	var DistributionFunctionLabelGenerator labelGenerator

	var TabbedPropertySheetWidgetFactory factory
	var Composite composite
	var DefaultToolTip cComboToolTip

	override createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage)

		factory = widgetFactory
		composite = factory.createFlatFormComposite(parent)
	}

	def getLabelGenerator() {
		labelGenerator = labelGenerator ?: new DistributionFunctionLabelGenerator
	}

	def createComboViewer(Object[] valueSet, Object topObject, int readOnly) {
		val cv = new ComboViewer(composite, readOnly) => [
			setContentProvider(ArrayContentProvider.instance)
			setLabelProvider(new CComboLabelProvider)
			setInput(valueSet)
		]

		cv.control.layoutData = getElementFormData(topObject as Control)

		cv
	}

	def createComboViewer(Object[] valueSet, int readOnly) {
		createComboViewer(valueSet, composite, readOnly)
	}

	def createCCombo(Object[] valueSet, Object topObject, int readOnly) {

		val cv = new CCombo(composite, SWT.NONE.bitwiseOr(SWT.BORDER)) => [
			setLayoutData(getElementFormData(topObject as Control))
		]
		cComboToolTip = new DefaultToolTip(cv)
		setComboAndToolTipTexts(valueSet, cv)
		val selListener = new CComboToolTipSelectionListener(cComboToolTip)
		cv.addSelectionListener(selListener)

		cv
	}

	def createCCombo(Object[] valueSet, int readOnly) {
		createCCombo(valueSet, composite, readOnly)
	}

	private def void setComboAndToolTipTexts(Object[] valueSet, CCombo cv) {
		valueSet.forEach [
			if (it instanceof IDistributionFunction) {
				val distFunc = it as IDistributionFunction
				val distLabel = getLabelGenerator.getDistributionFunctionFor(distFunc)
				cv.add(distLabel)
				cv.setData(distLabel, distFunc)
				cComboToolTip.setData(distLabel, distFunc)
			} else {
				cv.add(it.toString)
				cv.setData(it.toString, it)
			}
		]
	}

	def createCLabel(Control control, String labelText) {
		val label = factory.createCLabel(composite, labelText)
		label.layoutData = getLabelFormData(control)
		label
	}

	def createText(Object controlElementAbove, String textValue, int swtValues) {
		val text = factory.createText(composite, textValue, swtValues)
		text.layoutData = getElementFormData(controlElementAbove as Control)
		text
	}
	
	
	def createText(Object controlElementAbove, String textValue) {
		createText(controlElementAbove, textValue, SWT.NONE)
	}

	def createText(String textValue) {
		createText(composite, textValue)
	}
	
	def createText(String textValue, int swtValues) {
		createText(composite, textValue, swtValues)
	}

	def getLabelFormData(Control control) {
		new FormData => [
			left = new FormAttachment(0, 0)
			right = new FormAttachment(control, -HSPACE)
			top = new FormAttachment(control, 0, SWT.CENTER)
		]
	}

	def getElementFormData(Control topObject) {
		new FormData => [
			left = new FormAttachment(0, 130)
			right = new FormAttachment(100, 0)
			top = new FormAttachment(topObject, VSPACE)
		]
	}

	def getElementFormData() {
		new FormData => [
			left = new FormAttachment(0, 130)
			right = new FormAttachment(100, 0)
			top = new FormAttachment(composite, VSPACE)
		]
	}

}
