package com.users.College_scout;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.JsonObject;
import com.pixplicity.easyprefs.library.Prefs;
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

import static android.app.Activity.RESULT_OK;


public class AdditemFragment extends Fragment {
    ArrayList<String> category= new ArrayList<>();
    SpinnerDialog spinnerDialog;
    Context mcontext;
    TextView et_category,et_type;
    String choosen;
    Dialog dialog;
    Dialog dialog2;
    Button btn_photo,btn_upload;
    ImageView imageView;
    Bitmap bitmap;
    private static final int MY_PERMISSIONS_REQUEST=100;
    StorageReference mStorageRef;
    Uri img_uri;
    String imgUrl;
    boolean type;
    EditText foodname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        add_category();
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    private void upload_img() {
        final StorageReference ImageName=mStorageRef.child("Fooditems").child("img"+img_uri.getLastPathSegment());
        ImageName.putFile(img_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imgUrl= String.valueOf(uri);
                        Toast.makeText(getContext(), "Url is "+imgUrl, Toast.LENGTH_LONG).show();
                        add_item();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
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
        imageView=view.findViewById(R.id.img);
        btn_photo=view.findViewById(R.id.add_img);
        btn_upload=view.findViewById(R.id.button4);
        foodname=view.findViewById(R.id.etfoodname);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST);
        }

        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Intent myfileintent = new Intent();
                        myfileintent.setAction(Intent.ACTION_GET_CONTENT);
                        myfileintent.setType("image/*");
                        // myfileintent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        startActivityForResult(Intent.createChooser(myfileintent, "image"), 10);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_img();
                //add_item();
            }
        });

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

    private void add_item() {
        String category= et_category.getText().toString();
        String imgurl=imgUrl;
        String email= Prefs.getString("email","");
        String veg;
        if(type)
        veg= "true";
        else
            veg= "false";
        String itemname= foodname.getText().toString();
        String price="150";
        String size="None";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("image", imgurl);
        jsonObject.addProperty("category", category);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("isveg", veg);
        jsonObject.addProperty("itemName", itemname);
        JsonObject data=new JsonObject();
        data.addProperty("price", price);
        data.addProperty("size", size);
        jsonObject.add("price",data);
        Call<ResponseBody> call= Retroclient
                .getInstance()
                .getapi()
                .additem(jsonObject,"Bearer "+Prefs.getString("access_token",""));

       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               Toast.makeText(getContext(), "res_code: "+response.code(), Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case MY_PERMISSIONS_REQUEST:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Uri uri;
        if (resultCode == RESULT_OK && requestCode == 10 && data != null) {

                uri = data.getData();
               img_uri=uri;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                    imageView.setImageBitmap(bitmap);
                    btn_photo.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
        et_veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //String type = "Vegetarian";
                // Drawable drawable= ContextCompat.getDrawable(mcontext,R.drawable.ic_veg);
                et_veg.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.background));

              et_type.setText("Veg");
               et_type.setCompoundDrawables(ContextCompat.getDrawable(getContext(),R.drawable.ic_veg),null,null,null);
               et_type.setCompoundDrawablePadding(10);
               type=true;
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