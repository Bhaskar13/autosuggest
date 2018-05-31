package com.application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Domain {
	@JsonProperty
    private String name;
	@JsonProperty
	private int rating;
 
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Domain() {
		super();
	}

	public Domain(String name, int rating) {
		super();
		this.name = name;
		this.rating = rating;
	}

}
