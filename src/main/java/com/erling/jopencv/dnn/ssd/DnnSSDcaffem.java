package com.erling.jopencv.dnn.ssd;

import com.erling.jopencv.struct.io.FaceFeatureByte;
import com.erling.jopencv.struct.io.ImageData;
import com.erling.jopencv.struct.io.OutputJson;
import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface DnnSSDcaffem extends Library {
    Pointer DnnSSDcaffemCreate(String configPath,Pointer netPtr);

    Pointer DnnSSDcaffemCreate512(String configPath,Pointer netPtr);

    Pointer DnnSSDcaffemCreateArc(String configPath,Pointer netPtr);

    void DnnSSDcaffemDestroy(Pointer ptr);

    void  DnnSSDcaffemDebugTime(Pointer ptr, int size, byte[] bytes, ImageData output,
                                OutputJson outputJson);

    void DetectionFeatureByte(Pointer ptr, int size, byte[] bytes, ImageData output,
                              OutputJson outputJson, FaceFeatureByte feature);
    void DetectionFeatureByte512d(Pointer ptr, int size, byte[] bytes, ImageData output,
                                  OutputJson outputJson, FaceFeatureByte feature);

    void DetectionFeatureByteArc(Pointer ptr, int size, byte[] bytes, ImageData output,
                                  OutputJson outputJson, FaceFeatureByte feature);



}
