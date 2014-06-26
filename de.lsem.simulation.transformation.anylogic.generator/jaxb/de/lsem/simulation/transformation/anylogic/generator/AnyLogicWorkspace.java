/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * AnyLogic Project File
 *  
 *  
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AnyLogicWorkspace implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _workspaceVersion.
     */
    private float _workspaceVersion;

    /**
     * keeps track of state for field: _workspaceVersion
     */
    private boolean _has_workspaceVersion;

    /**
     * Field _anyLogicVersion.
     */
    private java.lang.String _anyLogicVersion;

    /**
     * Field _alpVersion.
     */
    private java.lang.String _alpVersion;

    /**
     * Field _model.
     */
    private de.lsem.simulation.transformation.anylogic.generator.Model _model;


      //----------------/
     //- Constructors -/
    //----------------/

    public AnyLogicWorkspace() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteWorkspaceVersion(
    ) {
        this._has_workspaceVersion= false;
    }

    /**
     * Returns the value of field 'alpVersion'.
     * 
     * @return the value of field 'AlpVersion'.
     */
    public java.lang.String getAlpVersion(
    ) {
        return this._alpVersion;
    }

    /**
     * Returns the value of field 'anyLogicVersion'.
     * 
     * @return the value of field 'AnyLogicVersion'.
     */
    public java.lang.String getAnyLogicVersion(
    ) {
        return this._anyLogicVersion;
    }

    /**
     * Returns the value of field 'model'.
     * 
     * @return the value of field 'Model'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.Model getModel(
    ) {
        return this._model;
    }

    /**
     * Returns the value of field 'workspaceVersion'.
     * 
     * @return the value of field 'WorkspaceVersion'.
     */
    public float getWorkspaceVersion(
    ) {
        return this._workspaceVersion;
    }

    /**
     * Method hasWorkspaceVersion.
     * 
     * @return true if at least one WorkspaceVersion has been added
     */
    public boolean hasWorkspaceVersion(
    ) {
        return this._has_workspaceVersion;
    }

    /**
     * Sets the value of field 'alpVersion'.
     * 
     * @param alpVersion the value of field 'alpVersion'.
     */
    public void setAlpVersion(
            final java.lang.String alpVersion) {
        this._alpVersion = alpVersion;
    }

    /**
     * Sets the value of field 'anyLogicVersion'.
     * 
     * @param anyLogicVersion the value of field 'anyLogicVersion'.
     */
    public void setAnyLogicVersion(
            final java.lang.String anyLogicVersion) {
        this._anyLogicVersion = anyLogicVersion;
    }

    /**
     * Sets the value of field 'model'.
     * 
     * @param model the value of field 'model'.
     */
    public void setModel(
            final de.lsem.simulation.transformation.anylogic.generator.Model model) {
        this._model = model;
    }

    /**
     * Sets the value of field 'workspaceVersion'.
     * 
     * @param workspaceVersion the value of field 'workspaceVersion'
     */
    public void setWorkspaceVersion(
            final float workspaceVersion) {
        this._workspaceVersion = workspaceVersion;
        this._has_workspaceVersion = true;
    }

}
