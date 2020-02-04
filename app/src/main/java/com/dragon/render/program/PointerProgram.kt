package com.dragon.render.program

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class PointerProgram : BaseProgram() {
    override val vertexShader = """
            attribute vec2 vPosition;
            void main(){
                gl_Position = vec4(vPosition,1.0,1.0);
                gl_PointSize = 6.0 * 6.0;
            }
        """
    override val fragmentShader = """
             void main(){
                gl_FragColor = vec4(1.0,0.0,0.0,1.0);
            }
        """

    private val vPositionHandle: Int by lazy {
        GLES20.glGetAttribLocation(programHandle, "vPosition")
    }

    private val positionBuffer: FloatBuffer by lazy {
        val byteBuffer = ByteBuffer.allocateDirect(2 * 4)
        byteBuffer.order(ByteOrder.nativeOrder())
        val ret = byteBuffer.asFloatBuffer()
        ret.put(0.0f).put(0.0f).position(0)
        ret
    }

    fun setPosition(x: Float, y: Float) {
        positionBuffer.position(0)
        positionBuffer.put(x).put(y).position(0)
    }

    override fun drawContent() {
        GLES20.glEnableVertexAttribArray(vPositionHandle)
        GLES20.glVertexAttribPointer(vPositionHandle, 2, GLES20.GL_FLOAT, false, 2, positionBuffer)
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 1)
        GLES20.glDisableVertexAttribArray(vPositionHandle)
    }
}