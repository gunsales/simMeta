/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

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
    private int _width;

    /**
     * keeps track of state for field: _width
     */
    private boolean _has_width;

    /**
     * Field _height.
     */
    private int _height;

    /**
     * keeps track of state for field: _height
     */
    private boolean _has_height;

    /**
     * Field _asObject.
     */
    private java.lang.String _asObject;

    /**
     * Field _embeddedIcon.
     */
    private java.lang.String _embeddedIcon;

    /**
     * Field _fillColor.
     */
    private java.lang.String _fillColor;

    /**
     * Field _textColor.
     */
    private java.lang.String _textColor;

    /**
     * Field _actionCode.
     */
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
    public java.lang.String getActionCode(
    ) {
        return this._actionCode;
    }

    /**
     * Returns the value of field 'asObject'.
     * 
     * @return the value of field 'AsObject'.
     */
    public java.lang.String getAsObject(
    ) {
        return this._asObject;
    }

    /**
     * Returns the value of field 'embeddedIcon'.
     * 
     * @return the value of field 'EmbeddedIcon'.
     */
    public java.lang.String getEmbeddedIcon(
    ) {
        return this._embeddedIcon;
    }

    /**
     * Returns the value of field 'fillColor'.
     * 
     * @return the value of field 'FillColor'.
     */
    public java.lang.String getFillColor(
    ) {
        return this._fillColor;
    }

    /**
     * Returns the value of field 'height'.
     * 
     * @return the value of field 'Height'.
     */
    public int getHeight(
    ) {
        return this._height;
    }

    /**
     * Returns the value of field 'textColor'.
     * 
     * @return the value of field 'TextColor'.
     */
    public java.lang.String getTextColor(
    ) {
        return this._textColor;
    }

    /**
     * Returns the value of field 'width'.
     * 
     * @return the value of field 'Width'.
     */
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

}
