package com.example.famin.fragment.MarketInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.famin.R
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
class ContentFragment : Fragment() {

    private val menuImage = ArrayList<String>()
    private val menuTitle = ArrayList<String>()
    private val menuInfo = ArrayList<String>()
    private val menuPrice = ArrayList<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_content, container, false)

        // 여기에다가 메뉴들 정보 불러오면 될듯 함

        val list_adaptor = ListAdaptor(requireContext(), menuImage, menuTitle, menuInfo, menuPrice)
        view.content_listview.adapter = list_adaptor

        return view
    }

}