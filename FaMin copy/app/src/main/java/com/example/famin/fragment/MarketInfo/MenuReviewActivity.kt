package com.example.famin.fragment.MarketInfo

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.famin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_review.*

class MenuReviewActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private val db = FirebaseFirestore.getInstance()

    private val textArray = ArrayList<String>()
    private val nicknameArray = ArrayList<String>()
    private val ratingArray = ArrayList<String>()
    private val profileImageArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_review)

        auth = FirebaseAuth.getInstance()

        val reviewAdaptor = ReviewListAdaptor(this, nicknameArray, textArray, ratingArray,
            profileImageArray
        )
        listview_review.adapter = reviewAdaptor

        val store: String = intent.getStringExtra("store")
        val menu: String = intent.getStringExtra("menu")

        db.collection("menu_reviews")
            .whereEqualTo("store", store)
            .whereEqualTo("menu", menu)
            .get()
            .addOnSuccessListener {result ->
                for (document in result){
                    textArray.add(document.get("text") as String)
                    nicknameArray.add(document.get("writer") as String + "님의 리뷰")
                    ratingArray.add(document.get("rating") as String + " 점")
                    profileImageArray.add(document.get("user_image") as String)
                }
                reviewAdaptor.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, exception)
            }

        write_button.setOnClickListener{
            if (auth.currentUser == null){
                Toast.makeText(this, "가입 또는 로그인을 해주세요", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, WriteActivity::class.java)
                intent.putExtra("store", store)
                intent.putExtra("menu", menu)
                startActivity(intent)
            }
        }
    }
}