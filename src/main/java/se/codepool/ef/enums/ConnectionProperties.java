package se.codepool.ef.enums;

public enum ConnectionProperties {
	DB("db", "Database name"), 
	URL("url", "Database URL"), 
	PASS("pass", "Database password"), 
	USER("user", "Database username"),
	DRIVER("driver", "Database driver");
	// stores the value for enum that represents the property will be passed to JPA
	private final String value; 
	// stores the key for enum that represents the property will be passed to properties to find the value
	private final String key;
	// constructor to set the value
	private ConnectionProperties(String key, String value) {
		this.value = value;
		this.key = key;
	}

	/*
	 * 
	 */
	public String getPropertyValue() {
		return this.value;
	}
	/*
	 * 
	 */
	public String getPropertyKey() {
		return this.key;
	}
}
