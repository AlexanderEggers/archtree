package archtree.helper;

import android.content.Context;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.text.Html;
import android.text.Spanned;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TextAccessor {
    private final Context context;

    @Inject
    public TextAccessor(Context context) {
        this.context = context;
    }

    public String getText(final @StringRes int stringId, Object... objs) {
        return String.format(context.getString(stringId), objs);
    }

    public String getPlural(final @PluralsRes int pluralId, int count, Object... objs) {
        return String.format(context.getResources().getQuantityString(pluralId, count), objs);
    }

    public Spanned parseHtml(String html) {
        return Html.fromHtml(html);
    }
}
