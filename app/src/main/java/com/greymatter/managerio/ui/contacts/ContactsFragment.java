package com.greymatter.managerio.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        contactListViewAdapter.updateItems(ContactsDBHelper.getAll());
        contactListViewAdapter.notifyDataSetChanged();
        /*final TextView textView = root.findViewById(R.id.text_dashboard);
        contactViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/



        final FloatingActionButton addNewContactButton = root.findViewById(R.id.add_new_contact);
        addNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ActivityEditContact.class);
                startActivity(i);
            }
        });

        return root;
    }
}