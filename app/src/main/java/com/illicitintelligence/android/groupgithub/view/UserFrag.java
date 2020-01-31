package com.illicitintelligence.android.groupgithub.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.illicitintelligence.android.groupgithub.R;
import com.illicitintelligence.android.groupgithub.adapter.Swiper;
import com.illicitintelligence.android.groupgithub.adapter.UserAdapter;
import com.illicitintelligence.android.groupgithub.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFrag extends Fragment implements UserAdapter.ChooseUserDelegate {

    public interface ColorChangedDelegate {
        void rereadSharedPreferences();
        void notifyDeletionFromSharedP(String user);
    }

    public UserFrag(ColorChangedDelegate delegate) {
        this.delegate = delegate;
    }

    @BindView(R.id.save_button_usersfrag)
    Button saveButton;
    private Button button;
    private TextView textViewww;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ConstraintLayout innerLayout;
    private TextView themeSelection;
    private ArrayList<String> listUsers = new ArrayList<>();
    private SharedPreferences preferences;
    String preferencesUser;
    int color = 0;
    ColorChangedDelegate delegate;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction()!=null&&intent.getAction().equals("reread_shared_preferences")){
                readFromSharedPreferences();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_frag, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        this.getContext().registerReceiver(receiver,new IntentFilter("reread_shared_preferences"));

        button = view.findViewById(R.id.button_adduser);
        textViewww = view.findViewById(R.id.users_txt);
        recyclerView = view.findViewById(R.id.users_rv_userslayout);
        innerLayout = view.findViewById(R.id.inner_layout_userfrag);
        themeSelection = view.findViewById(R.id.theme_selection_textview);
        Swiper swiper = new Swiper(new Swiper.SwipeListener() {
            @Override
            public void itemSelected(int position) {
                String username = userAdapter.removeItem(position);
                userAdapter.notifyDataSetChanged();
                removeItemFromSharedP(username);
            }
        });
        ItemTouchHelper touchHelper = new ItemTouchHelper(swiper);
        touchHelper.attachToRecyclerView(recyclerView);

        preferences = getContext().getSharedPreferences(Constants.DUMMY_SHAREDPREFERENCES, Context.MODE_PRIVATE);
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
                LoginFragment login = new LoginFragment();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.expand_center, R.anim.collapse_center, R.anim.expand_center, R.anim.collapse_center)
                        .addToBackStack(login.getTag())
                        .add(R.id.frame_layout_userfrag, login)
                        .commit();
            }
        });
    }

    private void readFromSharedPreferences() {
        preferencesUser = preferences.getString(Constants.DUMMY_SHAREDPREFERENCES_KEY, "");
        if (preferencesUser.equals("")) {
            //do nothing?
        } else {
            listUsers = new ArrayList<>();
            String[] splitUsers = preferencesUser.split(",");
            listUsers.addAll(Arrays.asList(splitUsers));
            userAdapter = new UserAdapter(listUsers, this);
            recyclerView.setAdapter(userAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        }
    }

    private void removeItemFromSharedP(String item) {
        Log.d("TAG_X", "removeItemFromSharedP: " + preferencesUser);
        String[] split = preferencesUser.split(item);
        String[] splitLeadginComma = preferencesUser.split(","+item);
        String[] splitTrailingComma = preferencesUser.split(item+",");
        String[] splitLeadingAndTrailingComma = preferencesUser.split(","+item+",");

        if (splitLeadingAndTrailingComma.length == split.length) {
            split= splitLeadingAndTrailingComma;
        }else if(splitLeadginComma.length == split.length){
            split= splitLeadginComma;
        }else if(splitTrailingComma.length == split.length){
            split = splitTrailingComma;
        }

        if (split.length == 0) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Constants.DUMMY_SHAREDPREFERENCES_KEY, "");
            editor.apply();
            editor.clear();
            return;
        }
        for (int i = 0; i < split.length; i++) {
            Log.d("TAG_X", "removeItemFromSharedP: " + split[i]);
        }
        String toReturn = split[0];

        for (int i = 1; i < split.length; i++) {
            toReturn += ',' + split[i];
        }
        while (toReturn.charAt(0) == ',') {
            toReturn = toReturn.substring(1);
        }
        while(toReturn.charAt(toReturn.length()-1)==','){
            toReturn= toReturn.substring(0,toReturn.length()-1);
        }
        Log.d("TAG_X","toReturn: " + toReturn);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.DUMMY_SHAREDPREFERENCES_KEY, toReturn);
        preferencesUser = toReturn;
        delegate.notifyDeletionFromSharedP(item.split("\\.")[0]);
        editor.apply();
        editor.clear();
    }

    @Override
    public void clickUser(String username) {
        String[] userColor = username.split("\\.");
        textViewww.setText(userColor[0]);
        innerLayout.setVisibility(View.VISIBLE);
        themeSelection.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.color00, R.id.color01, R.id.color02,
            R.id.color10, R.id.color11, R.id.color12,
            R.id.color20, R.id.color21, R.id.color22})
    public void onClickColor(View view) {
        color = ((ColorDrawable) view.getBackground()).getColor();
        Log.d("TAG_X", "onClickColor: " + color);
        saveButton.setVisibility(View.VISIBLE);
        saveButton.getBackground().setTint(color);
    }

    @OnClick(R.id.save_button_usersfrag)
    public void saveSelection(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        String input = "";
        for (int i = 0; i < listUsers.size(); i++) {
            String[] oneUser = listUsers.get(i).split("\\.");
            input += oneUser[0] + ".";
            if (oneUser[0].equals(textViewww.getText().toString())) {
                oneUser[1] = "" + color;
                Log.d("TAG_X", oneUser[1]);
            }
            input += oneUser[1];
            input += ",";
        }
        Log.d("TAG_X", input);
        input = input.substring(0, input.length() - 1);
        if(input.charAt(0)==','){
            input=input.substring(1);
        }
        if(input.charAt(input.length()-1)==','){
            input=input.substring(0,input.length()-1);
        }
        editor.putString(Constants.DUMMY_SHAREDPREFERENCES_KEY, input);
        editor.commit();
        editor.clear();
        delegate.rereadSharedPreferences();
        userAdapter.setUsers(input.split(","));
        userAdapter.notifyDataSetChanged();
        innerLayout.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        themeSelection.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        if(this.getContext()!=null){
            this.getContext().unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
}
