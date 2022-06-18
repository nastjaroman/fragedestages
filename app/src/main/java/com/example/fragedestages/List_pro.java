package com.example.fragedestages;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Dialog;
import android.content.Intent;

public class List_pro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pro);
    }

/* Wenn der Nutzer auf Backbutton klickt, wird dialog Layout gezeigt und
 dort wird zwei Buten gezeigt in dem
der Nutzer auswällen kann, ob er im App bleiben, oder App beenden will!
 */

    @Override
   public void onBackPressed() {
        final Dialog dialog = new Dialog(List_pro.this);
        dialog.setContentView(R.layout.dialog);
        Button btn_stay = dialog.findViewById(R.id.stay);
        Button btn_exit = dialog.findViewById(R.id.exit);

        btn_stay.setOnClickListener(new View.OnClickListener(){
            @Override // dismiss - im App bleiben
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
          /*  wenn ich die Aufgabe von Button ändern will
           public void onClick(View view) {   }*/

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override //App beenden
              public void onClick(View view){
                finishAffinity();
              }
        });
        dialog.show();
    }

  public void Start(View view) {
        Intent intent = new Intent(List_pro.this, FrageActivity.class);
        startActivity(intent);
    }

    /*
             * Event handler für Button 'Contact'
             *
             * @param cmd */
  public void onClickDlgContact(final View cmd) {
      startActivity(new Intent(this, ActShowContact.class));
  }

  /** Event handler für Button 'Help'
   * *
         * @param cmd
         */
   public void onClickDlgHelp(final View cmd) {
       startActivity(new Intent(this, ActShowHelp.class));
    }

}