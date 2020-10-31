package com.users.College_scout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.users.College_scout.Interface.Retroclient;
import com.users.College_scout.Request.ResetRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResetFragment extends Fragment {
    EditText et_email;
    Button sendotp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset, container, false);
        et_email = view.findViewById(R.id.et_remail);
        sendotp = view.findViewById(R.id.btn_remail);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        return view;
    }

    private void send() {
        String email = et_email.getText().toString();
        if (email.isEmpty()) {
            et_email.setError("Email is required");
            et_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Please enter a valid email id");
            et_email.requestFocus();
            return;
        } else {
            ResetRequest reset= new ResetRequest(email);
            Call<ResponseBody> call = Retroclient
                    .getInstance()
                    .getapi()
                    .resetotpemail(reset);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.isSuccessful()) {
                            String res = response.body().string();
                            JSONObject jsonObject = new JSONObject(res);
                            String msg= jsonObject.getString("message");
                            Toasty.success(getContext(), msg, Toast.LENGTH_LONG, true).show();
                            Fragment fragment= new ResetotpFragment();
                            FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.container, fragment).commit();
                        }
                        else
                        {
                            String error =response.errorBody().string();
                            Toasty.error(getContext(), error, Toast.LENGTH_LONG, true).show();

                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toasty.error(getContext(),t.getMessage(), Toast.LENGTH_LONG, true).show();
                }
            });
        }
    }
}