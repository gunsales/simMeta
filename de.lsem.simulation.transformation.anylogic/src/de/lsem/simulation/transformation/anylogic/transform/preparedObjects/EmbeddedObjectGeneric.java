package de.lsem.simulation.transformation.anylogic.transform.preparedObjects;

import de.lsem.simulation.transformation.anylogic.generator.persistant.EmbeddedObject;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Parameter;
import de.lsem.simulation.transformation.anylogic.generator.persistant.Parameters;

public class EmbeddedObjectGeneric extends EmbeddedObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3367338524839538580L;
	
	/**
	 * Creates an empty parameter in the form <name = @param, value = (empty)>
	 * wrapped into <![CDATA[]]>
	 * @param name key of the parameter
	 * @return the created empty parameter
	 */
	public static Parameter createEmptyParameter(String name) {
//		System.out.println(">> Adding parameter: " + name);
		Parameter parameter;
		parameter = new Parameter();
		parameter.setName("<![CDATA[" + name + "]]>");
		parameter.setValue("<![CDATA[]]>");
		return parameter;
	}
	
	public static Parameter createParameter(String name, String value) {
//		System.out.println(">> Adding parameter: " + name);
		Parameter parameter;
		parameter = new Parameter();
		parameter.setName("<![CDATA[" + name + "]]>");
		parameter.setValue("<![CDATA[" + value + "]]>");
		return parameter;
	}
	
	public static void main(String[] args) {
		EmbeddedObjectGeneric gen = new EmbeddedObjectGeneric();
		Parameters p = new Parameters();
		gen.setParameters(p);
		gen.getParameters().addParameter(createEmptyParameter("TEST_CASE"));
		gen.getParameters().addParameter(createParameter("TEST_CASE", "TEST"));
//		System.out.println(gen);
	}

	@Override
	public String toString() {
		return "EmbeddedObjectGeneric [getCollectionType()="
				+ getCollectionType() + ", getGenericParametersSubstitute()="
				+ getGenericParametersSubstitute() + ", getId()=" + getId()
				+ ", getLabel()=" + getLabel() + ", getName()=" + getName()
				+ ", getParameters()=" + getParameters()
				+ ", getPresentationFlag()=" + getPresentationFlag()
				+ ", getPublicFlag()=" + getPublicFlag() + ", getShowLabel()="
				+ getShowLabel() + ", getX()=" + getX() + ", getY()=" + getY()
				+ ", hasId()=" + hasId() + ", hasX()=" + hasX() + ", hasY()="
				+ hasY() + ", getActiveObjectClassInner()="
				+ getActiveObjectClassInner() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
