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
 * Class PresentationProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class PresentationProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _enableAdaptiveFrameManagement.
     */
	@XmlAttribute(name="EnableAdaptiveFrameManagement")
    private java.lang.String _enableAdaptiveFrameManagement;

    /**
     * Field _enableAntiAliasing.
     */
	@XmlAttribute(name="EnableAntiAliasing")
    private java.lang.String _enableAntiAliasing;

    /**
     * Field _enableEnhancedModelElementsAnimation.
     */
	@XmlAttribute(name="EnableEnhancedModelElementsAnimation")
    private java.lang.String _enableEnhancedModelElementsAnimation;

    /**
     * Field _enablePanning.
     */
	@XmlAttribute(name="EnablePanning")
    private java.lang.String _enablePanning;

    /**
     * Field _toolbarCustomizableAtRuntime.
     */
	@XmlAttribute(name="ToolbarCustomizableAtRuntime")
    private java.lang.String _toolbarCustomizableAtRuntime;

    /**
     * Field _enableZoom.
     */
	@XmlAttribute(name="EnableZoom")
    private java.lang.String _enableZoom;

    /**
     * Field _executionMode.
     */
	@XmlElement(name="ExecutionMode")
    private java.lang.String _executionMode;

    /**
     * Field _cpuRatio.
     */
	@XmlElement(name="CpuRatio")
    private java.lang.String _cpuRatio;

    /**
     * Field _title.
     */
	@XmlElement(name="Title")
    private java.lang.String _title;

    /**
     * Field _framesPerSecond.
     */
	@XmlElement(name="FramesPerSecond")
    private java.lang.String _framesPerSecond;
    /**
     * keeps track of state for field: _framesPerSecond
     */
    private boolean _has_framesPerSecond;

    /**
     * Field _realTimeScale.
     */
	@XmlElement(name="RealTimeScale")
    private float _realTimeScale;

    /**
     * keeps track of state for field: _realTimeScale
     */
    private boolean _has_realTimeScale;

    /**
     * Field _UIPropertyList.
     */
	@XmlElement(name="UIProperty")
    private java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty> _UIPropertyList;


      //----------------/
     //- Constructors -/
    //----------------/

    public PresentationProperties() {
        super();
        this._UIPropertyList = new java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vUIProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addUIProperty(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty vUIProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._UIPropertyList.addElement(vUIProperty);
    }

    /**
     * 
     * 
     * @param index
     * @param vUIProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addUIProperty(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty vUIProperty)
    throws java.lang.IndexOutOfBoundsException {
        this._UIPropertyList.add(index, vUIProperty);
    }

    /**
     */
    public void deleteFramesPerSecond(
    ) {
        this._has_framesPerSecond= false;
    }

    /**
     */
    public void deleteRealTimeScale(
    ) {
        this._has_realTimeScale= false;
    }

    /**
     * Method enumerateUIProperty.
     * 
     * @return an Enumeration over all
     * de.lsem.simulation.transformation.anylogic.generator.UIProperty
     * elements
     */
    public java.util.Enumeration<? extends de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty> enumerateUIProperty(
    ) {
        return this._UIPropertyList.elements();
    }

    /**
     * Returns the value of field 'cpuRatio'.
     * 
     * @return the value of field 'CpuRatio'.
     */
    @XmlTransient
    public java.lang.String getCpuRatio(
    ) {
        return this._cpuRatio;
    }

    /**
     * Returns the value of field 'enableAdaptiveFrameManagement'.
     * 
     * @return the value of field 'EnableAdaptiveFrameManagement'.
     */
    @XmlTransient
    public java.lang.String getEnableAdaptiveFrameManagement(
    ) {
        return this._enableAdaptiveFrameManagement;
    }

    /**
     * Returns the value of field 'enableAntiAliasing'.
     * 
     * @return the value of field 'EnableAntiAliasing'.
     */
    @XmlTransient
    public java.lang.String getEnableAntiAliasing(
    ) {
        return this._enableAntiAliasing;
    }

    /**
     * Returns the value of field
     * 'enableEnhancedModelElementsAnimation'.
     * 
     * @return the value of field
     * 'EnableEnhancedModelElementsAnimation'.
     */
    @XmlTransient
    public java.lang.String getEnableEnhancedModelElementsAnimation(
    ) {
        return this._enableEnhancedModelElementsAnimation;
    }

    /**
     * Returns the value of field 'enablePanning'.
     * 
     * @return the value of field 'EnablePanning'.
     */
    @XmlTransient
    public java.lang.String getEnablePanning(
    ) {
        return this._enablePanning;
    }

    /**
     * Returns the value of field 'enableZoom'.
     * 
     * @return the value of field 'EnableZoom'.
     */
    @XmlTransient
    public java.lang.String getEnableZoom(
    ) {
        return this._enableZoom;
    }

    /**
     * Returns the value of field 'executionMode'.
     * 
     * @return the value of field 'ExecutionMode'.
     */
    @XmlTransient
    public java.lang.String getExecutionMode(
    ) {
        return this._executionMode;
    }

    /**
     * Returns the value of field 'framesPerSecond'.
     * 
     * @return the value of field 'FramesPerSecond'.
     */
    @XmlTransient
    public String getFramesPerSecond(
    ) {
        return this._framesPerSecond;
    }

    /**
     * Returns the value of field 'realTimeScale'.
     * 
     * @return the value of field 'RealTimeScale'.
     */
    @XmlTransient
    public float getRealTimeScale(
    ) {
        return this._realTimeScale;
    }

    /**
     * Returns the value of field 'title'.
     * 
     * @return the value of field 'Title'.
     */
    @XmlTransient
    public java.lang.String getTitle(
    ) {
        return this._title;
    }

    /**
     * Returns the value of field 'toolbarCustomizableAtRuntime'.
     * 
     * @return the value of field 'ToolbarCustomizableAtRuntime'.
     */
    @XmlTransient
    public java.lang.String getToolbarCustomizableAtRuntime(
    ) {
        return this._toolbarCustomizableAtRuntime;
    }

    /**
     * Method getUIProperty.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * de.lsem.simulation.transformation.anylogic.generator.UIProperty
     * at the given index
     */
    public de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty getUIProperty(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._UIPropertyList.size()) {
            throw new IndexOutOfBoundsException("getUIProperty: Index value '" + index + "' not in range [0.." + (this._UIPropertyList.size() - 1) + "]");
        }

        return (de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty) _UIPropertyList.get(index);
    }

    /**
     * Method getUIProperty.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty[] getUIProperty(
    ) {
        de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty[] array = new de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty[0];
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty[]) this._UIPropertyList.toArray(array);
    }

    /**
     * Method getUIPropertyCount.
     * 
     * @return the size of this collection
     */
    @XmlTransient
    public int getUIPropertyCount(
    ) {
        return this._UIPropertyList.size();
    }

    /**
     * Method hasFramesPerSecond.
     * 
     * @return true if at least one FramesPerSecond has been added
     */
    public boolean hasFramesPerSecond(
    ) {
        return this._has_framesPerSecond;
    }

    /**
     * Method hasRealTimeScale.
     * 
     * @return true if at least one RealTimeScale has been added
     */
    public boolean hasRealTimeScale(
    ) {
        return this._has_realTimeScale;
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
     */
    public void removeAllUIProperty(
    ) {
        this._UIPropertyList.clear();
    }

    /**
     * Method removeUIProperty.
     * 
     * @param vUIProperty
     * @return true if the object was removed from the collection.
     */
    public boolean removeUIProperty(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty vUIProperty) {
        boolean removed = _UIPropertyList.remove(vUIProperty);
        return removed;
    }

    /**
     * Method removeUIPropertyAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty removeUIPropertyAt(
            final int index) {
        java.lang.Object obj = this._UIPropertyList.remove(index);
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty) obj;
    }

    /**
     * Sets the value of field 'cpuRatio'.
     * 
     * @param cpuRatio the value of field 'cpuRatio'.
     */
    public void setCpuRatio(
            final java.lang.String cpuRatio) {
        this._cpuRatio = cpuRatio;
    }

    /**
     * Sets the value of field 'enableAdaptiveFrameManagement'.
     * 
     * @param enableAdaptiveFrameManagement the value of field
     * 'enableAdaptiveFrameManagement'.
     */
    public void setEnableAdaptiveFrameManagement(
            final java.lang.String enableAdaptiveFrameManagement) {
        this._enableAdaptiveFrameManagement = enableAdaptiveFrameManagement;
    }

    /**
     * Sets the value of field 'enableAntiAliasing'.
     * 
     * @param enableAntiAliasing the value of field
     * 'enableAntiAliasing'.
     */
    public void setEnableAntiAliasing(
            final java.lang.String enableAntiAliasing) {
        this._enableAntiAliasing = enableAntiAliasing;
    }

    /**
     * Sets the value of field
     * 'enableEnhancedModelElementsAnimation'.
     * 
     * @param enableEnhancedModelElementsAnimation the value of
     * field 'enableEnhancedModelElementsAnimation'.
     */
    public void setEnableEnhancedModelElementsAnimation(
            final java.lang.String enableEnhancedModelElementsAnimation) {
        this._enableEnhancedModelElementsAnimation = enableEnhancedModelElementsAnimation;
    }

    /**
     * Sets the value of field 'enablePanning'.
     * 
     * @param enablePanning the value of field 'enablePanning'.
     */
    public void setEnablePanning(
            final java.lang.String enablePanning) {
        this._enablePanning = enablePanning;
    }

    /**
     * Sets the value of field 'enableZoom'.
     * 
     * @param enableZoom the value of field 'enableZoom'.
     */
    public void setEnableZoom(
            final java.lang.String enableZoom) {
        this._enableZoom = enableZoom;
    }

    /**
     * Sets the value of field 'executionMode'.
     * 
     * @param executionMode the value of field 'executionMode'.
     */
    public void setExecutionMode(
            final java.lang.String executionMode) {
        this._executionMode = executionMode;
    }

    /**
     * Sets the value of field 'framesPerSecond'.
     * 
     * @param framesPerSecond the value of field 'framesPerSecond'.
     */
    public void setFramesPerSecond(
            final String framesPerSecond) {
        this._framesPerSecond = framesPerSecond;
        this._has_framesPerSecond = true;
    }

    /**
     * Sets the value of field 'realTimeScale'.
     * 
     * @param realTimeScale the value of field 'realTimeScale'.
     */
    public void setRealTimeScale(
            final float realTimeScale) {
        this._realTimeScale = realTimeScale;
        this._has_realTimeScale = true;
    }

    /**
     * Sets the value of field 'title'.
     * 
     * @param title the value of field 'title'.
     */
    public void setTitle(
            final java.lang.String title) {
        this._title = title;
    }

    /**
     * Sets the value of field 'toolbarCustomizableAtRuntime'.
     * 
     * @param toolbarCustomizableAtRuntime the value of field
     * 'toolbarCustomizableAtRuntime'.
     */
    public void setToolbarCustomizableAtRuntime(
            final java.lang.String toolbarCustomizableAtRuntime) {
        this._toolbarCustomizableAtRuntime = toolbarCustomizableAtRuntime;
    }

    /**
     * 
     * 
     * @param index
     * @param vUIProperty
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setUIProperty(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty vUIProperty)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._UIPropertyList.size()) {
            throw new IndexOutOfBoundsException("setUIProperty: Index value '" + index + "' not in range [0.." + (this._UIPropertyList.size() - 1) + "]");
        }

        this._UIPropertyList.set(index, vUIProperty);
    }

    /**
     * 
     * 
     * @param vUIPropertyArray
     */
    public void setUIProperty(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.UIProperty[] vUIPropertyArray) {
        //-- copy array
        _UIPropertyList.clear();

        for (int i = 0; i < vUIPropertyArray.length; i++) {
                this._UIPropertyList.add(vUIPropertyArray[i]);
        }
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
     * de.lsem.simulation.transformation.anylogic.generator.PresentationProperties
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.PresentationProperties unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.PresentationProperties) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.PresentationProperties.class, reader);
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
