package com.greymatter.managerio.ui.contacts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greymatter.managerio.ActivityEditContact;
import com.greymatter.managerio.R;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.helpers.ContactsDBHelper;
import com.greymatter.managerio.objects.Contact;

public class ContactsFragment extends Fragment {
    private ContactListViewAdapter contactListViewAdapter;
    private ContactViewModel contactViewModel;
    private ListView contactsListView;
    private FloatingActionButton addNewContactButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);

        contactsListView = root.findViewById(R.id.contacts_list);
        contactListViewAdapter = new ContactListViewAdapter(getContext());
        contactsListView.setAdapter(contactListViewAdapter);
        contactsListView.setOnItemClickListener(listItemClickListener);
        contactListViewAdapter.updateItems(ContactsDBHelper.getAll());
        contactListViewAdapter.notifyDataSetChanged();

        addNewContactButton = root.findViewById(R.id.add_new_contact);
        addNewContactButton.setOnClickListener(viewOnClickListener);

        return root;
    }

    public void showAddNewContactDialog() {
        Dialog dialog  = new Dialog(getContext());
        // dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.add_new_contact_dialog);
        dialog.create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        contactListViewAdapter.notifyDataSetChanged();
    }

    private final AdapterView.OnItemClickListener listItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getContext(), ActivityEditContact.class);
            intent.putExtra("contact_id", adapterView.getItemIdAtPosition(i));
            startActivity(intent);
        }
    };

    private final View.OnClickListener viewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_new_contact:
//                    Intent i = new Intent(getContext(), ActivityEditContact.class);
//                    i.putExtra("contact_id", -1);
//                    startActivity(i);
                    showAddNewContactDialog();
                    break;
                default:
                    break;
            }
        }
    };
}