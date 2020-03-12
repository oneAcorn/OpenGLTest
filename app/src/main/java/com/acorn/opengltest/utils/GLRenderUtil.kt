package com.acorn.opengltest.utils

import android.opengl.GLES20

/**
 * Created by acorn on 2020/3/12.
 */
fun loadShader(type: Int, shaderCode: String): Int {
    //创建顶点着色器类型（GLES20.GL_VERTEX_SHADER） 或片段着色器类型（GLES20.GL_FRAGMENT_SHADER）
    val shader: Int = GLES20.glCreateShader(type)
    //将源码添加到着色器并进行编译
    GLES20.glShaderSource(shader, shaderCode)
    GLES20.glCompileShader(shader)
    return shader
}