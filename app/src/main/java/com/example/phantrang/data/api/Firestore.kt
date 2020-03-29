package com.example.phantrang.data.api

import com.example.phantrang.data.db.model.NumberItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class Firestore {
    private  var db: FirebaseFirestore
    init {
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build() // tat tu dong luu offline cua firestore
        db.firestoreSettings = settings
    }
    private var first = db.collection("numbers").orderBy("content").limit(40)
    private var next: Query? = null

    fun refresh() {
        next = null // xóa đánh dấu next để quay lại đầu của truy vấn
    }

    fun getOnePage(onSuccess: (List<NumberItem>) -> Unit) {
       if(next != null) {
           next!!.get().addOnSuccessListener {querySnapshot: QuerySnapshot ->
               if (!querySnapshot.documents.isEmpty()) {
                   val lastItem = querySnapshot.documents[querySnapshot.size()-1]
                   next = db.collection("numbers").orderBy("content")
                       .startAfter(lastItem)
                       .limit(20)
                   onSuccess( querySnapshot.map { it.toObject(NumberItem::class.java) }) // send back
               }

           }

       } else {

           first.get().addOnSuccessListener { querrySnap ->
               if (!querrySnap.documents.isEmpty()) {
                   val lastItem = querrySnap.documents[querrySnap.size()-1]
                   next = db.collection("numbers").orderBy("content")
                       .startAfter(lastItem)
                       .limit(20)

                   onSuccess( querrySnap.map { it.toObject(NumberItem::class.java) }) // send back
               }

           }
       }


    }
}