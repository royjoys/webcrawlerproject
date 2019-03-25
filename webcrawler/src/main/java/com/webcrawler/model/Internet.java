package com.webcrawler.model;

import java.util.List;

public class Internet {
	private List<Page> pages;

	public Internet() {
	}
	
	public Internet(List<Page> pages) {
		this.pages = pages;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Internet [pages=" + pages + "]";
	}
	

}
