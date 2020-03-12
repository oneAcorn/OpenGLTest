package com.acorn.opengltest

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

/**
 * Created by acorn on 2020/3/11.
 */
class TestGLSurfaceView(context: Context, attributeSet: AttributeSet) : GLSurfaceView(context, attributeSet) {
    init {
        //使用OpenGL ES 2.0
        setEGLContextClientVersion(2)
        setRenderer(TestGLRenderer())
    }
}