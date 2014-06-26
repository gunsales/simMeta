/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class Model.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Model implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private long _id;

    /**
     * keeps track of state for field: _id
     */
    private boolean _has_id;

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _engineVersion.
     */
    private int _engineVersion;

    /**
     * keeps track of state for field: _engineVersion
     */
    private boolean _has_engineVersion;

    /**
     * Field _javaPackageName.
     */
    private java.lang.String _javaPackageName;

    /**
     * Field _modelTimeUnit.
     */
    private java.lang.String _modelTimeUnit;

    /**
     * Field _activeObjectClasses.
     */
    private de.lsem.simulation.transformation.anylogic.generator.ActiveObjectClasses _activeObjectClasses;

    /**
     * Field _experiments.
     */
    private de.lsem.simulation.transformation.anylogic.generator.Experiments _experiments;

    /**
     * Field _requiredLibraryReference.
     */
    private de.lsem.simulation.transformation.anylogic.generator.RequiredLibraryReference _requiredLibraryReference;


      //----------------/
     //- Constructors -/
    //----------------/

    public Model() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteEngineVersion(
    ) {
        this._has_engineVersion= false;
    }

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     * Returns the value of field 'activeObjectClasses'.
     * 
     * @return the value of field 'ActiveObjectClasses'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.ActiveObjectClasses getActiveObjectClasses(
    ) {
        return this._activeObjectClasses;
    }

    /**
     * Returns the value of field 'engineVersion'.
     * 
     * @return the value of field 'EngineVersion'.
     */
    public int getEngineVersion(
    ) {
        return this._engineVersion;
    }

    /**
     * Returns the value of field 'experiments'.
     * 
     * @return the value of field 'Experiments'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.Experiments getExperiments(
    ) {
        return this._experiments;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public long getId(
    ) {
        return this._id;
    }

    /**
     * Returns the value of field 'javaPackageName'.
     * 
     * @return the value of field 'JavaPackageName'.
     */
    public java.lang.String getJavaPackageName(
    ) {
        return this._javaPackageName;
    }

    /**
     * Returns the value of field 'modelTimeUnit'.
     * 
     * @return the value of field 'ModelTimeUnit'.
     */
    public java.lang.String getModelTimeUnit(
    ) {
        return this._modelTimeUnit;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'requiredLibraryReference'.
     * 
     * @return the value of field 'RequiredLibraryReference'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.RequiredLibraryReference getRequiredLibraryReference(
    ) {
        return this._requiredLibraryReference;
    }

    /**
     * Method hasEngineVersion.
     * 
     * @return true if at least one EngineVersion has been added
     */
    public boolean hasEngineVersion(
    ) {
        return this._has_engineVersion;
    }

    /**
     * Method hasId.
     * 
     * @return true if at least one Id has been added
     */
    public boolean hasId(
    ) {
        return this._has_id;
    }

    /**
     * Sets the value of field 'activeObjectClasses'.
     * 
     * @param activeObjectClasses the value of field
     * 'activeObjectClasses'.
     */
    public void setActiveObjectClasses(
            final de.lsem.simulation.transformation.anylogic.generator.ActiveObjectClasses activeObjectClasses) {
        this._activeObjectClasses = activeObjectClasses;
    }

    /**
     * Sets the value of field 'engineVersion'.
     * 
     * @param engineVersion the value of field 'engineVersion'.
     */
    public void setEngineVersion(
            final int engineVersion) {
        this._engineVersion = engineVersion;
        this._has_engineVersion = true;
    }

    /**
     * Sets the value of field 'experiments'.
     * 
     * @param experiments the value of field 'experiments'.
     */
    public void setExperiments(
            final de.lsem.simulation.transformation.anylogic.generator.Experiments experiments) {
        this._experiments = experiments;
    }

    /**
     * Sets the value of field 'id'.
     * 
     * @param id the value of field 'id'.
     */
    public void setId(
            final long id) {
        this._id = id;
        this._has_id = true;
    }

    /**
     * Sets the value of field 'javaPackageName'.
     * 
     * @param javaPackageName the value of field 'javaPackageName'.
     */
    public void setJavaPackageName(
            final java.lang.String javaPackageName) {
        this._javaPackageName = javaPackageName;
    }

    /**
     * Sets the value of field 'modelTimeUnit'.
     * 
     * @param modelTimeUnit the value of field 'modelTimeUnit'.
     */
    public void setModelTimeUnit(
            final java.lang.String modelTimeUnit) {
        this._modelTimeUnit = modelTimeUnit;
    }

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'requiredLibraryReference'.
     * 
     * @param requiredLibraryReference the value of field
     * 'requiredLibraryReference'.
     */
    public void setRequiredLibraryReference(
            final de.lsem.simulation.transformation.anylogic.generator.RequiredLibraryReference requiredLibraryReference) {
        this._requiredLibraryReference = requiredLibraryReference;
    }

}
