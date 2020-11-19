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

/*
리뷰를 작성하는 페이지
리뷰를 작성하는 페이지 입니다.
별점은 String 값으로 들어가게 됩니다.
*/

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
        rating_area.setOnRatingBarChangeListener { _, fl, _ ->
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
                // 메뉴가 아닌 가게의 리뷰를 남길때입니다. 코드 재사용을 위해 한 곳에서 리뷰 저장을 처리하게 만들었습니다.
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
                // 메뉴에 관련된 리뷰를 남길때 입니다.
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