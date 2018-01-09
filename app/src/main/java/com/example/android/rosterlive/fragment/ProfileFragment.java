package com.example.android.rosterlive.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.rosterlive.R;
import com.example.android.rosterlive.helper.SQLiteHandler;
import com.example.android.rosterlive.utilities.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.tv_nim)
    TextView tvNim;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_jurusan)
    TextView tvJurusan;

    private SQLiteHandler db;
    private SessionManager session;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        // SqLite database handler
        db = new SQLiteHandler(view.getContext());

        // session manager
        session = new SessionManager(view.getContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        tvNim.setText(user.get("username"));
        tvName.setText(user.get("name"));
        tvJurusan.setText(user.get("jurusan"));
        // Inflate the layout for this fragment
        return view;
    }

}
