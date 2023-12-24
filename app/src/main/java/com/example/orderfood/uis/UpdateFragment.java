package com.example.orderfood.uis;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderfood.R;
import com.example.orderfood.models.User;
import com.example.orderfood.models.database.DBHelper;
import com.example.orderfood.models.database.PrefManager;
import com.example.orderfood.models.database.UserRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    UserRepository userRepository;
    DBHelper dbHelper;
    NavController navController;
    TextView tvUsername;
    EditText edtFirstname,edtLastname,edtOldPassword,edtNewPassword,edtImage,edtPhoneNumber,edtAddress;
    Button btnUpdate;
    User user;

    public UpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance(String param1, String param2) {
        UpdateFragment fragment = new UpdateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=tvUsername.getText().toString().trim();
                String firstname=edtFirstname.getText().toString().trim();
                String lastname=edtLastname.getText().toString().trim();
                String oldPass=edtOldPassword.getText().toString().trim();
                String newPass=edtNewPassword.getText().toString().trim();
                String image=edtImage.getText().toString().trim();
                String phone=edtPhoneNumber.getText().toString().trim();
                String address=edtAddress.getText().toString().trim();
                if(username.length()<=0 ||
                        firstname.length()<=0 ||
                        lastname.length()<=0 ||
                        oldPass.length()<=0 ||
                        newPass.length()<=0 ||
                        phone.length()<=0 ||
                        address.length()<=0 ){
                    Toast.makeText(getContext(), "Please enter complete information!", Toast.LENGTH_SHORT).show();
                }
                else if(!oldPass.equals(user.getPassword())){
                    Toast.makeText(getContext(), "Incorrect password!", Toast.LENGTH_SHORT).show();
                }else {
                    if(image!=null){
                        User user1=new User(user.getId(),username,firstname,lastname,newPass,image,phone,address);
                        userRepository.updateUser(user1,user.getId());
                    }else{
                        User user1=new User(user.getId(),username,firstname,lastname,newPass,phone,address);
                        userRepository.updateUser(user1,user.getId());
                    }
                    Toast.makeText(getContext(), "Update successful!", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_updateFragment_to_userFragment);
                }
            }
        });
    }

    private void initView(View view) {
        edtAddress=view.findViewById(R.id.edtAddressUpdate);
        edtFirstname=view.findViewById(R.id.edtFirstnameUpdate);
        edtLastname=view.findViewById(R.id.edtLastnameUpdate);
        edtOldPassword=view.findViewById(R.id.edtOldPassword);
        edtNewPassword=view.findViewById(R.id.edtNewPassword);
        edtPhoneNumber=view.findViewById(R.id.edtPhoneNumberUpdate);
        edtImage=view.findViewById(R.id.edtImageUpdate);
        tvUsername=view.findViewById(R.id.tvUsernameUpdate);
        btnUpdate=view.findViewById(R.id.btnUpdate2);
        dbHelper=new DBHelper(getContext());
        userRepository=new UserRepository(dbHelper);
        user=userRepository.getUserByUsername(PrefManager.getString(getContext(),"username"));
        tvUsername.setText(user.getUsername());
        edtFirstname.setText(user.getFirstname());
        edtLastname.setText(user.getLastname());
        edtPhoneNumber.setText(user.getPhoneNumber());
        edtImage.setText(user.getImage());
        edtAddress.setText(user.getAddress());
    }
}