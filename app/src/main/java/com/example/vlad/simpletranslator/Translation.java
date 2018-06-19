package com.example.vlad.simpletranslator;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translation {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("text")
    @Expose
    private List<String> text = null;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

}