package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private ListView lvNotes;
    private ArrayAdapter listAdapter;
    private ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvNotes = findViewById(R.id.lvNotes);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        this.lvNotes.setAdapter(this.listAdapter);
        this.lvNotes.setLongClickable(true);
        ListView listView = findViewById(R.id.lvNotes);
        registerForContextMenu(lvNotes);}
        //this.lvNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Toast.makeText(MainActivity.this, "long clicked, "+"pos: " + i, Toast.LENGTH_LONG).show();
//
//                PopupMenu menu = new PopupMenu(MainActivity.this, view);
//                menu.getMenu().add("DELETE");
//                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        if(menuItem.getTitle().equals("DELETE")) {
//                            notesList.remove(i);
//                            listAdapter.notifyDataSetChanged();
//                            //delete the note
//                        }
//                        return true;
//                    }
//                });
////                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
////                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
////                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
////                    @Override
////                   public boolean onMenuItemClick(MenuItem menuItem) {
//
//                        return true;
//                    }
//                });
//
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//        getMenuInflater().inflate(R.menu.menu_popup,menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.Delete:
//                this.notesList.remove(1);
//                this.listAdapter.notifyDataSetChanged();
//                Toast.makeText(this, "Deleted ", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }


    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Your adapter loses reference to your list.
        //https://stackoverflow.com/questions/15422120/notifydatasetchange-not-working-from-custom-adapter
        notesList.clear();
        this.notesList.addAll((sp.getStringSet("notes", new HashSet<String>())));
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note_activity:
                Intent addActivityIntent = new Intent(getApplicationContext(), com.example.third_assignment_template.AddNoteActivity.class);
                startActivity(addActivityIntent);
                return true;
            case R.id.delete_note_activity:
                Intent deleteActivityIntent = new Intent(getApplicationContext(), com.example.third_assignment_template.DeleteNoteActivity1.class);
                startActivity(deleteActivityIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
