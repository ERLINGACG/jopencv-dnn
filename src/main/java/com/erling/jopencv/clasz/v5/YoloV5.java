package com.erling.jopencv.clasz.v5;

import com.erling.jopencv.annotation.DyLibrary;
import com.erling.jopencv.dnn.v5.DnnYoloV5;
import com.erling.jopencv.load.Loader;
import com.erling.jopencv.struct.io.ImageData;
import com.erling.jopencv.struct.io.OutputJson;
import com.erling.uitls.ReadToml;
import com.sun.jna.Pointer;

import java.util.Map;

public class YoloV5 {
    Pointer netPtr;


    @DyLibrary(TomlPath = "/config/Lib.toml")
    interface IDnnYoloV5 extends DnnYoloV5 {}

     private final IDnnYoloV5 dnnYoloV5;

    public YoloV5(){
           dnnYoloV5 = Loader.LoadTLibrary(IDnnYoloV5.class);
           netPtr= dnnYoloV5.DnnYoloV5Create(
                   ReadToml.TomlString("/config/Lib.toml","YOLOV5ConfigPath")
           );
    }
    public void DnnYoloV5Debug(){
        dnnYoloV5.DnnYoloV5Debug(netPtr);
    }
     public void DnnYoloV5Destroy(){
        dnnYoloV5.DnnYoloV5Destroy(netPtr);
    }
     public Map<String,byte[]> DnnYoloV5DebugTime(int size, byte[] bytes){
        ImageData imageData = new ImageData();
        OutputJson outputJson = new OutputJson();
        dnnYoloV5.DnnYoloV5DebugTime(netPtr,size,bytes,imageData,outputJson);
        return Map.of(
                outputJson.getDataJsonUtf8(), imageData.getDataBuffer()
        );
    }
    public Map<String,byte[]> DnnYoloV5Detection(int size, byte[] bytes){
        ImageData imageData = new ImageData();
        OutputJson outputJson = new OutputJson();
        dnnYoloV5.DnnYoloV5Detection(netPtr,size,bytes,imageData,outputJson);
        return Map.of(
                outputJson.getDataJsonUtf8(), imageData.getDataBuffer()
        );
    }


    public void Destroy(){
        dnnYoloV5.DnnYoloV5Destroy(netPtr);
    }
}
