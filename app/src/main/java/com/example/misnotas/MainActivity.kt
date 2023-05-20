package com.example.misnotas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var newNoteTitle:EditText?=null
    var newNoteContent:EditText?=null
    var noteTitle:TextView?=null
    var noteContent:TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var preferences:SharedPreferences=getSharedPreferences("nota", MODE_PRIVATE)

        if (preferences.getString("help","no existe")=="no existe"){
            var editor:SharedPreferences.Editor=preferences.edit()
            editor.putString("help","existe")
            editor.commit()

            val intent =Intent(this,HelpActivity::class.java)
            startActivity(intent)
        }
        iniciarComponentes()



        /*val notesList = findViewById<TextView>(R.id.notes_list)*/

    }
    private fun iniciarComponentes() {

        newNoteTitle=findViewById(R.id.new_note_title)
        newNoteContent=findViewById(R.id.new_note_content)
        noteTitle=findViewById(R.id.note_title)
        noteContent=findViewById(R.id.note_content)

        val addNoteButton = findViewById<Button>(R.id.add_note_button)
        addNoteButton.setOnClickListener { guardarDatos()}
        val editNoteButton:Button = findViewById(R.id.edit_note_button)
        editNoteButton.setOnClickListener {
            var preferences:SharedPreferences=getSharedPreferences("notas",Context.MODE_PRIVATE)
            var editor:SharedPreferences.Editor=preferences.edit()
            editor.clear()
            noteTitle?.text=""
            noteContent?.text=""
        }

    }
    private fun guardarDatos() {
        //creamos las preferencias
        var preferences:SharedPreferences=getSharedPreferences("notas",Context.MODE_PRIVATE)

        var title= newNoteTitle?.text.toString()
        var content= newNoteContent?.text.toString()

        //decimos que vamos a editar el archivo
        var editor:SharedPreferences.Editor=preferences.edit()
        editor.putString("titulo",title)
        editor.putString("contenido",content)

        noteTitle?.text=title
        noteContent?.text=content

        //confirmamos el almacenamiento
        editor.commit()

        Toast.makeText(this,"Se ha guardado la nota",Toast.LENGTH_SHORT).show()
    }
}