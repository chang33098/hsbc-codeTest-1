package org.example.splitter;

import org.example.dictionary.Dictionary;
import org.example.dictionary.NormalDictionary;
import org.example.loader.CollectionDictionaryLoader;
import org.example.loader.DictionaryLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 单元测试类
 */
class SentenceSplitterTest {

    private SentenceSplitter splitter;

    @BeforeEach
    void setUp() {
        splitter = new SentenceSplitter();
    }

    @Test
    @DisplayName("案例1：句子可以完全被词典拆分")
    void testSimpleSplit() throws Exception {
        DictionaryLoader loader = new CollectionDictionaryLoader(Set.of("i", "like", "sam", "sung", "samsung"));
        NormalDictionary dictionary = new NormalDictionary(loader);

        String sentence = "ilikesamsung";

        List<String> result = splitter.split(sentence, dictionary);
        assertTrue(result.contains("i like sam sung"));
        assertTrue(result.contains("i like samsung"));
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("案例2：句子有多个分词组合")
    void testMultipleSplits() throws Exception {
        DictionaryLoader loader = new CollectionDictionaryLoader(Set.of("sam", "sung", "samsung"));
        NormalDictionary dictionary = new NormalDictionary(loader);

        //String sentence = "pineapplepenapple";
        String sentence = "samsungsungsamsung";
        List<String> result = splitter.split(sentence, dictionary);

        assertTrue(result.contains("sam sung sung sam sung"));
        assertTrue(result.contains("sam sung sung samsung"));
        assertTrue(result.contains("samsung sung sam sung"));
        assertTrue(result.contains("samsung sung samsung"));
        assertEquals(4, result.size());
    }

    @Test
    @DisplayName("案例3：无法拆分的句子返回空结果")
    void testNoMatch() throws Exception {
        DictionaryLoader loader = new CollectionDictionaryLoader(Set.of("sam", "samsung"));
        NormalDictionary dictionary = new NormalDictionary(loader);

        String sentence = "sa";
        List<String> result = splitter.split(sentence, dictionary);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("案例4：空字符串输入")
    void testEmptyInput() throws Exception {
        DictionaryLoader loader = new CollectionDictionaryLoader(Set.of("sam", "samsung"));
        NormalDictionary dictionary = new NormalDictionary(loader);

        List<String> result = splitter.split("", dictionary);
        assertEquals(1, result.size()); // 应返回一个空句子 ""
        assertEquals("", result.get(0));
    }

    @Test
    @DisplayName("案例5：空字典输入")
    void testEmptyDictionary() throws Exception {
        DictionaryLoader loader = new CollectionDictionaryLoader(Collections.emptyList());
        NormalDictionary dictionary = new NormalDictionary(loader);

        String sentence = "ilikesamsung";
        List<String> result = splitter.split(sentence, dictionary);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("案例6：词典中只有一个单词，匹配整个句子")
    void testSingleWordMatch() throws Exception {
        DictionaryLoader loader = new CollectionDictionaryLoader(Set.of("samsung"));
        NormalDictionary dictionary = new NormalDictionary(loader);

        String sentence = "samsung";
        List<String> result = splitter.split(sentence, dictionary);
        assertEquals(1, result.size());
        assertEquals("samsung", result.get(0));
    }

    @Test
    @DisplayName("案例7：sentence 为 null 应抛出异常")
    void testNullInput() {
        DictionaryLoader loader = new CollectionDictionaryLoader(Set.of("sam", "samsung"));
        NormalDictionary dictionary = new NormalDictionary(loader);

        assertThrows(NullPointerException.class, () -> splitter.split(null, dictionary));
    }

    @Test
    @DisplayName("案例8：dictionary 为 null 应抛出异常")
    void testNullDictionary() {
        assertThrows(NullPointerException.class, () -> splitter.split("test", null));
    }

}