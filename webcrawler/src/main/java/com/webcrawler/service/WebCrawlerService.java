package com.webcrawler.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.webcrawler.exception.WebCrawlerException;
import com.webcrawler.model.Internet;
import com.webcrawler.model.Output;
import com.webcrawler.model.Page;

/**
 * @author Soumik.Roy
 *
 */
public interface WebCrawlerService {


	Map<String, List<String>> formMapFromList(List<Page> pages);

	Output getOutputValues(Map<String, List<String>> input);

	String formJSON(Output output, File f) throws WebCrawlerException;

	Internet readObjectFromJSON(File f) throws WebCrawlerException;

}
