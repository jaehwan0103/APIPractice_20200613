package kr.ptus.apipractice_20200613.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import kr.ptus.apipractice_20200613.R
import kr.ptus.apipractice_20200613.data.TopicReply
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject
import org.w3c.dom.Text

class ReplyAdapter(val mContext: Context, val resId: Int, val mList: List<TopicReply>) :
    ArrayAdapter<TopicReply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        tempRow?.let {

        }.let {
            tempRow = inf.inflate(R.layout.topic_reply_list_item, null)
        }

        val row = tempRow!!


        val writerNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val dislikeBtn = row.findViewById<Button>(R.id.disLikeBtn)
        val selectedSideTitleTxt = row.findViewById<TextView>(R.id.selectedSideTitleTxt)


        val data = mList[position]

        writerNickNameTxt.text = data.user.nickName
        contentTxt.text = data.content

        selectedSideTitleTxt.text = "(${data.selectedSide.title})"


        replyBtn.text = "답글 : ${data.replyCount}"
        likeBtn.text = "좋아요 : ${data.likeCount}"
        dislikeBtn.text = "싫어요 : ${data.disLikeCount}"

        if (data.isMyLike){

            likeBtn.setBackgroundResource(R.drawable.red_border_box)
            likeBtn.setTextColor(mContext.resources.getColor(R.color.red))
            dislikeBtn.setBackgroundResource(R.drawable.gray_border_box)
            dislikeBtn.setTextColor(mContext.resources.getColor(R.color.darkGray))


        }
        else if (data.isMydisLike){
            likeBtn.setBackgroundResource(R.drawable.gray_border_box)
            dislikeBtn.setBackgroundResource(R.drawable.blue_border_box)

            likeBtn.setTextColor(mContext.resources.getColor(R.color.darkGray))
            dislikeBtn.setTextColor(mContext.resources.getColor(R.color.blue))


        }
        else{
            likeBtn.setBackgroundResource(R.drawable.gray_border_box)
            dislikeBtn.setBackgroundResource(R.drawable.gray_border_box)
            likeBtn.setTextColor(mContext.resources.getColor(R.color.darkGray))
            dislikeBtn.setTextColor(mContext.resources.getColor(R.color.darkGray))
        }


        likeBtn.setOnClickListener {

            ServerUtil.postRequestReplyLiek(
                mContext,
                data.id,
                true,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {


                        val dataObj = json.getJSONObject("data")
                        val reply = dataObj.getJSONObject("reply")

                        data.likeCount = reply.getInt("like_count")
                        data.likeCount = reply.getInt("dislike_count")

                        data.isMyLike = reply.getBoolean("my_like")
                        data.isMydisLike = reply.getBoolean("my_dislike")

                        Handler(Looper.getMainLooper()).post {
                            notifyDataSetChanged()
                        }
                    }
                })
        }

        dislikeBtn.setOnClickListener {

            ServerUtil.postRequestReplyLiek(
                mContext,
                data.id,
                false,
                object : ServerUtil.JsonResponseHandler {

                    override fun onResponse(json: JSONObject) {
                        val dataObj = json.getJSONObject("data")
                        val reply = dataObj.getJSONObject("reply")

                        data.likeCount = reply.getInt("like_count")
                        data.disLikeCount = reply.getInt("dislike_count")

                        data.isMyLike = reply.getBoolean("my_like")
                        data.isMydisLike = reply.getBoolean("my_dislike")

                        Handler(Looper.getMainLooper()).post {
                            notifyDataSetChanged()
                        }

                    }


                })

        }

        return row


    }

}