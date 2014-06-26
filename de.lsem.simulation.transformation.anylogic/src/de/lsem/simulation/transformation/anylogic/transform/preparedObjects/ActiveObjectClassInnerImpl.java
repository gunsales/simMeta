package de.lsem.simulation.transformation.anylogic.transform.preparedObjects;

import de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClassInner;

public class ActiveObjectClassInnerImpl extends ActiveObjectClassInner {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4625611532338698336L;

	public ActiveObjectClassInnerImpl(String type) {
		setPackageName("<![CDATA[com.xj.anylogic.libraries.enterprise]]>");
		setClassName("<![CDATA["+type+"]]>");
	}
}
