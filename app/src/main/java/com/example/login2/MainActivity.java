package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        userName        = findViewById(R.id.loginUserName);
        pass            = findViewById(R.id.loginPass);
        pass2           = findViewById(R.id.editTextTextPassword2);
        registerBtn     = findViewById(R.id.loginBtn);
        existingUserBtn = findViewById(R.id.existingUserBtn);
        DB = new DBHelper(this);

        registerBtn.setOnClickListener(view -> {
            String user = userName.getText().toString();
            String password = pass.getText().toString();
            String password2 = pass2.getText().toString();

            if (user.equals("") ||password.equals("")||password2.equals(""))
                Toast.makeText(MainActivity.this,"Please enter all  the fields",Toast.LENGTH_SHORT).show();
            else{
                if (password.equals(password2)){
                    Boolean checkuser = DB.checkusername(user);
                    if (!checkuser){
                        Boolean insert = DB.insertData(user,password);
                        if (insert){
                            Toast.makeText(MainActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else Toast.makeText(MainActivity.this,"Registered failed",Toast.LENGTH_SHORT).show();

                    }else Toast.makeText(MainActivity.this,"User already exist",Toast.LENGTH_SHORT).show();

                }else Toast.makeText(MainActivity.this,"Passwords not matching",Toast.LENGTH_SHORT).show();
            }

        });

        existingUserBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            
        });
    }
}