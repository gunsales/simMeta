package de.lsem.simulation.features;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;

import de.lsem.repository.model.simulation.IActivity;
import de.lsem.simulation.features.custom.CollapseActivityFeature;

public class SimulationToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public SimulationToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}


	@Override
	public IContextButtonPadData getContextButtonPad(
			IPictogramElementContext context) {

		IContextButtonPadData contextButtonPad = super.getContextButtonPad(context);
		PictogramElement pictogramElement = context.getPictogramElement();

		Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pictogramElement);

		setGenericContextButtons(contextButtonPad, pictogramElement, 
				CONTEXT_BUTTON_DELETE | CONTEXT_BUTTON_UPDATE);

		
		CustomContext cc = new CustomContext(new PictogramElement[]{pictogramElement});

		ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);

		for(int i = 0 ; i < cf.length ; i++) {
			ICustomFeature iCustomFeature = cf[i];

			if(iCustomFeature instanceof CollapseActivityFeature 
					&& iCustomFeature.canExecute(cc)) {
				String image = IPlatformImageConstants.IMG_EDIT_EXPAND;
				String collapseExpand = "Collapse";
				if(Boolean.parseBoolean(Graphiti.getPeService().getPropertyValue(pictogramElement, "isCollapsed"))){
					image = IPlatformImageConstants.IMG_EDIT_COLLAPSE;
					collapseExpand = "Expand";
				}
				String name = "";

				if(bo instanceof IActivity){
					IActivity bo2= (IActivity)bo;

					if(bo2!=null && bo2.getName()!=null){
						name = bo2.getName();
					}
				}

				IContextButtonEntry collapseButton  = new ContextButtonEntry(iCustomFeature, cc); 
				collapseButton.setDescription(collapseExpand+" "+name);
				collapseButton.setText(collapseExpand);
				collapseButton.setIconId(image);

				contextButtonPad.setCollapseContextButton(collapseButton);
				break;
			}

		}
		return contextButtonPad;
	}

}
