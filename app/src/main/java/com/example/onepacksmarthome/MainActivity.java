package com.example.onepacksmarthome;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onepacksmarthome.androidlogin.UserInfo;
import com.example.onepacksmarthome.androidlogin.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    public  MyRecyclerViewAdapter adapter;
    JSONObject aaaaaaaaaa;
    ArrayList<String> map = new ArrayList<>();
    RecyclerView recyclerView;
    private ImageView profile;

    private UserInfo userInfo;
    private UserSession userSession;
    private boolean bool= false;
    TextView   textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commande);

        userInfo        = new UserInfo(this);
        userSession     = new UserSession(this);
        //  logout          = (Button)findViewById(R.id.logout);
        // tvUsername      = (TextView)findViewById(R.id.key_username);
        // tvEmail         = (TextView)findViewById(R.id.key_email);
        profile = (ImageView) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), profile.class));

            }
        });

        if(!userSession.isUserLoggedin()){
            startActivity(new Intent(this, Login.class));
            finish();
        }

        String username = userInfo.getKeyEmail();

        textView = (TextView) findViewById(R.id.maison_name);

        textView.setText("Maison "+username);



//        String email    = userInfo.getKeyEmail();
//
//        tvUsername.setText(username);
//        tvEmail.setText(email);
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userSession.setLoggedin(false);
//                userInfo.clearUserInfo();
//                startActivity(new Intent(main2.this, Login.class));
//                finish();
//            }
//        });

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));



        load();



        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //your method
                if((bool==true)&&(MyRecyclerViewAdapter.bool==true))
                load2();
            }
        }, 0, 2000);
//               adapter.notifyDataSetChanged();

    }

    private void load2() {
        String updateProductUrl = Server.URL_LOGIN;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // converting the string to json array object
                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            try {
                                JSONObject product = array.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                aaaaaaaaaa =array.getJSONObject(i);
                                String ss = aaaaaaaaaa.getString("stat");

                                JSONObject jsonObject = new JSONObject(ss);


                                for (int j = 0; j <jsonObject.length() ; j++) {
                                    map.set(j,jsonObject.getString("stat_"+(j+1)));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            adapter.notifyDataSetChanged();

                        }

                        //creating adapter object and setting it to recyclerview

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
                ;

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }


    private void load() {


        String updateProductUrl = Server.URL_LOGIN;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // converting the string to json array object
                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            try {
                                JSONObject product = array.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                aaaaaaaaaa =array.getJSONObject(i);
                                String ss = aaaaaaaaaa.getString("stat");

                                JSONObject jsonObject = new JSONObject(ss);


                                for (int j = 0; j <jsonObject.length() ; j++) {
                                    map.add(jsonObject.getString("stat_"+(j+1)));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter = new MyRecyclerViewAdapter(getApplicationContext(), map);
                            recyclerView.setAdapter(adapter);
                            bool =true;


                        }

                        //creating adapter object and setting it to recyclerview

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
                ;

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
}