package com.example.famin.fragment.ListFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.famin.R
import com.example.famin.fragment.MarketInfo.MarketInfoActivity
import com.example.famin.utils.FirebaseUtils
import kotlinx.android.synthetic.main.fragment_first.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        val list_array = arrayListOf<ContentsListModel>(
            ContentsListModel(R.drawable.bbq, "bbq", 1, "가장 유서 깊은 치킨집 bbq! 1990년부터 이어진 맛집 정신으로 여러분의 입맛을 즐겁게 해드립니다."),
            ContentsListModel(R.drawable.bhc, "bhc", 1, "bhc! 아 그 이름만 들어도 설레는 이 기분, 과연 당신은 무엇을 그토록 원했을까요?"),
            ContentsListModel(R.drawable.ddoreore, "또레오레", 1, "d"),
            ContentsListModel(R.drawable.goopne, "굽네치킨", 1, "d"),
            ContentsListModel(R.drawable.hosik, "호식이 두마리 치킨", 1, "d"),
            ContentsListModel(R.drawable.kfc, "kfc", 1, "d")
            )

        val list_adaptor = FirstFragAdapter(requireContext(), list_array)
        view.listview_first_fragment.adapter = list_adaptor

        view.listview_first_fragment.setOnItemClickListener {
                _, _, i, _ ->
            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title", list_array[i].title)
            intent.putExtra("logo", list_array[i].image)
            intent.putExtra("info", list_array[i].info)
            startActivity(intent)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}