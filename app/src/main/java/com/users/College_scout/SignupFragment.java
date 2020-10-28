package com.users.College_scout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.users.College_scout.R;


public class SignupFragment extends Fragment {

    TextView login;
    EditText etpassword, etconfirmpassword;
    View view;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        etpassword= view.findViewById(R.id.etpassword);
        etconfirmpassword= view.findViewById(R.id.etconfirmpassword);
        btn= view.findViewById(R.id.btn_nxt1);

        etpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (etpassword.getRight() - etpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (etpassword.getTransformationMethod().getClass().getSimpleName() .equals("PasswordTransformationMethod")) {
                            etpassword.setTransformationMethod(new SingleLineTransformationMethod());
                        }
                        else {
                            etpassword.setTransformationMethod(new PasswordTransformationMethod());
                        }

                        etpassword.setSelection(etpassword.getText().length());
                        return true;
                    }
                }
                return false;
            }
        });
        etconfirmpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (etconfirmpassword.getRight() - etconfirmpassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        if (etconfirmpassword.getTransformationMethod().getClass().getSimpleName() .equals("PasswordTransformationMethod")) {
                            etconfirmpassword.setTransformationMethod(new SingleLineTransformationMethod());
                        }
                        else {
                            etconfirmpassword.setTransformationMethod(new PasswordTransformationMethod());
                        }

                        etconfirmpassword.setSelection(etconfirmpassword.getText().length());
                        return true;
                    }
                }
                return false;
            }
        });

        login= view.findViewById(R.id.tv_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LoginFragment();
                FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment).commit();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment= new OtpFragment();
                FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment).commit();
            }
        });

        return view;
    }
}