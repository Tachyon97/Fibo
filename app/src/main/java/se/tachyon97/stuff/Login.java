package se.tachyon97.stuff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText pass;
    EditText email;
    private FirebaseAuth mAuth;
    private String tag = "@@@@";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        pass = findViewById(R.id.passwordField);
        email = findViewById(R.id.emailField);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void SignIn(final String email, String password) {
        Log.d(tag, "Signin: " + email);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(tag, "SignIn; sucessful");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    Toast.makeText(Login.this, "Welcome back " + email, Toast.LENGTH_LONG).show();
                } else {
                    Log.w(tag, "SigIn: Failed", task.getException());
                    Toast.makeText(Login.this, "Authentication failed", Toast.LENGTH_LONG).show();
                    updateUI(null);
                }
            }
        });
    }

    public void loginButton(View view) {
        Log.w("@@@@", findViewById(R.id.emailField).toString() + " " + findViewById(R.id.passwordField).toString());
        String e = email.getText().toString();
        String p = pass.getText().toString();
        if (mAuth != null) {
            SignIn(e, p);
        }
    }

    public void openRegistration(View view) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
}