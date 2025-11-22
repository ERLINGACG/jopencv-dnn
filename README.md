# Jopencv-dnn

Jopencv-dnn 是一个基于 Java 的计算机视觉库，通过 JNA (Java Native Access) 技术封装了 OpenCV 的深度学习功能，提供了便捷的人脸检测、人脸识别、目标检测等功能接口。

## 功能特性

- **人脸检测**：使用 YuNet 模型进行高效人脸检测
- **人脸识别**：支持多种人脸识别模型（FaceNet、FaceNet512d、ArcFace）
- **目标检测**：基于 YoloV5 和 SSD 模型的目标检测
- **特征提取**：支持人脸特征提取和比对
- **跨平台**：通过 JNA 技术调用本地库，支持多平台运行

## 项目结构

```
Jopencv-dnn/
├── src/main/java/com/erling/jopencv/  # 主源码目录
│   ├── annotation/                    # 注解定义
│   ├── clasz/                         # 封装的类
│   │   ├── fasenet/                   # 人脸识别相关类
│   │   ├── ssd/                       # SSD 目标检测相关类
│   │   ├── v5/                        # YoloV5 目标检测相关类
│   │   └── yunet/                     # YuNet 人脸检测相关类
│   ├── dnn/                           # 本地接口定义
│   ├── load/                          # 库加载工具
│   ├── struct/                        # 数据结构定义
│   └── uitls/                         # 工具类
├── config/                            # 配置文件目录
│   ├── yunet/                         # YuNet 模型配置
│   ├── facenet/                       # FaceNet 模型配置
│   ├── arcface/                       # ArcFace 模型配置
│   └── testN/                         # 测试配置
├── example/                           # 示例图片
├── clibs/                             # 本地库文件
├── build.gradle                       # Gradle 构建文件
└── README.md                          # 项目说明
```

## 核心模块

### 1. YuNet（人脸检测）
- 基于 YuNet 模型的高效人脸检测
- 支持人脸特征点定位

### 2. FaceNet/FaceNet512d（人脸识别）
- 人脸特征提取和比对
- 支持 128 维和 512 维特征向量

### 3. ArcFace（人脸识别）
- 基于 ArcFace 模型的人脸识别
- 提供更高精度的人脸识别能力

### 4. SSDcaffem（目标检测）
- 基于 SSD 模型的目标检测
- 支持与不同人脸特征提取模型集成

### 5. YoloV5（目标检测）
- 基于 YoloV5 模型的目标检测
- 支持多种目标的检测和识别

## 技术依赖

- **Java**：主要开发语言
- **JNA 5.14.0**：用于调用本地库
- **OpenCV**：提供深度学习功能支持
- **Gradle**：项目构建工具
- **toml4j 0.7.2**：配置文件解析

## 快速开始

### 环境要求
- JDK 8 或更高版本
- OpenCV 4.12.0-dev 或兼容版本
- 支持 CUDA 的 GPU（可选，用于加速）

### 安装

1. 克隆项目
```bash
git clone [项目地址]
cd Jopencv-dnn
```

2. 构建项目
```bash
./gradlew build
```

3. 确保本地库文件正确配置
   - Windows：确保 `opencv_world4120.dll` 和其他必要的 DLL 文件在系统路径中
   - 配置文件中的路径指向正确的模型文件位置

### 使用示例

- 参照TestLib中的示例代码进行使用

## 配置说明

项目使用 TOML 格式的配置文件进行配置，主要配置文件位于 `/config/Lib.toml`。配置项包括：

- 模型文件路径
- 输入尺寸设置
- 阈值参数
- CUDA 支持设置

## 性能优化

- 启用 CUDA 加速（在配置文件中设置 `isCUDA: true`）
- 调整模型输入尺寸以平衡精度和速度
- 批量处理以提高吞吐量

## 注意事项

1. 确保本地库文件与系统架构匹配（32位/64位）
2. 对于 GPU 加速，需要正确安装 CUDA 和 cuDNN
3. 模型文件需要正确下载并配置路径
4. 处理完资源后记得调用相应的销毁方法释放资源

