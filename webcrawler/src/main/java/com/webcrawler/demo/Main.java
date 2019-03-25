package com.webcrawler.demo;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.webcrawler.exception.WebCrawlerException;
import com.webcrawler.task.CrawlingTask;
import com.webcrawler.util.WebCrawlerUtil;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		logger.info("test");
		ExecutorService ex = null;
		try {
			File folder = new File(WebCrawlerUtil.getProperties("web-crawler.input"));
			File[] files = folder.listFiles();
			ex = Executors.newFixedThreadPool(Integer.parseInt(WebCrawlerUtil.getProperties("web-crawler.noOfThreads")));
			for (File file : files) {
				CrawlingTask task = new CrawlingTask(file);
				ex.submit(task);
			}
		} catch (WebCrawlerException e) {
			logger.error(e.getMessage());
		} finally {
			ex.shutdown();
			while (!ex.isTerminated()) {
			}
		}
		

	}

}