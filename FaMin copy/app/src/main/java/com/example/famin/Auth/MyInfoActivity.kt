package com.example.famin.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.famin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_comment.*

/*
내 정보를 보여주는 액티비티 입니다.
*/


class MyInfoActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_comment)

        auth = FirebaseAuth.getInstance()
        val docRef = db.collection("users")
            .document(auth.currentUser?.uid.toString())
        docRef.get().addOnSuccessListener { documentSnapshot ->
            nickname_area.text = documentSnapshot.get("nickname").toString()
            Picasso.get().load(documentSnapshot.get("user_image").toString()).into(profile_img)
        }
    }
}