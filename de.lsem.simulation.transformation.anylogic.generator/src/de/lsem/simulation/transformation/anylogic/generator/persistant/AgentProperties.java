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
 * Class AgentProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AgentProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _spaceType.
     */
	@XmlElement(name="SpaceType")
    private java.lang.String _spaceType;

    /**
     * Field _environmentDefinesInitialLocation.
     */
	@XmlElement(name="EnvironmentDefinesInitialLocation")
    private java.lang.String _environmentDefinesInitialLocation;


      //----------------/
     //- Constructors -/
    //----------------/

    public AgentProperties() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'environmentDefinesInitialLocation'.
     * 
     * @return the value of field
     * 'EnvironmentDefinesInitialLocation'.
     */
    @XmlTransient
    public java.lang.String getEnvironmentDefinesInitialLocation(
    ) {
        return this._environmentDefinesInitialLocation;
    }

    /**
     * Returns the value of field 'spaceType'.
     * 
     * @return the value of field 'SpaceType'.
     */
    @XmlTransient
    public java.lang.String getSpaceType(
    ) {
        return this._spaceType;
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
     * Sets the value of field 'environmentDefinesInitialLocation'.
     * 
     * @param environmentDefinesInitialLocation the value of field
     * 'environmentDefinesInitialLocation'.
     */
    public void setEnvironmentDefinesInitialLocation(
            final java.lang.String environmentDefinesInitialLocation) {
        this._environmentDefinesInitialLocation = environmentDefinesInitialLocation;
    }

    /**
     * Sets the value of field 'spaceType'.
     * 
     * @param spaceType the value of field 'spaceType'.
     */
    public void setSpaceType(
            final java.lang.String spaceType) {
        this._spaceType = spaceType;
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
     * de.lsem.simulation.transformation.anylogic.generator.AgentProperties
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.AgentProperties unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.AgentProperties) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.AgentProperties.class, reader);
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
