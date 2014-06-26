/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class Text.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Text implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id.
     */
    private long _id;

    /**
     * keeps track of state for field: _id
     */
    private boolean _has_id;

    /**
     * Field _name.
     */
    private java.lang.String _name;

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
     * Field _label.
     */
    private de.lsem.simulation.transformation.anylogic.generator.Label _label;

    /**
     * Field _publicFlag.
     */
    private java.lang.String _publicFlag;

    /**
     * Field _presentationFlag.
     */
    private java.lang.String _presentationFlag;

    /**
     * Field _showLabel.
     */
    private java.lang.String _showLabel;

    /**
     * Field _asObject.
     */
    private java.lang.String _asObject;

    /**
     * Field _embeddedIcon.
     */
    private java.lang.String _embeddedIcon;

    /**
     * Field _lock.
     */
    private java.lang.String _lock;

    /**
     * Field _showIn3D.
     */
    private java.lang.String _showIn3D;

    /**
     * Field _z.
     */
    private int _z;

    /**
     * keeps track of state for field: _z
     */
    private boolean _has_z;

    /**
     * Field _rotation.
     */
    private float _rotation;

    /**
     * keeps track of state for field: _rotation
     */
    private boolean _has_rotation;

    /**
     * Field _color.
     */
    private int _color;

    /**
     * keeps track of state for field: _color
     */
    private boolean _has_color;

    /**
     * Field _text.
     */
    private java.lang.String _text;

    /**
     * Field _font.
     */
    private de.lsem.simulation.transformation.anylogic.generator.Font _font;

    /**
     * Field _alignment.
     */
    private java.lang.String _alignment;


      //----------------/
     //- Constructors -/
    //----------------/

    public Text() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteColor(
    ) {
        this._has_color= false;
    }

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     */
    public void deleteRotation(
    ) {
        this._has_rotation= false;
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
     */
    public void deleteZ(
    ) {
        this._has_z= false;
    }

    /**
     * Returns the value of field 'alignment'.
     * 
     * @return the value of field 'Alignment'.
     */
    public java.lang.String getAlignment(
    ) {
        return this._alignment;
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
     * Returns the value of field 'color'.
     * 
     * @return the value of field 'Color'.
     */
    public int getColor(
    ) {
        return this._color;
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
     * Returns the value of field 'font'.
     * 
     * @return the value of field 'Font'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.Font getFont(
    ) {
        return this._font;
    }

    /**
     * Returns the value of field 'id'.
     * 
     * @return the value of field 'Id'.
     */
    public long getId(
    ) {
        return this._id;
    }

    /**
     * Returns the value of field 'label'.
     * 
     * @return the value of field 'Label'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.Label getLabel(
    ) {
        return this._label;
    }

    /**
     * Returns the value of field 'lock'.
     * 
     * @return the value of field 'Lock'.
     */
    public java.lang.String getLock(
    ) {
        return this._lock;
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
     * Returns the value of field 'presentationFlag'.
     * 
     * @return the value of field 'PresentationFlag'.
     */
    public java.lang.String getPresentationFlag(
    ) {
        return this._presentationFlag;
    }

    /**
     * Returns the value of field 'publicFlag'.
     * 
     * @return the value of field 'PublicFlag'.
     */
    public java.lang.String getPublicFlag(
    ) {
        return this._publicFlag;
    }

    /**
     * Returns the value of field 'rotation'.
     * 
     * @return the value of field 'Rotation'.
     */
    public float getRotation(
    ) {
        return this._rotation;
    }

    /**
     * Returns the value of field 'showIn3D'.
     * 
     * @return the value of field 'ShowIn3D'.
     */
    public java.lang.String getShowIn3D(
    ) {
        return this._showIn3D;
    }

    /**
     * Returns the value of field 'showLabel'.
     * 
     * @return the value of field 'ShowLabel'.
     */
    public java.lang.String getShowLabel(
    ) {
        return this._showLabel;
    }

    /**
     * Returns the value of field 'text'.
     * 
     * @return the value of field 'Text'.
     */
    public java.lang.String getText(
    ) {
        return this._text;
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
     * Returns the value of field 'z'.
     * 
     * @return the value of field 'Z'.
     */
    public int getZ(
    ) {
        return this._z;
    }

    /**
     * Method hasColor.
     * 
     * @return true if at least one Color has been added
     */
    public boolean hasColor(
    ) {
        return this._has_color;
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
     * Method hasRotation.
     * 
     * @return true if at least one Rotation has been added
     */
    public boolean hasRotation(
    ) {
        return this._has_rotation;
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
     * Method hasZ.
     * 
     * @return true if at least one Z has been added
     */
    public boolean hasZ(
    ) {
        return this._has_z;
    }

    /**
     * Sets the value of field 'alignment'.
     * 
     * @param alignment the value of field 'alignment'.
     */
    public void setAlignment(
            final java.lang.String alignment) {
        this._alignment = alignment;
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
     * Sets the value of field 'color'.
     * 
     * @param color the value of field 'color'.
     */
    public void setColor(
            final int color) {
        this._color = color;
        this._has_color = true;
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
     * Sets the value of field 'font'.
     * 
     * @param font the value of field 'font'.
     */
    public void setFont(
            final de.lsem.simulation.transformation.anylogic.generator.Font font) {
        this._font = font;
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
            final de.lsem.simulation.transformation.anylogic.generator.Label label) {
        this._label = label;
    }

    /**
     * Sets the value of field 'lock'.
     * 
     * @param lock the value of field 'lock'.
     */
    public void setLock(
            final java.lang.String lock) {
        this._lock = lock;
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
     * Sets the value of field 'rotation'.
     * 
     * @param rotation the value of field 'rotation'.
     */
    public void setRotation(
            final float rotation) {
        this._rotation = rotation;
        this._has_rotation = true;
    }

    /**
     * Sets the value of field 'showIn3D'.
     * 
     * @param showIn3D the value of field 'showIn3D'.
     */
    public void setShowIn3D(
            final java.lang.String showIn3D) {
        this._showIn3D = showIn3D;
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
     * Sets the value of field 'text'.
     * 
     * @param text the value of field 'text'.
     */
    public void setText(
            final java.lang.String text) {
        this._text = text;
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
     * Sets the value of field 'z'.
     * 
     * @param z the value of field 'z'.
     */
    public void setZ(
            final int z) {
        this._z = z;
        this._has_z = true;
    }

}
