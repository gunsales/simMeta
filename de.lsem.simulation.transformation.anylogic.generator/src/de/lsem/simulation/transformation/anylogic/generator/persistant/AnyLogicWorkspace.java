/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator.persistant;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.exolab.castor.xml.ValidationException;

/**
 * AnyLogic Project File
 *  
 *  
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
@XmlRootElement(name="AnyLogicWorkspace")
public class AnyLogicWorkspace implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _workspaceVersion.
     */
	@XmlAttribute(name="WorkspaceVersion")
    private float _workspaceVersion;

    /**
     * keeps track of state for field: _workspaceVersion
     */
    private boolean _has_workspaceVersion;

    /**
     * Field _anyLogicVersion.
     */
	@XmlAttribute(name="AnyLogicVersion")
    private java.lang.String _anyLogicVersion;

    /**
     * Field _alpVersion.
     */
	@XmlAttribute(name="AlpVersion")
    private java.lang.String _alpVersion;

    /**
     * Field _model.
     */
	@XmlElement(name="Model")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.Model _model;


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
    @XmlTransient
    public java.lang.String getAlpVersion(
    ) {
        return this._alpVersion;
    }

    /**
     * Returns the value of field 'anyLogicVersion'.
     * 
     * @return the value of field 'AnyLogicVersion'.
     */
    @XmlTransient
    public java.lang.String getAnyLogicVersion(
    ) {
        return this._anyLogicVersion;
    }

    /**
     * Returns the value of field 'model'.
     * 
     * @return the value of field 'Model'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Model getModel(
    ) {
        return this._model;
    }

    /**
     * Returns the value of field 'workspaceVersion'.
     * 
     * @return the value of field 'WorkspaceVersion'.
     */
    @XmlTransient
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
    throws org.exolab.castor.xml.MarshalException, ValidationException{
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Model model) {
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

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * de.lsem.simulation.transformation.anylogic.generator.AnyLogicWorkspace
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.AnyLogicWorkspace unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.AnyLogicWorkspace) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.AnyLogicWorkspace.class, reader);
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
