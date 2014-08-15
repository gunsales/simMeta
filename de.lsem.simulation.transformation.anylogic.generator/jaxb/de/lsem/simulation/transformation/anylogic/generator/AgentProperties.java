/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class AgentProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class AgentProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _spaceType.
     */
    private java.lang.String _spaceType;

    /**
     * Field _environmentDefinesInitialLocation.
     */
    private java.lang.String _environmentDefinesInitialLocation;


      //----------------/
     //- Constructors -/
    //----------------/

    public AgentProperties() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field
     * 'environmentDefinesInitialLocation'.
     * 
     * @return the value of field
     * 'EnvironmentDefinesInitialLocation'.
     */
    public java.lang.String getEnvironmentDefinesInitialLocation(
    ) {
        return this._environmentDefinesInitialLocation;
    }

    /**
     * Returns the value of field 'spaceType'.
     * 
     * @return the value of field 'SpaceType'.
     */
    public java.lang.String getSpaceType(
    ) {
        return this._spaceType;
    }

    /**
     * Sets the value of field 'environmentDefinesInitialLocation'.
     * 
     * @param environmentDefinesInitialLocation the value of field
     * 'environmentDefinesInitialLocation'.
     */
    public void setEnvironmentDefinesInitialLocation(
            final java.lang.String environmentDefinesInitialLocation) {
        this._environmentDefinesInitialLocation = environmentDefinesInitialLocation;
    }

    /**
     * Sets the value of field 'spaceType'.
     * 
     * @param spaceType the value of field 'spaceType'.
     */
    public void setSpaceType(
            final java.lang.String spaceType) {
        this._spaceType = spaceType;
    }

}
