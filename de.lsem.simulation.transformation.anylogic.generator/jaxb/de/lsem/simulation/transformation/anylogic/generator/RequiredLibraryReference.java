/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

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
    private java.lang.String _libraryName;

    /**
     * Field _versionMajor.
     */
    private int _versionMajor;

    /**
     * keeps track of state for field: _versionMajor
     */
    private boolean _has_versionMajor;

    /**
     * Field _versionMinor.
     */
    private int _versionMinor;

    /**
     * keeps track of state for field: _versionMinor
     */
    private boolean _has_versionMinor;

    /**
     * Field _versionBuild.
     */
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
    public java.lang.String getLibraryName(
    ) {
        return this._libraryName;
    }

    /**
     * Returns the value of field 'versionBuild'.
     * 
     * @return the value of field 'VersionBuild'.
     */
    public int getVersionBuild(
    ) {
        return this._versionBuild;
    }

    /**
     * Returns the value of field 'versionMajor'.
     * 
     * @return the value of field 'VersionMajor'.
     */
    public int getVersionMajor(
    ) {
        return this._versionMajor;
    }

    /**
     * Returns the value of field 'versionMinor'.
     * 
     * @return the value of field 'VersionMinor'.
     */
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

}
