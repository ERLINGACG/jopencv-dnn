package com.erling.jopencv.clasz.yunet;

import com.erling.jopencv.annotation.DyLibrary;
import com.erling.jopencv.clasz.fasenet.ArcFace;
import com.erling.jopencv.dnn.ssd.DnnSSDcaffem;
import com.erling.jopencv.dnn.yunet.DnnYuNet;
import com.erling.jopencv.load.Loader;
import com.erling.jopencv.struct.io.FaceFeatureByte;
import com.erling.jopencv.struct.io.ImageData;
import com.erling.jopencv.struct.io.OutputJson;
import com.erling.uitls.ReadToml;
import com.sun.jna.Pointer;

import java.io.PipedOutputStream;

public class YuNet {

    @DyLibrary(TomlPath = "/config/Lib.toml")
    interface  IDnnYuNet extends DnnYuNet {};
    final IDnnYuNet dnnYuNet;

    final Pointer netPtr;
    public YuNet(ArcFace arcFace) {
        dnnYuNet = Loader.LoadTLibrary(IDnnYuNet.class);
        netPtr = dnnYuNet.DnnYuNetCreate(
                ReadToml.TomlString("/config/Lib.toml","YuNetPath")
        );
        dnnYuNet.DnnYuNetSetArc(netPtr, arcFace.getNetPtr());

    }

    public byte[] DnnYuNetDebugTime(int size, byte[] bytes) {
        ImageData imageData = new ImageData();
        OutputJson outputJson = new OutputJson();
        FaceFeatureByte feature = new FaceFeatureByte();
        dnnYuNet.DnnYuNetDebugTime(netPtr, size, bytes, imageData, outputJson, feature);
//        System.out.println("invoke:"+imageData.getDataBuffer().length);
        return feature.getData();
    }

    public  void getInfo(){
        dnnYuNet.DnnYuNetGetInfo(netPtr);
    }
}
