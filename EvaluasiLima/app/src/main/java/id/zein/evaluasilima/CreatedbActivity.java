package id.zein.evaluasilima;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import id.zein.evaluasilima.Requests.Requests;


public class CreatedbActivity extends AppCompatActivity {

    private DatabaseReference database;

    private Button btsubmit;
    private EditText editTextNama, editTextNohp, editTextDivisi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createdb_activity);

        editTextNama = (EditText) findViewById(R.id.editNama);
        editTextNohp = (EditText) findViewById(R.id.editNohp);
        editTextDivisi = (EditText) findViewById(R.id.editDivisi);

        btsubmit = (Button) findViewById(R.id.buttonSubmit);

        database = FirebaseDatabase.getInstance().getReference();

        final Requests requests = (Requests) getIntent().getSerializableExtra("data");

        if (requests != null) {
            editTextNama.setText(requests.getNama());
            editTextNohp.setText(requests.getNohp());
            editTextDivisi.setText(requests.getDivisi());
            btsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requests.setNama(editTextNama.getText().toString());
                    requests.setNohp(editTextNohp.getText().toString());
                    requests.setDivisi(editTextDivisi.getText().toString());

                    updateBarang(requests);
                }
            });
        } else {
            btsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isEmpty(editTextNama.getText().toString()) && !isEmpty(editTextNohp.getText().toString()) && !isEmpty(editTextDivisi.getText().toString()))
                        submitData(new Requests(editTextNama.getText().toString(), editTextNohp.getText().toString(), editTextDivisi.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.buttonSubmit), "Data tidak boleh kosong", Snackbar.LENGTH_SHORT).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            editTextNama.getWindowToken(), 0);
                }
            });
        }
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void updateBarang(Requests requests){
        database.child("Evaluasi Lima")
                .child(requests.getKey())
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Snackbar.make(findViewById(R.id.buttonSubmit), "Data berhasil diupdate", Snackbar.LENGTH_SHORT).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view){
                                finish();
                            }
                        }).show();
                    }
                });finish();

    }

    private void submitData(Requests requests) {
        database.child("Evaluasi Lima")
                .push()
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                editTextNama.setText("");
                editTextNohp.setText("");
                editTextDivisi.setText("");

                finish();
                Snackbar.make(findViewById(R.id.buttonSubmit), "Data berhasil ditambahkan", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, CreatedbActivity.class);
    }
}
