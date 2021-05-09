package com.greymatter.managerio.ui.contacts;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greymatter.managerio.ui.activities.ActivityViewContact;
import com.greymatter.managerio.AppServices;
import com.greymatter.managerio.R;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.ui.contacts.uihelpers.ContactsFragmentUIHelper;

public class ContactsFragment extends Fragment {
    private ContactListViewAdapter contactListViewAdapter;
    private ContactViewModel contactViewModel;
    private ListView contactsListView;
    private FloatingActionButton addNewContactButton;
    private Dialog addContactDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);

        contactsListView = root.findViewById(R.id.contacts_list);
        contactListViewAdapter = new ContactListViewAdapter(getContext());
        contactsListView.setAdapter(contactListViewAdapter);
        contactsListView.setOnItemClickListener(listItemClickListener);
        contactListViewAdapter.setItems(DBServices.getContactsDBHelper().getAllOwnedBy(AppServices.getActiveUser().getId()));

        addNewContactButton = root.findViewById(R.id.add_new_contact_floating_button);
        addNewContactButton.setOnClickListener(viewOnClickListener);

        return root;
    }

    public void showAddNewContactDialog() {
        addContactDialog  = new Dialog(getContext());
        addContactDialog.setCancelable(false);
        addContactDialog.setContentView(R.layout.add_new_contact_dialog);
        addContactDialog.findViewById(R.id.add_contact_dialog_cancel).setOnClickListener(viewOnClickListener);
        addContactDialog.findViewById(R.id.add_contact_dialog_done).setOnClickListener(viewOnClickListener);
        addContactDialog.create();
        addContactDialog.show();
    }

    private void addNewContact() {
        try{
            ContactsFragmentUIHelper.tryAndAddContactFromDialog(addContactDialog);
            addContactDialog.dismiss();
            contactListViewAdapter.setItems(DBServices.getContactsDBHelper().getAllOwnedBy(AppServices.getActiveUser().getId()));
            Toast.makeText(getContext(), getString(R.string.contact_added_success), Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        contactListViewAdapter.notifyDataSetChanged();
    }

    private final AdapterView.OnItemClickListener listItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), ActivityViewContact.class);
            intent.putExtra("contact_id", adapterView.getItemIdAtPosition(i));
            startActivity(intent);
        }
    };

    private final View.OnClickListener viewOnClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_new_contact_floating_button:
                    showAddNewContactDialog();
                    break;
                case R.id.add_contact_dialog_done:
                    addNewContact();
                    break;
                case R.id.add_contact_dialog_cancel:
                    addContactDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };
}