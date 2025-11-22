package com.erling.uitls.env;

import com.erling.uitls.ReadToml;

import java.util.List;

public enum OpenCVDllLoader {
    INSTANCE;

    OpenCVDllLoader() {
        try {
            List<String> env = ReadToml.TomlStringArray("/config/Lib.toml","ENV");
            if (env != null) {
                for(String dllFullPath:env){
                    System.load(dllFullPath); // 加载指定路径的 DLL
                }
            }
            System.out.println("DLL LOAD SUCCESS");
        } catch (UnsatisfiedLinkError e) {
            throw new RuntimeException(e);
        }
    }
   public void LOAD(){}
}
