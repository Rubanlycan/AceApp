package com.ionicframework.itech719214.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.ionicframework.itech719214.View_All_Activity;
import com.squareup.picasso.Picasso;
import com.ionicframework.itech719214.ApiClass;
import com.ionicframework.itech719214.Data;
import com.ionicframework.itech719214.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Subscribe_fragment extends Fragment implements BillingProcessor.IBillingHandler {

    Button sub_button;
    TextView txt_title,txt_date;
    ImageView cur_image;
    String url;
    BillingProcessor bp;
    private  static  final String  bill_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj2bqyboAT7u2CTC/mX7ajy+RHubmAFipgfRQnVxcOazdoOH1Gbb5T2RThT/Sth+1yfamZEhdEFBjps7cdLej+3SuCnvVNA2kPhZ6OXY2zaCQcwQVa+pJJrCiYgldZmj7z9gMsWetSflD9t6V+TowMvGFz7XCKicOyN7UKopm7vEoUaIjDBMAEMjei4jZhh24BpFr5ZIIxg7v0BaE2Nq+rtvweeBHgkdfy9P8gZkSI6PERPq+YXkF0eREOMnkPRRwQMA3HSjzWabRaSJthJrKubMHQTM/8UwlHg3FTq58EdPQwMisvymceayd7U37jAFiy7P8qJTGcpLJqEj84oWo6wIDAQAB";
       private final String[] ACE_SUBSCRIBE = {"aceupdate_subscription_1year","aceupdate_subscription_2year","aceupdate_subscription_3year"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.subscribe_fragment,container,false);

       txt_title = view.findViewById(R.id.sub_title);
       txt_date = view.findViewById(R.id.sub_date);
       cur_image = view.findViewById(R.id.current_image);
       sub_button = view.findViewById(R.id.subscribe_btn);
       sub_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("TAG","subscription"+bp.getSubscriptionListingDetails(ACE_SUBSCRIBE[1]));
               AlertDialog.Builder sub_dialog = new AlertDialog.Builder(getContext());
               View sub_view = getLayoutInflater().inflate(R.layout.subscribe_dialog,null);
               Button sub_1year = sub_view.findViewById(R.id.sub_1year);
               sub_1year.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       bp.subscribe((Activity) getContext(),ACE_SUBSCRIBE[0]);
                   }
               });
               Button sub_2year = sub_view.findViewById(R.id.sub_2year);
               sub_2year.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       bp.subscribe((Activity) getContext(),ACE_SUBSCRIBE[1]);
                   }
               });
               Button sub_3year = sub_view.findViewById(R.id.sub_3year);
               sub_3year.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       bp.subscribe((Activity) getContext(),ACE_SUBSCRIBE[2]);
                   }
               });
               sub_dialog.setView(sub_view);
               sub_dialog.create();
               sub_dialog.show();
           }
       });


    getData();

        bp = new BillingProcessor(getContext(), bill_key, this);
        bp.initialize();


        return view;
    }
    public void getData() {

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClass.LATEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        ApiClass api = retrofit.create(ApiClass.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<List<Data>> call = api.getProducts_Latest();

        //then finallly we are making the call using enqueue()
        //it takes callback interface as an argument
        //and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed
        //if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<List<Data>>() {


            @Override
            public void onResponse(Call<List<Data>> call, final Response<List<Data>> response) {
                Log.e("Response", response.message());

                if (response.isSuccessful()) {
                    txt_title.setText(response.body().get(0).getTitle());
                    txt_date.setText(response.body().get(0).getName());
                    Picasso.with(getContext())
                            .load("https://aceupdate.com/ACE_App/CoverPage/" + response.body().get(0).getImage())
                            .into(cur_image);

                    cur_image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            url = response.body().get(0).getLink();
                            Log.i("onresponse ", "url" + url);
                            Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                            Log.i("onClick: ", "url" + uri);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    });
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

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
            Log.i("tag", "onDestroy: destroyed ");
        }
        super.onDestroy();
    }
}
