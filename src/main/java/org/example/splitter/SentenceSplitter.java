package org.example.splitter;

import org.example.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * 断句的工具类，根据字典进行句子分割
 */
public class SentenceSplitter {

    /**
     * 断句方法，传入指定的句子以及解析的字典，对句子进行断句
     *
     * @param sentence   句子
     * @param dictionary 字典
     * @return
     * @throws Exception
     */
    public List<String> split(String sentence, Dictionary dictionary) throws Exception {
        var results = new ArrayList<String>();
        backtrack(sentence, 0, dictionary, new ArrayList<>(), results);
        return List.copyOf(results); // 返回不可变的数组，防止结果被修改
    }

    /**
     * 回溯算法，根据字典进行断句
     * 以下回溯（Backtracking）算法用于在一个无空格的句子中，枚举所有可能的切分方式，使得每段都出现在给定词典中。
     * 核心思想是：从句子开头出发，尝试所有可能的截断点，只要截断出的前缀是合法单词，就“猜”它是一个词，然后递归地对剩余后缀做同样处理，直到走到字符串末尾，便得到一个完整切分方案。
     * <p>
     * path 用来临时保存当前已经选择的单词序列；results 收集所有最终结果。
     *
     * @param sentence
     * @param start
     * @param dict
     * @param path
     * @param results
     */
    private void backtrack(String sentence, int start, Dictionary dict, List<String> path, List<String> results) {
        // 遍历的索引下标 = 字符串的长度代表已完成所有的遍历
        if (start == sentence.length()) {
            // 将路径中的单词用空格连接成一句话
            System.out.println("完成遍历，记录下path: " + String.join(" ", path));
            results.add(String.join(" ", path));
            return;
        }

        for (int end = start + 1; end <= sentence.length(); end++) {
            var word = sentence.substring(start, end);
            if (dict.contains(word)) {
                //System.out.println("单词[" + word + "]在词典匹配成功，当前下标: " + start + "，结束下标: " + end);
                // 1. 字典匹配成功，选择：把 word 加入当前路径
                path.add(word);
                //System.out.println("删除前的path: " + String.join(" ", path));

                // 2. 递归：处理剩余子串 [end, ...]（使用递归的原因是需要所有组合的结果，例如samsung一词，有”samsung“和”sam sung“两种结果 ）
                backtrack(sentence, end, dict, path, results);

                // 3. 撤销选择：回溯，移除 path 末尾，为下一轮尝试做准备
                // 还是以samsung为例，有”samsung“和”sam sung“两种结果，在path=[i, like]后，递归先匹配到了”sam, sung...“等单词，再完整解析了"i like..."之后的句子后，
                // 再回溯到"i like"时，需要删除"i like"中的"like"，再尝试"i like sam sung..."，所以需要回溯删除path中的"sam sung"等单词

                path.remove(path.size() - 1);
                //System.out.println("删除后的path: " + String.join(" ", path));
            }
        }
    }

}
