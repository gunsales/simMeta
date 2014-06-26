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
 * ========= Active Object Class ========
 *  
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ActiveObjectClass implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

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
     * Field _clientAreaTopLeft.
     */
	@XmlElement(name="ClientAreaTopLeft")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.ClientAreaTopLeft _clientAreaTopLeft;

    /**
     * Field _presentationTopGroupPersistent.
     */
	@XmlElement(name="PresentationTopGroupPersistent")
    private java.lang.String _presentationTopGroupPersistent;

    /**
     * Field _iconTopGroupPersistent.
     */
	@XmlElement(name="IconTopGroupPersistent")
    private java.lang.String _iconTopGroupPersistent;

    /**
     * Field _generic.
     */
	@XmlElement(name="Generic")
    private java.lang.String _generic;

    /**
     * Field _genericParameters.
     */
	@XmlElement(name="GenericParameters")
    private java.lang.String _genericParameters;

    /**
     * Field _genericParametersLabel.
     */
	@XmlElement(name="GenericParametersLabel")
    private java.lang.String _genericParametersLabel;

    /**
     * Field _samplesToKeep.
     */
	@XmlElement(name="SamplesToKeep")
    private int _samplesToKeep;

    /**
     * keeps track of state for field: _samplesToKeep
     */
    private boolean _has_samplesToKeep;

    /**
     * Field _limitNumberOfArrayElements.
     */
	@XmlElement(name="LimitNumberOfArrayElements")
    private java.lang.String _limitNumberOfArrayElements;

    /**
     * Field _elementsLimitValue.
     */
	@XmlElement(name="ElementsLimitValue")
    private int _elementsLimitValue;

    /**
     * keeps track of state for field: _elementsLimitValue
     */
    private boolean _has_elementsLimitValue;

    /**
     * Field _makeDefaultViewArea.
     */
	@XmlElement(name="MakeDefaultViewArea")
    private java.lang.String _makeDefaultViewArea;

    /**
     * Field _sceneGridColor.
     */
	@XmlElement(name="SceneGridColor")
    private java.lang.String _sceneGridColor;

    /**
     * Field _sceneBackgroundColor.
     */
	@XmlElement(name="SceneBackgroundColor")
    private java.lang.String _sceneBackgroundColor;

    /**
     * Field _agentProperties.
     */
	@XmlElement(name="AgentProperties")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.AgentProperties _agentProperties;

    /**
     * Field _datasetsCreationProperties.
     */
	@XmlElement(name="DatasetsCreationProperties")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.DatasetsCreationProperties _datasetsCreationProperties;

	/**
     * Field _connectors.
     */
	@XmlElement(name="Connectors")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.Connectors _connectors;

    /**
     * Field _embeddedObjects.
     */
	@XmlElement(name="EmbeddedObjects")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObjects _embeddedObjects;


      //----------------/
     //- Constructors -/
    //----------------/

    public ActiveObjectClass() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteElementsLimitValue(
    ) {
        this._has_elementsLimitValue= false;
    }

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     */
    public void deleteSamplesToKeep(
    ) {
        this._has_samplesToKeep= false;
    }

    /**
     * Returns the value of field 'agentProperties'.
     * 
     * @return the value of field 'AgentProperties'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.AgentProperties getAgentProperties(
    ) {
        return this._agentProperties;
    }

    /**
     * Returns the value of field 'clientAreaTopLeft'.
     * 
     * @return the value of field 'ClientAreaTopLeft'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.ClientAreaTopLeft getClientAreaTopLeft(
    ) {
        return this._clientAreaTopLeft;
    }

    /**
     * Returns the value of field 'connectors'.
     * 
     * @return the value of field 'Connectors'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Connectors getConnectors(
    ) {
        return this._connectors;
    }
    
    /**
     * Returns the value of field 'datasetsCreationProperties'.
     * 
     * @return the value of field 'DatasetsCreationProperties'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.DatasetsCreationProperties getDatasetsCreationProperties(
    ) {
        return this._datasetsCreationProperties;
    }

    /**
     * Returns the value of field 'elementsLimitValue'.
     * 
     * @return the value of field 'ElementsLimitValue'.
     */
    @XmlTransient
    public int getElementsLimitValue(
    ) {
        return this._elementsLimitValue;
    }

    /**
     * Returns the value of field 'embeddedObjects'.
     * 
     * @return the value of field 'EmbeddedObjects'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObjects getEmbeddedObjects(
    ) {
        return this._embeddedObjects;
    }

    /**
     * Returns the value of field 'generic'.
     * 
     * @return the value of field 'Generic'.
     */
    @XmlTransient
    public java.lang.String getGeneric(
    ) {
        return this._generic;
    }

    /**
     * Returns the value of field 'genericParameters'.
     * 
     * @return the value of field 'GenericParameters'.
     */
    @XmlTransient
    public java.lang.String getGenericParameters(
    ) {
        return this._genericParameters;
    }

    /**
     * Returns the value of field 'genericParametersLabel'.
     * 
     * @return the value of field 'GenericParametersLabel'.
     */
    @XmlTransient
    public java.lang.String getGenericParametersLabel(
    ) {
        return this._genericParametersLabel;
    }

    /**
     * Returns the value of field 'iconTopGroupPersistent'.
     * 
     * @return the value of field 'IconTopGroupPersistent'.
     */
    @XmlTransient
    public java.lang.String getIconTopGroupPersistent(
    ) {
        return this._iconTopGroupPersistent;
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
     * Returns the value of field 'limitNumberOfArrayElements'.
     * 
     * @return the value of field 'LimitNumberOfArrayElements'.
     */
    @XmlTransient
    public java.lang.String getLimitNumberOfArrayElements(
    ) {
        return this._limitNumberOfArrayElements;
    }

    /**
     * Returns the value of field 'makeDefaultViewArea'.
     * 
     * @return the value of field 'MakeDefaultViewArea'.
     */
    @XmlTransient
    public java.lang.String getMakeDefaultViewArea(
    ) {
        return this._makeDefaultViewArea;
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
     * Returns the value of field 'presentationTopGroupPersistent'.
     * 
     * @return the value of field 'PresentationTopGroupPersistent'.
     */
    @XmlTransient
    public java.lang.String getPresentationTopGroupPersistent(
    ) {
        return this._presentationTopGroupPersistent;
    }

    /**
     * Returns the value of field 'samplesToKeep'.
     * 
     * @return the value of field 'SamplesToKeep'.
     */
    @XmlTransient
    public int getSamplesToKeep(
    ) {
        return this._samplesToKeep;
    }

    /**
     * Returns the value of field 'sceneBackgroundColor'.
     * 
     * @return the value of field 'SceneBackgroundColor'.
     */
    @XmlTransient
    public java.lang.String getSceneBackgroundColor(
    ) {
        return this._sceneBackgroundColor;
    }

    /**
     * Returns the value of field 'sceneGridColor'.
     * 
     * @return the value of field 'SceneGridColor'.
     */
    @XmlTransient
    public java.lang.String getSceneGridColor(
    ) {
        return this._sceneGridColor;
    }

    /**
     * Method hasElementsLimitValue.
     * 
     * @return true if at least one ElementsLimitValue has been adde
     */
    public boolean hasElementsLimitValue(
    ) {
        return this._has_elementsLimitValue;
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
     * Method hasSamplesToKeep.
     * 
     * @return true if at least one SamplesToKeep has been added
     */
    public boolean hasSamplesToKeep(
    ) {
        return this._has_samplesToKeep;
    }


    /**
     * Sets the value of field 'agentProperties'.
     * 
     * @param agentProperties the value of field 'agentProperties'.
     */
    public void setAgentProperties(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.AgentProperties agentProperties) {
        this._agentProperties = agentProperties;
    }

    /**
     * Sets the value of field 'clientAreaTopLeft'.
     * 
     * @param clientAreaTopLeft the value of field
     * 'clientAreaTopLeft'.
     */
    public void setClientAreaTopLeft(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.ClientAreaTopLeft clientAreaTopLeft) {
        this._clientAreaTopLeft = clientAreaTopLeft;
    }

    /**
     * Sets the value of field 'connectors'.
     * 
     * @param connectors the value of field 'connectors'.
     */
    public void setConnectors(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Connectors connectors) {
        this._connectors = connectors;
    }
    
    /**
     * Sets the value of field 'datasetsCreationProperties'.
     * 
     * @param datasetsCreationProperties the value of field
     * 'datasetsCreationProperties'.
     */
    public void setDatasetsCreationProperties(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.DatasetsCreationProperties datasetsCreationProperties) {
        this._datasetsCreationProperties = datasetsCreationProperties;
    }

    /**
     * Sets the value of field 'elementsLimitValue'.
     * 
     * @param elementsLimitValue the value of field
     * 'elementsLimitValue'.
     */
    public void setElementsLimitValue(
            final int elementsLimitValue) {
        this._elementsLimitValue = elementsLimitValue;
        this._has_elementsLimitValue = true;
    }

    /**
     * Sets the value of field 'embeddedObjects'.
     * 
     * @param embeddedObjects the value of field 'embeddedObjects'.
     */
    public void setEmbeddedObjects(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObjects embeddedObjects) {
        this._embeddedObjects = embeddedObjects;
    }

    /**
     * Sets the value of field 'generic'.
     * 
     * @param generic the value of field 'generic'.
     */
    public void setGeneric(
            final java.lang.String generic) {
        this._generic = generic;
    }

    /**
     * Sets the value of field 'genericParameters'.
     * 
     * @param genericParameters the value of field
     * 'genericParameters'.
     */
    public void setGenericParameters(
            final java.lang.String genericParameters) {
        this._genericParameters = genericParameters;
    }

    /**
     * Sets the value of field 'genericParametersLabel'.
     * 
     * @param genericParametersLabel the value of field
     * 'genericParametersLabel'.
     */
    public void setGenericParametersLabel(
            final java.lang.String genericParametersLabel) {
        this._genericParametersLabel = genericParametersLabel;
    }

    /**
     * Sets the value of field 'iconTopGroupPersistent'.
     * 
     * @param iconTopGroupPersistent the value of field
     * 'iconTopGroupPersistent'.
     */
    public void setIconTopGroupPersistent(
            final java.lang.String iconTopGroupPersistent) {
        this._iconTopGroupPersistent = iconTopGroupPersistent;
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
     * Sets the value of field 'limitNumberOfArrayElements'.
     * 
     * @param limitNumberOfArrayElements the value of field
     * 'limitNumberOfArrayElements'.
     */
    public void setLimitNumberOfArrayElements(
            final java.lang.String limitNumberOfArrayElements) {
        this._limitNumberOfArrayElements = limitNumberOfArrayElements;
    }

    /**
     * Sets the value of field 'makeDefaultViewArea'.
     * 
     * @param makeDefaultViewArea the value of field
     * 'makeDefaultViewArea'.
     */
    public void setMakeDefaultViewArea(
            final java.lang.String makeDefaultViewArea) {
        this._makeDefaultViewArea = makeDefaultViewArea;
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
     * Sets the value of field 'presentationTopGroupPersistent'.
     * 
     * @param presentationTopGroupPersistent the value of field
     * 'presentationTopGroupPersistent'.
     */
    public void setPresentationTopGroupPersistent(
            final java.lang.String presentationTopGroupPersistent) {
        this._presentationTopGroupPersistent = presentationTopGroupPersistent;
    }

    /**
     * Sets the value of field 'samplesToKeep'.
     * 
     * @param samplesToKeep the value of field 'samplesToKeep'.
     */
    public void setSamplesToKeep(
            final int samplesToKeep) {
        this._samplesToKeep = samplesToKeep;
        this._has_samplesToKeep = true;
    }

    /**
     * Sets the value of field 'sceneBackgroundColor'.
     * 
     * @param sceneBackgroundColor the value of field
     * 'sceneBackgroundColor'.
     */
    public void setSceneBackgroundColor(
            final java.lang.String sceneBackgroundColor) {
        this._sceneBackgroundColor = sceneBackgroundColor;
    }

    /**
     * Sets the value of field 'sceneGridColor'.
     * 
     * @param sceneGridColor the value of field 'sceneGridColor'.
     */
    public void setSceneGridColor(
            final java.lang.String sceneGridColor) {
        this._sceneGridColor = sceneGridColor;
    }
}
