package com.erling;

import com.erling.jopencv.clasz.fasenet.ArcFace;
import com.erling.jopencv.clasz.fasenet.FaceNet;
import com.erling.jopencv.clasz.fasenet.FaceNet512d;
import com.erling.jopencv.clasz.ssd.SSDcaffem;
import com.erling.jopencv.clasz.v5.YoloV5;
import com.erling.jopencv.clasz.yunet.YuNet;
import com.erling.uitls.env.OpenCVDllLoader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class TestLib {

    public void Test() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\e3.jpg"));

        YoloV5 v5 = new YoloV5();
        for (int i = 0; i < 10; i++) {
            v5.DnnYoloV5DebugTime(bytes.length,bytes);
        }
        Map<String,byte[]> map = v5.DnnYoloV5DebugTime(bytes.length,bytes);

        String filePath = "E:\\SmartSecurity\\javaLib\\Jopencv\\example\\e2.jpg";
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(map.values().iterator().next());
        fos.close();
        System.out.println(map.values().iterator().next().length);
        System.out.println("j"+map.keySet().iterator().next());
    }
    public void Test1() throws IOException {
        SSDcaffem ssdcaffem = new SSDcaffem(new FaceNet());
        byte[] bytes = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\e3.jpg"));
        Map<String,byte[]> map=new HashMap<>();
//        for (int i = 0; i < 10; i++) {
//
//        }
        map = ssdcaffem.DnnSSDcaffemDetection(bytes.length,bytes);
        String filePath = "E:\\SmartSecurity\\javaLib\\Jopencv\\example\\e6.jpg";
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(map.values().iterator().next());
        fos.close();
        System.out.println(map.values().iterator().next().length);
        System.out.println("j"+map.keySet().iterator().next());
    }
    public void Test2() throws IOException {
//        FaseNet faseNet = new FaseNet();
        SSDcaffem ssdcaffem = new SSDcaffem(new FaceNet());
        byte[] bytes1 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\z4.jpg"));
        byte[] feature1 = ssdcaffem.DnnSSDcaffemDetectionFeature(bytes1.length,bytes1);
        byte[] bytes2 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\e7.jpg"));
        byte[] feature2 = ssdcaffem.DnnSSDcaffemDetectionFeature(bytes2.length,bytes2);
        System.out.println("verify feature dis:"+ssdcaffem.VerifyFeature(feature1,feature2));

//        ssdcaffem.DnnSSDcaffemDetectionFeature(bytes.length,bytes);


    }
    void Test3() throws IOException {
        byte[] bytes1 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\e4.jpg"));

        byte[] bytes2 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\e7.jpg"));
        SSDcaffem ssdcaffem = new SSDcaffem(new FaceNet512d());
        byte[] f1=ssdcaffem.DnnSSDcaffemDetection512Feature(bytes2.length,bytes2);
        byte[] f2=ssdcaffem.DnnSSDcaffemDetection512Feature(bytes1.length,bytes1);
        System.out.println("verify feature dis:"+ssdcaffem.VerifyFeature512(f1,f2));
//        System.out.println(feature.length);
    }
    void Test4() throws IOException {
        byte[] bytes1 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\z4.jpg"));

        byte[] bytes2 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\z3.jpg"));
        SSDcaffem ssdcaffem = new SSDcaffem(new FaceNet());
        byte[] f1=ssdcaffem.DnnSSDcaffemDetectionFeature(bytes2.length,bytes2);
        byte[] f2=ssdcaffem.DnnSSDcaffemDetectionFeature(bytes1.length,bytes1);
        System.out.println("verify feature dis:"+ssdcaffem.VerifyFeature(f1,f2));
    }
    void Test5() throws IOException {
        SSDcaffem ssdcaffem = new SSDcaffem(new ArcFace());
        byte[] bytes1 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\z1.jpg"));
        byte[] bytes2 = Files.readAllBytes(Paths.get("E:\\SmartSecurity\\javaLib\\Jopencv\\example\\z4.jpg"));
        byte[] feature1 = ssdcaffem.DnnSSDcaffemDetectionArcFeature(bytes1.length,bytes1);
        var time=System.currentTimeMillis();
        byte[] feature2 = ssdcaffem.DnnSSDcaffemDetectionArcFeature(bytes2.length,bytes2);
        var time2=System.currentTimeMillis();
        System.out.println("java invoke time:"+(time2-time));
        System.out.println("verify feature dis5:"+ssdcaffem.VerifyFeatureArc(feature1,feature2));
    }

    void Test6() throws IOException {
        ArcFace arcface = new ArcFace();
        YuNet yunet = new YuNet(arcface);
        yunet.getInfo();
        byte[] bytes1 = Files.readAllBytes(Paths.get(".\\example\\e4.jpg"));
        byte[] bytes2 = Files.readAllBytes(Paths.get(".\\example\\z4.jpg"));
//        byte[] f1 = yunet.DnnYuNetDebugTime(bytes1.length,bytes1);
        for (int i = 0; i < 10; i++) {
            yunet.DnnYuNetDebugTime(bytes1.length,bytes1);
        }
//        byte[] f2 = yunet.DnnYuNetDebugTime(bytes2.length,bytes2);
//        System.out.println("verify feature dis6:"+arcface.VerifyFeature(f1,f2));

    }
    public TestLib(){
        OpenCVDllLoader.INSTANCE.LOAD();
    }
    public void printWorkPath(){
        System.out.println(System.getProperty("user.dir"));
    }

    public static void main(String[] args) throws IOException {
        TestLib testLib = new TestLib();
        testLib.printWorkPath();
//        testLib.Test3();
//        testLib.Test5();
        testLib.Test6();


    }
}