package com.example.famin.fragment.MarketInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.famin.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_review.*

class MenuReviewActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private val textArray = ArrayList<String>()
    private val nicknameArray = ArrayList<String>()
    private val ratingArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_review)

        val reviewAdaptor = ReviewListAdaptor(this, nicknameArray, textArray, ratingArray)
        listview_review.adapter = reviewAdaptor
    }
}