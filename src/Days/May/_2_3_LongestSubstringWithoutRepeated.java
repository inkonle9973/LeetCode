package Days.May;

import java.util.HashMap;
import java.util.Map;

public class _2_3_LongestSubstringWithoutRepeated {
    public static int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        int max_length = 0;
        int left=0;
        Map<Character, Integer> strmap = new HashMap<>();
        for (int i = 0; i < ch.length; i++) {
            if (strmap.containsKey(ch[i])) {
                left = Math.max(left,strmap.get(ch[i])+1);


            }
            strmap.put(ch[i], i);
            max_length = Math.max(max_length, i - left + 1);

        }

        return max_length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abba"));

    }
}
