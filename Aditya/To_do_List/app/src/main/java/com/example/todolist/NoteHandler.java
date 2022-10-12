package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NoteHandler extends DatabaseHelper{
    public NoteHandler(Context context){
        super(context);
    }

    //creating method for CRUD operations (create , read , update , delete)

    public boolean create(Note note){

        ContentValues values = new ContentValues();   //object for storing
        values.put("title", note.getTitle());   // key value pairs inside the object .
        values.put("description",note.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        //insert Note object inside this database

        boolean isSuccessfull = db.insert("Note",null,values) > 0;    // null : our table is allowed to accept empty rows means we can ommit title or des
        db.close();
        return isSuccessfull;
    }

    //new method return type arraylist

    public ArrayList<Note> readNotes(){
        ArrayList<Note> notes  = new ArrayList<>();  //it will return after inflation

        String sqlQuery = "SELECT * FROM Note ORDER BY id ASC";   //SQL for fetching those notes, return notes from lowest id (1,2..)

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);  //navigating through the database

        if(cursor.moveToFirst()){
            do{

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));                     //operation is fetching notes from the table
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                 Note note = new Note(title, description);
                 note.setId(id);
                 notes.add(note); //add notes to the notes list
            }while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return notes;
    }

    // create method for fetching single NOTE

    public Note readSingleNote(int id){
            Note note = null;  //declaring here so we can access outside of if statements
        String sqlQuery = "SELECT * FROM Note WHERE id =" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery,null);

        if(cursor.moveToFirst()) {
            int noteID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));                     //operation is fetching notes from the table
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String description = cursor.getString(cursor.getColumnIndex("description"));

            note = new Note(title, description);
            note.setId(noteID);
        }
        cursor.close();
        db.close();
        return note;
    }

    //for updating current note

    public boolean update(Note note){
        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("description", note.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccessfull = db.update("Note",values,"id='"+note.getId()+"'",null) > 0 ;// single quote due to sql
        db.close();
        return isSuccessfull;
    }
    //for delete note

    public boolean delete(int id ){
        boolean isDeleted;
        SQLiteDatabase db = this.getWritableDatabase();
        isDeleted = db.delete("Note", "id='" + id + "'",null) > 0;
        db.close();
        return isDeleted;
    }
}
