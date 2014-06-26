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
 * Class Experiments.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Experiments implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * ========= Simulation Experiment ========
     *  
     */
	@XmlElement(name="SimulationExperiment")
    private de.lsem.simulation.transformation.anylogic.generator.persistant.SimulationExperiment _simulationExperiment;


      //----------------/
     //- Constructors -/
    //----------------/

    public Experiments() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'simulationExperiment'. The field
     * 'simulationExperiment' has the following description:
     * ========= Simulation Experiment ========
     *  
     * 
     * @return the value of field 'SimulationExperiment'.
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.SimulationExperiment getSimulationExperiment(
    ) {
        return this._simulationExperiment;
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
     * Sets the value of field 'simulationExperiment'. The field
     * 'simulationExperiment' has the following description:
     * ========= Simulation Experiment ========
     *  
     * 
     * @param simulationExperiment the value of field
     * 'simulationExperiment'.
     */
    public void setSimulationExperiment(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.SimulationExperiment simulationExperiment) {
        this._simulationExperiment = simulationExperiment;
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
     * de.lsem.simulation.transformation.anylogic.generator.Experiments
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.Experiments unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.Experiments) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.Experiments.class, reader);
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
