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
 * Class ActiveObjectClasses.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ActiveObjectClasses implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ========= Active Object Class ========
     *  
     */
	@XmlElement(name="ActiveObjectClass")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClass _activeObjectClass;


      //----------------/
     //- Constructors -/
    //----------------/

    public ActiveObjectClasses() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'activeObjectClass'. The field
     * 'activeObjectClass' has the following description: =========
     * Active Object Class ========
     *  
     * 
     * @return the value of field 'ActiveObjectClass'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClass getActiveObjectClass(
    ) {
        return this._activeObjectClass;
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
     * Sets the value of field 'activeObjectClass'. The field
     * 'activeObjectClass' has the following description: =========
     * Active Object Class ========
     *  
     * 
     * @param activeObjectClass the value of field
     * 'activeObjectClass'.
     */
    public void setActiveObjectClass(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClass activeObjectClass) {
        this._activeObjectClass = activeObjectClass;
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
     * de.lsem.simulation.transformation.anylogic.generator.ActiveObjectClasses
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClasses unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClasses) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClasses.class, reader);
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
