/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class EmbeddedObject.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class EmbeddedObject implements java.io.Serializable {


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
     * Field _activeObjectClassInner.
     */
    private de.lsem.simulation.transformation.anylogic.generator.ActiveObjectClassInner _activeObjectClassInner;

    /**
     * Field _genericParametersSubstitute.
     */
    private java.lang.String _genericParametersSubstitute;

    /**
     * Field _parameters.
     */
    private de.lsem.simulation.transformation.anylogic.generator.Parameters _parameters;

    /**
     * Field _collectionType.
     */
    private java.lang.String _collectionType;


      //----------------/
     //- Constructors -/
    //----------------/

    public EmbeddedObject() {
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
     * Returns the value of field 'activeObjectClassInner'.
     * 
     * @return the value of field 'ActiveObjectClassInner'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.ActiveObjectClassInner getActiveObjectClassInner(
    ) {
        return this._activeObjectClassInner;
    }

    /**
     * Returns the value of field 'collectionType'.
     * 
     * @return the value of field 'CollectionType'.
     */
    public java.lang.String getCollectionType(
    ) {
        return this._collectionType;
    }

    /**
     * Returns the value of field 'genericParametersSubstitute'.
     * 
     * @return the value of field 'GenericParametersSubstitute'.
     */
    public java.lang.String getGenericParametersSubstitute(
    ) {
        return this._genericParametersSubstitute;
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
     * Returns the value of field 'name'.
     * 
     * @return the value of field 'Name'.
     */
    public java.lang.String getName(
    ) {
        return this._name;
    }

    /**
     * Returns the value of field 'parameters'.
     * 
     * @return the value of field 'Parameters'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.Parameters getParameters(
    ) {
        return this._parameters;
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
     * Returns the value of field 'showLabel'.
     * 
     * @return the value of field 'ShowLabel'.
     */
    public java.lang.String getShowLabel(
    ) {
        return this._showLabel;
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
     * Sets the value of field 'activeObjectClassInner'.
     * 
     * @param activeObjectClassInner the value of field
     * 'activeObjectClassInner'.
     */
    public void setActiveObjectClassInner(
            final de.lsem.simulation.transformation.anylogic.generator.ActiveObjectClassInner activeObjectClassInner) {
        this._activeObjectClassInner = activeObjectClassInner;
    }

    /**
     * Sets the value of field 'collectionType'.
     * 
     * @param collectionType the value of field 'collectionType'.
     */
    public void setCollectionType(
            final java.lang.String collectionType) {
        this._collectionType = collectionType;
    }

    /**
     * Sets the value of field 'genericParametersSubstitute'.
     * 
     * @param genericParametersSubstitute the value of field
     * 'genericParametersSubstitute'.
     */
    public void setGenericParametersSubstitute(
            final java.lang.String genericParametersSubstitute) {
        this._genericParametersSubstitute = genericParametersSubstitute;
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
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(
            final java.lang.String name) {
        this._name = name;
    }

    /**
     * Sets the value of field 'parameters'.
     * 
     * @param parameters the value of field 'parameters'.
     */
    public void setParameters(
            final de.lsem.simulation.transformation.anylogic.generator.Parameters parameters) {
        this._parameters = parameters;
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
