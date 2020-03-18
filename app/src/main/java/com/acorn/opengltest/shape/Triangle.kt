package com.acorn.opengltest.shape

import android.opengl.GLES20
import com.acorn.opengltest.utils.loadShader
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
    private var program = 0

    private var vertexBuffer: FloatBuffer? = null
    private val COORDS_PER_VERTEX = 3
    //颜色RGBA
    private val color = floatArrayOf(0.63671875f, 0.76953125f, 0.22265625f, 1.0f)

    private var positionHandle = 0
    private var colorHandle = 0
    private var mMVPMatrixHandle=0
    private val vertexCount = triangleCoords.size / COORDS_PER_VERTEX
    //一个顶点占用空间,其中每个顶点单维值占4字节
    private val vertexStride = COORDS_PER_VERTEX * 4

    companion object{
        internal val triangleCoords = floatArrayOf( //三角形坐标,逆时针顺序
            0f, 0.622008459f, 0f, //上x,y,z
            -0.5f, -0.311004243f, 0f, //左下x,y,z
            0.5f, -0.311004243f, 0f //右下x,y,z
        )
    }

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

        val vertaxShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)
        //创建一个空的OpenGL ES程序
        program = GLES20.glCreateProgram()
        //将着色器添加到程序中
        GLES20.glAttachShader(program, vertaxShader)
        GLES20.glAttachShader(program, fragmentShader)
        //编译链接OpenGL ES程序
        GLES20.glLinkProgram(program)
    }

    public fun draw(mvpMatrix: FloatArray) {
        //将程序添加到OpenGL ES环境
        GLES20.glUseProgram(program)
        //获取顶点着色器vPosition属性(位置)的句柄
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition")
        //启用三角形顶点的句柄
        GLES20.glEnableVertexAttribArray(positionHandle)
        //准备三角形坐标数据
        GLES20.glVertexAttribPointer(
            positionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false, vertexStride, vertexBuffer
        )
        //获取片段着色器vColor(颜色)的句柄
        colorHandle=GLES20.glGetAttribLocation(program,"vColor")
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(colorHandle,1,color,0)
        //获取形状变换矩阵的句柄
        mMVPMatrixHandle=GLES20.glGetUniformLocation(program,"uMVPMatrix")
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle,1,false,mvpMatrix,0)
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES,0,vertexCount)
        //禁用顶点数组
        GLES20.glDisableVertexAttribArray(positionHandle)
    }
}