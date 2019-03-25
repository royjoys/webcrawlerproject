package com.webcrawler.model;

import java.util.Set;

public class Output {
	private Set<String> success; 
	private Set<String> skipped; 
	private Set<String> error;
	
	public Output() {
	}
	public Output(Set<String> success, Set<String> skipped, Set<String> error) {
		this.success = success;
		this.skipped = skipped;
		this.error = error;
	}
	public Set<String> getSuccess() {
		return success;
	}
	public void setSuccess(Set<String> success) {
		this.success = success;
	}
	public Set<String> getSkipped() {
		return skipped;
	}
	public void setSkipped(Set<String> skipped) {
		this.skipped = skipped;
	}
	public Set<String> getError() {
		return error;
	}
	public void setError(Set<String> error) {
		this.error = error;
	} 

}
