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
 * Class RequiredLibraryReference.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class RequiredLibraryReference implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _libraryName.
     */
    @XmlElement(name="LibraryName")
    private java.lang.String _libraryName;

    /**
     * Field _versionMajor.
     */
    @XmlElement(name="VersionMajor")
    private int _versionMajor;

    /**
     * keeps track of state for field: _versionMajor
     */
    private boolean _has_versionMajor;

    /**
     * Field _versionMinor.
     */
    @XmlElement(name="VersionMinor")
    private int _versionMinor;

    /**
     * keeps track of state for field: _versionMinor
     */
    private boolean _has_versionMinor;

    /**
     * Field _versionBuild.
     */
    @XmlElement(name="VersionBuild")
    private int _versionBuild;

    /**
     * keeps track of state for field: _versionBuild
     */
    private boolean _has_versionBuild;


      //----------------/
     //- Constructors -/
    //----------------/

    public RequiredLibraryReference() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteVersionBuild(
    ) {
        this._has_versionBuild= false;
    }

    /**
     */
    public void deleteVersionMajor(
    ) {
        this._has_versionMajor= false;
    }

    /**
     */
    public void deleteVersionMinor(
    ) {
        this._has_versionMinor= false;
    }

    /**
     * Returns the value of field 'libraryName'.
     * 
     * @return the value of field 'LibraryName'.
     */
    @XmlTransient
    public java.lang.String getLibraryName(
    ) {
        return this._libraryName;
    }

    /**
     * Returns the value of field 'versionBuild'.
     * 
     * @return the value of field 'VersionBuild'.
     */
    @XmlTransient
    public int getVersionBuild(
    ) {
        return this._versionBuild;
    }

    /**
     * Returns the value of field 'versionMajor'.
     * 
     * @return the value of field 'VersionMajor'.
     */
    @XmlTransient
    public int getVersionMajor(
    ) {
        return this._versionMajor;
    }

    /**
     * Returns the value of field 'versionMinor'.
     * 
     * @return the value of field 'VersionMinor'.
     */
    @XmlTransient
    public int getVersionMinor(
    ) {
        return this._versionMinor;
    }

    /**
     * Method hasVersionBuild.
     * 
     * @return true if at least one VersionBuild has been added
     */
    public boolean hasVersionBuild(
    ) {
        return this._has_versionBuild;
    }

    /**
     * Method hasVersionMajor.
     * 
     * @return true if at least one VersionMajor has been added
     */
    public boolean hasVersionMajor(
    ) {
        return this._has_versionMajor;
    }

    /**
     * Method hasVersionMinor.
     * 
     * @return true if at least one VersionMinor has been added
     */
    public boolean hasVersionMinor(
    ) {
        return this._has_versionMinor;
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
     * Sets the value of field 'libraryName'.
     * 
     * @param libraryName the value of field 'libraryName'.
     */
    public void setLibraryName(
            final java.lang.String libraryName) {
        this._libraryName = libraryName;
    }

    /**
     * Sets the value of field 'versionBuild'.
     * 
     * @param versionBuild the value of field 'versionBuild'.
     */
    public void setVersionBuild(
            final int versionBuild) {
        this._versionBuild = versionBuild;
        this._has_versionBuild = true;
    }

    /**
     * Sets the value of field 'versionMajor'.
     * 
     * @param versionMajor the value of field 'versionMajor'.
     */
    public void setVersionMajor(
            final int versionMajor) {
        this._versionMajor = versionMajor;
        this._has_versionMajor = true;
    }

    /**
     * Sets the value of field 'versionMinor'.
     * 
     * @param versionMinor the value of field 'versionMinor'.
     */
    public void setVersionMinor(
            final int versionMinor) {
        this._versionMinor = versionMinor;
        this._has_versionMinor = true;
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
     * de.lsem.simulation.transformation.anylogic.generator.RequiredLibraryReference
     */
    public static de.lsem.simulation.transformation.anylogic.generator.persistant.RequiredLibraryReference unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (de.lsem.simulation.transformation.anylogic.generator.persistant.RequiredLibraryReference) org.exolab.castor.xml.Unmarshaller.unmarshal(de.lsem.simulation.transformation.anylogic.generator.persistant.RequiredLibraryReference.class, reader);
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
