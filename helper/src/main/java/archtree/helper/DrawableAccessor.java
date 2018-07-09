package archtree.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DrawableAccessor {

    private final Context context;

    @Inject
    public DrawableAccessor(Context context) {
        this.context = context;
    }

    public Drawable getDrawable(final @DrawableRes int resId) {
        return ContextCompat.getDrawable(context, resId);
    }
}
