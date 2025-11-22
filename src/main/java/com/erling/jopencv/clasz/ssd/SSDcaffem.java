package com.erling.jopencv.clasz.ssd;

import com.erling.jopencv.annotation.DyLibrary;
import com.erling.jopencv.clasz.fasenet.ArcFace;
import com.erling.jopencv.clasz.fasenet.FaceNet;
import com.erling.jopencv.clasz.fasenet.FaceNet512d;
import com.erling.jopencv.dnn.ssd.DnnSSDcaffem;
import com.erling.jopencv.load.Loader;
import com.erling.jopencv.struct.io.FaceFeatureByte;
import com.erling.jopencv.struct.io.ImageData;
import com.erling.jopencv.struct.io.OutputJson;
import com.erling.uitls.ReadToml;
import com.sun.jna.Pointer;

import java.util.Map;

public class SSDcaffem {


    FaceNet faceNet;

    FaceNet512d  faceNet512d;

    ArcFace   arcFace;
    Pointer netPtr;
    @DyLibrary(TomlPath = "/config/Lib.toml")
    interface  IDnnSSDcaffem extends DnnSSDcaffem{};


   final IDnnSSDcaffem dnnSSDcaffem;


    public SSDcaffem(FaceNet faceNet) {
        this.faceNet = faceNet;
        dnnSSDcaffem = Loader.LoadTLibrary(IDnnSSDcaffem.class);
        netPtr = dnnSSDcaffem.DnnSSDcaffemCreate(
                ReadToml.TomlString("/config/Lib.toml","SSDConfigPath"
                ),
                faceNet.getNetPtr()
        );
    }
    public SSDcaffem(FaceNet512d faceNet512d) {
        this.faceNet512d = faceNet512d;
        dnnSSDcaffem = Loader.LoadTLibrary(IDnnSSDcaffem.class);
        netPtr = dnnSSDcaffem.DnnSSDcaffemCreate512(
                ReadToml.TomlString("/config/Lib.toml","SSDConfigPath"
                ),
                faceNet512d.getNetPtr()
        );
    }
    public SSDcaffem(ArcFace arcFace) {
        this.arcFace = arcFace;
        dnnSSDcaffem = Loader.LoadTLibrary(IDnnSSDcaffem.class);
        netPtr = dnnSSDcaffem.DnnSSDcaffemCreateArc(
                ReadToml.TomlString("/config/Lib.toml","SSDConfigPath"
                ),
                arcFace.getNetPtr()
        );
    }

    public Map<String,byte[]> DnnSSDcaffemDetection(int size, byte[] bytes){
        ImageData imageData = new ImageData();
        OutputJson outputJson = new OutputJson();
        dnnSSDcaffem.DnnSSDcaffemDebugTime(netPtr,size,bytes,imageData,outputJson);
        return Map.of(
                outputJson.getDataJsonUtf8(), imageData.getDataBuffer()
        );
    }
     public byte[] DnnSSDcaffemDetectionFeature(int size, byte[] bytes){
        ImageData imageData = new ImageData();
        OutputJson outputJson = new OutputJson();
        FaceFeatureByte feature = new FaceFeatureByte();
        dnnSSDcaffem.DetectionFeatureByte(netPtr,size,bytes,imageData,outputJson,feature);
        return feature.getData();
    }
    public byte[] DnnSSDcaffemDetection512Feature(int size, byte[] bytes){
        ImageData imageData = new ImageData();
        OutputJson outputJson = new OutputJson();
        FaceFeatureByte feature = new FaceFeatureByte();
        dnnSSDcaffem.DetectionFeatureByte512d(netPtr,size,bytes,imageData,outputJson,feature);
        System.out.println("Java invoke feature.getData().length="+feature.getData().length);
        return feature.getData();
    }
     public byte[] DnnSSDcaffemDetectionArcFeature(int size, byte[] bytes){
        ImageData imageData = new ImageData();
        OutputJson outputJson = new OutputJson();
        FaceFeatureByte feature = new FaceFeatureByte();
        dnnSSDcaffem.DetectionFeatureByteArc(netPtr,size,bytes,imageData,outputJson,feature);
        return feature.getData();
    }

    public double VerifyFeature(byte[] feature1,byte[] feature2){
        return faceNet.VerifyFeature(feature1,feature2);
    }

    public double VerifyFeature512(byte[] feature1,byte[] feature2){
        return faceNet512d.VerifyFeature(feature1,feature2);
    }
    public double VerifyFeatureArc(byte[] feature1,byte[] feature2){
        return arcFace.VerifyFeature(feature1,feature2);
    }
}
