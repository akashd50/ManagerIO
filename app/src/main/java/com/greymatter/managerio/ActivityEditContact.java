package com.greymatter.managerio;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityEditContact extends AppCompatActivity {
    private EditText firstNameField, lastNameField, mobileNoField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        firstNameField = findViewById(R.id.edit_contact_first_name);
        lastNameField = findViewById(R.id.edit_contact_last_name);
        mobileNoField = findViewById(R.id.edit_contact_mobile_no);

        // For custom toolbar with back button add toolbar in xml and use this:
        /*mToolbar.setNavigationIcon(R.drawable.ic_arrow);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Your code
                finish();
            }
        });*/
    }

}