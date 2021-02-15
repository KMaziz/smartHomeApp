package com.example.onepacksmarthome.androidlogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onepacksmarthome.Login;
import com.example.onepacksmarthome.R;


public class main2 extends AppCompatActivity {


    private UserInfo userInfo;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInfo        = new UserInfo(this);
        userSession     = new UserSession(this);
      //  logout          = (Button)findViewById(R.id.logout);
       // tvUsername      = (TextView)findViewById(R.id.key_username);
       // tvEmail         = (TextView)findViewById(R.id.key_email);

        if(!userSession.isUserLoggedin()){
            startActivity(new Intent(this, Login.class));
            finish();
        }

        String username = userInfo.getKeyUsername();





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


    }

}
