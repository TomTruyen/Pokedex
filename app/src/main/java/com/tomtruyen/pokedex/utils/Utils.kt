package com.tomtruyen.pokedex.utils

import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Utils {
    companion object {
        fun getBitmapFromView(bmp: Bitmap?, cacheDir: File?): Uri? {
            var bmpUri: Uri? = null
            try {
                val file = File(cacheDir, System.currentTimeMillis().toString() + ".jpg")

                val out = FileOutputStream(file)
                bmp?.compress(Bitmap.CompressFormat.JPEG, 90, out)
                out.close()
                bmpUri = Uri.fromFile(file)

            } catch (e: IOException) {
                e.printStackTrace()
            }
            return bmpUri
        }
    }
}