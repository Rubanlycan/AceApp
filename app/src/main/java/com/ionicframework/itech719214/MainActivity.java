package com.ionicframework.itech719214;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ionicframework.itech719214.fragment.About_fragment;
import com.ionicframework.itech719214.fragment.Home_fragment;
import com.ionicframework.itech719214.fragment.Subscribe_fragment;

public class MainActivity extends AppCompatActivity {


    TelephonyManager tm;
    String phoneNumber;
    TextView view_all;

    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMyPhoneNO();


        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null) {
            Toast.makeText(this, "connected to internet", Toast.LENGTH_SHORT).show();


        } else {
            AlertDialog.Builder netBuilder = new AlertDialog.Builder(MainActivity.this);
            netBuilder.setTitle("Network Error!");
            netBuilder.setIcon(R.drawable.network_error);
            netBuilder.setMessage("Please make sure device is connected to Internet!");
            netBuilder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(getIntent());
                }
            });
            netBuilder.setCancelable(false);
            netBuilder.show();
        }



        BottomNavigationView btm_nav_view = findViewById(R.id.bottom_navigation);

        btm_nav_view.setOnNavigationItemSelectedListener(nav_item);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Home_fragment()).commit();

        /*getData();
        getData_previous();*/

//        fetchData process = new fetchData();
//        process.execute();
//        Log.i("api", "onCreate: " + process.execute());

        /*txt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View_All_Activity.class);
                startActivity(intent);
            }
        });*/


        /*int images[] = {R.drawable.b, R.drawable.b2, R.drawable.b3};
        v_flipper = findViewById(R.id.v_flipper);


        for (int image : images) {
            flipImage(image);
        }
*/

//            for (int i = 0;i<4;i++)
//            {
//                View view = inflater.inflate(R.layout.item,gallery,false);
//                TextView text = view.findViewById(R.id.text);
//                TextView text2 = view.findViewById(R.id.text2);
//                text.setText("item"+i);
//                text2.setText("Date"+i);
//
//                ImageView img_slide = view.findViewById(R.id.img_slide);
//                img_slide.setImageResource(R.drawable.ic_android_black_24dp);
//
//                gallery.addView(view);
//            }


    }



    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        }
    }

    public boolean getMyPhoneNO() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String phonenumber;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {

                phonenumber = tm.getLine1Number();
                Log.i("tag", "isPhoneNumberPermissionGranted: " + tm.getLine1Number());
                return true;
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                Log.i("tag", "isPhoneNumberPermissionisRevoked ");
                return false;

            }
        } else { //permission is automatically granted on sdk<23 upon installation
            phonenumber = tm.getLine1Number();
            Log.i("tag", "isPhoneNumberAutomaticallyGranted " + phonenumber);
            return true;
        }
    }


    ///changes

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav_item = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.home:
                    selectedFragment = new Home_fragment();
                    break;
                case R.id.About:
                    selectedFragment = new About_fragment();
                    break;
                case R.id.subscribe:
                    selectedFragment = new Subscribe_fragment();
                    break;


            }
            return loadFragment(selectedFragment);
        }
    };

}






