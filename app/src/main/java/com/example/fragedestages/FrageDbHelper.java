package com.example.fragedestages;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



public class FrageDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuestionQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public FrageDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                FrageContract.FragenTable.TABLE_NAME + " ( " +
                FrageContract.FragenTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FrageContract.FragenTable.COLUMN_QUESTION + " TEXT, " +
                FrageContract.FragenTable.COLUMN_OPTION1 + " TEXT, " +
                FrageContract.FragenTable.COLUMN_OPTION2 + " TEXT, " +
                FrageContract.FragenTable.COLUMN_OPTION3 + " TEXT, " +
                FrageContract.FragenTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillFragenTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FrageContract.FragenTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillFragenTable() {
        Frage q1 = new Frage("Welcher Vogel legt seine Eier in fremde Nester?", "Kuckuck", "Amsel","Elster", 1);
        addQuestion(q1);
        Frage q2 = new Frage("Wie heißt der freche Kobold von Meister Eder? ", "Pucca", "Pumuckl","Pokemon", 2);
        addQuestion(q2);
        Frage q3 = new Frage("In welcher italienischen Stadt steht der berühmte schiefe Turm?", "Pisa ", "Mailand","Rom", 1);
        addQuestion(q3);
        Frage q4 = new Frage("Wie heißen die Neffen von Donald Duck?", "Tick, Trick, Trock", "Tick, Trock, Track","Tick, Trick, Track", 3);
        addQuestion(q4);
        Frage q5 = new Frage("Wie nennt man die Schwester der Mutter?", "Cousine", "Tante","Freundin", 2);
        addQuestion(q5);
        Frage q6 = new Frage("In welcher Stadt steht die Freiheitsstatue?", "New York", " Paris","Berlin", 1);
        addQuestion(q6);

    }

    private void addQuestion(Frage frage) {
        ContentValues fr = new ContentValues();
        fr .put(FrageContract.FragenTable.COLUMN_QUESTION, frage.getQuestion());
        fr.put(FrageContract.FragenTable.COLUMN_OPTION1, frage.getOption1());
        fr.put(FrageContract.FragenTable.COLUMN_OPTION2, frage.getOption2());
        fr.put(FrageContract.FragenTable.COLUMN_OPTION3, frage.getOption3());
        fr.put(FrageContract.FragenTable.COLUMN_ANSWER_NR, frage.getAnswerNr());
        db.insert(FrageContract.FragenTable.TABLE_NAME, null, fr);
    }

    @SuppressLint("Range")
    public List<Frage> getAllQuestions() {
        List<Frage> frageList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + FrageContract.FragenTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Frage frage = new Frage();
                frage.setQuestion(c.getString(c.getColumnIndex(FrageContract.FragenTable.COLUMN_QUESTION)));
                frage.setOption1(c.getString(c.getColumnIndex(FrageContract.FragenTable.COLUMN_OPTION1)));
                frage.setOption2(c.getString(c.getColumnIndex(FrageContract.FragenTable.COLUMN_OPTION2)));
                frage.setOption3(c.getString(c.getColumnIndex(FrageContract.FragenTable.COLUMN_OPTION3)));
                frage.setAnswerNr(c.getInt(c.getColumnIndex(FrageContract.FragenTable.COLUMN_ANSWER_NR)));
                frageList.add(frage);
            } while (c.moveToNext());
        }

        c.close();
        return frageList;
    }
}
