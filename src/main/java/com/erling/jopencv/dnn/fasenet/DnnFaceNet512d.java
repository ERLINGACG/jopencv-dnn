package com.erling.jopencv.dnn.fasenet;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface DnnFaceNet512d extends Library {
    Pointer DnnFaceNet512dCreate(String configPath);
     double DnnFaceNet512dVerifyFeature(Pointer ptr, byte[] input1, byte[] input2);

}
