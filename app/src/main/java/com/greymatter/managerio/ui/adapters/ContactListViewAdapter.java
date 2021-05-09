package com.greymatter.managerio.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greymatter.managerio.R;
import com.greymatter.managerio.objects.Contact;

public class ContactListViewAdapter extends AListViewAdapter<Contact> {
    public ContactListViewAdapter(Context context) {
        super(context);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate(R.layout.contact_view_item, container, false);
        }

        String name = getItem(position).getFirstName() + " " + getItem(position).getLastName();

        ((TextView) convertView.findViewById(R.id.contact_view_item_name))
                .setText(name);
        return convertView;
    }
}
