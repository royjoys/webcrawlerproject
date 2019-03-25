package com.webcrawler.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webcrawler.exception.WebCrawlerException;
import com.webcrawler.model.Internet;
import com.webcrawler.model.Output;
import com.webcrawler.model.Page;
import com.webcrawler.service.WebCrawlerService;

/**
 * @author Soumik.Roy
 *
 */
public class WebCrawlerServiceImpl implements WebCrawlerService {

	private ObjectMapper mapper = null;
	private Set<String> success;
	private Set<String> skipped;
	private Set<String> error;
	private Map<String, List<String>> collect;

	@Override
	public Internet readObjectFromJSON(File f) throws WebCrawlerException {
		mapper = new ObjectMapper();
		Internet internet = null;
		try {
			internet = mapper.readValue(f, Internet.class);
		} catch (JsonParseException e) {
			throw new WebCrawlerException("Parsing of JSON Failed for "+f.getName()+"==>"+e.getMessage());
		} catch (JsonMappingException e) {
			throw new WebCrawlerException("Mapping of JSON Failed for "+f.getName()+"==>"+e.getMessage());
		} catch (IOException e) {
			throw new WebCrawlerException("IOException for "+f.getName()+"==>"+e.getMessage());
		}
		return internet;

	}

	@Override
	public Map<String, List<String>> formMapFromList(List<Page> pages) {
		return pages.stream().collect(
				Collectors.toMap(Page::getAddress, Page::getLinks,
						(e1, e2) -> e1, LinkedHashMap::new));
	}

	@Override
	public Output getOutputValues(Map<String, List<String>> input) {
		initialize();
		collect = input;
		crawl(collect.keySet().iterator().next());
		return new Output(success, skipped, error);

	}

	@Override
	public String formJSON(Output output, File f) throws WebCrawlerException {
		String jsonFormattedString = "";
		mapper = new ObjectMapper();
		try {
			jsonFormattedString = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(output);
		} catch (JsonProcessingException e) {
			throw new WebCrawlerException("Mapping of JSON Failed for "+f.getName()+"==>"+e.getMessage());
		}
		return jsonFormattedString;
	}

	private void initialize() {
		success = new LinkedHashSet<String>();
		skipped = new LinkedHashSet<String>();
		error = new LinkedHashSet<String>();
	}

	/**
	 * @param url
	 * recursively calls the method to 
	 * populate success 
	 */
	private void crawl(String url) {
		if (collect.keySet().contains(url)) {
			if (success.add(url)) {
				//success.add(url);
				for (String eachLink : collect.get(url)) {
					crawl(eachLink);
				}
			} else {
				skipped.add(url);
			}
		} else {
			error.add(url);
		}
	}

}
