package se.tachyon97.stuff;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemizedRequestAdapter extends ArrayAdapter<ItemizedOrder> {
    private Context context;
    private ArrayList<ItemizedOrder> requestObjects;
    private LayoutInflater inflater;


    public ItemizedRequestAdapter(Context context, ArrayList<ItemizedOrder> requestObjects) {
        super(context, -1, requestObjects);
        this.context = context;
        this.requestObjects = requestObjects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        @SuppressLint("ViewHolder") final View items = inflater.inflate(R.layout.activity_adapter, parent, false);
        TextView email = items.findViewById(R.id.textView);
        TextView from = items.findViewById(R.id.from);
        TextView to = items.findViewById(R.id.to);
        TextView phone = items.findViewById(R.id.phoneNumber);
        TextView message = items.findViewById(R.id.textView2);
        TextView price = items.findViewById(R.id.calcs);
        Button contact = items.findViewById(R.id.openContact);
        email.setText("Email: " + requestObjects.get(position).getEmail());
        from.setText("Pickup: " + requestObjects.get(position).getpick());
        to.setText("Dropoff: " + requestObjects.get(position).getdrop());
        phone.setText("number: " + requestObjects.get(position).getnumber());
        message.setText("Message: " + requestObjects.get(position).getMessage());
        price.setText("Price: " + requestObjects.get(position).getPrice());
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + requestObjects.get(position).getnumber()));
                context.startActivity(intent);
            }
        });
        return items;
    }
}
