package de.lsem.process.io.epml;

import de.lsem.process.io.ProcessModelReader;
import de.lsem.process.model.ProcessModel;

/*
 * Copyright (c) 2013 Christopher Klinkm�ller
 * 
 * This software is released under the terms of the
 * MIT license. See http://opensource.org/licenses/MIT
 * for more information.
 */

/**
 * 
 * @author Christopher Klinkm�ller
 *
 */
public class EpmlReader extends ProcessModelReader {
	private EpmlImporter importer; 
	private EpmlTransformer transformer;
	
	public EpmlReader() {
		this(false, false, false);
	}
	
	public EpmlReader(boolean removeMultipleEntries, boolean removeMultipleExits, boolean removeSilentTransititions) {
		this.importer = new EpmlImporter();
		this.transformer = new EpmlTransformer();
	}
	
	@Override
	protected ProcessModel readModel(String filename) {
		EventDrivenProcessChain epc = this.importer.importEpc(filename);
		ProcessModel process = this.transformer.transform(epc);
		return process;
	}

}
