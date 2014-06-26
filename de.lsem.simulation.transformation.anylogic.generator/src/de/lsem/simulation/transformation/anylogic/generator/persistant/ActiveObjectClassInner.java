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
 * Class ActiveObjectClassInner.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ActiveObjectClassInner implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _packageName.
     */
	@XmlElement(name="PackageName")
    private java.lang.String _packageName;

    /**
     * Field _className.
     */
	@XmlElement(name="ClassName")
    private java.lang.String _className;


      //----------------/
     //- Constructors -/
    //----------------/

    public ActiveObjectClassInner() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'className'.
     * 
     * @return the value of field 'ClassName'.
     */
    @XmlTransient
    public java.lang.String getClassName(
    ) {
        return this._className;
    }

    /**
     * Returns the value of field 'packageName'.
     * 
     * @return the value of field 'PackageName'.
     */
    @XmlTransient
    public java.lang.String getPackageName(
    ) {
        return this._packageName;
    }

    /**
     * Sets the value of field 'className'.
     * 
     * @param className the value of field 'className'.
     */
    public void setClassName(
            final java.lang.String className) {
        this._className = className;
    }

    /**
     * Sets the value of field 'packageName'.
     * 
     * @param packageName the value of field 'packageName'.
     */
    public void setPackageName(
            final java.lang.String packageName) {
        this._packageName = packageName;
    }


}
