package de.lsem.simulation.transformation.anylogic.transform.preparedObjects;

import de.lsem.simulation.transformation.anylogic.generator.persistant.ActiveObjectClassInner;
import static de.lsem.simulation.transformation.anylogic.transform.xtend.helper.NamingHelper.*;

public class ActiveObjectClassInnerImpl extends ActiveObjectClassInner {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4625611532338698336L;

	public ActiveObjectClassInnerImpl(String type) {
		setPackageName(wrapCDATA("[com.xj.anylogic.libraries.enterprise]]"));
		setClassName(wrapCDATA(type));
	}
}
