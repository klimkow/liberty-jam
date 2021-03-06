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
    private static LocalizationUtil instanse;
    private static Locale locale;
    private static Table<String, String, String> valueTable = HashBasedTable.create();
    private static ResourceBundle resourceBundle = null;
    private static String[] allowedLanguages = {"EN", "RU"};

    private final static String TRANSLATION_LOC = "locution";
    private final static String ENCODING = "UTF-8";
    private final static String EXTENSION = ".properties";

    private LocalizationUtil()
    {}


    public static LocalizationUtil getInstance(Locale newLocale)
    {
        locale = newLocale;
        if (instanse == null) {
            instanse = new LocalizationUtil();
        }
        return instanse;
    }


    private static void initializeBundle(Locale locale)
    {
        StringBuilder bundleName = new StringBuilder();
        bundleName.append(TRANSLATION_LOC.replace(".", "/"));
        if (!locale.getLanguage().toUpperCase().equals("RU")) {
            bundleName.append("_").
                    append(locale.getLanguage().toUpperCase());
        }
        bundleName.append(EXTENSION);
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


    private static void putToValueTable(String language)
    {
        Set<String> keys = resourceBundle.keySet();
        Iterator<String> it = keys.iterator();
        while(it.hasNext()) {
            String key = it.next();
            valueTable.put(language, key, resourceBundle.getString(key));
        }
    }


    public static String getString(String key)
    {
        String result = "";
        if (key != null) {
            if (locale == null) {
                locale = new Locale("RU");
            }
            if (!valueTable.containsRow(locale.getLanguage())) {
                initializeBundle(locale);
            }
            result = valueTable.get(locale.getLanguage(), key);
        }
        return result == null ? "@" + key : result;
    }


    public static List<String> getAllowedLanguages()
    {
        String currentLang = locale.getLanguage().toUpperCase();
        List<String> result = new ArrayList<>();
        for (String lang : allowedLanguages) {
            if(!currentLang.equals(lang)) {
                result.add(lang);
            }
        }
        return result;
    }


    public static String getCurrentLocale()
    {
        return locale.getLanguage().toUpperCase();
    }
}
