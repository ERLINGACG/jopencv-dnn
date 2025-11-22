package com.erling.jopencv.dnn.yunet;

import com.erling.jopencv.struct.io.FaceFeatureByte;
import com.erling.jopencv.struct.io.ImageData;
import com.erling.jopencv.struct.io.OutputJson;
import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface DnnYuNet extends Library {

    Pointer DnnYuNetCreate(String modelPath);

    void DnnYuNetDestroy(Pointer netPtr);

    void DnnYuNetGetInfo(Pointer netPtr);

    void DnnYuNetSetArc(Pointer netPtr, Pointer arcFacePtr);

    void DnnYuNetDebugTime(Pointer ptr, int size, byte[] bytes, ImageData output,
                           OutputJson outputJson, FaceFeatureByte feature);
}
