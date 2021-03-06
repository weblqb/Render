package com.dragon.render.texture

import android.graphics.Bitmap
import com.dragon.render.utils.OpenGlUtils

class BitmapTexture(private val bitmap: Bitmap) : BasicTexture(bitmap.width, bitmap.height) {
    init {
        textureId = OpenGlUtils.createBitmapTexture(bitmap)
    }

    override fun release() {
        super.release()
        OpenGlUtils.releaseTexture(textureId)
        bitmap.recycle()
    }

    fun hasAlpha() = bitmap.hasAlpha()
}