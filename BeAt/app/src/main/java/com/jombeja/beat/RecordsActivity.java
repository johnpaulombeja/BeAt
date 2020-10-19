package com.jombeja.beat;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import static com.jombeja.beat.AttendanceDatabaseContract.DetailsEntry.*;

public class RecordsActivity extends AppCompatActivity {

    private RecyclerView mRecordsrv;
    private ViewAdapter mAdapter;
    private AttendanceOpenHelper myDbHelper;
    private RecyclerView.LayoutManager mLayoutManager;
    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        mRecordsrv = findViewById(R.id.rvAttendanceList);
        mRecordsrv.setHasFixedSize(true);
        myDbHelper = new AttendanceOpenHelper(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecordsrv.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.back_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_back:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new ViewAdapter(myDbHelper.peopleList(filter), this);
        mRecordsrv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}