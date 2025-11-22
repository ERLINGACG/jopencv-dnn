package com.erling.jopencv.struct.io;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;


//struct FaceFeatureByte{
//int dataSize;
//int faceNum;
//std::unique_ptr<unsigned char[]> data;
//
//        ~FaceFeatureByte()=default;
//    };

@Structure.FieldOrder({"dataSize", "faceNum", "data"})
public class FaceFeatureByte extends Structure {
    public int dataSize;
    public int faceNum;
    public Pointer data;

    public byte[] getData() {
        return data.getByteArray(0, dataSize);
    }
}
