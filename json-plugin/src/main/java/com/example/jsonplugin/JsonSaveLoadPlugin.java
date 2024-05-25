package com.example.jsonplugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.istiqomah.if2210_tb2_iq9.plugin.SaveLoadPlugin;
import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class JsonSaveLoadPlugin implements SaveLoadPlugin {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(String filePath, Map<String, Object> data) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();  // Create the folder if it doesn't exist
            }
            objectMapper.writeValue(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> load(String filePath) {
        try {
            return objectMapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<String> getSupportedFormats() {
        return Set.of("json");
    }
}
