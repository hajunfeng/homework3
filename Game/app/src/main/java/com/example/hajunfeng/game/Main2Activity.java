package com.example.hajunfeng.game;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public int rand1, rand2, gold = 0, time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        time = 0;
        rand1 =(int)(Math.random()*8);
        rand2 =(int)(Math.random()*8);
        changeImage(rand1);
        changeName(rand2);
    }

    public void exit_but(View view) {
        System.exit(0);
    }
    public void changeImage(int rand){

        switch (rand){
            case 0:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.bear));break;
            case 1:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.bird));break;
            case 2:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.cat));break;
            case 3:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.fish));break;
            case 4:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.lion));break;
            case 5:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.pig));break;
            case 6:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.sun));break;
            case 7:((ImageView)findViewById(R.id.show)).setImageDrawable(getResources().getDrawable(R.drawable.wolf));break;
            default:break;
        }
    }
    public void changeName(int rand){

        switch (rand){
            case 0: ((TextView)findViewById(R.id.name)).setText("bear");break;
            case 1: ((TextView)findViewById(R.id.name)).setText("bird");break;
            case 2: ((TextView)findViewById(R.id.name)).setText("cat");break;
            case 3: ((TextView)findViewById(R.id.name)).setText("fish");break;
            case 4: ((TextView)findViewById(R.id.name)).setText("lion");break;
            case 5: ((TextView)findViewById(R.id.name)).setText("pig");break;
            case 6: ((TextView)findViewById(R.id.name)).setText("sun");break;
            case 7: ((TextView)findViewById(R.id.name)).setText("wolf");break;
            default:break;
        }
    }

    public void yes_but(View view) {
        if(rand1 == rand2){
            gold++;
            String temp ="" + gold * 10;
            ((TextView)findViewById(R.id.show_gold)).setText(temp);
            rand1 =(int)(Math.random()*8);
            rand2 =(int)(Math.random()*8);
            changeImage(rand1);
            changeName(rand2);
        }
        else{
            time++;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Wrong");
            if(time == 1)
                builder.setMessage("You have two more chances!");
            else if(time == 2)
                builder.setMessage("You have one more chances!");
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        if(time >= 3){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("defeat");
            builder.setMessage("You got " + gold * 10 + "gold!");
            builder.setPositiveButton("exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void no_but(View view) {
        if(rand1 == rand2){
            time++;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Wrong");
            if(time == 1)
                builder.setMessage("You have two more chances!");
            else if(time == 2)
                builder.setMessage("You have one more chances!");
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            gold++;
            String temp = "" + gold * 10;
            ((TextView)findViewById(R.id.show_gold)).setText(temp);
            rand1 =(int)(Math.random()*8);
            rand2 =(int)(Math.random()*8);
            changeImage(rand1);
            changeName(rand2);
        }
        if(time >= 3){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("defeat");
            builder.setMessage("You got " + gold * 10 + "gold!");
            builder.setPositiveButton("exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
