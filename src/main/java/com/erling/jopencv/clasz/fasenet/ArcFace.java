package com.erling.jopencv.clasz.fasenet;

import com.erling.jopencv.annotation.DyLibrary;
import com.erling.jopencv.dnn.fasenet.DnnArcFace;
import com.erling.jopencv.load.Loader;
import com.erling.uitls.ReadToml;
import com.sun.jna.Pointer;

public class ArcFace {


    @DyLibrary(TomlPath = "/config/Lib.toml")
    interface IDnnArcFace extends DnnArcFace {};

    final Pointer netPtr;

    final IDnnArcFace IDnnArcFace;
    public ArcFace() {
        IDnnArcFace = Loader.LoadTLibrary(IDnnArcFace.class);
        netPtr = IDnnArcFace.DnnArcFaceCreate(
                ReadToml.TomlString("/config/Lib.toml","ArcFacePath")
        );
    }

    public Pointer getNetPtr() {
        return netPtr;
    }
    public double VerifyFeature(byte[] input1, byte[] input2){
      return IDnnArcFace.DnnArcFaceVerifyFeature(netPtr,input1,input2);
    }

     public void Destroy(){
        IDnnArcFace.DnnArcFaceDestroy(netPtr);
    }

}
