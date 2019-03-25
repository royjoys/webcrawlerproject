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
		try{
			Internet internet = service.readObjectFromJSON(file);
			process(file, internet.getPages());
		} catch(WebCrawlerException e ){
			System.out.println(e.getMessage());
			throw new WebCrawlerException(e.getMessage());
		}
		return "Success";
	}

	private void process(File f, List<Page> pages) throws WebCrawlerException {
		try {
			String jsonInString ="";
			if(pages.size() > 0){
				 jsonInString = service.formJSON(service
						.getOutputValues(service.formMapFromList(pages)),f);

			} else {
				jsonInString = "empty";
			}
			
			System.out.println("The output for " + f.getName() + " is \n"
					+ jsonInString);
		} catch (WebCrawlerException e) {
			throw new WebCrawlerException(e.getMessage());
		}

	}

}
