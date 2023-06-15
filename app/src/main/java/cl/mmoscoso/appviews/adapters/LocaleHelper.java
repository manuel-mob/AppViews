package cl.mmoscoso.appviews.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LocaleHelper {
    public static void setLocale(Context context, String language) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}