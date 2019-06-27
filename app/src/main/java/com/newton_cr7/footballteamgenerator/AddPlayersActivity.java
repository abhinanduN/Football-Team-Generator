package com.newton_cr7.footballteamgenerator;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class AddPlayersActivity extends AppCompatActivity {

    TableLayout playerList;
    int counter=0;
    EditText gkOne,gkTwo,newPlayer;
    Random randomGenerator;

    ArrayList<String> outPlayers,teamOne,teamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        teamOne=new ArrayList<>();
        teamTwo=new ArrayList<>();
        outPlayers=new ArrayList<>();
        gkOne=findViewById(R.id.edit_gk_one);
        gkTwo=findViewById(R.id.edit_gk_two);
        newPlayer=findViewById(R.id.edit_new_player_name);

        Button addPlayers=findViewById(R.id.btn_add_row);
        Button createTeam=findViewById(R.id.btn_create_team);
        playerList=findViewById(R.id.table_players);

        randomGenerator=new Random();

        addPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newPlayer.getText().toString().isEmpty()) {
                    outPlayers.add(newPlayer.getText().toString());
                    TableRow tr=new TableRow(getApplicationContext());
                    counter++;
                    TextView count= new TextView(getApplicationContext());
                    TextView name= new TextView(getApplicationContext());
                    count.setText(""+counter);
                    name.setText(newPlayer.getText().toString());
                    tr.addView(count);
                    tr.addView(name);
                    playerList.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    newPlayer.setText("");
                }
            }
        });

        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!(gkOne.getText().toString().isEmpty()))&&(!(gkTwo.getText().toString().isEmpty()))){
                    teamOne.add(gkOne.getText().toString());
                    teamTwo.add(gkTwo.getText().toString());
                    while(outPlayers.size()>0)
                    {
                        int index = randomGenerator.nextInt(outPlayers.size());
                        teamOne.add(outPlayers.get(index));
                        outPlayers.remove(index);
                        if(outPlayers.size()!=0) {
                            index = randomGenerator.nextInt(outPlayers.size());
                            teamTwo.add(outPlayers.get(index));
                            outPlayers.remove(index);
                        }
                    }
                    showTeam();
                }
                else{
                    Toast.makeText(AddPlayersActivity.this, "Enter Goalkeeper names", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void showTeam(){
        setContentView(R.layout.show_team);
        TableLayout teamOneLayout=findViewById(R.id.layout_team_one);
        TableLayout teamTwoLayout=findViewById(R.id.layout_team_two);
        for(int i=0;i<teamOne.size();i++)
        {
            TableRow tr=new TableRow(getApplicationContext());
            TextView count= new TextView(getApplicationContext());
            TextView name= new TextView(getApplicationContext());
            count.setText(i+". ");
            name.setText(teamOne.get(i));
            tr.addView(count);
            tr.addView(name);
            teamOneLayout.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
        for(int i=0;i<teamTwo.size();i++)
        {
            TableRow tr=new TableRow(getApplicationContext());
            TextView count= new TextView(getApplicationContext());
            TextView name= new TextView(getApplicationContext());
            count.setText(i+". ");
            name.setText(teamTwo.get(i));
            tr.addView(count);
            tr.addView(name);
            teamTwoLayout.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

}
