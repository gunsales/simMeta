package de.lsem.config;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;

/*
 * Copyright (c) 2013 Christopher Klinkmüller
 * 
 * This software is released under the terms of the
 * MIT license. See http://opensource.org/licenses/MIT
 * for more information.
 */

/**
 * 
 * @author Christopher Klinkmüller
 * 
 */
public final class Configuration {
	private List<String> stopWords;
	private String wordNetFolder;
	private IDictionary dictionary;
	private static String configurationFile = "D:/EclipseWorkspace/simulation/jpmmt/src/main/resources/config.xml";

	public static final Configuration INSTANCE = new Configuration();

	private Configuration() {
		this.stopWords = new ArrayList<String>();
		try {
			File configFile = new File(configurationFile);
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(configFile);
			document.getDocumentElement().normalize();

			NodeList stopWordNodes = document.getElementsByTagName("StopWord");
			for (int a = 0; a < stopWordNodes.getLength(); a++) {
				Element stopWord = (Element) stopWordNodes.item(a);
				this.stopWords.add(stopWord.getTextContent());
			}

			NodeList wordNetNode = document.getElementsByTagName("WordNet");
			this.wordNetFolder = ((Element) wordNetNode.item(0))
					.getAttribute("dictionaryFolder");

			URL url = new URL("file", null, this.wordNetFolder);
			this.dictionary = new Dictionary(url);
			this.dictionary.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.dictionary.close();
	}

	public Collection<String> getStopWords() {
		return this.stopWords;
	}

	public String getWordNetFolder() {
		return this.wordNetFolder;
	}

	public IDictionary getWordNetDictionary() {
		return this.dictionary;
	}

	public static void main(String[] args) {
		Collection<String> words = Configuration.INSTANCE.getStopWords();
//		System.out.println(words);
	}
}
