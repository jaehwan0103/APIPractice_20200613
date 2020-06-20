package kr.ptus.apipractice_20200613.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kr.ptus.apipractice_20200613.R
import kr.ptus.apipractice_20200613.data.TopicReply
import org.w3c.dom.Text

class ReplyAdapter(val mContext: Context, val resId: Int, val mList: List<TopicReply>) :
    ArrayAdapter<TopicReply>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var  tempRow = convertView

        tempRow?.let {

        }.let {
            tempRow = inf.inflate(R.layout.topic_reply_list_item,null)
        }

        val row = tempRow!!


        val writerNickNameTxt = row.findViewById<TextView>(R.id.writerNickNameTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        val data = mList[position]

        writerNickNameTxt.text = data.user.nickName
        contentTxt.text = data.content

        return  row


    }

}