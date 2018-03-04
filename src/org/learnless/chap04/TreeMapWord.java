package org.learnless.chap04;

import java.util.*;

/**
 * treeMap的应用
 * 将单词只有一个字母不同进行分组
 * Created by learnless on 18.3.3.
 */
public class TreeMapWord<T> {

    /**
     * 判断两单词是否只有一个字母不同
     * @param s1
     * @param s2
     * @return
     */
    public boolean oneDiff(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (++diff > 1)
                    return false;
            }
        }

        return diff == 1;
    }

    /**
     * 存储单词
     * @param map
     * @param k
     * @param v
     * @param <T>
     */
    public <T> void update(Map<T, List<String>> map, T k, String v) {
        List<String> list = map.get(k);
        if (list == null) {
            list = new ArrayList<>();
            map.put(k, list);
        }
        list.add(v);
    }

    /**
     * 只按单词的索引进行分类
     * @param words
     * @return
     */
    public Map<String, List<String>> group(List<String> words) {
        Map<String, List<String>> map = new TreeMap<>();
        String[] wordsarr = new String[words.size()];
        words.toArray(wordsarr);
        for (int i = 0; i < wordsarr.length; i++) {
            for (int j = i + 1; j < wordsarr.length; j++) {
                if (oneDiff(wordsarr[i], wordsarr[j])) {
                    update(map, wordsarr[i], wordsarr[j]);
                    update(map, wordsarr[j], wordsarr[i]);
                }
            }
        }

        return map;
    }

    /**
     * 同时使用单词长度进行分组
     * @param words
     * @return
     */
    public Map<String, List<String>> group2(List<String> words) {
        Map<String, List<String>> map = new TreeMap<>();
        Map<Integer, List<String>> maplen = new TreeMap<>();

        for (String word : words) {
            update(maplen, word.length(), word);
        }

        for (List<String> wordslen : maplen.values()) {
            String[] wordsarr = new String[wordslen.size()];
            wordslen.toArray(wordsarr);
            for (int i = 0; i < wordsarr.length; i++) {
                for (int j = i + 1; j < wordsarr.length; j++) {
                    if (oneDiff(wordsarr[i], wordsarr[j])) {
                        update(map, wordsarr[i], wordsarr[j]);
                        update(map, wordsarr[j], wordsarr[i]);
                    }
                }
            }
        }


        return map;
    }

    public Map<String, List<String>> group3(List<String> words) {
        Map<String, List<String>> map = new TreeMap<>();
        Map<Integer, List<String>> maplen = new TreeMap<>();

        for (String word : words) {
            update(maplen, word.length(), word);
        }

        for (Map.Entry<Integer, List<String>> entry : maplen.entrySet()) {
            List<String> groupsWords = entry.getValue();
            int groupNum = entry.getKey();

            for (int i = 0; i < groupNum; i++) {
                Map<String, List<String>> repToWord = new TreeMap<>();
                for (String str : groupsWords) {
                    String rep = str.substring(0, i) + str.substring(i + 1);
                    update(repToWord, rep, str);
                }

                for (List<String> wordClique : repToWord.values()) {
                    if (wordClique.size() >= 2) {
                        for (String s1 : wordClique) {
                            for (String s2 : wordClique) {
                                if (s1 != s2) {
                                    update(map, s1, s2);
                                }
                            }
                        }
                    }
                }
            }
        }


        return map;
    }


    public static void main(String[] args) {
        TreeMapWord treeMapWord = new TreeMapWord();
        List<String> words = new ArrayList<>();
        words.add("cat");
        words.add("fat");
        words.add("hat");
        words.add("cut");
        words.add("wood");
        words.add("good");
        words.add("cook");

        Map group = treeMapWord.group(words);
        Map group2 = treeMapWord.group2(words);
        Map group3 = treeMapWord.group3(words);


        System.out.println();
    }


}
