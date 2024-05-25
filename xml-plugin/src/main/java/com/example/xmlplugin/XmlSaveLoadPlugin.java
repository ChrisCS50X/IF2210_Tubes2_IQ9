package com.example.xmlplugin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.istiqomah.if2210_tb2_iq9.plugin.SaveLoadPlugin;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class XmlSaveLoadPlugin implements SaveLoadPlugin {

    @Override
    public void save(String filePath, Map<String, Object> data) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            // Ensure the folder exists
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();  // Create the folder if it doesn't exist
            }

            xmlMapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Map<String, Object> load(String filePath) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            // Memuat data dari file XML
            return xmlMapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public Set<String> getSupportedFormats() {
        Set<String> formats = new HashSet<>();
        formats.add("xml");
        return formats;
    }
}
