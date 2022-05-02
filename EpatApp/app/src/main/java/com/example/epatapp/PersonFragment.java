package com.example.epatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PersonFragment extends Fragment {

    ListView listFunc;
    ArrayList<String> funcName = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFunc = view.findViewById(R.id.list_func);
        setListFunc();
    }

    private void setListFunc(){
        funcName.add("Thông tin cá nhân");
        funcName.add("Đổi mật khẩu");
        funcName.add("Đăng xuất");
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_activated_1,funcName);
        listFunc.setAdapter(adapter);
        listFunc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0://chọn tt cs nhân
                        Intent intent = new Intent(getContext(), PersonalActivity.class);
                        startActivity(intent);
                        break;
                    case 1://chọn đổi mk
                        Intent intent2 = new Intent(getContext(), ChangePasswordActivity.class);
                        startActivity(intent2);
                        break;
                    case 2://chọn đăng xuất
                        Intent intent3 = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
}
