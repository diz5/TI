package Hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPattern {
	public boolean wordPatternMatch(String pattern, String input) {
		Map<Character, String> map = new HashMap<>();
		Set<String> set = new HashSet<>();

		return isMatch(input, 0, pattern, 0, map, set);
	}

	boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
		// base case
		if (i == str.length() && j == pat.length())
			return true;
		if (i == str.length() || j == pat.length())
			return false;

		// get current pattern character
		char c = pat.charAt(j);

		// if the pattern character exists
		if (map.containsKey(c)) {
			String s = map.get(c);

			// then check if we can use it to match str[i...i+s.length()]
			if (!str.startsWith(s, i)) {
				return false;
			}

			// if it can match, great, continue to match the rest
			return isMatch(str, i + s.length(), pat, j + 1, map, set);
		}

		// pattern character does not exist in the map
		for (int k = i; k < str.length(); k++) {
			String p = str.substring(i, k + 1);

			if (set.contains(p)) {
				continue;
			}

			// create or update it
			map.put(c, p);
			set.add(p);

			// continue to match the rest
			if (isMatch(str, k + 1, pat, j + 1, map, set)) {
				return true;
			}

			// backtracking
			map.remove(c);
			set.remove(p);
		}

		// we've tried our best but still no luck
		return false;
	}

	public static void main(String[] args) {
		WordPattern s = new WordPattern();
		System.out.println(s.wordPatternMatch("abab", "redblueredblue"));
	}
}
