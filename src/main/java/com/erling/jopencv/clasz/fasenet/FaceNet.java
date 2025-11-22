package com.erling.jopencv.clasz.fasenet;

import com.erling.jopencv.annotation.DyLibrary;
import com.erling.jopencv.dnn.fasenet.DnnFaceNet;
import com.erling.jopencv.load.Loader;
import com.erling.uitls.ReadToml;
import com.sun.jna.Pointer;

public class FaceNet {

    @DyLibrary(TomlPath = "/config/Lib.toml")
    interface IDnnFaceNet extends DnnFaceNet {};
    final Pointer netPtr;

    final IDnnFaceNet dnnFaceNet;
    public FaceNet() {
        dnnFaceNet = Loader.LoadTLibrary(IDnnFaceNet.class);
        netPtr = dnnFaceNet.DnnFaceNetCreate(
                ReadToml.TomlString("/config/Lib.toml","FaceNetPath")
        );
    }

    public double VerifyFeature(byte[] input1, byte[] input2){
      return dnnFaceNet.DnnFaceNetVerifyFeature(netPtr,input1,input2);
    }
    public Pointer getNetPtr() {
        return netPtr;
    }
}
