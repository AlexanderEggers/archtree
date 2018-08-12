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

    @JvmOverloads
    fun getBitmapFromResource(@DrawableRes imageRes: Int, bitmapOptions: BitmapFactory.Options? = null): Bitmap? {
        return BitmapFactory.decodeResource(context.resources, imageRes, bitmapOptions)
    }

    @JvmOverloads
    fun getBitmapFromFile(filePath: String, bitmapOptions: BitmapFactory.Options? = null): Bitmap? {
        return BitmapFactory.decodeFile(filePath, bitmapOptions)
    }

    @JvmOverloads
    fun getBitmapFromByteArray(byteArray: ByteArray, offset: Int, length: Int, bitmapOptions: BitmapFactory.Options? = null): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, offset, length, bitmapOptions)
    }
}