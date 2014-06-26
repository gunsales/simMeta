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
	@XmlElement(name="EmbeddedObject")
    private java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject> _embeddedObjectList;


      //----------------/
     //- Constructors -/
    //----------------/

    public EmbeddedObjects() {
        super();
        this._embeddedObjectList = new java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject>();
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject vEmbeddedObject)
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject vEmbeddedObject)
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
    public java.util.Enumeration<? extends de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject> enumerateEmbeddedObject(
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
    public de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject getEmbeddedObject(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._embeddedObjectList.size()) {
            throw new IndexOutOfBoundsException("getEmbeddedObject: Index value '" + index + "' not in range [0.." + (this._embeddedObjectList.size() - 1) + "]");
        }

        return (de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject) _embeddedObjectList.get(index);
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
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject[] getEmbeddedObject(
    ) {
        de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject[] array = new de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject[0];
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject[]) this._embeddedObjectList.toArray(array);
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject vEmbeddedObject) {
        boolean removed = _embeddedObjectList.remove(vEmbeddedObject);
        return removed;
    }

    /**
     * Method removeEmbeddedObjectAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject removeEmbeddedObjectAt(
            final int index) {
        java.lang.Object obj = this._embeddedObjectList.remove(index);
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject) obj;
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject vEmbeddedObject)
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
            final de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject[] vEmbeddedObjectArray) {
        //-- copy array
        _embeddedObjectList.clear();

        for (int i = 0; i < vEmbeddedObjectArray.length; i++) {
                this._embeddedObjectList.add(vEmbeddedObjectArray[i]);
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
     * de.lsem.simulation.transformation.anylogic.generator.EmbeddedObjects
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObjects unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObjects) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObjects.class, reader);
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
