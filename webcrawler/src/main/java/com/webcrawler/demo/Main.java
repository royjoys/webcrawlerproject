package com.webcrawler.demo;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.webcrawler.exception.WebCrawlerException;
import com.webcrawler.task.CrawlingTask;
import com.webcrawler.util.WebCrawlerUtil;

public class Main {

	public static void main(String[] args) {
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
			e.printStackTrace();
		} finally {
			ex.shutdown();
			while (!ex.isTerminated()) {
			}
		}
		

	}

}