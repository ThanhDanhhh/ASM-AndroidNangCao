package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class Fragment_Social extends Fragment {
    TextView tv;
    Button btnShareDialog;
    ImageView imgShare;
    CallbackManager callbackManager;
    ImageView profilePictureView;
    Bitmap image;
    SharePhoto photo;
    ShareDialog shareDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_fragment__social,container,false);
        tv=view.findViewById(R.id.tv);
        imgShare=view.findViewById(R.id.imgShare);
        btnShareDialog=view.findViewById(R.id.btnShareDialog);
        profilePictureView = view. findViewById(R.id.friendProfilePicture);
        shareDialog = new ShareDialog(getActivity());
        FacebookSdk.sdkInitialize(getActivity());

        btnShareDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get Drawable from imageView
                Drawable drawable = imgShare.getDrawable();
                Bitmap bmp = null;
                if (drawable instanceof BitmapDrawable){
                    bmp = ((BitmapDrawable) imgShare.getDrawable()).getBitmap();
                    Log.d("t","co bitmap");
                }else Log.d("t","bitmap null");

                photo = new SharePhoto.Builder()
                        .setCaption("Photo Test")
                        .setBitmap(bmp)
                        .build();
                // only share image
                SharePhotoContent content1 = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                shareDialog.show(getActivity(),content1);
            }
        });
        //Share link with ShareButton
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=dLQe4qEfVJw&list=RDKZWDE9LLw2g&index=24"))
                .build();



        ShareButton shareButton = view.findViewById(R.id.btnShare);
        shareButton.setShareContent(content);

        // Login

        callbackManager = CallbackManager.Factory.create();

//        setlogin_face();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {


                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location,name"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError");
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });
        return view;
    }




    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");


            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");

                bundle.putString("profile_pic", profile_pic.toString());

                Glide.with(getActivity()).load(profile_pic.toString()).into(profilePictureView);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));
            if (object.has("name"))
                bundle.putString("name", object.getString("name"));

            return bundle;
        }
        catch(JSONException e) {
            Log.d("TAG","Error parsing JSON");
        }
        return null;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d("FRAGMENT", "onResultCalled");
    }

}