package com.ionicframework.itech719214;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.ionicframework.itech719214.Adapter.RecyclerListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class View_All_Activity extends AppCompatActivity implements  BillingProcessor.IBillingHandler{

    View view;
    RelativeLayout view_relative;
    List<Data> previous_view_all;
    ImageView img_all;
    Button buy;

    LinearLayout gallery;
    LayoutInflater inflater;
    BillingProcessor bp;

    RecyclerView recyclerView;
    public final int view_j =2;

    private static final String bill_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj2bqyboAT7u2CTC/mX7ajy+RHubmAFipgfRQnVxcOazdoOH1Gbb5T2RThT/Sth+1yfamZEhdEFBjps7cdLej+3SuCnvVNA2kPhZ6OXY2zaCQcwQVa+pJJrCiYgldZmj7z9gMsWetSflD9t6V+TowMvGFz7XCKicOyN7UKopm7vEoUaIjDBMAEMjei4jZhh24BpFr5ZIIxg7v0BaE2Nq+rtvweeBHgkdfy9P8gZkSI6PERPq+YXkF0eREOMnkPRRwQMA3HSjzWabRaSJthJrKubMHQTM/8UwlHg3FTq58EdPQwMisvymceayd7U37jAFiy7P8qJTGcpLJqEj84oWo6wIDAQAB";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__all_);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refresh_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
            }
        });


        recyclerView = findViewById(R.id.recycler_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(
                new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                    viewLink(previous_view_all.get(position).link);

                    }
                })
        );




        bp = new BillingProcessor(View_All_Activity.this, bill_key, this);
        bp.initialize();

        view_relative = findViewById(R.id.view_relative);

        inflater = LayoutInflater.from(this);


        Data_previous();

    }



    private void Data_previous() {

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClass.Previous_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        ApiClass api = retrofit.create(ApiClass.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<Data>> call = api.getProducts_Previous();

        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<List<Data>>() {


            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                Log.e("Response", "response =" + response.message());
                if (response.isSuccessful()) {
                    previous_view_all = response.body();
                    recyclerView.setAdapter(new RecyclerListView(previous_view_all));


                }
            }


            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.e("Response", t.getMessage());
            }
        });

    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Toast.makeText(this, "successfully purchased", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Toast.makeText(this, "Failed in purchased", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        if (bp != null) {
            bp.release();
            Log.i("tag", "onDestroy: destroyed ");
        }
        super.onDestroy();
    }

    public void viewLink(String url) {
        try {
            Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
            Log.i("onClick: ", "url" + uri);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
//    private void shareIssueLink( String response,String date)
//    {
//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("text/plain");
//        String msg = "Read our Ace Update "+date+"issue for free\n";
//        i.putExtra(Intent.EXTRA_TEXT,msg + response);
//        startActivity(Intent.createChooser(i,"share link"));
//
//    }



    }

