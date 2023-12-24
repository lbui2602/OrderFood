package com.example.orderfood.uis;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfood.R;
import com.example.orderfood.models.User;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.PrefManager;
import com.example.orderfood.models.database.UserRepository;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin;
    TextView tvRegister;
    DBHelper dbHelper;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtUsername.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                if(username!=null&&password!=null){
                    User user=userRepository.getUserByUsername(username);
                    if(user!=null){
                        if(user.getPassword().equals(password)){
                            PrefManager.saveString(LoginActivity.this,"username",username);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                        else{
                            //mat klhau sai
                            Toast.makeText(LoginActivity.this, "Incorrect pasword!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        //ten dang nhap sai
                        Toast.makeText(LoginActivity.this, "Username does not exist!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                goToRegister.launch(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> goToRegister = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        Intent intent= result.getData();
                        User user= (User)intent.getSerializableExtra("result");


                        if(user !=null){
                            edtUsername.setText(user.getUsername().toString());
                            edtPassword.setText(user.getPassword());
                        }
                        else{
                            edtUsername.setText("1234");
                        }
                        Toast.makeText(LoginActivity.this, "Register successful!", Toast.LENGTH_SHORT).show();
                    }
                    if(result.getResultCode()==RESULT_CANCELED){
                        Toast.makeText(LoginActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onStart() {
        super.onStart();
        if(PrefManager.getString(this,"username") != null ){
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void initData() {
        dbHelper=new DBHelper(this);
        userRepository=new UserRepository(dbHelper);
    }

    private void initView() {
        btnLogin=findViewById(R.id.btnLogin);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        tvRegister=findViewById(R.id.tvRegister);
    }
}