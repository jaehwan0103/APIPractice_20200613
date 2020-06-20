package kr.ptus.apipractice_20200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import kr.ptus.apipractice_20200613.adapter.ReplyAdapter
import kr.ptus.apipractice_20200613.data.Topic
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    var mTopicId = -1

    lateinit var mTopic : Topic

    lateinit var mReplyAdapter : ReplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)

        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        firstBtn.setOnClickListener {

            ServerUtil.postRequestVote(mContext,mTopic.sides[0].id,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

                }


            })


        }


        secondBtn.setOnClickListener {
            ServerUtil.postRequestVote(mContext,mTopic.sides[1].id,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

                    val code = json.getInt("code")

                    if (code == 200) {

                        runOnUiThread {
                            Toast.makeText(mContext, "참여해주셔서 감사합니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        val message = json.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }


            })


        }

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

                        Glide.with(mContext).load(mTopic.imageUrl).into(topicImg)

                        firstSideTxt.text = mTopic.sides[0].title
                        secondSideTxt.text = mTopic.sides[1].title

                        firstSideVoteCountTxt.text = "${mTopic.sides[0].votecount}표"
                        secondSideVoteCountTxt.text = "${mTopic.sides[1].votecount}표"

                        mReplyAdapter = ReplyAdapter(mContext, R.layout.topic_reply_list_item, mTopic.replies)
                        replyListView.adapter = mReplyAdapter

                    }
                }

            }


        })



    }
}
