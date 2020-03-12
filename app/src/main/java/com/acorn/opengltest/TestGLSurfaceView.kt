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
        //仅在绘图数据发生更改时才渲染视图
        //RENDERMODE_WHEN_DIRTY模式下,当渲染内容发生变化时不会主动刷新渲染效果,需要手动调用requestRender()
        renderMode = RENDERMODE_WHEN_DIRTY
    }
}