package com.tc.webapp01.controller.locale;


import java.util.Locale;
import java.util.ResourceBundle;

public final class LocaleManager {
    private final static LocaleManager instance = new LocaleManager();

    private static final ResourceBundle resourceBundleRU = ResourceBundle.getBundle(
            "prop", new Locale("ru","RU"));
    private static final ResourceBundle resourceBundleEN = ResourceBundle.getBundle(
            "prop", new Locale("en","EN"));

    public static LocaleManager getInstance() {
        return instance;
    }

    public ResourceBundle getResourceBundleRU() {
        return resourceBundleRU;
    }

    public ResourceBundle getResourceBundleEN() {
        return resourceBundleEN;
    }
}