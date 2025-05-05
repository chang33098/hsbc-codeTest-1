package org.example.dictionary;

import java.util.HashSet;
import java.util.Set;

/**
 * 定义字典抽象类，公共属性[words]，公共方法[contains]，[getDictWords]
 */
public abstract class Dictionary {

    protected Set<String> words;

    public Dictionary() {
        this.words = new HashSet<>();
    }

    /**
     * 判断单词是否存在
     *
     * @param word
     * @return
     */
    public boolean contains(String word) {
        return words.contains(word);
    }

    /**
     * 获取字典中的所有单词
     */
    public Set<String> getDictWords() {
        return words;
    }
}
