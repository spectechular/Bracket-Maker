package com.mike.rezen.bracketmaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {


    int numPlayers;

    ArrayList<String> playerArray = new ArrayList<String>();

    int names_entered = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button submitNumPlayers = (Button) findViewById(R.id.submit_num_players);
        final Button submitNameButton = (Button) findViewById(R.id.name_submit_button);
        final Button generateListButton = (Button) findViewById(R.id.generate_list);

        final TextView queuedNames = (TextView) findViewById(R.id.player_queue);
        final TextView playerCounter = (TextView) findViewById(R.id.names_left_to_enter);


        final EditText numPlayersField = (EditText) findViewById(R.id.num_players_field);
        final EditText nameField = (EditText) findViewById(R.id.name_field);


        numPlayersField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                submitNumPlayers.setEnabled(false);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().trim().length() == 0) {
                    submitNumPlayers.setEnabled(false);
                } else {
                    submitNumPlayers.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        submitNumPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                numPlayers = Integer.parseInt(numPlayersField.getText().toString());

                numPlayersField.setVisibility(View.GONE);
                submitNumPlayers.setVisibility(View.GONE);

                playerCounter.setVisibility(View.VISIBLE);

                playerCounter.setText(names_entered + "/" + numPlayers + " Names Entered");

                nameField.setVisibility(View.VISIBLE);
                submitNameButton.setVisibility(View.VISIBLE);


            }
        });



        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                submitNameButton.setEnabled(false);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (s.toString().trim().length() == 0) {
                    submitNameButton.setEnabled(false);
                } else {
                    submitNameButton.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        submitNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playerArray.add(nameField.getText().toString());

                Log.d("NAME ADDED: ", nameField.getText().toString());

                queuedNames.setVisibility(View.VISIBLE);

                queuedNames.append(nameField.getText().toString() + "\n");

                names_entered++;

                playerCounter.setText(names_entered + "/" + numPlayers + " Names Entered");


                nameField.setText("");


                if (playerArray.size() == numPlayers) {


                    nameField.setVisibility(View.GONE);
                    submitNameButton.setVisibility(View.GONE);

                    generateListButton.setVisibility(View.VISIBLE);
                    nameField.setEnabled(false);
                    nameField.setText("");
                    submitNameButton.setEnabled(false);

                }

            }
        });

        generateListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Collections.shuffle(playerArray);

                Intent intent = new Intent(MainActivity.this, GeneratedBracket.class);
                intent.putExtra("playerArray", playerArray);
                startActivity(intent);


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}