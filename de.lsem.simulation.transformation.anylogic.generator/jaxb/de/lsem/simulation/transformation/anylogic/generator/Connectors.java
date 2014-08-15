/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class Connectors.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Connectors implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _connectorList.
     */
    private java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.Connector> _connectorList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Connectors() {
        super();
        this._connectorList = new java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.Connector>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vConnector
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addConnector(
            final de.lsem.simulation.transformation.anylogic.generator.Connector vConnector)
    throws java.lang.IndexOutOfBoundsException {
        this._connectorList.addElement(vConnector);
    }

    /**
     * 
     * 
     * @param index
     * @param vConnector
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addConnector(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.Connector vConnector)
    throws java.lang.IndexOutOfBoundsException {
        this._connectorList.add(index, vConnector);
    }

    /**
     * Method enumerateConnector.
     * 
     * @return an Enumeration over all
     * de.lsem.simulation.transformation.anylogic.generator.Connector
     * elements
     */
    public java.util.Enumeration<? extends de.lsem.simulation.transformation.anylogic.generator.Connector> enumerateConnector(
    ) {
        return this._connectorList.elements();
    }

    /**
     * Method getConnector.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * de.lsem.simulation.transformation.anylogic.generator.Connector
     * at the given index
     */
    public de.lsem.simulation.transformation.anylogic.generator.Connector getConnector(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._connectorList.size()) {
            throw new IndexOutOfBoundsException("getConnector: Index value '" + index + "' not in range [0.." + (this._connectorList.size() - 1) + "]");
        }

        return (de.lsem.simulation.transformation.anylogic.generator.Connector) _connectorList.get(index);
    }

    /**
     * Method getConnector.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public de.lsem.simulation.transformation.anylogic.generator.Connector[] getConnector(
    ) {
        de.lsem.simulation.transformation.anylogic.generator.Connector[] array = new de.lsem.simulation.transformation.anylogic.generator.Connector[0];
        return (de.lsem.simulation.transformation.anylogic.generator.Connector[]) this._connectorList.toArray(array);
    }

    /**
     * Method getConnectorCount.
     * 
     * @return the size of this collection
     */
    public int getConnectorCount(
    ) {
        return this._connectorList.size();
    }

    /**
     */
    public void removeAllConnector(
    ) {
        this._connectorList.clear();
    }

    /**
     * Method removeConnector.
     * 
     * @param vConnector
     * @return true if the object was removed from the collection.
     */
    public boolean removeConnector(
            final de.lsem.simulation.transformation.anylogic.generator.Connector vConnector) {
        boolean removed = _connectorList.remove(vConnector);
        return removed;
    }

    /**
     * Method removeConnectorAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public de.lsem.simulation.transformation.anylogic.generator.Connector removeConnectorAt(
            final int index) {
        java.lang.Object obj = this._connectorList.remove(index);
        return (de.lsem.simulation.transformation.anylogic.generator.Connector) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vConnector
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setConnector(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.Connector vConnector)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._connectorList.size()) {
            throw new IndexOutOfBoundsException("setConnector: Index value '" + index + "' not in range [0.." + (this._connectorList.size() - 1) + "]");
        }

        this._connectorList.set(index, vConnector);
    }

    /**
     * 
     * 
     * @param vConnectorArray
     */
    public void setConnector(
            final de.lsem.simulation.transformation.anylogic.generator.Connector[] vConnectorArray) {
        //-- copy array
        _connectorList.clear();

        for (int i = 0; i < vConnectorArray.length; i++) {
                this._connectorList.add(vConnectorArray[i]);
        }
    }

}
