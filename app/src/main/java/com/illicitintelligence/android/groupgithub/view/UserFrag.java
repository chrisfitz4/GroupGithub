package com.illicitintelligence.android.groupgithub.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class UserFrag extends Fragment {


    public UserFrag() { }

    private Button button;
    private TextView textViewww;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ArrayList<String> listUsers;
    private SharedPreferences preferences;
    private final String User_KEY = "shared.preferences.users.key";
    String preferencesUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_frag, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.button_adduser);
        textViewww = view.findViewById(R.id.users_txt);
        recyclerView = view.findViewById(R.id.users_rv_userslayout);

        preferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);
        readFromSharedPreferences();

        String user = "";
        try {
            user = getArguments().getString("this user");
        } catch (NullPointerException n) {
            Log.d("TAG_X", "onViewCreated: " + n.getMessage());
        }
        if (user != null) {
            textViewww.setText(user);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: make this open a new fragment to add new user
            }
        });
    }

    private void readFromSharedPreferences() {
        preferencesUser = preferences.getString(User_KEY, "");
        if(preferencesUser.equals("")){
            //do nothing?
        }else{
            String[] splitUsers = preferencesUser.split(",");
            listUsers.addAll(Arrays.asList(splitUsers));
            userAdapter = new UserAdapter(listUsers);
            recyclerView.setAdapter(userAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        }

    }

}
