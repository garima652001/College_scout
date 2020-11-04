package com.users.College_scout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.users.College_scout.Interface.Retroclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdditemFragment extends Fragment {
    ArrayList<String> category= new ArrayList<>();
    SpinnerDialog spinnerDialog;
    Context mcontext;
    TextView et_category,et_type;
    String choosen;
    Dialog dialog;
    Dialog dialog2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add_category();
    }

    private void add_category() {
        Call<ResponseBody> call= Retroclient
                .getInstance()
                .getapi()
                .getcategory();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        JSONArray jsonArray = new JSONArray(str);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String foodcategory = jsonObject.getString("category");
                            category.add(foodcategory);
                        }
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

                @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.additem, container, false);
        dialog= new Dialog(getContext());
        dialog2= new Dialog(getContext());

        et_category=view.findViewById(R.id.etcategory);
        et_type= view.findViewById(R.id.ettype);
        et_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                dialog_type();
            }
        });


        et_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_category();
                spinnerDialog=new SpinnerDialog(getActivity(),category,"Select category","Close Button Text");// With No Animation
                //spinnerDialog=new SpinnerDialog(getActivity(),category,"Select category",R.style.DialogAnimations_SmileWindow,"Close Button Text");// With Animation

                spinnerDialog.setCancellable(true);
                spinnerDialog.setShowKeyboard(false);// for open keyboard by default
                //spinnerDialog.setItemColor(Color.parseColor("#263238"));
                spinnerDialog.showSpinerDialog();
                spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                    @Override
                    public void onClick(String item, int position) {
                        choosen = item;
                        category.clear();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                et_category.setText(choosen);
                                et_category.setTextColor(getResources().getColor(R.color.black));
                            }
                        });
                    }
                });

            }
        });


        return view;
    }

    private void dialog_type() {
        dialog.setContentView(R.layout.type_dialog);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.bg2));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        final TextView et_veg,et_nonveg;
        Button done_type;
        et_veg=dialog.findViewById(R.id.veg);
        et_nonveg=dialog.findViewById(R.id.non_veg);
        done_type=dialog.findViewById(R.id.button4);
        final String type_final;
        Drawable drawable_final;
        int c;
        et_veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //String type = "Vegetarian";
                //Drawable drawable= ContextCompat.getDrawable(mcontext,R.drawable.ic_veg);
                et_veg.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.background));

              et_type.setText("Veg");
               et_type.setCompoundDrawables(ContextCompat.getDrawable(getContext(),R.drawable.ic_veg),null,null,null);
               et_type.setCompoundDrawablePadding(10);
            }
        });
        et_nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nonveg.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.background));

                et_type.setText("Non-veg");
                et_type.setCompoundDrawables(ContextCompat.getDrawable(getContext(),R.drawable.ic_nonveg),null,null,null);
                et_type.setCompoundDrawablePadding(10);
            }
        });

        done_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}