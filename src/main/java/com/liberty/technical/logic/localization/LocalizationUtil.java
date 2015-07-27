package com.liberty.technical.logic.localization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @author M-AKI.
 */
public class LocalizationUtil {
    private static Table<String, String, String> valueTable = HashBasedTable.create();
    private static ResourceBundle resourceBundle = null;

    private final static String TRANSLATION_LOC = "com.liberty.technical.logic.localization.locution";
    private final static String ENCODING = "UTF-8";
    private final static String EXTENSION = ".properties";


    private static void initializeBundle(Locale locale)
    {
        if (locale != null) {
            StringBuilder bundleName = new StringBuilder();
            bundleName.append(TRANSLATION_LOC.replace(".", "/")).
                    append("_").
                    append(locale.getLanguage().toUpperCase()).append(EXTENSION);
            try {
                InputStream is = LocalizationUtil.class.getClassLoader().getResourceAsStream(bundleName.toString());
                if (is != null) {
                    resourceBundle = new PropertyResourceBundle(new InputStreamReader(is, ENCODING));
                } else {
                    resourceBundle = ResourceBundle.getBundle(TRANSLATION_LOC);
                }
            } catch (IOException ex) {
                resourceBundle = ResourceBundle.getBundle(TRANSLATION_LOC);
            }
            putToValueTable(locale.getLanguage());
        }
    }


    private static void putToValueTable(String language)
    {
        Set<String> keys = resourceBundle.keySet();
        Iterator<String> it = keys.iterator();
        while(it.hasNext()) {
            String key = it.next();
            valueTable.put(language, key, resourceBundle.getString(key));
        }
    }


    public static String getString(String key, Locale locale)
    {
        String result = "";
        if (key != null && locale != null) {
            if (!valueTable.containsRow(locale.getLanguage())) {
                initializeBundle(locale);
            }
            result = valueTable.get(locale.getLanguage(), key);
        }
        return result == null ? "@" + key : result;
    }
}
