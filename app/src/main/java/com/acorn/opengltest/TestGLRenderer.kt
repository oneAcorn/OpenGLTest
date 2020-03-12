package com.acorn.opengltest

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * OpenGL渲染器
 * Created by acorn on 2020/3/11.
 */
class TestGLRenderer : GLSurfaceView.Renderer {
    override fun onDrawFrame(gl: GL10) {
        //以背景色刷新
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        //设置视图展示窗口全屏
        GLES20.glViewport(0,0,width,height)
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        //设置背景色
        GLES20.glClearColor(0.2f, 0.8f, 0.4f, 1.0f)
    }
}