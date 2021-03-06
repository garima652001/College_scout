package com.users.College_scout;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;
import com.pixplicity.easyprefs.library.Prefs;
import com.users.College_scout.Interface.Retroclient;
import com.users.College_scout.Request.Otpverify;
import com.users.College_scout.Request.Resendotp;
import com.users.College_scout.Respose.OtpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import ir.samanjafari.easycountdowntimer.CountDownInterface;
import ir.samanjafari.easycountdowntimer.EasyCountDownTextview;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpFragment extends Fragment {

    Button btn_verify;
    EditText etotp;
    TextView timer;
    private OtpView otpView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Prefs.Builder()
                .setContext(getContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName("Collegescout")
                .setUseDefaultSharedPreference(true)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_otp, container, false);
        btn_verify= view.findViewById(R.id.btn_verify);
        etotp= view.findViewById(R.id.et_otp);
        timer=view.findViewById(R.id.timer);
        otpView = view.findViewById(R.id.otp_view);
        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override public void onOtpCompleted(String otp) {
                // do Stuff
                btn_verify.setClickable(true);
            }
        });
        timer();

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendotp();
            }
        });
        return view;
    }

    private void timer() {
     
            new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("Time remaining: 00:" + millisUntilFinished / 1000);
                    timer.setClickable(false);
                }

                public void onFinish() {
                    timer.setText("Resend OTP");
                    timer.setClickable(true);
                }

            }.start();
        }
    

    private void resendotp() {
        String email = Prefs.getString("email","");
        Resendotp otpresend= new Resendotp(email);
        Call<ResponseBody> call = Retroclient
                .getInstance()
                .getapi()
                .resend_otp(otpresend);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                    Toasty.success(getContext(), "New OTP has been sent to your mail", Toast.LENGTH_LONG).show();
                }

                else {
                        String str = null;
                        str = response.errorBody().string();
                        Toasty.warning(getContext(), str, Toast.LENGTH_LONG, true).show();
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toasty.error(getContext(),t.getMessage(), Toast.LENGTH_LONG, true).show();
            }
        });

    }

    private void verify() {
        String otp = otpView.getText().toString();
        final String email = Prefs.getString("email","");
        Otpverify verify= new Otpverify(email,otp);
        Call<OtpResponse> call= Retroclient
                .getInstance()
                .getapi()
                .verifyuser(verify);

        call.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                //Toast.makeText(getActivity(),"on response",Toast.LENGTH_LONG).show();
                //Log.d("onresponse",response.toString());
                try {
                    if (response.code()==200)
                    {
                        Prefs.putBoolean("registered",true);
                        OtpResponse res = response.body();
                        String msg= res.getMessage();
                        String accesstoken = res.getSignAccessToken();
                        String refreshtoken = res.getRefreshToken();
                        Prefs.putString("access_token", accesstoken);
                        Prefs.putString("refresh_token", refreshtoken);
                        Prefs.putString("my_email",email);
                        //Prefs.putString("email","this is email");

                       /* String str = response.body().string();
                        JSONObject jsonObject = new JSONObject(str);
                        String message = jsonObject.getString("message");
                        String accesstoken = jsonObject.getString("signAccessToken");
                        String refreshtoken = jsonObject.getString("refreshToken");
                        Prefs.putString("access_token", accesstoken);
                        Prefs.putString("refresh_token", refreshtoken);*/

                        Toasty.success(getContext(), msg, Toast.LENGTH_LONG, true).show();
                        startActivity(new Intent(getActivity(),DetailActivity.class));
                    }
                    else
                    {
                        String str = response.errorBody().string();
                        Toasty.warning(getContext(), str, Toast.LENGTH_LONG, true).show();
                    }
                }
                catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                Toasty.error(getContext(),t.getMessage(), Toast.LENGTH_LONG, true).show();
            }
        });

    }
}