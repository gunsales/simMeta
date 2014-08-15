/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class Frame.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Frame implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _x.
     */
    private int _x;

    /**
     * keeps track of state for field: _x
     */
    private boolean _has_x;

    /**
     * Field _y.
     */
    private int _y;

    /**
     * keeps track of state for field: _y
     */
    private boolean _has_y;

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
     * Field _maximized.
     */
    private java.lang.String _maximized;

    /**
     * Field _closeConfirmation.
     */
    private java.lang.String _closeConfirmation;


      //----------------/
     //- Constructors -/
    //----------------/

    public Frame() {
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
     * Returns the value of field 'closeConfirmation'.
     * 
     * @return the value of field 'CloseConfirmation'.
     */
    public java.lang.String getCloseConfirmation(
    ) {
        return this._closeConfirmation;
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
     * Returns the value of field 'maximized'.
     * 
     * @return the value of field 'Maximized'.
     */
    public java.lang.String getMaximized(
    ) {
        return this._maximized;
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
     * Returns the value of field 'x'.
     * 
     * @return the value of field 'X'.
     */
    public int getX(
    ) {
        return this._x;
    }

    /**
     * Returns the value of field 'y'.
     * 
     * @return the value of field 'Y'.
     */
    public int getY(
    ) {
        return this._y;
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
     * Sets the value of field 'closeConfirmation'.
     * 
     * @param closeConfirmation the value of field
     * 'closeConfirmation'.
     */
    public void setCloseConfirmation(
            final java.lang.String closeConfirmation) {
        this._closeConfirmation = closeConfirmation;
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
     * Sets the value of field 'maximized'.
     * 
     * @param maximized the value of field 'maximized'.
     */
    public void setMaximized(
            final java.lang.String maximized) {
        this._maximized = maximized;
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

}
