package com.greymatter.managerio.ui.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.greymatter.managerio.R;
import com.greymatter.managerio.objects.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactListViewAdapter extends BaseAdapter {
    private final Context context;
    private List<Contact> items;
    public ContactListViewAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate(R.layout.contact_view_item, container, false);
        }

        String name = getItem(position).getFirstName() + " " + getItem(position).getLastName();

        ((TextView) convertView.findViewById(R.id.contact_view_item_name))
                .setText(name);
        return convertView;
    }

    public void updateItems(List<Contact> list) {
        items.addAll(list);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Contact getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }
}
