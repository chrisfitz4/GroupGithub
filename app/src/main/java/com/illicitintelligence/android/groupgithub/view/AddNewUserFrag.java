package com.illicitintelligence.android.groupgithub.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.illicitintelligence.android.groupgithub.R;

public class AddNewUserFrag extends Fragment {





    public AddNewUserFrag(Addfrag addfrag) {
        this.addfrag = addfrag;
    }

    private Addfrag addfrag;

    public interface Addfrag {

        void redrawCall();

    }

    private Button buttonadd;
    private EditText editText;
    private ImageView imageView;

    public AddNewUserFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.adduser_frag, container, false);

        return view;

    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    buttonadd = buttonadd.findViewById(R.id.button_addnewuser);
    editText = editText.findViewById(R.id.addnewusers_txt);
    final String newuser = getArguments().getString("new user") ;
            if (newuser != null) {
                editText.setText(newuser);
            }
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
