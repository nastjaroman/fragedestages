package com.example.fragedestages;

import static android.content.ContentValues.TAG;

import static com.example.fragedestages.ActShowHelp.DBG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.Dialog;
import android.content.Intent;
import android.widget.Toast;

public class List_pro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pro);


    }

    @Override
    public void onBackPressed() {

        final Dialog dialog = new Dialog(List_pro.this);
        dialog.setContentView(R.layout.dialog);
        Button btn_stay = dialog.findViewById(R.id.stay);
        Button btn_exit = dialog.findViewById(R.id.exit);

        btn_stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });


        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        dialog.show();


    }




    public void Start(View view) {
        Intent intent = new Intent(List_pro.this, FrageActivity.class);

        startActivity(intent);
    }



            /**
             * Event handler für Button 'Contact'
             *
             * @param cmd
             */
    public void onClickDlgContact(final View cmd) {

        startActivity(new Intent(this, ActShowContact.class));
        }



        /**
         * Event handler für Button 'Help'
         *
         * @param cmd
         */
    public void onClickDlgHelp(final View cmd) {


        startActivity(new Intent(this, ActShowHelp.class));

    }

}



