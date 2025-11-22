package com.erling.jopencv.load;

import com.erling.jopencv.annotation.DyLibrary;
import com.erling.uitls.ReadToml;
import com.sun.jna.Library;
import com.sun.jna.Native;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

public class Loader {

    @SuppressWarnings("unchecked")
    public static <T extends Library> T LoadLibrary(Class<? extends Library> libraryClass){
            DyLibrary annotation = libraryClass.getAnnotation(DyLibrary.class);
            if (annotation != null && annotation.DynamicPath()!= null  ) {
                    String path = annotation.DynamicPath();
                    if(System.getProperty("os.name").toLowerCase().contains("win")){
                          path+=".dll";
                    }else{
                          path+=".so";
                    }
                    return (T) Native.load(
                            (path),
                            libraryClass
                    );
            }else{
                throw  new RuntimeException("DyLibrary annotation is null");
            }
    }
    @SuppressWarnings("unchecked")
    public static <T extends Library> T LoadTLibrary(Class<? extends Library> libraryClass){
        DyLibrary annotation = libraryClass.getAnnotation(DyLibrary.class);
        if (annotation!=null && annotation.TomlPath()!= null) {
                String path = ReadToml.TomlString(annotation.TomlPath(),"LibPath");
                if(System.getProperty("os.name").toLowerCase().contains("win")){
                      path+=".dll";
                }else{
                      path+=".so";
                }
                return (T) Native.load(
                        (path),
                        libraryClass
                );
        }else{
            throw  new RuntimeException("DyLibrary annotation is null");
        }
    }




}
