package com.webcrawler.demo;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.webcrawler.exception.WebCrawlerException;
import com.webcrawler.task.CrawlingTask;

public class Main {

	public static void main(String[] args) {
		ExecutorService ex = null;
		try {
			File folder = new File("C:\\testcases");
			File[] files = folder.listFiles();
			ex = Executors.newFixedThreadPool(5);
			for (File file : files) {
				CrawlingTask task = new CrawlingTask(file);
				ex.submit(task);
			}
		} catch (WebCrawlerException e) {
			System.out.println(e.getMessage());
		} finally {
			ex.shutdown();
			while (!ex.isTerminated()) {
			}
		}
		

	}

}