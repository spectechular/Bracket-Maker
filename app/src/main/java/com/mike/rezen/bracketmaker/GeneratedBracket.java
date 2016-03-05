package com.mike.rezen.bracketmaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GeneratedBracket extends AppCompatActivity {

    ArrayList<String> playerArray = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_bracket);

        final ListView listView = (ListView) findViewById(R.id.generated_list_view);


        Intent intent = getIntent();

        playerArray = intent.getStringArrayListExtra("playerArray");

        for (int i = 0; i < playerArray.size() ; i++) {

            playerArray.set(i, Integer.toString(i+1) + ". " + playerArray.get(i) );

        }

         final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(GeneratedBracket.this, R.layout.list_players, playerArray);//(this,R.layout.list_players, playerArray);
        listView.setAdapter(arrayAdapter);

        

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(GeneratedBracket.this);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Player lost Match?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final int positionToRemove = position;

                        playerArray.remove(positionToRemove);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", null);

                builder.show();

                return false;
            }
        });
    }
}
