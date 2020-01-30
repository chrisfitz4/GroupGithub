package com.illicitintelligence.android.groupgithub.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.illicitintelligence.android.groupgithub.R;

public class UserFrag extends Fragment {

    public UserFrag(SillyFragment sillyFragmentInserted) {
        this.sillyFragment = sillyFragmentInserted;
    }

    private SillyFragment sillyFragment;

    public interface SillyFragment {
        void redrawCall();
    }

    private Button button;
    private TextView textViewww;

    public UserFrag() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_frag, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = button.findViewById(R.id.button_adduser);
        textViewww = textViewww.findViewById(R.id.users_txt);

        final String user = getArguments().getString("this user");
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

}
