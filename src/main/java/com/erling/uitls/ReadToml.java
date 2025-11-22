package com.erling.uitls;

import com.moandjiezana.toml.Toml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReadToml{

   public static String TomlString(String path,String key){
        InputStream inputStream = ReadToml.class.getResourceAsStream(path);
        if (inputStream != null) {
            Toml toml = new Toml().read(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return toml.getString(key);
        }
        return null;
   }
   public static List<String> TomlStringArray(String path, String key){
        InputStream inputStream = ReadToml.class.getResourceAsStream(path);
        if (inputStream != null) {
            Toml toml = new Toml().read(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return toml.getList(key);
        }
        return null;
   }

}
