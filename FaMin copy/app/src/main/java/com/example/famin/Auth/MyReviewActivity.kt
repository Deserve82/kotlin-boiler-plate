package com.example.famin.Auth

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.famin.R
import com.example.famin.fragment.MarketInfo.ReviewListAdaptor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lecture.*
import kotlinx.android.synthetic.main.activity_my_comment.*
import kotlinx.android.synthetic.main.activity_my_review.*

class MyReviewActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var userId : String

    private val db = FirebaseFirestore.getInstance()

    private val textArray = ArrayList<String>()
    private val nicknameArray = ArrayList<String>()
    private val ratingArray = ArrayList<String>()
    private val profileImageArray = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_review)

        auth = FirebaseAuth.getInstance()
        val reviewAdaptor = ReviewListAdaptor(this, nicknameArray, textArray, ratingArray,
            profileImageArray
        )
        listview_review.adapter = reviewAdaptor

        val docRef = db.collection("users")
            .document(auth.currentUser?.uid.toString())
        docRef.get().addOnSuccessListener { documentSnapshot ->
            userId = documentSnapshot.get("nickname").toString()
            db.collection("reviews")
                .whereEqualTo("writer", userId)
                .get()
                .addOnSuccessListener {result ->
                    for (document in result) {
                        textArray.add(document.get("text") as String)
                        nicknameArray.add(document.get("writer") as String + "님의 리뷰")
                        ratingArray.add(document.get("rating") as String + " 점")
                        profileImageArray.add(document.get("user_image") as String)
                    }
                    reviewAdaptor.notifyDataSetChanged()
                }
                .addOnFailureListener {exception ->
                    Log.w(ContentValues.TAG, exception)
                }
        }

    }
}