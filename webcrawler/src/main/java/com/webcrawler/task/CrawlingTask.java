package com.webcrawler.task;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import com.webcrawler.exception.WebCrawlerException;
import com.webcrawler.model.Internet;
import com.webcrawler.model.Page;
import com.webcrawler.service.WebCrawlerService;
import com.webcrawler.service.impl.WebCrawlerServiceImpl;

public class CrawlingTask implements Callable<String> {

	File file;

	WebCrawlerService service = new WebCrawlerServiceImpl();

	public CrawlingTask(File file) {
		this.file = file;
	}

	public String call() throws Exception {
		Internet internet = service.readObjectFromJSON(file);
		process(file, internet.getPages());
		return "Success";
	}

	private void process(File f, List<Page> pages) {
		try {
			String jsonInString = service.formJSON(service
					.getOutputValues(service.formMapFromList(pages)));

			System.out.println("The output for " + f.getName() + " is \n"
					+ jsonInString);
		} catch (WebCrawlerException e) {
			throw new WebCrawlerException(e.getMessage());
		}

	}

}
