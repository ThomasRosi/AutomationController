package com.thomas.informatique.heh.be.projectandroid17_18.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.thomas.informatique.heh.be.projectandroid17_18.Database.AutomatonAccessDB;
import com.thomas.informatique.heh.be.projectandroid17_18.Models.Automaton;
import com.thomas.informatique.heh.be.projectandroid17_18.Models.ReadTabletS7;
import com.thomas.informatique.heh.be.projectandroid17_18.R;

/**
 * Connection screen to a tablet automaton.
 *
 * @author Thomas Rosi
 */
public class TabletActivity extends Activity {

    /**
     * UI references.
     */
    private TextView tv_tablet_title;
    private EditText et_tablet_ip;
    private Button bt_tablet_connect;
    private Toolbar tb_tablet_toolbar;
    private CheckBox cb_tablet_on;
    private CheckBox cb_tablet_detectfilling;
    private CheckBox cb_tablet_detectbouchonning;
    private CheckBox cb_tablet_detecttablets;
    private CheckBox cb_tablet_genbottles;
    private CheckBox cb_tablet_online;
    private CheckBox cb_tablet_distribtabletcontact;
    private CheckBox cb_tablet_enginebandcontact;
    private CheckBox cb_tablet_demand5;
    private CheckBox cb_tablet_demand10;
    private CheckBox cb_tablet_demand15;
    private TextView tv_tablet_numtablets;
    private TextView tv_tablet_numbottles;

    /**
     * Internet references.
     */
    private ReadTabletS7 readS7;
    private NetworkInfo network;
    private ConnectivityManager connexStatus;

    /**
     * DB references.
     */
    private AutomatonAccessDB automatonDB;
    private Bundle extra;
    private int data;
    private Automaton automaton;

    /**
     * Method called at the creation of the activity.
     *
     * @param savedInstanceState save the state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet);

        tv_tablet_title = findViewById(R.id.tv_tablet_title);
        et_tablet_ip = findViewById(R.id.et_tablet_ip);
        bt_tablet_connect = findViewById(R.id.bt_tablet_connect);
        tb_tablet_toolbar = findViewById(R.id.tb_tablet_toolbar);
        cb_tablet_on = findViewById(R.id.cb_tablet_on);
        cb_tablet_detectfilling = findViewById(R.id.cb_tablet_detectfilling);
        cb_tablet_detectbouchonning = findViewById(R.id.cb_tablet_detectbouchonning);
        cb_tablet_detecttablets = findViewById(R.id.cb_tablet_detecttablets);
        cb_tablet_genbottles = findViewById(R.id.cb_tablet_genbottles);
        cb_tablet_online = findViewById(R.id.cb_tablet_online);
        cb_tablet_distribtabletcontact = findViewById(R.id.cb_tablet_distribtabletcontact);
        cb_tablet_enginebandcontact = findViewById(R.id.cb_tablet_enginebandcontact);
        cb_tablet_demand5 = findViewById(R.id.cb_tablet_demand5);
        cb_tablet_demand10 = findViewById(R.id.cb_tablet_demand10);
        cb_tablet_demand15 = findViewById(R.id.cb_tablet_demand15);
        tv_tablet_numtablets = findViewById(R.id.tv_tablet_numtablets);
        tv_tablet_numbottles = findViewById(R.id.tv_tablet_numbottles);

        setActionBar(tb_tablet_toolbar);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        extra = this.getIntent().getExtras();
        if(extra != null) {
            data = Integer.parseInt(extra.getString("id"));
        } else {
            Toast.makeText(getApplicationContext(), "Error: data not found", Toast.LENGTH_SHORT).show();
            startActivity( new Intent(this,AutomatonsActivity.class));
        }

        automatonDB = new AutomatonAccessDB(this);
        automatonDB.openForRead();
        automaton = automatonDB.getAutomatonById(data);
        automatonDB.close();

        connexStatus = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        network = connexStatus.getActiveNetworkInfo();
    }

    /**
     * Manages the different buttons of the activity.
     *
     * @param v view of the activity
     */
    public void OnTabletClickManager(View v) {
        switch (v.getId()) {
            case R.id.bt_tablet_connect:
                if(et_tablet_ip.getText().toString().matches("([0-9]{1,3}\\.){3}[0-9]{1,3}")) {
                    if(network != null && network.isConnectedOrConnecting()){
                        if(bt_tablet_connect.getText().equals("CONNECT")) {
                            Toast.makeText(this, network.getTypeName(), Toast.LENGTH_SHORT).show();
                            bt_tablet_connect.setText("DISCONNECT");
                            tv_tablet_title.setText("Tablet packaging (S7‐1516 2DPPN)");
                            readS7 = new ReadTabletS7(v, et_tablet_ip, cb_tablet_on, cb_tablet_detectfilling, cb_tablet_detectbouchonning,
                                    cb_tablet_detecttablets, cb_tablet_genbottles, cb_tablet_online, cb_tablet_distribtabletcontact, cb_tablet_enginebandcontact,
                                    cb_tablet_demand5, cb_tablet_demand10, cb_tablet_demand15, tv_tablet_numtablets, tv_tablet_numbottles);
                            readS7.start(et_tablet_ip.getText().toString(), String.valueOf(automaton.getRackNumber()), String.valueOf(automaton.getSlotNumber()));
                        } else {
                            readS7.stop();
                            bt_tablet_connect.setText("CONNECT");
                            tv_tablet_title.setText("Complete IP address:");
                            Toast.makeText(this, "Connection to the automaton interrupted by the user.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Error: impossible connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Error: invalid ip address", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
