package id.zein.evaluasilima.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.zein.evaluasilima.CreatedbActivity;
import id.zein.evaluasilima.R;
import id.zein.evaluasilima.ReaddbSingleActivity;
import id.zein.evaluasilima.Requests.Requests;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {
    private ArrayList<Requests> daftarAnggota;
    private Context context;
    private FirebaseDataListener listener;

    public AdapterRecyclerView(ArrayList<Requests> barangs, Context ctx) {

        daftarAnggota = barangs;
        context = ctx;
        listener = (FirebaseDataListener) ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.textNama);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itembarang_activity, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarAnggota.get(position).getNama();
        System.out.println("BARANG DATA one by one" + position + daftarAnggota.size());
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Read Detail Data
                context.startActivity(ReaddbSingleActivity.getActIntent((Activity) context).putExtra("data", daftarAnggota.get(position)));
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                //Delete dan Update

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogview_activity);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editbutton = (Button) dialog.findViewById(R.id.buttonEdit);
                Button deletebutton = (Button) dialog.findViewById(R.id.buttonDelete);

                //klik tombol edit
                editbutton.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View view) {
                                                      dialog.dismiss();
                                                      context.startActivity(CreatedbActivity.getActIntent((Activity) context).putExtra("data", daftarAnggota.get(position)));
                                                  }
                                              }
                );
                //klik tombol delete
                deletebutton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                        //Delete Data
                                                        dialog.dismiss();
                                                        listener.onDeleteData(daftarAnggota.get(position), position);
                                                    }
                                                }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {

        return daftarAnggota.size();
    }

    public interface FirebaseDataListener {
        void onDeleteData(Requests requests, int position);
    }
}
