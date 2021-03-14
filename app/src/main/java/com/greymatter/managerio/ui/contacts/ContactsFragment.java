package com.greymatter.managerio.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactsListView = root.findViewById(R.id.contacts_list);


        contactListViewAdapter = new ContactListViewAdapter(getContext());
        contactsListView.setAdapter(contactListViewAdapter);

        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), ActivityEditContact.class);
                intent.putExtra("contact_id", adapterView.getItemIdAtPosition(i));
                startActivity(intent);
            }
        });

        contactListViewAdapter.updateItems(ContactsDBHelper.getAll());
        contactListViewAdapter.notifyDataSetChanged();

        final FloatingActionButton addNewContactButton = root.findViewById(R.id.add_new_contact);
        addNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ActivityEditContact.class);
                i.putExtra("contact_id", -1);
                startActivity(i);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        contactListViewAdapter.notifyDataSetChanged();
    }
}