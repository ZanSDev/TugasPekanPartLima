package id.zein.evaluasilima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

import id.zein.evaluasilima.Adapter.AdapterRecyclerView;
import id.zein.evaluasilima.Requests.Requests;

public class ReaddbActivity extends AppCompatActivity implements AdapterRecyclerView.FirebaseDataListener {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Requests> daftarAnggota;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dbread_activity);


        rvView = (RecyclerView) findViewById(R.id.recyclerview);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("Evaluasi Lima")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                daftarAnggota = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Requests anggota = noteDataSnapshot.getValue(Requests.class);
                    anggota.setKey(noteDataSnapshot.getKey());

                    daftarAnggota.add(anggota);
                }
                adapter = new AdapterRecyclerView(daftarAnggota, ReaddbActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ReaddbActivity.class);
    }

    @Override
    public void onDeleteData(Requests requests, final int position){
        if (database != null){
            database.child("Evaluasi Lima")
                    .child(requests.getKey())
                    .removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ReaddbActivity.this, "Data berhasil di hapus", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
