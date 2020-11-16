package com.example.famin.fragment.MarketInfo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.famin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_content.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContentFragment(title: String) : Fragment() {

    private lateinit var auth : FirebaseAuth

    private val db = FirebaseFirestore.getInstance()

    private val menus = ArrayList<MenuListModel>()

    private val t = title

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_content, container, false)

        // 여기에다가 메뉴들 정보 불러오면 될듯 함

        auth = FirebaseAuth.getInstance()

        db.collection("menu")
            .whereEqualTo("store", t)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents){
                    val menu = MenuListModel(
                        document.data["image"] as String,
                        document.data["name"] as String,
                        document.data["price"] as Long,
                        document.data["info"] as String
                    )
                    menus.add(menu)
                }
                val list_adaptor = ListAdaptor(requireContext(), menus)
                view.content_listview.adapter = list_adaptor
            }
            .addOnFailureListener {exception ->
                Log.w(TAG, "ERROR", exception)
            }
        return view
    }

}