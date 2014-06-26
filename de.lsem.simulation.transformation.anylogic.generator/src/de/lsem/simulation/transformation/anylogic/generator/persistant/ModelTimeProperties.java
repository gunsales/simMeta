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
 * Class ModelTimeProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ModelTimeProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _useCalendar.
     */
	@XmlAttribute(name="UseCalendar")
    private java.lang.String _useCalendar;

    /**
     * Field _stopOption.
     */
	@XmlElement(name="StopOption")
    private java.lang.String _stopOption;

    /**
     * Field _initialDate.
     */
	@XmlElement(name="InitialDate")
    private java.lang.String _initialDate;

    /**
     * Field _initialTime.
     */
	@XmlElement(name="InitialTime")
    private java.lang.String _initialTime;

    /**
     * Field _finalDate.
     */
	@XmlElement(name="FinalDate")
    private java.lang.String _finalDate;

    /**
     * Field _finalTime.
     */
	@XmlElement(name="FinalTime")
    private java.lang.String _finalTime;


      //----------------/
     //- Constructors -/
    //----------------/

    public ModelTimeProperties() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'finalDate'.
     * 
     * @return the value of field 'FinalDate'.
     */
    @XmlTransient
    public java.lang.String getFinalDate(
    ) {
        return this._finalDate;
    }

    /**
     * Returns the value of field 'finalTime'.
     * 
     * @return the value of field 'FinalTime'.
     */
    @XmlTransient
    public java.lang.String getFinalTime(
    ) {
        return this._finalTime;
    }

    /**
     * Returns the value of field 'initialDate'.
     * 
     * @return the value of field 'InitialDate'.
     */
    @XmlTransient
    public java.lang.String getInitialDate(
    ) {
        return this._initialDate;
    }

    /**
     * Returns the value of field 'initialTime'.
     * 
     * @return the value of field 'InitialTime'.
     */
    @XmlTransient
    public java.lang.String getInitialTime(
    ) {
        return this._initialTime;
    }

    /**
     * Returns the value of field 'stopOption'.
     * 
     * @return the value of field 'StopOption'.
     */
    @XmlTransient
    public java.lang.String getStopOption(
    ) {
        return this._stopOption;
    }

    /**
     * Returns the value of field 'useCalendar'.
     * 
     * @return the value of field 'UseCalendar'.
     */
    @XmlTransient
    public java.lang.String getUseCalendar(
    ) {
        return this._useCalendar;
    }

    /**
     * Sets the value of field 'finalDate'.
     * 
     * @param finalDate the value of field 'finalDate'.
     */
    public void setFinalDate(
            final java.lang.String finalDate) {
        this._finalDate = finalDate;
    }

    /**
     * Sets the value of field 'finalTime'.
     * 
     * @param finalTime the value of field 'finalTime'.
     */
    public void setFinalTime(
            final java.lang.String finalTime) {
        this._finalTime = finalTime;
    }

    /**
     * Sets the value of field 'initialDate'.
     * 
     * @param initialDate the value of field 'initialDate'.
     */
    public void setInitialDate(
            final java.lang.String initialDate) {
        this._initialDate = initialDate;
    }

    /**
     * Sets the value of field 'initialTime'.
     * 
     * @param initialTime the value of field 'initialTime'.
     */
    public void setInitialTime(
            final java.lang.String initialTime) {
        this._initialTime = initialTime;
    }

    /**
     * Sets the value of field 'stopOption'.
     * 
     * @param stopOption the value of field 'stopOption'.
     */
    public void setStopOption(
            final java.lang.String stopOption) {
        this._stopOption = stopOption;
    }

    /**
     * Sets the value of field 'useCalendar'.
     * 
     * @param useCalendar the value of field 'useCalendar'.
     */
    public void setUseCalendar(
            final java.lang.String useCalendar) {
        this._useCalendar = useCalendar;
    }
}
