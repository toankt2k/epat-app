package com.example.epatapp;

import android.app.Activity;
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
        setComponent(view);
        return view;
    }

    private void setComponent(View view){
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
                        break;
                    case 2://chọn đổi mk
                        break;
                    case 3://chọn đăng xuất
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
