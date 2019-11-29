package se.tachyon97.stuff;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<ItemizedOrder> orders = new ArrayList<>();
    private FrameLayout jobs;
    private FrameLayout stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        final Context context = this;
        final CollectionReference itemsRef = db.collection("requests");
        itemsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                orders.clear();
                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    ItemizedOrder i = snapshot.toObject(ItemizedOrder.class);
                    orders.add(i);
                }
                ListView list = findViewById(R.id.listView);
                ItemizedRequestAdapter adapter = new ItemizedRequestAdapter(context, orders);
                list.setAdapter(adapter);
            }
        });
    }

    public void SignOuts(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void OpenJobsActivity(View view) {
        jobs = findViewById(R.id.jobsContainer);
        stats = findViewById(R.id.statsContainer);
        jobs.setVisibility(View.VISIBLE);
        stats.setVisibility(View.INVISIBLE);
    }

    public void OpenStatsActivity(View view) {
        jobs = findViewById(R.id.jobsContainer);
        stats = findViewById(R.id.statsContainer);
        jobs.setVisibility(View.INVISIBLE);
        stats.setVisibility(View.VISIBLE);
    }
}