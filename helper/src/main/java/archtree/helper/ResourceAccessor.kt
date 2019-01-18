package archtree.helper

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.text.Html
import android.text.Spanned
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ResourceAccessor
@Inject constructor(private val context: Context) {

    /**
     * Generates a Spanned object for the given text.
     *
     * @param text Text which needs to be translated to a Spanned object
     * @return a new Spanned object
     * @since 1.0.0
     */
    @Suppress("DEPRECATION")
    open fun getHtmlText(text: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(text)
        }
    }

    /**
     * Returns a String based for the given resource and it's arguments.
     *
     * @param stringRes an Int value which will be used to retrieve the String
     * @param arguments Objects that can be attached to the retrieved String
     * @return a new String
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    @JvmOverloads
    open fun getString(@StringRes stringRes: Int, vararg arguments: Any? = emptyArray()): String {
        return context.getString(stringRes, *arguments)
    }

    /**
     * Returns a String based for the given resource, the amount and it's arguments.
     *
     * @param pluralRes an Int value which will be used to retrieve the String
     * @param amount    an Int value which will determine the correct plural String
     * @param arguments Objects that can be attached to the retrieved String
     * @return a new String
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    @JvmOverloads
    open fun getPlural(@PluralsRes pluralRes: Int, amount: Int, vararg arguments: Any? = emptyArray()): String {
        return context.resources.getQuantityString(pluralRes, amount, *arguments)
    }

    /**
     * Returns an Integer based for the given resource.
     *
     * @param integerRes Resource which will be used to retrieve the Integer
     * @return a new Integer
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getInteger(@IntegerRes integerRes: Int): Int {
        return context.resources.getInteger(integerRes)
    }

    /**
     * Returns an Boolean based for the given resource.
     *
     * @param boolRes an Int value which will be used to retrieve the Boolean
     * @return a new Boolean
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getBoolean(@BoolRes boolRes: Int): Boolean {
        return context.resources.getBoolean(boolRes)
    }

    /**
     * Returns an color int based for the given resource.
     *
     * @param colorRes an Int value which will be used to retrieve the color int
     * @return a new int
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }

    /**
     * Returns a Typeface int based for the given resource.
     *
     * @param fontRes an Int value which will be used to retrieve the Typeface
     * @return a new Typeface
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getFont(@FontRes fontRes: Int): Typeface? {
        return ResourcesCompat.getFont(context, fontRes)
    }

    /**
     * Returns a Drawable int based for the given resource.
     *
     * @param drawableRes an Int value which will be used to retrieve the Drawable
     * @return a new Drawable
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableRes)
    }

    /**
     * Returns a dimension pixel size for the given resource.
     *
     * @param res     an Int value which will be used to retrieve the pixel size
     * @return a new int
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getDimensionPixelSize(@DimenRes res: Int): Int {
        return context.resources.getDimensionPixelSize(res)
    }

    /**
     * Returns a dimension pixel offset for the given resource.
     *
     * @param res     an Int value which will be used to retrieve the pixel offset
     * @return a new int
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getDimensionPixelOffset(@DimenRes res: Int): Int {
        return context.resources.getDimensionPixelOffset(res)
    }

    /**
     * Returns an int array for the given resource.
     *
     * @param res     an Int value which will be used to retrieve the int array
     * @return a new int array
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getIntArray(@ArrayRes res: Int): IntArray {
        return context.resources.getIntArray(res)
    }

    /**
     * Returns a dimensional for the given resource.
     *
     * @param res     an Int value which will be used to retrieve the dimensional
     * @return a new float
     * @throws android.content.res.Resources.NotFoundException if the given resource does not exist.
     * @since 1.0.0
     */
    open fun getDimension(@DimenRes res: Int): Float {
        return context.resources.getDimension(res)
    }

    /**
     * Returns a string for the given resource.
     *
     * @param fileName name of the file which content should be converted into a string
     * @return a new string
     * @since 1.0.0
     */
    open fun getJsonStringFromFile(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { reader ->
                reader.readText()
            }
        } catch (ignore: Exception) {
            null
        }
    }
}
