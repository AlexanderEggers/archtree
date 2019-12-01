package archtree.testing

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spanned
import archtree.helper.ResourceAccessor
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.ArgumentMatchers.anyString
import org.mockito.stubbing.Answer

fun mockResourceAccessor(resourceAccessor: ResourceAccessor) {
    `when`(resourceAccessor.getString(anyInt(), any())).thenAnswer { invocation ->
        val builder = StringBuilder()
        for (argument in invocation.arguments) {
            builder.append(argument)
        }
        builder.toString()
    }

    `when`(resourceAccessor.getHtmlText(anyString())).thenAnswer { invocation ->
        val spanned = mock(Spanned::class.java)
        `when`(spanned.toString())
                .thenReturn(invocation.arguments[0] as String)
        spanned
    }

    `when`(resourceAccessor.getPlural(anyInt(), anyInt(), any())).thenAnswer { invocation ->
        val builder = StringBuilder()
        for (argument in invocation.arguments) {
            builder.append(argument)
        }
        builder.toString()
    }

    `when`<Drawable>(resourceAccessor.getDrawable(anyInt())).thenAnswer { invocation ->
        val drawable = mock(Drawable::class.java)
        `when`(drawable.toString())
                .thenReturn((invocation.arguments[0] as Int).toString())
        drawable
    }

    `when`<Int>(resourceAccessor.getColor(anyInt())).thenAnswer { invocation ->
        invocation.arguments[0] as Int
    }

    `when`<Int>(resourceAccessor.getDimensionPixelOffset(anyInt())).thenAnswer { invocation ->
        invocation.arguments[0] as Int
    }

    `when`<Float>(resourceAccessor.getDimension(anyInt())).thenAnswer { invocation ->
        (invocation.arguments[0] as Int).toFloat()
    }

    `when`<Int>(resourceAccessor.getDimensionPixelSize(anyInt())).thenAnswer { invocation ->
        invocation.arguments[0] as Int
    }

    `when`<Int>(resourceAccessor.getInteger(anyInt())).thenAnswer { invocation ->
        invocation.arguments[0] as Int
    }

    `when`<Typeface>(resourceAccessor.getFont(anyInt())).thenAnswer { invocation ->
        val typeface = mock(Typeface::class.java)
        `when`(typeface.toString())
                .thenReturn(invocation.arguments[0] as String)
        typeface
    }
}

fun mockResourceAccessorGetIntArray(resourceAccessor: ResourceAccessor, answer: Answer<IntArray>) {
    `when`(resourceAccessor.getIntArray(anyInt())).thenAnswer(answer)
}

fun mockResourceAccessorGetBoolean(resourceAccessor: ResourceAccessor, answer: Answer<Boolean>) {
    `when`(resourceAccessor.getBoolean(anyInt())).thenAnswer(answer)
}


