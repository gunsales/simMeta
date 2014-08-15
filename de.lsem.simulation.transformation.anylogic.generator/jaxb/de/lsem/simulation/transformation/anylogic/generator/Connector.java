/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class Connector.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Connector implements java.io.Serializable {


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
     * Field _sourceEmbeddedObject.
     */
    private long _sourceEmbeddedObject;

    /**
     * keeps track of state for field: _sourceEmbeddedObject
     */
    private boolean _has_sourceEmbeddedObject;

    /**
     * Field _sourceConnectableName.
     */
    private java.lang.String _sourceConnectableName;

    /**
     * Field _targetEmbeddedObject.
     */
    private long _targetEmbeddedObject;

    /**
     * keeps track of state for field: _targetEmbeddedObject
     */
    private boolean _has_targetEmbeddedObject;

    /**
     * Field _targetConnectableName.
     */
    private java.lang.String _targetConnectableName;

    /**
     * Field _points.
     */
    private de.lsem.simulation.transformation.anylogic.generator.Points _points;


      //----------------/
     //- Constructors -/
    //----------------/

    public Connector() {
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
    public void deleteSourceEmbeddedObject(
    ) {
        this._has_sourceEmbeddedObject= false;
    }

    /**
     */
    public void deleteTargetEmbeddedObject(
    ) {
        this._has_targetEmbeddedObject= false;
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
     * Returns the value of field 'points'.
     * 
     * @return the value of field 'Points'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.Points getPoints(
    ) {
        return this._points;
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
     * Returns the value of field 'sourceConnectableName'.
     * 
     * @return the value of field 'SourceConnectableName'.
     */
    public java.lang.String getSourceConnectableName(
    ) {
        return this._sourceConnectableName;
    }

    /**
     * Returns the value of field 'sourceEmbeddedObject'.
     * 
     * @return the value of field 'SourceEmbeddedObject'.
     */
    public long getSourceEmbeddedObject(
    ) {
        return this._sourceEmbeddedObject;
    }

    /**
     * Returns the value of field 'targetConnectableName'.
     * 
     * @return the value of field 'TargetConnectableName'.
     */
    public java.lang.String getTargetConnectableName(
    ) {
        return this._targetConnectableName;
    }

    /**
     * Returns the value of field 'targetEmbeddedObject'.
     * 
     * @return the value of field 'TargetEmbeddedObject'.
     */
    public long getTargetEmbeddedObject(
    ) {
        return this._targetEmbeddedObject;
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
     * Method hasSourceEmbeddedObject.
     * 
     * @return true if at least one SourceEmbeddedObject has been
     * added
     */
    public boolean hasSourceEmbeddedObject(
    ) {
        return this._has_sourceEmbeddedObject;
    }

    /**
     * Method hasTargetEmbeddedObject.
     * 
     * @return true if at least one TargetEmbeddedObject has been
     * added
     */
    public boolean hasTargetEmbeddedObject(
    ) {
        return this._has_targetEmbeddedObject;
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
     * Sets the value of field 'points'.
     * 
     * @param points the value of field 'points'.
     */
    public void setPoints(
            final de.lsem.simulation.transformation.anylogic.generator.Points points) {
        this._points = points;
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
     * Sets the value of field 'sourceConnectableName'.
     * 
     * @param sourceConnectableName the value of field
     * 'sourceConnectableName'.
     */
    public void setSourceConnectableName(
            final java.lang.String sourceConnectableName) {
        this._sourceConnectableName = sourceConnectableName;
    }

    /**
     * Sets the value of field 'sourceEmbeddedObject'.
     * 
     * @param sourceEmbeddedObject the value of field
     * 'sourceEmbeddedObject'.
     */
    public void setSourceEmbeddedObject(
            final long sourceEmbeddedObject) {
        this._sourceEmbeddedObject = sourceEmbeddedObject;
        this._has_sourceEmbeddedObject = true;
    }

    /**
     * Sets the value of field 'targetConnectableName'.
     * 
     * @param targetConnectableName the value of field
     * 'targetConnectableName'.
     */
    public void setTargetConnectableName(
            final java.lang.String targetConnectableName) {
        this._targetConnectableName = targetConnectableName;
    }

    /**
     * Sets the value of field 'targetEmbeddedObject'.
     * 
     * @param targetEmbeddedObject the value of field
     * 'targetEmbeddedObject'.
     */
    public void setTargetEmbeddedObject(
            final long targetEmbeddedObject) {
        this._targetEmbeddedObject = targetEmbeddedObject;
        this._has_targetEmbeddedObject = true;
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
