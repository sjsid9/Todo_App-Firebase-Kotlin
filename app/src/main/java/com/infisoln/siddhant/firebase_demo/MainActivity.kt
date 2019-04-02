package com.infisoln.siddhant.firebase_demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.google.firebase.FirebaseApp
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etNote: EditText
    private lateinit var etSubtitle: EditText
    private lateinit var btnAdd: Button
    private var notesArrayList = ArrayList<Notes>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        recyclerView = findViewById(R.id.recyclerView)
        etNote = findViewById(R.id.etNote)
        btnAdd = findViewById(R.id.btnAdd)
        etSubtitle = findViewById(R.id.etSubtitle)

        val dbRef = FirebaseDatabase.getInstance().reference

        val recyclerViewAdapter = RecyclerViewAdapter(notesArrayList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerViewAdapter

        btnAdd.setOnClickListener { dbRef.child("Notes").push().setValue(Notes(etNote.text.toString(), etSubtitle.text.toString())) }


        dbRef.child("Notes").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val notes = dataSnapshot.getValue(Notes::class.java)
                notesArrayList.add(Notes(notes!!.title!!, notes.subtitle!!))
                recyclerViewAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


    }
}
