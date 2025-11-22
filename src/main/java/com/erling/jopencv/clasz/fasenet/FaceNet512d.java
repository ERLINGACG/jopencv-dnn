package com.erling.jopencv.clasz.fasenet;

import com.erling.jopencv.annotation.DyLibrary;
import com.erling.jopencv.dnn.fasenet.DnnFaceNet;
import com.erling.jopencv.dnn.fasenet.DnnFaceNet512d;
import com.erling.jopencv.load.Loader;
import com.erling.uitls.ReadToml;
import com.sun.jna.Pointer;

public class FaceNet512d {
    @DyLibrary(TomlPath = "/config/Lib.toml")
    interface IDnnFaceNet512 extends DnnFaceNet512d {};
    final Pointer netPtr;

    final IDnnFaceNet512 dnnFaceNet;
    public FaceNet512d() {
        dnnFaceNet = Loader.LoadTLibrary(IDnnFaceNet512.class);
        netPtr = dnnFaceNet.DnnFaceNet512dCreate(
                ReadToml.TomlString("/config/Lib.toml","FaceNetPath")
        );
    }
    public double VerifyFeature(byte[] input1, byte[] input2){
      return dnnFaceNet.DnnFaceNet512dVerifyFeature(netPtr,input1,input2);
    }
    public Pointer getNetPtr() {
        return netPtr;
    }
}
