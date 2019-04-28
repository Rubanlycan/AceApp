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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ionicframework.itech719214.R;

public class About_fragment extends Fragment {

    ImageView fb_link,twitter_link,lkd_link,mail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.about_fragment,container,false);
        fb_link  = v.findViewById(R.id.fb_link);
        fb_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_browse("https://www.facebook.com/aceupdate/");
            }
        });
        twitter_link  = v.findViewById(R.id.twitter_link);
        twitter_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_browse("https://twitter.com/aceupdatemag");
            }
        });
       lkd_link  = v.findViewById(R.id.ldn_link);
       lkd_link.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               intent_browse("https://goo.gl/d3Sz7B");
           }
       });
       mail=v.findViewById(R.id.mail);
        TextView txt_mail = v.findViewById(R.id.mail_text);
      txt_mail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("Send email", "");
               String[] TO = {""};
               String[] CC = {""};
               Intent emailIntent = new Intent(Intent.ACTION_SEND);

               emailIntent.setData(Uri.parse("mailto:"));
               emailIntent.setType("text/plain");
               emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
               emailIntent.putExtra(Intent.EXTRA_CC, CC);
               emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
               emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

               try {
                   startActivity(Intent.createChooser(emailIntent, "Send mail..."));
               } catch (android.content.ActivityNotFoundException ex) {
                   Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
               }
           }
       });

                return v;
    }

    public void intent_browse(String url)
    {

        Log.i("onresponse ", "url" + url);
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Log.i("onClick: ", "url" + uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
