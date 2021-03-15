package com.greymatter.managerio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.greymatter.managerio.db.helpers.ContactsDBHelper;
import com.greymatter.managerio.objects.Contact;
import com.greymatter.managerio.ui.contacts.uihelpers.ContactsValidator;

import java.util.List;

public class ActivityViewContact extends AppCompatActivity {
    private TextView initialsView;
    private EditText firstNameField, lastNameField, mobileNoField;
    private Button cancelButton, doneButton;
    private long currentContactId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        currentContactId = getIntent().getLongExtra("contact_id", -1);

        firstNameField = findViewById(R.id.edit_contact_first_name);
        lastNameField = findViewById(R.id.edit_contact_last_name);
        mobileNoField = findViewById(R.id.edit_contact_mobile_no);
        cancelButton = findViewById(R.id.edit_contact_cancel);
        doneButton = findViewById(R.id.edit_contact_done);
        initialsView = findViewById(R.id.edit_contact_initials);

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

        if (currentContactId != -1) {
            updateInitialsView();
            firstNameField.setEnabled(false);
            lastNameField.setEnabled(false);
            mobileNoField.setEnabled(false);
        }
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

                    try{
                        ContactsValidator.validateContact(c);
                        ContactsDBHelper.insert(c);
                        Toast.makeText(getApplicationContext(), "New Contact Added", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void updateInitialsView() {
        List<Contact> queryResult = ContactsDBHelper.getWithId(currentContactId);
        if (queryResult.size() > 0) {
            Contact c = queryResult.get(0);
            firstNameField.setText(c.getFirstName());
            lastNameField.setText(c.getLastName());
            mobileNoField.setText(c.getMobileNo());

            String initials = c.getFirstName().charAt(0) + "" + c.getLastName().charAt(0);
            initialsView.setText(initials);
        }
    }
}