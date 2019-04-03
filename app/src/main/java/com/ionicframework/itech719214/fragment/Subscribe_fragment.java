package com.ionicframework.itech719214.fragment;

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

public class Subscribe_fragment extends Fragment {

    Button sub_button;
    TextView txt_title,txt_date;
    ImageView cur_image;
    String url;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.subscribe_fragment,container,false);

       txt_title = view.findViewById(R.id.sub_title);
       txt_date = view.findViewById(R.id.sub_date);
       cur_image = view.findViewById(R.id.current_image);


    getData();

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
}
