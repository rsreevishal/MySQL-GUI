package model;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONObject;

public class TrieNode {
    private HashMap<String, TrieNode> children;
    public TrieNode() {
    	children = new HashMap<String, TrieNode>();
    }
	public HashMap<String, TrieNode> getChildren() {
		return children;
	}
	public void setChildren(HashMap<String, TrieNode> children) {
		this.children = children;
	}
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		Set<String> keys = children.keySet();
		for(String k : keys) {
			json.put(k, children.get(k).toJSON());
		}
		return json;
	}
}
