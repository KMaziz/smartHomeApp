package com.example.onepacksmarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onepacksmarthome.androidlogin.UserInfo;

import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {

    private ImageView home ,saveuser,declineuser , savepass,declinepass ;
    private TextView username,maison,usernameupdatetext ,user1,pass2;
    private UserInfo userInfo;
    private EditText useredit , newpass,passactuelle,newpass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        home =(ImageView) findViewById(R.id.home1);

        usernameupdatetext =(TextView) findViewById(R.id.usernameupdatetext);

        username=(TextView)findViewById(R.id.username);
        user1 = (TextView) findViewById(R.id.user1);
        useredit = (EditText) findViewById(R.id.edituser4);


        pass2=(TextView)findViewById(R.id.pass2);
        newpass =(EditText)findViewById(R.id.newpass);
        newpass2 =(EditText)findViewById(R.id.newpass2);
        passactuelle =(EditText)findViewById(R.id.passactuelle);

        saveuser =(ImageView) findViewById(R.id.saveuser);
        declineuser = (ImageView) findViewById(R.id.declineuser);

        savepass =(ImageView) findViewById(R.id.savepass);
        declinepass = (ImageView) findViewById(R.id.declinepassword);


        user1.setVisibility(View.GONE);
        username.setVisibility(View.GONE);
        useredit.setVisibility(View.GONE);
        saveuser.setVisibility(View.GONE);
        declineuser.setVisibility(View.GONE);

        newpass.setVisibility(View.GONE);
        newpass2.setVisibility(View.GONE);
        passactuelle.setVisibility(View.GONE);
        declinepass.setVisibility(View.GONE);
        savepass.setVisibility(View.GONE);


        userInfo = new UserInfo(this);
        maison=(TextView) findViewById(R.id.maison_name);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        usernameupdatetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user1.getVisibility()==View.VISIBLE)
                {
                    user1.setVisibility(View.GONE);
                    username.setVisibility(View.GONE);
                    useredit.setVisibility(View.GONE);
                    saveuser.setVisibility(View.GONE);
                    declineuser.setVisibility(View.GONE);
                    pass2.animate().
                           translationYBy(-430).
                           setDuration(200).
                           start();
                    useredit.setText("");

                }
                else
                {   if(newpass.getVisibility()==View.VISIBLE)
                {
                    newpass.setVisibility(View.GONE);
                    newpass2.setVisibility(View.GONE);
                    passactuelle.setVisibility(View.GONE);
                    declinepass.setVisibility(View.GONE);
                    savepass.setVisibility(View.GONE);
                    newpass.setText("");
                    newpass2.setText("");
                    passactuelle.setText("");



                }
                    pass2.animate().
                            translationYBy(430).
                            setDuration(200).
                            start();

                    user1.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    useredit.setVisibility(View.VISIBLE);
                    saveuser.setVisibility(View.VISIBLE);
                    declineuser.setVisibility(View.VISIBLE);


                }
            }
        });
        pass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newpass.getVisibility()==View.VISIBLE)
                {
                    newpass.setVisibility(View.GONE);
                    newpass2.setVisibility(View.GONE);
                    passactuelle.setVisibility(View.GONE);
                    declinepass.setVisibility(View.GONE);
                    savepass.setVisibility(View.GONE);
                    newpass.setText("");
                    newpass2.setText("");
                    passactuelle.setText("");
                }
                else
                {   if(user1.getVisibility()==View.VISIBLE)
                    {
                        user1.setVisibility(View.GONE);
                        username.setVisibility(View.GONE);
                        useredit.setVisibility(View.GONE);
                        saveuser.setVisibility(View.GONE);
                        declineuser.setVisibility(View.GONE);
                        pass2.animate().
                                translationYBy(-430).
                                setDuration(20).
                                start();
                        useredit.setText("");


                    }

                    newpass.setVisibility(View.VISIBLE);
                    newpass2.setVisibility(View.VISIBLE);
                    passactuelle.setVisibility(View.VISIBLE);
                    declinepass.setVisibility(View.VISIBLE);
                    savepass.setVisibility(View.VISIBLE);
                }
            }
        });
        username.setText(userInfo.getKeyUsername());
        maison.setText("Maison "+ userInfo.getKeyEmail());



        declineuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user1.setVisibility(View.GONE);
                username.setVisibility(View.GONE);
                useredit.setVisibility(View.GONE);
                saveuser.setVisibility(View.GONE);
                declineuser.setVisibility(View.GONE);
                pass2.animate().
                        translationYBy(-430).
                        setDuration(200).
                        start();
                useredit.setText("");

            }
        });

        declinepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newpass.setVisibility(View.GONE);
                newpass2.setVisibility(View.GONE);
                passactuelle.setVisibility(View.GONE);
                declinepass.setVisibility(View.GONE);
                savepass.setVisibility(View.GONE);
                newpass.setText("");
                newpass2.setText("");
                passactuelle.setText("");
            }
        });


        saveuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(useredit.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"username est vide ",Toast.LENGTH_LONG).show();
                }
                else
                {
                    final String user = String.valueOf(useredit.getText());



                    String updateProductUrl = Server.UPDATE_USERNAME;

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //   Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

                                }
                            })
                    {
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("username",userInfo.getKeyUsername());
                            params.put("newusername",user);


                            return params;
                        }
                    };

                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


                }
            }
        });




        savepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((passactuelle.getText().toString().equals(""))||(newpass2.getText().toString().equals(""))||(newpass.getText().toString().equals("")))
                {
                    Toast.makeText(getApplicationContext(),"password est vide ",Toast.LENGTH_LONG).show();

                }
                else if (String.valueOf(newpass2.getText()).equals(newpass.getText().toString().equals("")))
                {
                    Toast.makeText(getApplicationContext(),"le mot de passe n'est pas identique ",Toast.LENGTH_LONG).show();

                }
                else {


                    final String passworduser = String.valueOf(passactuelle.getText());
                    final String newpassword = String.valueOf(newpass.getText());


                    String updateProductUrl = Server.UPDATE_PASSWORD;

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    //   Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

                                }
                            })
                    {
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("username",userInfo.getKeyUsername());
                            params.put("password", passworduser);
                            params.put("new_pass",newpassword);

                            return params;
                        }
                    };

                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

                }

            }
        });




  }
}