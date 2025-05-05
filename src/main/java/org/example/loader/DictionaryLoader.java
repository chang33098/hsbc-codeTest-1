package org.example.loader;

import java.util.Set;

public interface DictionaryLoader {

    /**
     * 加载数据
     *
     * @param words 字典数据
     */
    void load(Set<String> words);

}
