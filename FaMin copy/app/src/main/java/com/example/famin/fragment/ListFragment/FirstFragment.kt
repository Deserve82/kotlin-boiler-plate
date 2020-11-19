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
            ContentsListModel(R.drawable.bbq, "bbq", 1, "BBQ는 최고의 치킨 맛,\n" +
                    "건강에 좋은 치킨을 만들겠다는 일념으로\n" +
                    "올리브유 중에서도 최고급유이자\n" +
                    "‘신이 내린 선물’이라 불리는\n" +
                    "100%엑스트라 버진 올리브유를\n" +
                    "후라잉오일의 원료로 사용하고 있으며\n" +
                    "국민건강 증진에 기여하고자 합니다.\n"),
            ContentsListModel(R.drawable.bhc, "bhc", 1, "더 맛있고!\n" +
                    "bhc만의 독보적인 신메뉴 개발에 집중하여 고객에게 폭발적인 인기를 얻는 치킨 업계의 새로운 패러다임을 주도하는 브랜드가 되겠습니다.\n" +
                    "\n" +
                    "더 깨끗한!\n" +
                    "안전한 먹거리 제공으로 고객에게 신뢰받는 브랜드로 끊임없이 성장하여 업계 주도하는 브랜드로 우뚝 서겠습니다.\n" +
                    "\n" +
                    "더 친절한!\n" +
                    "고객 응대와 서비스로 감성을 만족시켜 사랑받는 기업으로 기억될 수 있도록 노력하겠습니다.\n"),
            ContentsListModel(R.drawable.ddoreore, "또레오레", 1, "또래오래(TOREORE)는 \"즐거움이 있는 곳에 친구들을 초대한다\"라는 의미로 생각과 행동을 함께하는 또래들이 건전한 먹거리를 함께 즐길 수 있는 문화공간이라는 의미입니다.\n" +
                    "\n" +
                    "목우촌 또래오래는 HACCP(유해요소중점관리) 적용 사업장에서 안전하게 처리된 목우촌 닭고기를 사용하여 더욱 믿을 수 있습니다. 목우촌은 소비자들에게는 국내 축산농가에서 키운 안전한 먹거리를 제공해주고, 국내 축산농가에게는 안정적인 소비처를 확보해 줌으로써 양축 농가의 소득 증대에 기여하여 생산자와 소비자를 최대한 배려한 프랜차이즈 사업을 하고 있습니다.\n" +
                    "목우촌 또래오래는 항상 바른 먹거리를 선도할 것을 약속 드리며, 또래오래의 좋은 치킨을 통해 재미와 감동을 드리겠습니다.\n" +
                    "\n" +
                    "또래오래에 관심을 가지고 찾아주신 여러분들께 진심으로 감사 드리며, 그 성원을 잊지 않고 더욱더 정진하는 또래오래가 될 것을 약속드립니다.\n" +
                    "\n" +
                    "감사합니다.\n" +
                    "\n"),
            ContentsListModel(R.drawable.goopne, "굽네치킨", 1, "튀기지 않고 오븐에 구워 치킨 본연의 맛을 선보이겠다는 도전적인 발상은\n" +
                    "기름은 쏙 빼고 그 자리에 맛과 건강을 채워 넣는 치킨 혁신으로 이어졌습니다.\n" +
                    "‘더 맛있고, 더 건강한 치킨’을 만들기 위한 굽네치킨의 고집,\n" +
                    "대한민국을 넘어 세계에서 새로운 치킨 문화를 이끌어가고 있습니다.\n"),
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