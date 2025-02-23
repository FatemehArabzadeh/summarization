package com.example.summarizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private Button summarizeButton;
    private TextView outputText;
    private ApiService apiService;
    private static  String API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        API_KEY = getResources().getString(R.string.gemini_api_key);
        inputText = findViewById(R.id.inputText);
        summarizeButton = findViewById(R.id.summarizeButton);
        outputText = findViewById(R.id.outputText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        summarizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextForSummarization();
            }
        });
    }

    private void sendTextForSummarization() {
        String userInput = inputText.getText().toString().trim();

        /*if (userInput.isEmpty()) {
            Toast.makeText(this, "لطفاً یک متن وارد کنید", Toast.LENGTH_SHORT).show();
            return;
        }*/


        String prompt = "Please provide only a summary of this text without any additional explanation. Make sure to preserve the important details of the text, and the number of words in the summary should be between forty to sixty percent of the total word count of the original text. The summary should be in the same language as the original message, regardless of the language. :\n" + userInput;

        SummarizeRequest request = new SummarizeRequest(prompt);
        Call<SummarizeResponse> call = apiService.summarizeText(API_KEY, request);

        call.enqueue(new Callback<SummarizeResponse>() {
            @Override
            public void onResponse(Call<SummarizeResponse> call, Response<SummarizeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String summary = response.body().getSummary();
                    outputText.setText(summary);
                } else {
                    outputText.setText("خطایی رخ داد: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SummarizeResponse> call, Throwable t) {
                outputText.setText("اتصال برقرار نشد: " + t.getMessage());
            }
        });
    }

}
