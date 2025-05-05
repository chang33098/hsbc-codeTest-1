package org.example.loader;

import java.util.Collection;
import java.util.Set;

/**
 * 从Collection中加载字典数据
 */
public class CollectionDictionaryLoader implements DictionaryLoader {

    private Collection<String> collection;

    public CollectionDictionaryLoader(Collection<String> collection) {
        this.collection = collection;
    }

    @Override
    public void load(Set<String> words) {
        words.addAll(collection); // 将collection数据添加至字典中
    }
}
