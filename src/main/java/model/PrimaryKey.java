package model;

public class PrimaryKey {
	private String key, value;
	public PrimaryKey() {}
	public PrimaryKey(String key, String value) {
		this.setKey(key);
		this.setValue(value);
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
