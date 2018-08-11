package archtree.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.annotation.DrawableRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitmapAccessor
@Inject constructor(private val context: Context) {

    fun getBitmapFromResource(@DrawableRes imageRes: Int): Bitmap? {
        return BitmapFactory.decodeResource(context.resources, imageRes)
    }

    fun getBitmapFromResource(@DrawableRes imageRes: Int, bitmapOptions: BitmapFactory.Options): Bitmap? {
        return BitmapFactory.decodeResource(context.resources, imageRes, bitmapOptions)
    }

    fun getBitmapFromFile(filePath: String): Bitmap? {
        return BitmapFactory.decodeFile(filePath)
    }

    fun getBitmapFromFile(filePath: String, bitmapOptions: BitmapFactory.Options): Bitmap? {
        return BitmapFactory.decodeFile(filePath, bitmapOptions)
    }

    fun getBitmapFromByteArray(byteArray: ByteArray, offset: Int, length: Int): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, offset, length)
    }

    fun getBitmapFromByteArray(byteArray: ByteArray, offset: Int, length: Int, bitmapOptions: BitmapFactory.Options): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, offset, length, bitmapOptions)
    }
}