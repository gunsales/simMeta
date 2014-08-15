/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class EmbeddedObjects.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class EmbeddedObjects implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _embeddedObjectList.
     */
    private java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject> _embeddedObjectList;


      //----------------/
     //- Constructors -/
    //----------------/

    public EmbeddedObjects() {
        super();
        this._embeddedObjectList = new java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vEmbeddedObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEmbeddedObject(
            final de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject vEmbeddedObject)
    throws java.lang.IndexOutOfBoundsException {
        this._embeddedObjectList.addElement(vEmbeddedObject);
    }

    /**
     * 
     * 
     * @param index
     * @param vEmbeddedObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addEmbeddedObject(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject vEmbeddedObject)
    throws java.lang.IndexOutOfBoundsException {
        this._embeddedObjectList.add(index, vEmbeddedObject);
    }

    /**
     * Method enumerateEmbeddedObject.
     * 
     * @return an Enumeration over all
     * de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject
     * elements
     */
    public java.util.Enumeration<? extends de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject> enumerateEmbeddedObject(
    ) {
        return this._embeddedObjectList.elements();
    }

    /**
     * Method getEmbeddedObject.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject
     * at the given index
     */
    public de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject getEmbeddedObject(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._embeddedObjectList.size()) {
            throw new IndexOutOfBoundsException("getEmbeddedObject: Index value '" + index + "' not in range [0.." + (this._embeddedObjectList.size() - 1) + "]");
        }

        return (de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject) _embeddedObjectList.get(index);
    }

    /**
     * Method getEmbeddedObject.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject[] getEmbeddedObject(
    ) {
        de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject[] array = new de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject[0];
        return (de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject[]) this._embeddedObjectList.toArray(array);
    }

    /**
     * Method getEmbeddedObjectCount.
     * 
     * @return the size of this collection
     */
    public int getEmbeddedObjectCount(
    ) {
        return this._embeddedObjectList.size();
    }

    /**
     */
    public void removeAllEmbeddedObject(
    ) {
        this._embeddedObjectList.clear();
    }

    /**
     * Method removeEmbeddedObject.
     * 
     * @param vEmbeddedObject
     * @return true if the object was removed from the collection.
     */
    public boolean removeEmbeddedObject(
            final de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject vEmbeddedObject) {
        boolean removed = _embeddedObjectList.remove(vEmbeddedObject);
        return removed;
    }

    /**
     * Method removeEmbeddedObjectAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject removeEmbeddedObjectAt(
            final int index) {
        java.lang.Object obj = this._embeddedObjectList.remove(index);
        return (de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vEmbeddedObject
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setEmbeddedObject(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject vEmbeddedObject)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._embeddedObjectList.size()) {
            throw new IndexOutOfBoundsException("setEmbeddedObject: Index value '" + index + "' not in range [0.." + (this._embeddedObjectList.size() - 1) + "]");
        }

        this._embeddedObjectList.set(index, vEmbeddedObject);
    }

    /**
     * 
     * 
     * @param vEmbeddedObjectArray
     */
    public void setEmbeddedObject(
            final de.lsem.simulation.transformation.anylogic.generator.EmbeddedObject[] vEmbeddedObjectArray) {
        //-- copy array
        _embeddedObjectList.clear();

        for (int i = 0; i < vEmbeddedObjectArray.length; i++) {
                this._embeddedObjectList.add(vEmbeddedObjectArray[i]);
        }
    }

}
