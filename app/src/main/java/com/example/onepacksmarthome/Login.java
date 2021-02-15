package com.example.onepacksmarthome;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onepacksmarthome.androidlogin.UserInfo;
import com.example.onepacksmarthome.androidlogin.UserSession;
import com.example.onepacksmarthome.androidlogin.Utils;
import com.example.onepacksmarthome.androidlogin.main2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class Login extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = Login.class.getSimpleName();
    private EditText email, password;
    private ImageView login;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email        = (EditText)findViewById(R.id.email);
        password        = (EditText)findViewById(R.id.editTextTextPassword);
        login           = (ImageView)findViewById(R.id.login);

        progressDialog  = new ProgressDialog(this);
        session         = new UserSession(this);
        userInfo        = new UserInfo(this);

        if(session.isUserLoggedin()){
            startActivity(new Intent(this, main2.class));
            finish();
        }

        login.setOnClickListener(this);
//        signup.setOnClickListener(this);
    }

    private void login(final String email, final String password){
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // Now store the user in SQLite
                        JSONObject user = jObj.getJSONObject("user");
                        String uName = user.getString("username");
                        String email = user.getString("name");

                        // Inserting row in users table
                        userInfo.setEmail(email);
                        userInfo.setUsername(uName);
                        session.setLoggedin(true);

                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        toast(errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    toast("Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
      //  AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_req);
        Volley.newRequestQueue(getApplicationContext()).add(strReq);
    }

    private void toast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {


                String uName = email.getText().toString().trim();
                String pass  = password.getText().toString().trim();

                login(uName, pass);



    }
}
