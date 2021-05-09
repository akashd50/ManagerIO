package com.greymatter.managerio.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.greymatter.managerio.R;
import com.greymatter.managerio.objects.Transaction;

public class TransactionsListViewAdapter extends AListViewAdapter<Transaction> {
    public TransactionsListViewAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.transaction_view_item, container, false);
        }

        String date = getItem(position).getTransactionDateTime().toLocalDate().toString();
        String amount = getItem(position).getAmount() + "";
        ((TextView) convertView.findViewById(R.id.transaction_view_item_date)).setText(date);
        ((TextView) convertView.findViewById(R.id.transaction_view_item_amount)).setText(amount);
        return convertView;
    }
}
