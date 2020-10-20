package com.example.famin.fragment.MarketInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.famin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_review.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    private val db = FirebaseFirestore.getInstance()

    private val text_array = ArrayList<String>()
    private val nickname_array = ArrayList<String>()
    private val rating_array = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_review, container, false)

        auth = FirebaseAuth.getInstance()

        val review_adapter = ReviewListAdaptor(requireContext(), nickname_array, text_array, rating_array)
        view.listview_review.adapter = review_adapter

        db.collection("reviews")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    rating_array.add(document.get("rating") as String)
                    text_array.add(document.get("text") as String)
                    nickname_array.add(document.get("writer") as String)
                }
                review_adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->  }
        view.write_button.setOnClickListener{
            if (auth.currentUser == null){
                Toast.makeText(requireContext(), "가입 또는 로그인을 해주세요", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(requireContext(), WriteActivity::class.java)
                startActivity(intent)
            }
        }
        return view
    }
}