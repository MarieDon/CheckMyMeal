package ie.app.checkmymeal1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FacebookActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    TextView txtEmail, txtBirthday, txtFriends, txtView;
    ProgressDialog mDialog;
    ImageView imgAvatar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);



        callbackManager = CallbackManager.Factory.create();
        txtEmail = findViewById(R.id.txtEmail);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtFriends = findViewById(R.id.txtFriends);
        txtView = findViewById(R.id.hashKey);
        imgAvatar = findViewById(R.id.avatar);

        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(FacebookActivity.this);
                mDialog.setMessage("Retrieving Data");
                mDialog.show();

                String accesstoken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response", object.toString() );
                        getData(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        if(AccessToken.getCurrentAccessToken() != null){
            txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
        }

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "ie.app.checkmymeal1",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                txtView.setText(Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
       // printKeyHash();

    }

    private void getData(JSONObject obj) {
        try{
            URL profile_picture = new URL("https://graph.facebook.com/" + obj.getString("id") +"/picture?width=250&height=250");
            Picasso.with(this).load(profile_picture.toString()).into(imgAvatar);

            txtEmail.setText(obj.getString("email"));
            txtBirthday.setText(obj.getString("birthday"));
            txtFriends.setText("Firends: "+obj.getJSONObject("friends").getJSONObject("summary").getString("total_count"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private void printKeyHash() {
//        try{
//            PackageInfo info = getPackageManager().getPackageInfo("ie.app.checkmymeal1", PackageManager.GET_SIGNATURES);
//            for(Signature signature: info.signatures){
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                txtView.setText(Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.login).setVisible(false);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {

            case R.id.view_meals:
                startActivity(new Intent(FacebookActivity.this, MealPlans.class));
                break;

            case R.id.meals:
                startActivity(new Intent(FacebookActivity.this, Meals.class));
                break;

            case R.id.step_counter:
                startActivity(new Intent(FacebookActivity.this,StepCounter.class));
                break;

            case R.id.login:
                item.setEnabled(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
