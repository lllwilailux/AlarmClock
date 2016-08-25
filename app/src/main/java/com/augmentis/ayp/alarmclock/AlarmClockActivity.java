package com.augmentis.ayp.alarmclock;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class AlarmClockActivity extends AppCompatActivity implements CallBack{

    private static final String DIALOG_DATE = "AlarmClockActivity";
    private static final String TAG = "AlarmClockActivity";

    private RecyclerView aRecyclerView;
    private TextView aTextView;
    protected Activity activity;
    private AlarmClockAdapter adapter;
    private RecyclerView _recyclerView;

    public Clock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        aRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        aRecyclerView.setLayoutManager(new LinearLayoutManager(getParent()));


        List<Clock> listClock = AlarmLab.getInstance(this).getAlarm();
        adapter = new AlarmClockAdapter(this,listClock);
        aRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_time_picker:

                FragmentManager fm = getFragmentManager();
                TimePickerDialog dialogFragment = TimePickerDialog.newInstance();
                dialogFragment.show(fm, DIALOG_DATE);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI(){
        AlarmLab aLab = AlarmLab.getInstance(this);
        List<Clock> clock = aLab.getAlarm();

        if (adapter == null) {
            adapter = new AlarmClockAdapter(this,clock);
            _recyclerView.setAdapter(adapter);
        } else {
            adapter.setAlarm(aLab.getAlarm());
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void sendData(Date date) {
        Log.d(TAG,"callback");

        updateUI();
    }

    //// Holder ////
    class AlarmClockHolder extends RecyclerView.ViewHolder {
        public AlarmClockHolder(View itemView) {
            super(itemView);

            aTextView = (TextView) itemView.findViewById(R.id.text_view);
        }

        public void bind(Clock clock) {
            aTextView.setText(clock.getHours()+":"+clock.getMinutes());
        }
    }

    //// Adapter ////
    class AlarmClockAdapter extends RecyclerView.Adapter<AlarmClockHolder> {

        private List<Clock> item;
        private Context context;

        protected void setAlarm(List<Clock> clock) {
            item = clock;
        }

        public AlarmClockAdapter(Context context,List<Clock> item) {
            this.item = item;
            this.context = context;
        }

        @Override
        public AlarmClockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.list,parent,false);
            return new AlarmClockHolder(v);
        }

        @Override
        public void onBindViewHolder(AlarmClockHolder holder, int position) {
            Clock clock = item.get(position);
            Log.d("TAG","Position" + position);
            holder.bind(clock);
        }

        @Override
        public int getItemCount() {
            return item.size();
        }
    }
}
