package model;

import java.util.ArrayList;
import java.util.function.Function;

import org.json.JSONObject;

public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	public void insert(ArrayList<String> words) {
		TrieNode current = root;
		for (String word : words) {
			current = current.getChildren().computeIfAbsent(word, new Function<String, TrieNode>() {
				public TrieNode apply(String c) {
					return new TrieNode();
				}
			});
		}
	}

	public void insertAll(ArrayList<ArrayList<String>> keys) {
		for (ArrayList<String> key : keys) {
			if (!find(key)) {
				insert(key);
			}
		}
	}

	public boolean find(ArrayList<String> words) {
		TrieNode current = root;
		for (String word : words) {
			TrieNode node = current.getChildren().get(word);
			if (node == null) {
				return false;
			}
			current = node;
		}
		return true;
	}

	public ArrayList<TrieDifference> difference(Table table, ArrayList<ArrayList<String>> keys) {
		ArrayList<TrieDifference> trieDifferences = new ArrayList<TrieDifference>();
		for (ArrayList<String> row : keys) {
			TrieNode current = root;
			String fieldName = null;
			for (String word : row) {
				TrieNode node = current.getChildren().get(word);
				if (word.contains("fn_")) {
					fieldName = word.replaceFirst("^fn_", "");
				}
				if (node == null) {
					if (word.contains("fn_")) {
						trieDifferences.add(new TrieDifference(DifferenceType.NEW_FIELD, table.getName(),
								table.getFieldByName(fieldName)));
					} else if (word.contains("ft_")) {
						trieDifferences.add(new TrieDifference(DifferenceType.NEW_TYPE, table.getName(),
								table.getFieldByName(fieldName)));
					} else if (word.contains("fa_")) {
						trieDifferences.add(new TrieDifference(DifferenceType.NEW_ARGS, table.getName(),
								table.getFieldByName(fieldName)));
					} else if (word.contains("t_")) {
						System.out.println(String.format("%s form not available", word));
					}
					break;
				}
				current = node;
			}
		}
		return trieDifferences;
	}

	public boolean findAll(ArrayList<ArrayList<String>> keys) {
		for (ArrayList<String> key : keys) {
			if (find(key)) {
				return true;
			}
		}
		return false;
	}

	public JSONObject toJSON() {
		return root.toJSON();
	}
}
