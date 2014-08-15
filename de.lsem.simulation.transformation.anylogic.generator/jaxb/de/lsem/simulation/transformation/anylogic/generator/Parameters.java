/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class Parameters.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Parameters implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _parameterList.
     */
    private java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.Parameter> _parameterList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Parameters() {
        super();
        this._parameterList = new java.util.Vector<de.lsem.simulation.transformation.anylogic.generator.Parameter>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vParameter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addParameter(
            final de.lsem.simulation.transformation.anylogic.generator.Parameter vParameter)
    throws java.lang.IndexOutOfBoundsException {
        this._parameterList.addElement(vParameter);
    }

    /**
     * 
     * 
     * @param index
     * @param vParameter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addParameter(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.Parameter vParameter)
    throws java.lang.IndexOutOfBoundsException {
        this._parameterList.add(index, vParameter);
    }

    /**
     * Method enumerateParameter.
     * 
     * @return an Enumeration over all
     * de.lsem.simulation.transformation.anylogic.generator.Parameter
     * elements
     */
    public java.util.Enumeration<? extends de.lsem.simulation.transformation.anylogic.generator.Parameter> enumerateParameter(
    ) {
        return this._parameterList.elements();
    }

    /**
     * Method getParameter.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * de.lsem.simulation.transformation.anylogic.generator.Parameter
     * at the given index
     */
    public de.lsem.simulation.transformation.anylogic.generator.Parameter getParameter(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._parameterList.size()) {
            throw new IndexOutOfBoundsException("getParameter: Index value '" + index + "' not in range [0.." + (this._parameterList.size() - 1) + "]");
        }

        return (de.lsem.simulation.transformation.anylogic.generator.Parameter) _parameterList.get(index);
    }

    /**
     * Method getParameter.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public de.lsem.simulation.transformation.anylogic.generator.Parameter[] getParameter(
    ) {
        de.lsem.simulation.transformation.anylogic.generator.Parameter[] array = new de.lsem.simulation.transformation.anylogic.generator.Parameter[0];
        return (de.lsem.simulation.transformation.anylogic.generator.Parameter[]) this._parameterList.toArray(array);
    }

    /**
     * Method getParameterCount.
     * 
     * @return the size of this collection
     */
    public int getParameterCount(
    ) {
        return this._parameterList.size();
    }

    /**
     */
    public void removeAllParameter(
    ) {
        this._parameterList.clear();
    }

    /**
     * Method removeParameter.
     * 
     * @param vParameter
     * @return true if the object was removed from the collection.
     */
    public boolean removeParameter(
            final de.lsem.simulation.transformation.anylogic.generator.Parameter vParameter) {
        boolean removed = _parameterList.remove(vParameter);
        return removed;
    }

    /**
     * Method removeParameterAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public de.lsem.simulation.transformation.anylogic.generator.Parameter removeParameterAt(
            final int index) {
        java.lang.Object obj = this._parameterList.remove(index);
        return (de.lsem.simulation.transformation.anylogic.generator.Parameter) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vParameter
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setParameter(
            final int index,
            final de.lsem.simulation.transformation.anylogic.generator.Parameter vParameter)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._parameterList.size()) {
            throw new IndexOutOfBoundsException("setParameter: Index value '" + index + "' not in range [0.." + (this._parameterList.size() - 1) + "]");
        }

        this._parameterList.set(index, vParameter);
    }

    /**
     * 
     * 
     * @param vParameterArray
     */
    public void setParameter(
            final de.lsem.simulation.transformation.anylogic.generator.Parameter[] vParameterArray) {
        //-- copy array
        _parameterList.clear();

        for (int i = 0; i < vParameterArray.length; i++) {
                this._parameterList.add(vParameterArray[i]);
        }
    }

}
