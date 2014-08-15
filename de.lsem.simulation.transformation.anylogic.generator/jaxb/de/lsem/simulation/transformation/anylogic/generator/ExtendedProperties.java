/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package de.lsem.simulation.transformation.anylogic.generator;

/**
 * Class ExtendedProperties.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ExtendedProperties implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _fontExtendedProperties.
     */
    private de.lsem.simulation.transformation.anylogic.generator.FontExtendedProperties _fontExtendedProperties;

    /**
     * Field _labelText.
     */
    private java.lang.String _labelText;

    /**
     * Field _labelCode.
     */
    private java.lang.String _labelCode;


      //----------------/
     //- Constructors -/
    //----------------/

    public ExtendedProperties() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'fontExtendedProperties'.
     * 
     * @return the value of field 'FontExtendedProperties'.
     */
    public de.lsem.simulation.transformation.anylogic.generator.FontExtendedProperties getFontExtendedProperties(
    ) {
        return this._fontExtendedProperties;
    }

    /**
     * Returns the value of field 'labelCode'.
     * 
     * @return the value of field 'LabelCode'.
     */
    public java.lang.String getLabelCode(
    ) {
        return this._labelCode;
    }

    /**
     * Returns the value of field 'labelText'.
     * 
     * @return the value of field 'LabelText'.
     */
    public java.lang.String getLabelText(
    ) {
        return this._labelText;
    }

    /**
     * Sets the value of field 'fontExtendedProperties'.
     * 
     * @param fontExtendedProperties the value of field
     * 'fontExtendedProperties'.
     */
    public void setFontExtendedProperties(
            final de.lsem.simulation.transformation.anylogic.generator.FontExtendedProperties fontExtendedProperties) {
        this._fontExtendedProperties = fontExtendedProperties;
    }

    /**
     * Sets the value of field 'labelCode'.
     * 
     * @param labelCode the value of field 'labelCode'.
     */
    public void setLabelCode(
            final java.lang.String labelCode) {
        this._labelCode = labelCode;
    }

    /**
     * Sets the value of field 'labelText'.
     * 
     * @param labelText the value of field 'labelText'.
     */
    public void setLabelText(
            final java.lang.String labelText) {
        this._labelText = labelText;
    }

}
