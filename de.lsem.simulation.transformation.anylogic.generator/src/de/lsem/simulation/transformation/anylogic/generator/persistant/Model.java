/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator.persistant;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;



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
	@XmlElement(name="Id")
    private long _id;

    /**
     * keeps track of state for field: _id
     */
    private boolean _has_id;

    /**
     * Field _name.
     */
	@XmlElement(name="Name")
    private java.lang.String _name;

    /**
     * Field _engineVersion.
     */
	@XmlElement(name="EngineVersion")
    private int _engineVersion;

    /**
     * keeps track of state for field: _engineVersion
     */
    private boolean _has_engineVersion;

    /**
     * Field _javaPackageName.
     */
	@XmlElement(name="JavaPackageName")
    private java.lang.String _javaPackageName;

    /**
     * Field _modelTimeUnit.
     */
	@XmlElement(name="ModelTimeUnit")
    private java.lang.String _modelTimeUnit;

    /**
     * Field _activeObjectClasses.
     */
    @XmlElement(name="ActiveObjectClasses")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClasses _activeObjectClasses;

    /**
     * Field _experiments.
     */
    @XmlElement(name="Experiments")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.Experiments _experiments;

    /**
     * Field _requiredLibraryReference.
     */
    @XmlElement(name="RequiredLibraryReference")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.RequiredLibraryReference _requiredLibraryReference;


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
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClasses getActiveObjectClasses(
    ) {
        return this._activeObjectClasses;
    }

    /**
     * Returns the value of field 'engineVersion'.
     * 
     * @return the value of field 'EngineVersion'.
     */
    @XmlTransient
    public int getEngineVersion(
    ) {
        return this._engineVersion;
    }

    /**
     * Returns the value of field 'experiments'.
     * 
     * @return the value of field 'Experiments'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Experiments getExperiments(
    ) {
        return this._experiments;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    @XmlTransient
    public long getId(
    ) {
        return this._id;
    }

    /**
     * Returns the value of field 'javaPackageName'.
     * 
     * @return the value of field 'JavaPackageName'.
     */
    @XmlTransient
    public java.lang.String getJavaPackageName(
    ) {
        return this._javaPackageName;
    }

    /**
     * Returns the value of field 'modelTimeUnit'.
     * 
     * @return the value of field 'ModelTimeUnit'.
     */
    @XmlTransient
    public java.lang.String getModelTimeUnit(
    ) {
        return this._modelTimeUnit;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    @XmlTransient
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'requiredLibraryReference'.
     * 
     * @return the value of field 'RequiredLibraryReference'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.RequiredLibraryReference getRequiredLibraryReference(
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
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'activeObjectClasses'.
     * 
     * @param activeObjectClasses the value of field
     * 'activeObjectClasses'.
     */
    public void setActiveObjectClasses(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClasses activeObjectClasses) {
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Experiments experiments) {
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.RequiredLibraryReference requiredLibraryReference) {
        this._requiredLibraryReference = requiredLibraryReference;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * de.lsem.simulation.transformation.anylogic.generator.Model
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.Model unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.Model) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.Model.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
