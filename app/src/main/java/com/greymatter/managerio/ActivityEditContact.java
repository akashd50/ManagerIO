package com.greymatter.managerio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.greymatter.managerio.db.helpers.ContactsDBHelper;
import com.greymatter.managerio.objects.Contact;

public class ActivityEditContact extends AppCompatActivity {
    private EditText firstNameField, lastNameField, mobileNoField;
    private Button cancelButton, doneButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        firstNameField = findViewById(R.id.edit_contact_first_name);
        lastNameField = findViewById(R.id.edit_contact_last_name);
        mobileNoField = findViewById(R.id.edit_contact_mobile_no);
        cancelButton = findViewById(R.id.edit_contact_cancel);
        doneButton = findViewById(R.id.edit_contact_done);

        cancelButton.setOnClickListener(onClickListener);
        doneButton.setOnClickListener(onClickListener);
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

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.edit_contact_cancel:
                    onBackPressed();
                    break;
                case R.id.edit_contact_done:
                    Contact c = new Contact();
                    c.setFirstName(firstNameField.getText().toString());
                    c.setLastName(lastNameField.getText().toString());
                    c.setMobileNo(mobileNoField.getText().toString());
                    ContactsDBHelper.insert(c);
                    break;
            }
        }
    };
}