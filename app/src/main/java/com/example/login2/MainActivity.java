package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName,pass,pass2;
    Button registerBtn,existingUserBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName        = (EditText) findViewById(R.id.loginUserName);
        pass            = (EditText) findViewById(R.id.loginPass);
        pass2           = (EditText) findViewById(R.id.editTextTextPassword2);
        registerBtn     = (Button)   findViewById(R.id.loginBtn);
        existingUserBtn = (Button) findViewById(R.id.existingUserBtn);
        DB = new DBHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String password = pass.getText().toString();
                String password2 = pass2.getText().toString();

                if (user==""||password.equals("")||password2.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter all  the fields",Toast.LENGTH_SHORT).show();
                else{
                    if (password.equals(password2)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,password);
                            if (insert==true){
                                Toast.makeText(MainActivity.this,"Registered succesfull",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else Toast.makeText(MainActivity.this,"Registered failed",Toast.LENGTH_SHORT).show();

                        }else Toast.makeText(MainActivity.this,"User already exist",Toast.LENGTH_SHORT).show();

                    }else Toast.makeText(MainActivity.this,"Passwords not matching",Toast.LENGTH_SHORT).show();
                }

            }
        });

        existingUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                //TODO this text id just for some change because I want see changes in git
            }
        });
    }
}