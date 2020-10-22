package com.example.famin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.famin.Auth.LoginActivity
import com.example.famin.Auth.MyCommentActivity
import com.example.famin.utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.bottom.*

class CartActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var cartviewAdpater : CartlistAdapter

    private val cart_array = ArrayList<CartsListModel>()
    private var checked_cart_array = ArrayList<CartsListModel>()

    private var pimage : String = ""
    private var ptitle : String = ""
    private var price : String = ""
    private var qunatitiy : String = ""
    private var d : Int = 0

    fun showlist(){
        cartviewAdpater = CartlistAdapter(this, cart_array)
        cart_list_view.adapter = cartviewAdpater
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        auth = FirebaseAuth.getInstance()

         FirebaseUtils.db
            .collection("cart")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    for (cart in task.result!!){
                        pimage = cart.get("image") as String
                        ptitle = cart.get("title") as String
                        price = cart.get("price") as String
                        qunatitiy = cart.get("quantity") as String
                        val cart = CartsListModel(pimage, ptitle, price, qunatitiy)
                        cart_array.add(cart)
                    }
                showlist()
            }

        home.setOnClickListener {
            finish()
        }

        buy_cart_btn.setOnClickListener {
            checked_cart_array = cartviewAdpater.getChcekdItems()
            for (item in checked_cart_array){
                FirebaseUtils.db
                    .collection("cart")
                    .document(item.title)
                    .delete()
            }
            finish()
            val intent = Intent(this, BuyActivity::class.java)
            intent.putExtra("buy_items", checked_cart_array)
            startActivity(intent)
        }



        my_page.setOnClickListener {
            if(auth.currentUser == null){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this, MyCommentActivity::class.java)
                startActivity(intent)
            }
        }

    }
}