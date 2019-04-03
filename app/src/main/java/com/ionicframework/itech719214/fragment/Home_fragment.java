package com.ionicframework.itech719214.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;
import com.ionicframework.itech719214.ApiClass;
import com.ionicframework.itech719214.Data;
import com.ionicframework.itech719214.R;
import com.ionicframework.itech719214.View_All_Activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home_fragment extends Fragment {

    View view;
    int s;
    SwipeRefreshLayout swipeRefreshLayout;
    ViewFlipper v_flipper;
    TextView txt_view, prev_txt, textView_title, textView_date;
    public static TextView data;
    LinearLayout gallery;
    int i;
    ImageView img_latest,img_slide,sub_ads;
    LayoutInflater inflater, gal_inflater;
    String url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_content, container, false);

        getData();
        getData_previous();

        gallery = view.findViewById(R.id.gallery);
        txt_view = view.findViewById(R.id.txt_view_all);
        prev_txt = view.findViewById(R.id.txt_previous);
        img_latest = view.findViewById(R.id.img_latest);
        textView_date = view.findViewById(R.id.textView_date);
        textView_title = view.findViewById(R.id.textView_title);
        gal_inflater = LayoutInflater.from(getContext());

        swipeRefreshLayout = view.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        txt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), View_All_Activity.class);
                startActivity(i);
            }
        });

        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.share:
                Toast.makeText(getContext(),"share",Toast.LENGTH_LONG).show();
                break;

            case R.id.feedback:
                Toast.makeText(getContext(),"feedback",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
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
                    textView_title.setText(response.body().get(0).getTitle());
                    textView_date.setText(response.body().get(0).getName());
                    Log.i("tag", "onResponse: "+ "https://aceupdate.com/ACE_App/CoverPage/"+response.body().get(0).getImage());
                  Picasso.with(getContext()).load("https://aceupdate.com/ACE_App/CoverPage/"+response.body().get(0).getImage()).into(img_latest);


                  img_latest.setOnLongClickListener(new View.OnLongClickListener() {
                      @Override
                      public boolean onLongClick(View v) {
                       shareIssueLink(response);
                      return true;
                      }
                  });

                    img_latest.setOnClickListener(new View.OnClickListener() {
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

    private void getData_previous() {


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
            public void onResponse(Call<List<Data>> call, final Response<List<Data>> response) {
                Log.e("Response", response.message());
                if (response.isSuccessful()) {
                    for ( i = 0; i <= 2; i++) {

                        View gview = gal_inflater.inflate(R.layout.item, gallery, false);
                        TextView text = gview.findViewById(R.id.prev_title);
                        text.setText(response.body().get(i).getTitle());
                        TextView text2 = gview.findViewById(R.id.prev_date);
                        text2.setText(response.body().get(i).getName());
                          img_slide = gview.findViewById(R.id.img_slide);
                        Picasso.with(getContext())
                                .load("https://aceupdate.com/ACE_App2/CoverPage2/" + response.body().get(i).getImage())
                                .into(img_slide);
                         img_slide.setOnLongClickListener(new View.OnLongClickListener() {
                             @Override
                             public boolean onLongClick(View v) {


                                 return true;
                             }
                         });
                        gallery.addView(gview);

                    }

                }
            }


            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.e("Response", t.getMessage());
            }
        });

    }

    public void flipImage(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);

    }
    private void shareIssueLink( Response<List<Data>> response)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,response.body().get(0).getLink());
        startActivity(Intent.createChooser(i,"share link"));

    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            img_latest.setImageBitmap(bitmap);
            Log.i("tag", "StringToBitMap: "+bitmap);
            return bitmap;

        } catch (Exception e) {
            e.getMessage();
            return null;
        }


    }
}
