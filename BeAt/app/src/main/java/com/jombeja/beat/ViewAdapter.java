package com.jombeja.beat;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.jombeja.beat.AttendanceDatabaseContract.DetailsEntry.TABLE_NAME;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {

    private List<ModelClass> myList;
    private Context mcontext;
    private AttendanceOpenHelper mOpenHelper;

    public ViewAdapter(List<ModelClass> myList, Context context) {
        this.mcontext = context;
        this.myList = myList;
        mOpenHelper = new AttendanceOpenHelper(mcontext);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_mrecyclerlist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelClass modelClass = myList.get(position);
        final String id = modelClass.getId();
        holder.Name.setText(modelClass.getName());
        holder.IdNumber.setText(modelClass.getIdNumber());
        holder.DeviceName.setText(modelClass.getDeviceName());
        holder.MacAddress.setText(modelClass.getMacAddress());
        holder.Time.setText(modelClass.getTime());
        holder.EmailAddre.setText(modelClass.getEmailAddress());
        holder.IdPos.setText(modelClass.getId());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openDialog(
                        ""+id
                );
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void openDialog(final String id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int where) {
                // User clicked OK button
                mOpenHelper.deleteRecord(id);
                ((RecordsActivity)mcontext).onResume();
                Toast.makeText(mcontext,"Deleted!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //viewDetails();
                dialog.cancel();
            }
        });
        builder.setCancelable(true);
        builder.setTitle("Delete");
        builder.setMessage("Are You Sure ?");
        builder.setIcon(R.drawable.ic_round_delete_24);
        builder.show();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView IdPos;
        public TextView Name, IdNumber, EmailAddre, DeviceName, MacAddress, Time;
        public View layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            IdPos = itemView.findViewById(R.id.tvId);
            Name = itemView.findViewById(R.id.tvname);
            IdNumber = itemView.findViewById(R.id.tvregno);
            DeviceName = itemView.findViewById(R.id.tvDeviceName);
            EmailAddre = itemView.findViewById(R.id.tvemail);
            MacAddress = itemView.findViewById(R.id.tvmac);
            Time = itemView.findViewById(R.id.tvtime);
        }

    }
}
