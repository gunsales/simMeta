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
 * Class Shapes.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Shapes implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _textList.
     */
	@XmlElement(name="Text")
    private java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.persistant.Text> _textList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Shapes() {
        super();
        this._textList = new java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.persistant.Text>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vText
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addText(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Text vText)
    throws java.lang.IndexOutOfBoundsException {
        this._textList.addElement(vText);
    }

    /**
     * 
     * 
     * @param index
     * @param vText
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addText(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Text vText)
    throws java.lang.IndexOutOfBoundsException {
        this._textList.add(index, vText);
    }

    /**
     * Method enumerateText.
     * 
     * @return an Enumeration over all
     * de.lsem.simulation.transformation.anylogic.generator.Text
     * elements
     */
    public java.util.Enumeration<? extends de.lsem.simulation.transformation.anylogic.generator.persistant.Text> enumerateText(
    ) {
        return this._textList.elements();
    }

    /**
     * Method getText.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * de.lsem.simulation.transformation.anylogic.generator.Text at
     * the given index
     */
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Text getText(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._textList.size()) {
            throw new IndexOutOfBoundsException("getText: Index value '" + index + "' not in range [0.." + (this._textList.size() - 1) + "]");
        }

        return (de.lsem.simulation.transformation.anylogic.generator.persistant.Text) _textList.get(index);
    }

    /**
     * Method getText.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    @XmlTransient
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Text[] getText(
    ) {
        de.lsem.simulation.transformation.anylogic.generator.persistant.Text[] array = new de.lsem.simulation.transformation.anylogic.generator.persistant.Text[0];
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.Text[]) this._textList.toArray(array);
    }

    /**
     * Method getTextCount.
     * 
     * @return the size of this collection
     */
    public int getTextCount(
    ) {
        return this._textList.size();
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
    public void removeAllText(
    ) {
        this._textList.clear();
    }

    /**
     * Method removeText.
     * 
     * @param vText
     * @return true if the object was removed from the collection.
     */
    public boolean removeText(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Text vText) {
        boolean removed = _textList.remove(vText);
        return removed;
    }

    /**
     * Method removeTextAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public de.lsem.simulation.transformation.anylogic.generator.persistant.Text removeTextAt(
            final int index) {
        java.lang.Object obj = this._textList.remove(index);
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.Text) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vText
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setText(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Text vText)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._textList.size()) {
            throw new IndexOutOfBoundsException("setText: Index value '" + index + "' not in range [0.." + (this._textList.size() - 1) + "]");
        }

        this._textList.set(index, vText);
    }

    /**
     * 
     * 
     * @param vTextArray
     */
    public void setText(
            final de.lsem.simulation.transformation.anylogic.generator.persistant.Text[] vTextArray) {
        //-- copy array
        _textList.clear();

        for (int i = 0; i < vTextArray.length; i++) {
                this._textList.add(vTextArray[i]);
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
     * de.lsem.simulation.transformation.anylogic.generator.Shapes
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.Shapes unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.Shapes) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.Shapes.class, reader);
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
