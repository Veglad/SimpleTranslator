package com.example.vlad.simpletranslator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private final String URL = "https://translate.yandex.net";
    private final String KEY = "trnsl.1.1.20180614T200307Z.7c31aefdb0a2d387.33b439a4fe005160d34009503c2b8e1a02d575e3";

    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create(gson)).
            baseUrl(URL).
            build();

    private Translator translator = retrofit.create(Translator.class);

    private boolean isFromRusToEng;

    Button btnTranslate, btnChangeLanguages;
    EditText etInput;
    TextView tvOutput, tvLanguagesInfo, tvOtherVariants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isFromRusToEng = true;


        etInput = (EditText)findViewById(R.id.etInputText);
        tvOutput = (TextView)findViewById(R.id.tvOutputText);
        tvLanguagesInfo = (TextView)findViewById(R.id.tvLanguagesInfo);
        tvLanguagesInfo.setText(getResources().getString(R.string.formRuToEng));
        tvOtherVariants = (TextView)findViewById(R.id.tvOtherVariants);

        btnTranslate = (Button)findViewById(R.id.btnTranslate);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etInput.getText().length() == 0)
                    return;

                Map<String, String> mapJson = new HashMap<>();
                mapJson.put("key", KEY);
                mapJson.put("text", etInput.getText().toString());
                mapJson.put("lang", isFromRusToEng?"ru-en":"en-ru");

                Call<Translation> call = translator.translate(mapJson);

                call.enqueue(new Callback<Translation>() {
                    @Override
                    public void onResponse(Call<Translation> call, Response<Translation> response) {
                        Translation translation = response.body();

                        if(translation.getText().size() != 0){
                            tvOutput.setText(translation.getText().get(0));

                            //Show other variants of the translation
                            String otherVariants = "";
                            for(int i = 1 ; i < translation.getText().size(); i++){
                                otherVariants += translation.getText().get(i) + "\n";
                            }
                            tvOtherVariants.setText(otherVariants);
                        }
                        else
                            tvOtherVariants.setText("Ошибка перевода");
                    }


                    @Override
                    public void onFailure(Call<Translation> call, Throwable t) {
                        tvOutput.setText("Ошибка перевода");
                    }
                });
            }
        });
        btnChangeLanguages = (Button)findViewById(R.id.btnChangeLanguages);
        btnChangeLanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromRusToEng = !isFromRusToEng;

                if(isFromRusToEng)
                    tvLanguagesInfo.setText(getResources().getString(R.string.formRuToEng));
                else
                    tvLanguagesInfo.setText(getResources().getString(R.string.fromEngToRus));
            }
        });
    }
}
