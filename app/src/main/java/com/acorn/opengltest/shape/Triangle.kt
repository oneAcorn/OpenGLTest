package com.acorn.opengltest.shape

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * Created by acorn on 2020/3/12.
 */
class Triangle {
    //顶点着色器,用于渲染形状顶点的OpenGL ES图形代码
    private val vertexShaderCode = "attribute vec4 vPosition;" +
            "void main() {" +
            "  gl_Position = vPosition;" +
            "}"
    //片段着色器,用于渲染具有颜色或纹理的形状的面
    private val fragmentShaderCode = "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}"

    private var vertexBuffer: FloatBuffer? = null
    private val triangleCoords = floatArrayOf( //三角形坐标,逆时针顺序
        0f, 0.622008459f, 0f, //上x,y,z
        -0.5f, -0.311004243f, 0f, //左下x,y,z
        0.5f, -0.311004243f, 0f //右下x,y,z
    )
    //颜色RGBA
    private val color = arrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

    init {
        //为形状坐标初始化顶点的字节缓冲区,容量=数组长度*每个float占用的4字节
        val bb = ByteBuffer.allocateDirect(triangleCoords.size * 4)
        //缓冲区读取顺序使用设备硬件的本地字节读取顺序
        bb.order(ByteOrder.nativeOrder())
        //从ByteBuffer创建一个浮点缓冲区
        vertexBuffer = bb.asFloatBuffer().apply {
            //将坐标点加到FloatBuffer中
            put(triangleCoords)
            //设置缓冲区开始读取位置,这里设置从头开始读取
            position(0)
        }

    }
}