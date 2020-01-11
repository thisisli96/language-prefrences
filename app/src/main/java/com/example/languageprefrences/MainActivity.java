package com.example.languageprefrences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // untuk membuat menu seting
    public boolean onCreateOptionsMenu(Menu menu){ //

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);


        switch (item.getItemId()){
            case R.id.english:
               // Log.i("item selected", "settings");
                setlanguage("GOOD MORNING");

                return true;
            case R.id.indonesia:
                //Log.i("irem Seleted", "helps");
                setlanguage("SELAMAT PAGI");

                return  true;
            default:
                return  false;
        }
    }


// penutup menu setting

    // alurnya databasae sharedprefeces atau database lokal ini akan menyimpan alert
    // bahasa yang kita gunakan. dan jika sharedprefrences sudah menerima inputan maka
    // akan masuk ke else tapi jika Sharedpreferences belum dapat inputan maka hasilnya akan
    // error dan adakan masuk ke kondisi if.

    TextView bahasa;
    SharedPreferences sharedPreferences;

    public void setlanguage(String language){
        sharedPreferences.edit().putString("language",language).apply(); // harus pake apply untuk memasukkan ke database,
        bahasa.setText(language);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.languageprefrences", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "Error");

        bahasa = findViewById(R.id.bahasa);

        if (language.equals("Error")) {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("WITCH ONE LANGUAGE !?").setMessage("choose you language that you want to use ?")
                    .setPositiveButton("ENGLISH", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "selamat anda menang", Toast.LENGTH_SHORT).show();
                            //  bahasa.setText("GOOD MORNING !!");
                            setlanguage("GOOD MORNING");

                        }
                    }).setNegativeButton("INDONESIA", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //  bahasa.setText("SELAMAT PAGI !!");
                    setlanguage("SELAMAT PAGI");
                }
            }).show();

        } else {
            bahasa.setText(language);
        }
    }
}
