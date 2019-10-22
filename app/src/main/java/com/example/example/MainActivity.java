package com.example.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.example.Adapter.RecyClerViewAdapter;
import com.example.example.Adapter.SearchVO;
import com.example.example.Api.APIUrl;
import com.example.example.Api.Data.Image;
import com.example.example.Api.Data.ImageDocument;
import com.example.example.Api.RetrofitService;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout inputLayout;
    private EditText editText;
    private RecyclerView recyclerView;
    private RecyClerViewAdapter recyClerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Gson gson;
    private Context context;
    private Call<ImageDocument> callQueue;

    RetrofitService retrofitService;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();
        gson = new Gson();
        inputLayout = (TextInputLayout) findViewById(R.id.input_layout);
        progressDialog  = new ProgressDialog(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        editText = inputLayout.getEditText();
        gson = new Gson();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();

        retrofitService = retrofit.create(RetrofitService.class);
        progressDialog.setMessage("로딩중....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                progressDialog.show();
                retrofitService.requestSearchImage(editable.toString()).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()) {
                            ImageDocument[] array = gson.fromJson(response.body().get("documents").toString(), ImageDocument[].class);
                            List<ImageDocument> list = Arrays.asList(array);
                            ArrayList<SearchVO> ids = new ArrayList<>();
                            for(int i = 0; i < list.size(); i++){
                                String thumbnail_url = list.get(i).thumbnail_url;
                                ids.add(new SearchVO(thumbnail_url));
                            }
                            recyClerViewAdapter = new RecyClerViewAdapter(context, ids);
                            mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(recyClerViewAdapter);
                        }else{
                            recyclerView.setLayoutManager(null);
                            Toast.makeText(context, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "에러", Toast.LENGTH_SHORT).show();
                        Log.d("tiger", "onFailure: "+t.toString());
                    }
                });


            }
        });
    }



    private HttpLoggingInterceptor httpLoggingInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                android.util.Log.e("MyGitHubData :", message + "");
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
