package com.example.vlad.simpletranslator;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Translator {

    @FormUrlEncoded
    @POST("/api/v1.5/tr.json/translate")
    Call<Translation> translate(@FieldMap Map<String, String> map);
}
