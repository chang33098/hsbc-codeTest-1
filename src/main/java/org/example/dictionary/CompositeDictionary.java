package org.example.dictionary;

import org.example.loader.DictionaryLoader;

import java.util.HashSet;
import java.util.List;

/**
 * 组合字典，合并多个字典的数据
 * 实现第三个要求（stage3）
 */
public class CompositeDictionary extends Dictionary {

    private List<Dictionary> dictionaries;

    /**
     * 在构造函数中传入其他的字典，并将多个字典合并
     *
     * @param dictionaries
     */
    public CompositeDictionary(Dictionary... dictionaries) {
        this.dictionaries = List.of(dictionaries);
        // 合并字典数据
        for (Dictionary dictionary : dictionaries) {
            words.addAll(dictionary.words);
        }
    }

}
