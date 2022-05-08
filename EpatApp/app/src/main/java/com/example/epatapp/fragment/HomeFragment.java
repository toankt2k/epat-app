package com.example.epatapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.epatapp.ChangePasswordActivity;
import com.example.epatapp.LoginActivity;
import com.example.epatapp.PersonalActivity;
import com.example.epatapp.R;

public class HomeFragment extends Fragment {
    private Button searchPatient, changePassword, personInfo, logOut;
    private SharedPreferences sharedPreferences;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("authenticated", Context.MODE_PRIVATE);
        searchPatient = view.findViewById(R.id.search_patient_btn);
        changePassword = view.findViewById(R.id.change_password_btn);
        personInfo = view.findViewById(R.id.person_info_btn);
        logOut = view.findViewById(R.id.logout_btn);
        searchPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment replaceFragment = (ReplaceFragment) getActivity();
                replaceFragment.replace(new PatientFragment());
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PersonalActivity.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putString("account", null).commit();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }
    public interface ReplaceFragment{
        public void replace(Fragment fragment);
    }
}
