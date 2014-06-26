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
 * Class ExtendedProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ExtendedProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _font.
     */
	@XmlElement(name="Font")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.FontExtendedProperties _font;

    /**
     * Field _labelText.
     */
	@XmlElement(name="LabelText")
    private java.lang.String _labelText;

    /**
     * Field _labelCode.
     */
	@XmlElement(name="LabelCode")
    private java.lang.String _labelCode;


      //----------------/
     //- Constructors -/
    //----------------/

    public ExtendedProperties() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'font'.
     * 
     * @return the value of field 'Font'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.FontExtendedProperties getFont(
    ) {
        return this._font;
    }

    /**
     * Returns the value of field 'labelCode'.
     * 
     * @return the value of field 'LabelCode'.
     */
    @XmlTransient
    public java.lang.String getLabelCode(
    ) {
        return this._labelCode;
    }

    /**
     * Returns the value of field 'labelText'.
     * 
     * @return the value of field 'LabelText'.
     */
    @XmlTransient
    public java.lang.String getLabelText(
    ) {
        return this._labelText;
    }

    /**
     * Sets the value of field 'font'.
     * 
     * @param font the value of field 'font'.
     */
    public void setFont(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.FontExtendedProperties font) {
        this._font = font;
    }

    /**
     * Sets the value of field 'labelCode'.
     * 
     * @param labelCode the value of field 'labelCode'.
     */
    public void setLabelCode(
            final java.lang.String labelCode) {
        this._labelCode = labelCode;
    }

    /**
     * Sets the value of field 'labelText'.
     * 
     * @param labelText the value of field 'labelText'.
     */
    public void setLabelText(
            final java.lang.String labelText) {
        this._labelText = labelText;
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
     * de.lsem.simulation.transformation.anylogic.generator.ExtendedProperties
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.ExtendedProperties unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.ExtendedProperties) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.ExtendedProperties.class, reader);
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
