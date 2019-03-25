package com.webcrawler.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.webcrawler.exception.WebCrawlerException;

public class WebCrawlerUtil {

	private final static String PROPERTIES_FILE = "conf.properties";
	
	public static String getProperties(String key) {

		return new WebCrawlerUtil().readKey(PROPERTIES_FILE, key);
	}

	private String readKey(String propertiesFilename, String key) throws WebCrawlerException{
		if (propertiesFilename != null && !propertiesFilename.trim().isEmpty()
				&& key != null && !key.trim().isEmpty()) {
			ClassLoader objClassLoader = getClass().getClassLoader();
			Properties commonProperties = new Properties();
			try (FileInputStream objFileInputStream = new FileInputStream(
					objClassLoader.getResource(propertiesFilename).getFile());) {
				commonProperties.load(objFileInputStream);
				return String.valueOf(commonProperties.get(key));
			} catch (FileNotFoundException ex) {
				throw new WebCrawlerException();
			} catch (IOException ex) {
				throw new WebCrawlerException();
			}
		}
		return null;
	}
}
