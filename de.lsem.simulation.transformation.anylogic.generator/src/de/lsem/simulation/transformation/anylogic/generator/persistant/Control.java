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
 * Class Control.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Control implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _type.
     */
	@XmlAttribute(name="Type")
    private java.lang.String _type;

    /**
     * Field _embeddedIcon.
     */
	@XmlElement(name="EmbeddedIcon")
    private java.lang.String _embeddedIcon;

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
     * Field _x.
     */
	@XmlElement(name="X")
    private int _x;

    /**
     * keeps track of state for field: _x
     */
    private boolean _has_x;

    /**
     * Field _y.
     */
	@XmlElement(name="Y")
    private int _y;

    /**
     * keeps track of state for field: _y
     */
    private boolean _has_y;

    /**
     * Field _label.
     */
	@XmlElement(name="Label")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.Label _label;

    /**
     * Field _publicFlag.
     */
	@XmlElement(name="PublicFlag")
    private java.lang.String _publicFlag;

    /**
     * Field _presentationFlag.
     */
	@XmlElement(name="PresentationFlag")
    private java.lang.String _presentationFlag;

    /**
     * Field _showLabel.
     */
	@XmlElement(name="ShowLabel")
    private java.lang.String _showLabel;

    /**
     * Field _basicProperties.
     */
	@XmlElement(name="BasicProperties")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.BasicProperties _basicProperties;

    /**
     * Field _extendedProperties.
     */
	@XmlElement(name="ExtendedProperties")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.ExtendedProperties _extendedProperties;


      //----------------/
     //- Constructors -/
    //----------------/

    public Control() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     */
    public void deleteX(
    ) {
        this._has_x= false;
    }

    /**
     */
    public void deleteY(
    ) {
        this._has_y= false;
    }

    /**
     * Returns the value of field 'basicProperties'.
     * 
     * @return the value of field 'BasicProperties'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.BasicProperties getBasicProperties(
    ) {
        return this._basicProperties;
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
     * Returns the value of field 'extendedProperties'.
     * 
     * @return the value of field 'ExtendedProperties'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.ExtendedProperties getExtendedProperties(
    ) {
        return this._extendedProperties;
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
     * Returns the value of field 'label'.
     * 
     * @return the value of field 'Label'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Label getLabel(
    ) {
        return this._label;
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
     * Returns the value of field 'presentationFlag'.
     * 
     * @return the value of field 'PresentationFlag'.
     */
    @XmlTransient
    public java.lang.String getPresentationFlag(
    ) {
        return this._presentationFlag;
    }

    /**
     * Returns the value of field 'publicFlag'.
     * 
     * @return the value of field 'PublicFlag'.
     */
    @XmlTransient
    public java.lang.String getPublicFlag(
    ) {
        return this._publicFlag;
    }

    /**
     * Returns the value of field 'showLabel'.
     * 
     * @return the value of field 'ShowLabel'.
     */
    @XmlTransient
    public java.lang.String getShowLabel(
    ) {
        return this._showLabel;
    }

    /**
     * Returns the value of field 'type'.
     * 
     * @return the value of field 'Type'.
     */
    @XmlTransient
    public java.lang.String getType(
    ) {
        return this._type;
    }

    /**
     * Returns the value of field 'x'.
     * 
     * @return the value of field 'X'.
     */
    @XmlTransient
    public int getX(
    ) {
        return this._x;
    }

    /**
     * Returns the value of field 'y'.
     * 
     * @return the value of field 'Y'.
     */
    @XmlTransient
    public int getY(
    ) {
        return this._y;
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
     * Method hasX.
     * 
     * @return true if at least one X has been added
     */
    public boolean hasX(
    ) {
        return this._has_x;
    }

    /**
     * Method hasY.
     * 
     * @return true if at least one Y has been added
     */
    public boolean hasY(
    ) {
        return this._has_y;
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
     * Sets the value of field 'basicProperties'.
     * 
     * @param basicProperties the value of field 'basicProperties'.
     */
    public void setBasicProperties(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.BasicProperties basicProperties) {
        this._basicProperties = basicProperties;
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
     * Sets the value of field 'extendedProperties'.
     * 
     * @param extendedProperties the value of field
     * 'extendedProperties'.
     */
    public void setExtendedProperties(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.ExtendedProperties extendedProperties) {
        this._extendedProperties = extendedProperties;
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
     * Sets the value of field 'label'.
     * 
     * @param label the value of field 'label'.
     */
    public void setLabel(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Label label) {
        this._label = label;
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
     * Sets the value of field 'presentationFlag'.
     * 
     * @param presentationFlag the value of field 'presentationFlag'
     */
    public void setPresentationFlag(
            final java.lang.String presentationFlag) {
        this._presentationFlag = presentationFlag;
    }

    /**
     * Sets the value of field 'publicFlag'.
     * 
     * @param publicFlag the value of field 'publicFlag'.
     */
    public void setPublicFlag(
            final java.lang.String publicFlag) {
        this._publicFlag = publicFlag;
    }

    /**
     * Sets the value of field 'showLabel'.
     * 
     * @param showLabel the value of field 'showLabel'.
     */
    public void setShowLabel(
            final java.lang.String showLabel) {
        this._showLabel = showLabel;
    }

    /**
     * Sets the value of field 'type'.
     * 
     * @param type the value of field 'type'.
     */
    public void setType(
            final java.lang.String type) {
        this._type = type;
    }

    /**
     * Sets the value of field 'x'.
     * 
     * @param x the value of field 'x'.
     */
    public void setX(
            final int x) {
        this._x = x;
        this._has_x = true;
    }

    /**
     * Sets the value of field 'y'.
     * 
     * @param y the value of field 'y'.
     */
    public void setY(
            final int y) {
        this._y = y;
        this._has_y = true;
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
     * de.lsem.simulation.transformation.anylogic.generator.Control
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.Control unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.Control) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.Control.class, reader);
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
