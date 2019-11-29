package se.tachyon97.stuff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String tag = "@@@@";
    private String user, pass, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //   mAuth = FirebaseAuth.getInstance();

    }

    private void PushUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(tag, "createUserWithEmail:success");
                            change();
                        } else {
                            Log.w(tag, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration.this, "createUserWithEmail:success",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void change() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void CreateUserData(String user, String name, int earnedCapital) {
        Map<String, Object> collection = new HashMap<>();
        collection.put("name", name);
        collection.put("earnedCapital", earnedCapital);
        db.collection("users").document(user).set(collection);
    }

    public void CreateAccount(View view) {
        user = findViewById(R.id.emailField).toString();
        pass = findViewById(R.id.passwordField).toString();
        name = findViewById(R.id.nameField).toString();
        PushUser(user, pass);
        CreateUserData(user, name, 0);
    }
}
