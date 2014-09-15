package de.lsem.simulation.property.labelProvider;

import org.eclipse.jface.viewers.LabelProvider;

import de.lsem.repository.model.simulation.IDistributionFunction;
import de.lsem.simulation.util.DistributionFunctionLabelGenerator;

public class CComboLabelProvider extends LabelProvider{

	DistributionFunctionLabelGenerator labelGenerator = new DistributionFunctionLabelGenerator();
	
	@Override
	public String getText(Object element) {
		if (element instanceof IDistributionFunction){
			return labelGenerator.getDistributionFunctionFor((IDistributionFunction) element);
		}
		else if (element instanceof String){
			return element.toString();
		}
		return super.getText(element);
	}
}
