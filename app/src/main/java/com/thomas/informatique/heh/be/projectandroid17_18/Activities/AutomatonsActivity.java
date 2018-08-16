package com.thomas.informatique.heh.be.projectandroid17_18.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.thomas.informatique.heh.be.projectandroid17_18.Database.AutomatonAccessDB;
import com.thomas.informatique.heh.be.projectandroid17_18.Models.Automaton;
import com.thomas.informatique.heh.be.projectandroid17_18.Models.AutomatonAdapter;
import com.thomas.informatique.heh.be.projectandroid17_18.Models.FluidData;
import com.thomas.informatique.heh.be.projectandroid17_18.R;

import java.util.ArrayList;

/**
 * Main screen.
 *
 * @author Thomas Rosi
 */
public class AutomatonsActivity extends Activity {

    /**
     * UI references.
     */
    private GridView gv_automatons_automatonlist;
    private Toolbar tb_automatons_toolbar;

    /**
     * DB references.
     */
    private AutomatonAccessDB automatonDB;
    private ArrayList<Automaton> tabautomaton;
    private AutomatonAdapter adapter;

    /**
     * Method called at the creation of the activity.
     *
     * @param savedInstanceState save the state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatons);

        gv_automatons_automatonlist = findViewById(R.id.gv_automatons_automatonlist);
        tb_automatons_toolbar = findViewById(R.id.tb_automatons_toolbar);

        setActionBar(tb_automatons_toolbar);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        automatonDB = new AutomatonAccessDB(this);
        automatonDB.openForRead();
        tabautomaton = automatonDB.getAllAutomaton();
        automatonDB.close();

        if(tabautomaton.isEmpty()){
            Toast.makeText(getApplicationContext(), "Error: No automaton found", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new AutomatonAdapter(this, R.layout.gridview_automaton_row, tabautomaton);
            gv_automatons_automatonlist.setAdapter(adapter);

            gv_automatons_automatonlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final int id = i;
                    AlertDialog.Builder adb = new AlertDialog.Builder(AutomatonsActivity.this);
                    adb.setTitle(tabautomaton.get(i).getName().toString());
                    adb.setMessage(tabautomaton.get(i).getType().toString());
                    adb.setPositiveButton("Ok", null);
                    adb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            automatonDB.openForWrite();
                            automatonDB.removeAutomaton(tabautomaton.get(id).getId());
                            automatonDB.close();
                            adapter.remove(tabautomaton.get(id));
                            Toast.makeText(getApplicationContext(), "Automaton deleted", Toast.LENGTH_SHORT).show();
                            startActivity(getIntent());
                        }
                    });
                    adb.setNegativeButton("Connection", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.i("1",tabautomaton.get(id).getType());
                            if(tabautomaton.get(id).getType().toString().equals("Tablet packaging (S7‐1516 2DPPN)")){
                                Intent intentTablet = new Intent(getApplicationContext(), TabletActivity.class);
                                intentTablet.putExtra("id", "" + tabautomaton.get(id).getId());
                                startActivity(intentTablet);
                            } else {
                                Intent intentFluid = new Intent(getApplicationContext(), FluidActivity.class);
                                intentFluid.putExtra("id", "" + tabautomaton.get(id).getId());
                                startActivity(intentFluid);
                            }

                        }
                    });
                    adb.show();
                }
            });
        }

    }

    /**
     * Manages the different buttons of the activity.
     *
     * @param v view of the activity
     */
    public void OnAutomatonClickManager(View v) {
        switch (v.getId()) {
            case R.id.bt_automatons_add:
                startActivity(new Intent(getApplicationContext(),RegisterAutomatonActivity.class));
                break;
            default:
                break;
        }
    }

}
