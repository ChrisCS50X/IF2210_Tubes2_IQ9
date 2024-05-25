package com.istiqomah.if2210_tb2_iq9.plugin;

import java.util.Map;
import java.util.Set;

public interface SaveLoadPlugin {
    void save(String filePath, Map<String, Object> data);
    Map<String, Object> load(String filePath);
    Set<String> getSupportedFormats();
}
