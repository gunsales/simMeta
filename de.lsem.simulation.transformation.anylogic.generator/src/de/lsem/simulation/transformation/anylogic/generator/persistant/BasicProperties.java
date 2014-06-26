/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator.persistant;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class BasicProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class BasicProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _width.
     */
	@XmlAttribute(name="Width")
    private int _width;

    /**
     * keeps track of state for field: _width
     */
    private boolean _has_width;

    /**
     * Field _height.
     */
    @XmlAttribute(name="Height")
    private int _height;

    /**
     * keeps track of state for field: _height
     */
    private boolean _has_height;

    /**
     * Field _asObject.
     */
    @XmlAttribute(name="AsObject")
    private java.lang.String _asObject;

    /**
     * Field _embeddedIcon.
     */
    @XmlElement(name="EmbeddedIcon")
    private java.lang.String _embeddedIcon;

    /**
     * Field _fillColor.
     */
    @XmlElement(name="FillColor")
    private java.lang.String _fillColor;

    /**
     * Field _textColor.
     */
    @XmlElement(name="TextColor")
    private java.lang.String _textColor;

    /**
     * Field _actionCode.
     */
    @XmlElement(name="ActionCode")
    private java.lang.String _actionCode;


      //----------------/
     //- Constructors -/
    //----------------/

    public BasicProperties() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteHeight(
    ) {
        this._has_height= false;
    }

    /**
     */
    public void deleteWidth(
    ) {
        this._has_width= false;
    }

    /**
     * Returns the value of field 'actionCode'.
     * 
     * @return the value of field 'ActionCode'.
     */
    @XmlTransient
    public java.lang.String getActionCode(
    ) {
        return this._actionCode;
    }

    /**
     * Returns the value of field 'asObject'.
     * 
     * @return the value of field 'AsObject'.
     */
    @XmlTransient
    public java.lang.String getAsObject(
    ) {
        return this._asObject;
    }

    /**
     * Returns the value of field 'embeddedIcon'.
     * 
     * @return the value of field 'EmbeddedIcon'.
     */
    @XmlTransient
    public java.lang.String getEmbeddedIcon(
    ) {
        return this._embeddedIcon;
    }

    /**
     * Returns the value of field 'fillColor'.
     * 
     * @return the value of field 'FillColor'.
     */
    @XmlTransient
    public java.lang.String getFillColor(
    ) {
        return this._fillColor;
    }

    /**
     * Returns the value of field 'height'.
     * 
     * @return the value of field 'Height'.
     */
    @XmlTransient
    public int getHeight(
    ) {
        return this._height;
    }

    /**
     * Returns the value of field 'textColor'.
     * 
     * @return the value of field 'TextColor'.
     */
    @XmlTransient
    public java.lang.String getTextColor(
    ) {
        return this._textColor;
    }

    /**
     * Returns the value of field 'width'.
     * 
     * @return the value of field 'Width'.
     */
    @XmlTransient
    public int getWidth(
    ) {
        return this._width;
    }

    /**
     * Method hasHeight.
     * 
     * @return true if at least one Height has been added
     */
    public boolean hasHeight(
    ) {
        return this._has_height;
    }

    /**
     * Method hasWidth.
     * 
     * @return true if at least one Width has been added
     */
    public boolean hasWidth(
    ) {
        return this._has_width;
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
     * Sets the value of field 'actionCode'.
     * 
     * @param actionCode the value of field 'actionCode'.
     */
    public void setActionCode(
            final java.lang.String actionCode) {
        this._actionCode = actionCode;
    }

    /**
     * Sets the value of field 'asObject'.
     * 
     * @param asObject the value of field 'asObject'.
     */
    public void setAsObject(
            final java.lang.String asObject) {
        this._asObject = asObject;
    }

    /**
     * Sets the value of field 'embeddedIcon'.
     * 
     * @param embeddedIcon the value of field 'embeddedIcon'.
     */
    public void setEmbeddedIcon(
            final java.lang.String embeddedIcon) {
        this._embeddedIcon = embeddedIcon;
    }

    /**
     * Sets the value of field 'fillColor'.
     * 
     * @param fillColor the value of field 'fillColor'.
     */
    public void setFillColor(
            final java.lang.String fillColor) {
        this._fillColor = fillColor;
    }

    /**
     * Sets the value of field 'height'.
     * 
     * @param height the value of field 'height'.
     */
    public void setHeight(
            final int height) {
        this._height = height;
        this._has_height = true;
    }

    /**
     * Sets the value of field 'textColor'.
     * 
     * @param textColor the value of field 'textColor'.
     */
    public void setTextColor(
            final java.lang.String textColor) {
        this._textColor = textColor;
    }

    /**
     * Sets the value of field 'width'.
     * 
     * @param width the value of field 'width'.
     */
    public void setWidth(
            final int width) {
        this._width = width;
        this._has_width = true;
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
     * de.lsem.simulation.transformation.anylogic.generator.BasicProperties
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.BasicProperties unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.BasicProperties) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.BasicProperties.class, reader);
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
