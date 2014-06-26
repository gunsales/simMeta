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
 * Class DatasetsCreationProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class DatasetsCreationProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _autoCreate.
     */
	@XmlElement(name="AutoCreate")
    private java.lang.String _autoCreate;

    /**
     * Field _recurrenceCode.
     */
	@XmlElement(name="RecurrenceCode")
    private java.lang.String _recurrenceCode;


      //----------------/
     //- Constructors -/
    //----------------/

    public DatasetsCreationProperties() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'autoCreate'.
     * 
     * @return the value of field 'AutoCreate'.
     */
    @XmlTransient
    public java.lang.String getAutoCreate(
    ) {
        return this._autoCreate;
    }

    /**
     * Returns the value of field 'recurrenceCode'.
     * 
     * @return the value of field 'RecurrenceCode'.
     */
    @XmlTransient
    public java.lang.String getRecurrenceCode(
    ) {
        return this._recurrenceCode;
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
     * Sets the value of field 'autoCreate'.
     * 
     * @param autoCreate the value of field 'autoCreate'.
     */
    public void setAutoCreate(
            final java.lang.String autoCreate) {
        this._autoCreate = autoCreate;
    }

    /**
     * Sets the value of field 'recurrenceCode'.
     * 
     * @param recurrenceCode the value of field 'recurrenceCode'.
     */
    public void setRecurrenceCode(
            final java.lang.String recurrenceCode) {
        this._recurrenceCode = recurrenceCode;
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
     * de.lsem.simulation.transformation.anylogic.generator.DatasetsCreationProperties
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.DatasetsCreationProperties unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.DatasetsCreationProperties) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.DatasetsCreationProperties.class, reader);
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
