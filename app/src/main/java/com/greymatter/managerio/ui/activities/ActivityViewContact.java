package com.greymatter.managerio.ui.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greymatter.managerio.AppServices;
import com.greymatter.managerio.R;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.objects.Contact;
import com.greymatter.managerio.ui.activities.uihelpers.ActivityViewContactUIHelper;
import com.greymatter.managerio.ui.adapters.TransactionsListViewAdapter;

import java.util.List;

public class ActivityViewContact extends AppCompatActivity {
    private TextView initialsView;
    private EditText firstNameField, lastNameField, mobileCCField, mobileNoField;
    private FloatingActionButton addTransactionButton;
    private ListView transactionsListView;
    private TransactionsListViewAdapter transactionsListViewAdapter;
    private Dialog addTransactionDialog;
    private long currentContactId;
    private Contact currentContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentContactId = getIntent().getLongExtra("contact_id", -1);

        firstNameField = findViewById(R.id.activity_view_contact_first_name);
        lastNameField = findViewById(R.id.activity_view_contact_last_name);
        mobileNoField = findViewById(R.id.activity_view_contact_mobile_no);
        mobileCCField = findViewById(R.id.activity_view_contact_mobile_cc);
        initialsView = findViewById(R.id.activity_view_contact_initials);
        addTransactionButton = findViewById(R.id.activity_view_contact_add_new_transaction_button);
        addTransactionButton.setOnClickListener(onClickListener);

        if (currentContactId != -1) {
            updateInitialView();
            firstNameField.setEnabled(false);
            lastNameField.setEnabled(false);
            mobileCCField.setEnabled(false);
            mobileNoField.setEnabled(false);
        }

        transactionsListView = findViewById(R.id.activity_view_contact_transactions_list);
        transactionsListViewAdapter = new TransactionsListViewAdapter(ActivityViewContact.this);
        transactionsListView.setAdapter(transactionsListViewAdapter);
        transactionsListView.setOnItemClickListener(listItemClickListener);
        transactionsListViewAdapter.setItems(DBServices.getTransactionDBHelper().getAllForContact(currentContact.getId()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private final AdapterView.OnItemClickListener listItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            Intent intent = new Intent(ActivityViewContact.this, ActivityViewContact.class);
//            intent.putExtra("contact_id", adapterView.getItemIdAtPosition(i));
//            startActivity(intent);
        }
    };

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.activity_view_contact_add_new_transaction_button:
                    showAddNewTransactionDialog();
                    break;
                case R.id.add_transaction_dialog_done:
                    try {
                        ActivityViewContactUIHelper.tryAndAddTransactionFromDialog(currentContact, addTransactionDialog);
                        addTransactionDialog.dismiss();
                        transactionsListViewAdapter.setItems(DBServices.getTransactionDBHelper().getAllForContact(currentContact.getId()));
                        Toast.makeText(ActivityViewContact.this, getString(R.string.transaction_added_success), Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        Toast.makeText(ActivityViewContact.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.add_transaction_dialog_cancel:
                    addTransactionDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    private void showAddNewTransactionDialog() {
        addTransactionDialog = new Dialog(ActivityViewContact.this);
        addTransactionDialog.setCancelable(false);
        addTransactionDialog.setContentView(R.layout.add_new_transaction_dialog);
        addTransactionDialog.findViewById(R.id.add_transaction_dialog_done).setOnClickListener(onClickListener);
        addTransactionDialog.findViewById(R.id.add_transaction_dialog_cancel).setOnClickListener(onClickListener);
        addTransactionDialog.create();
        addTransactionDialog.show();
    }


    private void updateInitialView() {
        if (currentContact != null) {
            updateInitialViewHelper();
        }else{
            List<Contact> queryResult = DBServices.getContactsDBHelper().getWithId((int)currentContactId);
            if (queryResult.size() > 0) {
                currentContact = queryResult.get(0);
                updateInitialViewHelper();
            }
        }
    }

    private void updateInitialViewHelper() {
        firstNameField.setText(currentContact.getFirstName());
        lastNameField.setText(currentContact.getLastName());
        mobileNoField.setText(currentContact.getMobileNo());
        mobileCCField.setText(currentContact.getCountryCode());
        String initials = currentContact.getFirstName().toUpperCase().charAt(0) + ""
                + currentContact.getLastName().toUpperCase().charAt(0);
        initialsView.setText(initials);
    }
}