package ru.krinc.mainactivity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mUserAge;
    private Spinner mUserGender;
    private Button mGoToVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        mUserName = findViewById(R.id.et_username);
        mUserAge = findViewById(R.id.et_userage);

        mUserGender = findViewById(R.id.sp_usergender);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.tv_usergender_items, android.R.layout.simple_spinner_item);
        mUserGender.setAdapter(staticAdapter);
        mUserGender.setSelection(0);

        mGoToVideo = (Button) findViewById(R.id.btn_gotomain);
        assert mGoToVideo != null;
        mGoToVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToVideoHandler();
            }
        });

        mUserName.requestFocus();
    }

    protected void goToVideoHandler() {
        String userName = mUserName.getText().toString().trim();
        if (userName.length() == 0) {
            mUserName.requestFocus();
            Toast.makeText(this, "Please, enter the user name", Toast.LENGTH_SHORT).show();
            return;
        }
        int userAge = 0;
        try {
            userAge = Integer.parseInt(mUserAge.getText().toString());
        } catch (Exception unused) {
            userAge = 0;
        }
        if (userAge <= 0) {
            mUserAge.requestFocus();
            Toast.makeText(this, "Please, enter the user age", Toast.LENGTH_SHORT).show();
            return;
        }
        String userGender = mUserGender.getSelectedItem().toString();

        Bundle bundle = new Bundle();
        bundle.putString("user_name", userName);
        bundle.putInt("user_age", userAge);
        bundle.putString("user_gender", userGender);

        String manufacturer = Build.MANUFACTURER;
        String brand        = Build.BRAND;
        String product      = Build.PRODUCT;
        String model        = Build.MODEL;
        bundle.putString("device_manufacturer", manufacturer);
        bundle.putString("device_brand", brand);
        bundle.putString("device_product", product);
        bundle.putString("device_model", model);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_USERINFO, bundle);
        startActivity(intent);
    }
}
