package com.example.famin.fragment.MarketInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.famin.MainActivity
import com.example.famin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_write.*

class WriteActivity : AppCompatActivity() {

    private lateinit var rating_num : String

    private lateinit var auth : FirebaseAuth

    private lateinit var nickname: String
    private lateinit var userImage: String


    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        auth = FirebaseAuth.getInstance()

        // rating
        rating_area.setOnRatingBarChangeListener { ratingBar, fl, b ->
            rating_num = fl.toString()
        }

        //nickname 받아오기
        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot ->
            nickname = documentSnapshot.get("nickname") as String
            userImage = documentSnapshot.get("user_image") as String
        }

        val store: String = intent.getStringExtra("store")
        val menu: String = intent.getStringExtra("menu")

        writing_button.setOnClickListener {
            if (menu == "none"){
            val form = hashMapOf(
                "text" to text_input_area.text.toString(),
                "writer" to nickname,
                "rating" to rating_num,
                "user_image" to userImage,
                "store" to store
            )
            db.collection("reviews")
                .add(form)
                .addOnSuccessListener { Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                }
            }
            else {
                val form = hashMapOf(
                    "text" to text_input_area.text.toString(),
                    "writer" to nickname,
                    "rating" to rating_num,
                    "user_image" to userImage,
                    "store" to store,
                    "menu" to menu
                )
                db.collection("menu_reviews")
                    .add(form)
                    .addOnSuccessListener { Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}