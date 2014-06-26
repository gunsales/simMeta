package de.lsem.simulation.transformation.anylogic.transform.xtend.helper;

import com.google.common.base.Objects;
import de.lsem.repository.model.simulation.ISimulationElement;
import de.lsem.simulation.transformation.anylogic.transform.xtend.helper.Variables;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class PositionHelper {
  @Inject
  @Extension
  private Variables _variables;
  
  private static int POSITION_DIVISOR = 1;
  
  public int recalcPosition(final int i) {
    int _divide = (i / PositionHelper.POSITION_DIVISOR);
    return _divide;
  }
  
  public int getX(final ISimulationElement e) {
    int _xblockexpression = (int) 0;
    {
      final GraphicsAlgorithm a = this.getGraphicsAlgorithm(e);
      boolean _notEquals = (!Objects.equal(a, null));
      if (_notEquals) {
        int _x = a.getX();
        return this.recalcPosition(_x);
      }
      _xblockexpression = (0);
    }
    return _xblockexpression;
  }
  
  public int getY(final ISimulationElement e) {
    int _xblockexpression = (int) 0;
    {
      final GraphicsAlgorithm b = this.getGraphicsAlgorithm(e);
      boolean _notEquals = (!Objects.equal(b, null));
      if (_notEquals) {
        int _y = b.getY();
        return this.recalcPosition(_y);
      }
      _xblockexpression = (0);
    }
    return _xblockexpression;
  }
  
  public GraphicsAlgorithm getGraphicsAlgorithm(final ISimulationElement e) {
    Diagram _diagram = this._variables.getDiagram();
    EList<PictogramLink> _pictogramLinks = _diagram.getPictogramLinks();
    for (final PictogramLink picto : _pictogramLinks) {
      EList<EObject> _businessObjects = picto.getBusinessObjects();
      boolean _contains = _businessObjects.contains(e);
      if (_contains) {
        PictogramElement _pictogramElement = picto.getPictogramElement();
        return _pictogramElement.getGraphicsAlgorithm();
      }
    }
    return null;
  }
}
