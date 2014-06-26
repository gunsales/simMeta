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
 * ========= Simulation Experiment ========
 *  
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SimulationExperiment implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _activeObjectClassId.
     */
	@XmlAttribute(name="ActiveObjectClassId")
    private long _activeObjectClassId;

    /**
     * keeps track of state for field: _activeObjectClassId
     */
    private boolean _has_activeObjectClassId;

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
     * Field _frame.
     */
    @XmlElement(name="Frame")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.Frame _frame;

    /**
     * Field _commandLineArguments.
     */
    @XmlElement(name="CommandLineArguments")
    private java.lang.String _commandLineArguments;

    /**
     * Field _maximumMemory.
     */
    @XmlElement(name="MaximumMemory")
    private int _maximumMemory;

    /**
     * keeps track of state for field: _maximumMemory
     */
    private boolean _has_maximumMemory;

    /**
     * Field _randomNumberGenerationType.
     */
    @XmlElement(name="RandomNumberGenerationType")
    private java.lang.String _randomNumberGenerationType;

    /**
     * Field _customGeneratorCode.
     */
    @XmlElement(name="CustomGeneratorCode")
    private java.lang.String _customGeneratorCode;

    /**
     * Field _seedValue.
     */
    @XmlElement(name="SeedValue")
    private int _seedValue;

    /**
     * keeps track of state for field: _seedValue
     */
    private boolean _has_seedValue;

    /**
     * Field _randomSelectionModeForSimultaneousEvents.
     */
    @XmlElement(name="RandomSelectionModeForSimultaneousEvents")
    private java.lang.String _randomSelectionModeForSimultaneousEvents;

    /**
     * Field _vmArgs.
     */
    @XmlElement(name="VmArgs")
    private java.lang.String _vmArgs;

    /**
     * Field _absoluteAccuracy.
     */
    @XmlElement(name="AbsoluteAccuracy")
    private float _absoluteAccuracy;

    /**
     * keeps track of state for field: _absoluteAccuracy
     */
    private boolean _has_absoluteAccuracy;

    /**
     * Field _relativeAccuracy.
     */
    @XmlElement(name="RelativeAccuracy")
    private float _relativeAccuracy;

    /**
     * keeps track of state for field: _relativeAccuracy
     */
    private boolean _has_relativeAccuracy;

    /**
     * Field _timeAccuracy.
     */
    @XmlElement(name="TimeAccuracy")
    private float _timeAccuracy;

    /**
     * keeps track of state for field: _timeAccuracy
     */
    private boolean _has_timeAccuracy;

    /**
     * Field _fixedTimeStep.
     */
    @XmlElement(name="FixedTimeStep")
    private float _fixedTimeStep;

    /**
     * keeps track of state for field: _fixedTimeStep
     */
    private boolean _has_fixedTimeStep;

    /**
     * Field _loadRootFromSnapshot.
     */
    @XmlElement(name="LoadRootFromSnapshot")
    private java.lang.String _loadRootFromSnapshot;

    /**
     * Field _snapshotFile.
     */
    @XmlElement(name="SnapshotFile")
    private java.lang.String _snapshotFile;

    /**
     * Field _diffEquationsNumericalMethod.
     */
    @XmlElement(name="DiffEquationsNumericalMethod")
    private java.lang.String _diffEquationsNumericalMethod;

    /**
     * Field _mixedEquationsNumericalMethod.
     */
    @XmlElement(name="MixedEquationsNumericalMethod")
    private java.lang.String _mixedEquationsNumericalMethod;

    /**
     * Field _algebraicEquationsNumericalMethod.
     */
    @XmlElement(name="AlgebraicEquationsNumericalMethod")
    private java.lang.String _algebraicEquationsNumericalMethod;

    /**
     * Field _shapes.
     */
    @XmlElement(name="Shapes")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.Shapes _shapes;

    /**
     * Field _controls.
     */
    @XmlElement(name="Controls")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.Controls _controls;

    /**
     * Field _parameters.
     */
    @XmlElement(name="Parameters")
    private java.lang.String _parameters;

    /**
     * Field _presentationProperties.
     */
    @XmlElement(name="PresentationProperties")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.PresentationProperties _presentationProperties;

    /**
     * Field _modelTimeProperties.
     */
    @XmlElement(name="ModelTimeProperties")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.ModelTimeProperties _modelTimeProperties;


      //----------------/
     //- Constructors -/
    //----------------/

    public SimulationExperiment() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteAbsoluteAccuracy(
    ) {
        this._has_absoluteAccuracy= false;
    }

    /**
     */
    public void deleteActiveObjectClassId(
    ) {
        this._has_activeObjectClassId= false;
    }

    /**
     */
    public void deleteFixedTimeStep(
    ) {
        this._has_fixedTimeStep= false;
    }

    /**
     */
    public void deleteId(
    ) {
        this._has_id= false;
    }

    /**
     */
    public void deleteMaximumMemory(
    ) {
        this._has_maximumMemory= false;
    }

    /**
     */
    public void deleteRelativeAccuracy(
    ) {
        this._has_relativeAccuracy= false;
    }

    /**
     */
    public void deleteSeedValue(
    ) {
        this._has_seedValue= false;
    }

    /**
     */
    public void deleteTimeAccuracy(
    ) {
        this._has_timeAccuracy= false;
    }

    /**
     * Returns the value of field 'absoluteAccuracy'.
     * 
     * @return the value of field 'AbsoluteAccuracy'.
     */
    @XmlTransient
    public float getAbsoluteAccuracy(
    ) {
        return this._absoluteAccuracy;
    }

    /**
     * Returns the value of field 'activeObjectClassId'.
     * 
     * @return the value of field 'ActiveObjectClassId'.
     */
    @XmlTransient
    public long getActiveObjectClassId(
    ) {
        return this._activeObjectClassId;
    }

    /**
     * Returns the value of field
     * 'algebraicEquationsNumericalMethod'.
     * 
     * @return the value of field
     * 'AlgebraicEquationsNumericalMethod'.
     */
    @XmlTransient
    public java.lang.String getAlgebraicEquationsNumericalMethod(
    ) {
        return this._algebraicEquationsNumericalMethod;
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
     * Returns the value of field 'commandLineArguments'.
     * 
     * @return the value of field 'CommandLineArguments'.
     */
    @XmlTransient
    public java.lang.String getCommandLineArguments(
    ) {
        return this._commandLineArguments;
    }

    /**
     * Returns the value of field 'controls'.
     * 
     * @return the value of field 'Controls'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Controls getControls(
    ) {
        return this._controls;
    }

    /**
     * Returns the value of field 'customGeneratorCode'.
     * 
     * @return the value of field 'CustomGeneratorCode'.
     */
    @XmlTransient
    public java.lang.String getCustomGeneratorCode(
    ) {
        return this._customGeneratorCode;
    }

    /**
     * Returns the value of field 'diffEquationsNumericalMethod'.
     * 
     * @return the value of field 'DiffEquationsNumericalMethod'.
     */
    @XmlTransient
    public java.lang.String getDiffEquationsNumericalMethod(
    ) {
        return this._diffEquationsNumericalMethod;
    }

    /**
     * Returns the value of field 'fixedTimeStep'.
     * 
     * @return the value of field 'FixedTimeStep'.
     */
    @XmlTransient
    public float getFixedTimeStep(
    ) {
        return this._fixedTimeStep;
    }

    /**
     * Returns the value of field 'frame'.
     * 
     * @return the value of field 'Frame'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Frame getFrame(
    ) {
        return this._frame;
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
     * Returns the value of field 'loadRootFromSnapshot'.
     * 
     * @return the value of field 'LoadRootFromSnapshot'.
     */
    @XmlTransient
    public java.lang.String getLoadRootFromSnapshot(
    ) {
        return this._loadRootFromSnapshot;
    }

    /**
     * Returns the value of field 'maximumMemory'.
     * 
     * @return the value of field 'MaximumMemory'.
     */
    @XmlTransient
    public int getMaximumMemory(
    ) {
        return this._maximumMemory;
    }

    /**
     * Returns the value of field 'mixedEquationsNumericalMethod'.
     * 
     * @return the value of field 'MixedEquationsNumericalMethod'.
     */
    @XmlTransient
    public java.lang.String getMixedEquationsNumericalMethod(
    ) {
        return this._mixedEquationsNumericalMethod;
    }

    /**
     * Returns the value of field 'modelTimeProperties'.
     * 
     * @return the value of field 'ModelTimeProperties'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.ModelTimeProperties getModelTimeProperties(
    ) {
        return this._modelTimeProperties;
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
     * Returns the value of field 'parameters'.
     * 
     * @return the value of field 'Parameters'.
     */
    @XmlTransient
    public java.lang.String getParameters(
    ) {
        return this._parameters;
    }

    /**
     * Returns the value of field 'presentationProperties'.
     * 
     * @return the value of field 'PresentationProperties'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.PresentationProperties getPresentationProperties(
    ) {
        return this._presentationProperties;
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
     * Returns the value of field 'randomNumberGenerationType'.
     * 
     * @return the value of field 'RandomNumberGenerationType'.
     */
    @XmlTransient
    public java.lang.String getRandomNumberGenerationType(
    ) {
        return this._randomNumberGenerationType;
    }

    /**
     * Returns the value of field
     * 'randomSelectionModeForSimultaneousEvents'.
     * 
     * @return the value of field
     * 'RandomSelectionModeForSimultaneousEvents'.
     */
    @XmlTransient
    public java.lang.String getRandomSelectionModeForSimultaneousEvents(
    ) {
        return this._randomSelectionModeForSimultaneousEvents;
    }

    /**
     * Returns the value of field 'relativeAccuracy'.
     * 
     * @return the value of field 'RelativeAccuracy'.
     */
    @XmlTransient
    public float getRelativeAccuracy(
    ) {
        return this._relativeAccuracy;
    }

    /**
     * Returns the value of field 'seedValue'.
     * 
     * @return the value of field 'SeedValue'.
     */
    @XmlTransient
    public int getSeedValue(
    ) {
        return this._seedValue;
    }

    /**
     * Returns the value of field 'shapes'.
     * 
     * @return the value of field 'Shapes'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Shapes getShapes(
    ) {
        return this._shapes;
    }

    /**
     * Returns the value of field 'snapshotFile'.
     * 
     * @return the value of field 'SnapshotFile'.
     */
    @XmlTransient
    public java.lang.String getSnapshotFile(
    ) {
        return this._snapshotFile;
    }

    /**
     * Returns the value of field 'timeAccuracy'.
     * 
     * @return the value of field 'TimeAccuracy'.
     */
    @XmlTransient
    public float getTimeAccuracy(
    ) {
        return this._timeAccuracy;
    }

    /**
     * Returns the value of field 'vmArgs'.
     * 
     * @return the value of field 'VmArgs'.
     */
    @XmlTransient
    public java.lang.String getVmArgs(
    ) {
        return this._vmArgs;
    }

    /**
     * Method hasAbsoluteAccuracy.
     * 
     * @return true if at least one AbsoluteAccuracy has been added
     */
    public boolean hasAbsoluteAccuracy(
    ) {
        return this._has_absoluteAccuracy;
    }

    /**
     * Method hasActiveObjectClassId.
     * 
     * @return true if at least one ActiveObjectClassId has been
     * added
     */
    public boolean hasActiveObjectClassId(
    ) {
        return this._has_activeObjectClassId;
    }

    /**
     * Method hasFixedTimeStep.
     * 
     * @return true if at least one FixedTimeStep has been added
     */
    public boolean hasFixedTimeStep(
    ) {
        return this._has_fixedTimeStep;
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
     * Method hasMaximumMemory.
     * 
     * @return true if at least one MaximumMemory has been added
     */
    public boolean hasMaximumMemory(
    ) {
        return this._has_maximumMemory;
    }

    /**
     * Method hasRelativeAccuracy.
     * 
     * @return true if at least one RelativeAccuracy has been added
     */
    public boolean hasRelativeAccuracy(
    ) {
        return this._has_relativeAccuracy;
    }

    /**
     * Method hasSeedValue.
     * 
     * @return true if at least one SeedValue has been added
     */
    public boolean hasSeedValue(
    ) {
        return this._has_seedValue;
    }

    /**
     * Method hasTimeAccuracy.
     * 
     * @return true if at least one TimeAccuracy has been added
     */
    public boolean hasTimeAccuracy(
    ) {
        return this._has_timeAccuracy;
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
     * Sets the value of field 'absoluteAccuracy'.
     * 
     * @param absoluteAccuracy the value of field 'absoluteAccuracy'
     */
    public void setAbsoluteAccuracy(
            final float absoluteAccuracy) {
        this._absoluteAccuracy = absoluteAccuracy;
        this._has_absoluteAccuracy = true;
    }

    /**
     * Sets the value of field 'activeObjectClassId'.
     * 
     * @param activeObjectClassId the value of field
     * 'activeObjectClassId'.
     */
    public void setActiveObjectClassId(
            final long activeObjectClassId) {
        this._activeObjectClassId = activeObjectClassId;
        this._has_activeObjectClassId = true;
    }

    /**
     * Sets the value of field 'algebraicEquationsNumericalMethod'.
     * 
     * @param algebraicEquationsNumericalMethod the value of field
     * 'algebraicEquationsNumericalMethod'.
     */
    public void setAlgebraicEquationsNumericalMethod(
            final java.lang.String algebraicEquationsNumericalMethod) {
        this._algebraicEquationsNumericalMethod = algebraicEquationsNumericalMethod;
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
     * Sets the value of field 'commandLineArguments'.
     * 
     * @param commandLineArguments the value of field
     * 'commandLineArguments'.
     */
    public void setCommandLineArguments(
            final java.lang.String commandLineArguments) {
        this._commandLineArguments = commandLineArguments;
    }

    /**
     * Sets the value of field 'controls'.
     * 
     * @param controls the value of field 'controls'.
     */
    public void setControls(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Controls controls) {
        this._controls = controls;
    }

    /**
     * Sets the value of field 'customGeneratorCode'.
     * 
     * @param customGeneratorCode the value of field
     * 'customGeneratorCode'.
     */
    public void setCustomGeneratorCode(
            final java.lang.String customGeneratorCode) {
        this._customGeneratorCode = customGeneratorCode;
    }

    /**
     * Sets the value of field 'diffEquationsNumericalMethod'.
     * 
     * @param diffEquationsNumericalMethod the value of field
     * 'diffEquationsNumericalMethod'.
     */
    public void setDiffEquationsNumericalMethod(
            final java.lang.String diffEquationsNumericalMethod) {
        this._diffEquationsNumericalMethod = diffEquationsNumericalMethod;
    }

    /**
     * Sets the value of field 'fixedTimeStep'.
     * 
     * @param fixedTimeStep the value of field 'fixedTimeStep'.
     */
    public void setFixedTimeStep(
            final float fixedTimeStep) {
        this._fixedTimeStep = fixedTimeStep;
        this._has_fixedTimeStep = true;
    }

    /**
     * Sets the value of field 'frame'.
     * 
     * @param frame the value of field 'frame'.
     */
    public void setFrame(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Frame frame) {
        this._frame = frame;
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
     * Sets the value of field 'loadRootFromSnapshot'.
     * 
     * @param loadRootFromSnapshot the value of field
     * 'loadRootFromSnapshot'.
     */
    public void setLoadRootFromSnapshot(
            final java.lang.String loadRootFromSnapshot) {
        this._loadRootFromSnapshot = loadRootFromSnapshot;
    }

    /**
     * Sets the value of field 'maximumMemory'.
     * 
     * @param maximumMemory the value of field 'maximumMemory'.
     */
    public void setMaximumMemory(
            final int maximumMemory) {
        this._maximumMemory = maximumMemory;
        this._has_maximumMemory = true;
    }

    /**
     * Sets the value of field 'mixedEquationsNumericalMethod'.
     * 
     * @param mixedEquationsNumericalMethod the value of field
     * 'mixedEquationsNumericalMethod'.
     */
    public void setMixedEquationsNumericalMethod(
            final java.lang.String mixedEquationsNumericalMethod) {
        this._mixedEquationsNumericalMethod = mixedEquationsNumericalMethod;
    }

    /**
     * Sets the value of field 'modelTimeProperties'.
     * 
     * @param modelTimeProperties the value of field
     * 'modelTimeProperties'.
     */
    public void setModelTimeProperties(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.ModelTimeProperties modelTimeProperties) {
        this._modelTimeProperties = modelTimeProperties;
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
            final java.lang.String parameters) {
        this._parameters = parameters;
    }

    /**
     * Sets the value of field 'presentationProperties'.
     * 
     * @param presentationProperties the value of field
     * 'presentationProperties'.
     */
    public void setPresentationProperties(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.PresentationProperties presentationProperties) {
        this._presentationProperties = presentationProperties;
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
     * Sets the value of field 'randomNumberGenerationType'.
     * 
     * @param randomNumberGenerationType the value of field
     * 'randomNumberGenerationType'.
     */
    public void setRandomNumberGenerationType(
            final java.lang.String randomNumberGenerationType) {
        this._randomNumberGenerationType = randomNumberGenerationType;
    }

    /**
     * Sets the value of field
     * 'randomSelectionModeForSimultaneousEvents'.
     * 
     * @param randomSelectionModeForSimultaneousEvents the value of
     * field 'randomSelectionModeForSimultaneousEvents'.
     */
    public void setRandomSelectionModeForSimultaneousEvents(
            final java.lang.String randomSelectionModeForSimultaneousEvents) {
        this._randomSelectionModeForSimultaneousEvents = randomSelectionModeForSimultaneousEvents;
    }

    /**
     * Sets the value of field 'relativeAccuracy'.
     * 
     * @param relativeAccuracy the value of field 'relativeAccuracy'
     */
    public void setRelativeAccuracy(
            final float relativeAccuracy) {
        this._relativeAccuracy = relativeAccuracy;
        this._has_relativeAccuracy = true;
    }

    /**
     * Sets the value of field 'seedValue'.
     * 
     * @param seedValue the value of field 'seedValue'.
     */
    public void setSeedValue(
            final int seedValue) {
        this._seedValue = seedValue;
        this._has_seedValue = true;
    }

    /**
     * Sets the value of field 'shapes'.
     * 
     * @param shapes the value of field 'shapes'.
     */
    public void setShapes(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Shapes shapes) {
        this._shapes = shapes;
    }

    /**
     * Sets the value of field 'snapshotFile'.
     * 
     * @param snapshotFile the value of field 'snapshotFile'.
     */
    public void setSnapshotFile(
            final java.lang.String snapshotFile) {
        this._snapshotFile = snapshotFile;
    }

    /**
     * Sets the value of field 'timeAccuracy'.
     * 
     * @param timeAccuracy the value of field 'timeAccuracy'.
     */
    public void setTimeAccuracy(
            final float timeAccuracy) {
        this._timeAccuracy = timeAccuracy;
        this._has_timeAccuracy = true;
    }

    /**
     * Sets the value of field 'vmArgs'.
     * 
     * @param vmArgs the value of field 'vmArgs'.
     */
    public void setVmArgs(
            final java.lang.String vmArgs) {
        this._vmArgs = vmArgs;
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
     * de.lsem.simulation.transformation.anylogic.generator.SimulationExperiment
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.SimulationExperiment unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.SimulationExperiment) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.SimulationExperiment.class, reader);
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
