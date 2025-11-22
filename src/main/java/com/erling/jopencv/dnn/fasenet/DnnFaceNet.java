package com.erling.jopencv.dnn.fasenet;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface DnnFaceNet extends Library {
    Pointer DnnFaceNetCreate(String configPath);

    double DnnFaceNetVerifyFeature(Pointer ptr, byte[] input1, byte[] input2);

}
