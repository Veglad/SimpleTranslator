package com.example.vlad.simpletranslator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

public class TranslationAsyncTaskLoader extends AsyncTaskLoader<Translation> {
    /**
     * @param context used to retrieve the application context.
     */
    public TranslationAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Translation loadInBackground() {

        return null;
    }


}
