package com.erling.jopencv.dnn.v5;

import com.erling.jopencv.struct.io.ImageData;
import com.erling.jopencv.struct.io.OutputJson;
import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface DnnYoloV5 extends Library {
   Pointer DnnYoloV5Create(String configPath);
   void DnnYoloV5Destroy(Pointer ptr);
   void DnnYoloV5Debug(Pointer ptr);

   void DnnYoloV5DebugTime(Pointer ptr, int size, byte[] bytes, ImageData output,
                           OutputJson outputJson);
   void DnnYoloV5Detection(Pointer ptr, int size, byte[] bytes, ImageData output,
                           OutputJson outputJson);

}
