package archtree.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes

open class BitmapAccessor(private val context: Context) {

    @JvmOverloads
    open fun getBitmapFromResource(@DrawableRes imageRes: Int,
                                   bitmapOptions: BitmapFactory.Options? = null): Bitmap? {
        return BitmapFactory.decodeResource(context.resources, imageRes, bitmapOptions)
    }

    @JvmOverloads
    open fun getBitmapFromFile(filePath: String,
                               bitmapOptions: BitmapFactory.Options? = null): Bitmap? {
        return BitmapFactory.decodeFile(filePath, bitmapOptions)
    }

    @JvmOverloads
    open fun getBitmapFromByteArray(byteArray: ByteArray,
                                    offset: Int,
                                    length: Int,
                                    bitmapOptions: BitmapFactory.Options? = null): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, offset, length, bitmapOptions)
    }
}