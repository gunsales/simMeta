/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class Font.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Font implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _name.
     */
    private java.lang.String _name;

    /**
     * Field _size.
     */
    private int _size;

    /**
     * keeps track of state for field: _size
     */
    private boolean _has_size;

    /**
     * Field _style.
     */
    private int _style;

    /**
     * keeps track of state for field: _style
     */
    private boolean _has_style;


      //----------------/
     //- Constructors -/
    //----------------/

    public Font() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteSize(
    ) {
        this._has_size= false;
    }

    /**
     */
    public void deleteStyle(
    ) {
        this._has_style= false;
    }

    /**
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'size'.
     * 
     * @return the value of field 'Size'.
     */
    public int getSize(
    ) {
        return this._size;
    }

    /**
     * Returns the value of field 'style'.
     * 
     * @return the value of field 'Style'.
     */
    public int getStyle(
    ) {
        return this._style;
    }

    /**
     * Method hasSize.
     * 
     * @return true if at least one Size has been added
     */
    public boolean hasSize(
    ) {
        return this._has_size;
    }

    /**
     * Method hasStyle.
     * 
     * @return true if at least one Style has been added
     */
    public boolean hasStyle(
    ) {
        return this._has_style;
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
     * Sets the value of field 'size'.
     * 
     * @param size the value of field 'size'.
     */
    public void setSize(
            final int size) {
        this._size = size;
        this._has_size = true;
    }

    /**
     * Sets the value of field 'style'.
     * 
     * @param style the value of field 'style'.
     */
    public void setStyle(
            final int style) {
        this._style = style;
        this._has_style = true;
    }

}
