package org.example.loader;

import java.util.Set;

public class FileDictionaryLoader implements DictionaryLoader {

    private String filePath;

    public FileDictionaryLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void load(Set<String> words) {
        // TODO 从文件中读取字典数据，根据filePath读取文件数据
    }
}
