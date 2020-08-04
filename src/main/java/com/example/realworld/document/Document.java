package com.example.realworld.document;

import java.util.Map;

public class Document {

	private final Map<String, String> attributes;
	
	protected Document(final Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public String getAttribute(final String attributeName) {
		return attributes.get(attributeName);
	}
}
