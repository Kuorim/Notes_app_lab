package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity1 extends AppCompatActivity {
    private ListView lvNotes;
    private ArrayAdapter listAdapter;
    private ArrayList<String> notesList;


    Spinner spSelectionforDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_note);

        Spinner spSelectToDelete = findViewById(R.id.spSelectionforDelete);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String> notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spSelectToDelete.setAdapter(listAdapter);
    }

    public void onDeleteClick(View view) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> savedNotesList = sp.getStringSet("notes",new HashSet<String>());
        Spinner spSelectToDelete = (Spinner) findViewById(R.id.spSelectionforDelete);
        String selected = spSelectToDelete.getSelectedItem().toString();
        Set <String> StrSet = new HashSet<String>();
        for (String savedNote : savedNotesList){
            if (savedNote.equalsIgnoreCase(selected)){
                Toast.makeText(this,savedNote, Toast.LENGTH_SHORT).show();
            }
            else{
                StrSet.add(savedNote);
            }
        }
        spEd.putStringSet("notes", StrSet);//.commit();
        spEd.apply();
        finish();
    }







//    public boolean onContextItemSelected(MenuItem item) {
//        EditText txtNote_delete = findViewById(R.id.txtNote_delete);
//
//        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor spEd = sp.edit();
//            switch (item.getItemId()){
//                case R.id.Delete:
//                    //this.notesList.remove(1);
//                    //this.listAdapter.notifyDataSetChanged();
//                    Toast.makeText(this, "Deleted ", Toast.LENGTH_SHORT).show();
//                    spEd.remove("testsa").commit();
//                    spEd.apply();
//                    return true;
//                default:
//                    return super.onContextItemSelected(item);
//            }



//        spEd.remove(1).commit();
    //spEd.apply();

    //finish();
}

