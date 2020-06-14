package kr.ptus.apipractice_20200613

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kr.ptus.apipractice_20200613.adapter.TopicAdapter
import kr.ptus.apipractice_20200613.data.Topic
import kr.ptus.apipractice_20200613.util.ContextUtil
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject
import java.util.ArrayList

class MainActivity : BaseActivity() {

    val  topiclist = ArrayList<Topic>()

    lateinit var topicAdapter : TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


        logoutBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃 확인")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                ContextUtil.setuserToken(mContext, "")

                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

                finish()

            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }

    }

    override fun setValues() {

        getTopicListFromServer()

        topicAdapter = TopicAdapter(mContext, R.layout.topic_list_item,topiclist)
        topiclist

//        ServerUtil.getRequestMyInfo(mContext, object : ServerUtil.JsonResponseHandler {
//            override fun onResponse(json: JSONObject) {
//
//                val data = json.getJSONObject("data")
//                val user = data.getJSONObject("user")
//                val nickName = user.getString("nick_name")
//
//                runOnUiThread {
//                    loginUserNickNameTxt.text = "${nickName}님 환영합니다."
//                }
//            }
//
//
//        })


    }

    fun getTopicListFromServer() {

        ServerUtil.getRequestV2Mainifo(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val code = json.getInt("code")

                if (code == 200) {
                    val data = json.getJSONObject("data")
                    val topics = data.getJSONArray("topics")

                    for (i in 0..topics.length() - 1) {
                        val topicJson = topics.getJSONObject(i)

                        val topic = Topic.getTopicFromJson(topicJson)

                        topiclist.add(topic)

                    }

                    topicAdapter.notifyDataSetChanged()

                }


            }

        })

    }

}
