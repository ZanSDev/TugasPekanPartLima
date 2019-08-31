package id.zein.evaluasilima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.zein.evaluasilima.Requests.Requests;

public class ReaddbSingleActivity extends AppCompatActivity {

    private Button btsubmit;
    private EditText editNm, editNohape, editDvs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createdb_activity);
        editNm = (EditText) findViewById(R.id.editNama);
        editNohape = (EditText) findViewById(R.id.editNohp);
        editDvs = (EditText) findViewById(R.id.editDivisi);
        btsubmit = (Button) findViewById(R.id.buttonSubmit);

        editNm.setEnabled(false);
        editNohape.setEnabled(false);
        editDvs.setEnabled(false);
        btsubmit.setVisibility(View.GONE);

        Requests requests = (Requests) getIntent().getSerializableExtra("data");
        if(requests!=null){
            editNm.setText(requests.getNama());
            editNohape.setText(requests.getNohp());
            editDvs.setText(requests.getDivisi());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ReaddbSingleActivity.class);
    }

}
