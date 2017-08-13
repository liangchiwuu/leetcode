package algorithms;

import java.util.HashMap;

/**
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {

    /*
     * There are many string matching algorithms including:
     * Brute Force
     * Knuth–Morris–Pratt, KMP
     * Boyer–Moore–Horspool
     * Sunday
     * Rabin–Karp
     * ...
     * 
     * Only Brute Force and Sunday algorithm is implemented here.
     * 
     * Some reference material:
     * 
     * 字符串匹配——Sunday算法 - Switch的博客 - CSDN博客
     * http://blog.csdn.net/q547550831/article/details/51860017
     * 
     * (笔试前复习)比KMP算法更简单更快的字符串匹配算法 - 雷雨中的双桅船 - CSDN博客
     * http://blog.csdn.net/hereiskxm/article/details/8065751
     * 
     * Knuth–Morris–Pratt(KMP) Pattern Matching(Substring search) - YouTube
     * https://www.youtube.com/watch?v=GTJr8OvyEVQ
     */

    public static void main(String[] args) {
        String haystack = "abcdefg";
        String needle = "cd";
        int result = new ImplementStrStr().strStr(haystack, needle);
        System.out.println(result);
    }

    /**
     * Brute force solution, time complexity: worst case O(m*n)
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || haystack == null) {
            return -1;
        }

        search: for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    continue search;
                }
            }
            return i;
        }

        return -1;
    }

    /**
     * Sunday algorithm.
     */
    public int strStr2(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> stepMap = getSundayStepMap(needle);
        int i = 0;
        while (i < haystack.length() - needle.length() + 1) {

            int j = 0;
            while (j < needle.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                if (j >= needle.length() - 1) {
                    return i;
                }
                j++;
            }

            if (needle.length() + i < haystack.length()
                    && stepMap.containsKey(haystack.charAt(i + needle.length()))) {
                i += stepMap.get(haystack.charAt(i + needle.length()));
            } else {
                i += needle.length() + 1;
            }
        }

        return -1;
    }

    private HashMap<Character, Integer> getSundayStepMap(String needle) {
        HashMap<Character, Integer> stepMap = new HashMap<Character, Integer>();
        for (int i = 0; i < needle.length(); i++) {
            stepMap.put(needle.charAt(i), needle.length() - i);
        }
        return stepMap;
    }

}
