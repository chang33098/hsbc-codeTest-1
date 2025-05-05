package org.example.dictionary;

import org.example.loader.DictionaryLoader;

import java.util.Collection;
import java.util.Collections;

/**
 * 普通字典，通过定义[DictionaryLoader]初始化字典数据
 */
public class NormalDictionary extends Dictionary {

    private DictionaryLoader dictionaryLoader;

    /**
     * 通过定义[DictionaryLoader]初始化字典数据，load()给字典属性"words"赋值的功能
     *
     * @param dictionaryLoader
     */
    public NormalDictionary(DictionaryLoader dictionaryLoader) {
        this.dictionaryLoader = dictionaryLoader;
        dictionaryLoader.load(words); // 初始化字典数据
    }

}
