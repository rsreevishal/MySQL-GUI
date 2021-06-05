package model;

public class Pair<T,S> {
	private T key;
	private S value;
	public Pair(){}
	public Pair(T k, S v) {
		this.key = k;
		this.value = v;
	}
	public S getValue() {
		return value;
	}
	public void setValue(S value) {
		this.value = value;
	}
	public T getKey() {
		return key;
	}
	public void setKey(T key) {
		this.key = key;
	}
}
