package com.example.orderfood.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfood.R;
import com.example.orderfood.models.User;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.UserRepository;

import java.io.Serializable;

public class RegisterActivity extends AppCompatActivity {
    EditText edtFirstname,edtLastname,edtUsername2,edtPassword2,edtPasswordConfirm,edtImage,edtPhoneNumber,edtAddress;
    TextView tvFirstname,tvLastname,tvUsername2,tvPassword2,tvPasswordConfirm,tvImage,tvPhoneNumber,tvAddress;
    Button btnRegister;
    DBHelper dbHelper;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        Log.d("TAG", "onCreate: bat dau chua click");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String firstname=edtFirstname.getText().toString().trim();
                    String lastname=edtLastname.getText().toString().trim();
                    String username=edtUsername2.getText().toString().trim();
                    String password=edtPassword2.getText().toString().trim();
                    String passwordConfirm=edtPasswordConfirm.getText().toString().trim();
                    String image=edtImage.getText().toString().trim();
                    String phoneNumber=edtPhoneNumber.getText().toString().trim();
                    String address=edtAddress.getText().toString().trim();

                    if(firstname.length()!=0 && lastname.length()!=0 && username.length()!=0 &&password.length()!=0 &&passwordConfirm.length()!=0 &&image.length()!=0 &&phoneNumber.length()!=0 && address.length()!=0){
                        if(passwordConfirm.equals(password)){
                            User user=new User(username,firstname,lastname,password,image,phoneNumber,address);
                            userRepository.addUser(user);
                            Intent intent=new Intent();
                            intent.putExtra("result",user);
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }else{
                        if(firstname.length()==0){
                            tvFirstname.setText("Please do not leave it blank!");
                        }
                        else if(lastname.length()==0){
                            tvLastname.setText("Please do not leave it blank!");
                            tvFirstname.setText("");
                        }
                        else if(username.length()==0){
                            tvUsername2.setText("Please do not leave it blank!");
                            tvLastname.setText("");
                        }
                        else if(password.length()==0){
                            tvPassword2.setText("Please do not leave it blank!");
                            tvUsername2.setText("");
                        }
                        else if(passwordConfirm.length()==0){
                            tvPasswordConfirm.setText("Please do not leave it blank!");
                            tvPassword2.setText("");
                        }
                        else if(image.length()==0){
                            tvImage.setText("Please do not leave it blank!");
                            if(passwordConfirm.equals(password)){
                                tvPasswordConfirm.setText("");
                            }else{
                                tvPasswordConfirm.setText("Passwords do not match!");
                            }
                        }
                        else if(phoneNumber.length()==0){
                            tvPhoneNumber.setText("Please do not leave it blank!");
                            tvImage.setText("");
                        }
                        else if(address.length()==0){
                            tvAddress.setText("Please do not leave it blank!");
                            tvPhoneNumber.setText("");
                        }
                    }
                }catch (Exception ex){

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }

    private void initView() {
        edtFirstname=findViewById(R.id.edtFirstname);
        edtLastname=findViewById(R.id.edtLastname);
        edtUsername2=findViewById(R.id.edtUsername2);
        edtPassword2=findViewById(R.id.edtPassword2);
        edtPasswordConfirm=findViewById(R.id.edtPasswordConfirm);
        edtImage=findViewById(R.id.edtImage);
        edtPhoneNumber=findViewById(R.id.edtPhoneNumber);
        edtAddress=findViewById(R.id.edtAddress);

        tvFirstname=findViewById(R.id.tvFirstname);
        tvLastname=findViewById(R.id.tvLastname);
        tvUsername2=findViewById(R.id.tvUsername2);
        tvPassword2=findViewById(R.id.tvPassword2);
        tvPasswordConfirm=findViewById(R.id.tvPasswordConfirm);
        tvImage=findViewById(R.id.tvImage);
        tvPhoneNumber=findViewById(R.id.tvPhoneNumber);
        tvAddress=findViewById(R.id.tvAddress);

        btnRegister=findViewById(R.id.btnRegister);

        dbHelper=new DBHelper(this);
        userRepository=new UserRepository(dbHelper);
    }
}