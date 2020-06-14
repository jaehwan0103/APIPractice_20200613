package kr.ptus.apipractice_20200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import kr.ptus.apipractice_20200613.data.Topic
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    var mTopicId = -1

    lateinit var mTopic : Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)

        setupEvents()
        setValues()
    }
    override fun setupEvents() {


    }

    override fun setValues() {

        mTopicId = intent.getIntExtra("topic_id", -1)

        if (mTopicId == -1){

            Toast.makeText(mContext, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
            return

        }

        Log.d("넘겨받은 주제 id", mTopicId.toString())

        ServerUtil.getRequestTopicDetail(mContext, mTopicId,object : ServerUtil.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {

                val code = json.getInt("code")

                if (code == 200){

                    val data = json.getJSONObject("data")
                    val topic = data.getJSONObject("topic")

                    mTopic = Topic.getTopicFromJson(topic)

//                    받아온 주제의 제목을 화면에 표시
                    runOnUiThread{
                    topicTitleTxt.text = mTopic.title
                    }
                }

            }


        })

    }
}
