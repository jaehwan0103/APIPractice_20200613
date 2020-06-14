package kr.ptus.apipractice_20200613.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.ptus.apipractice_20200613.R
import kr.ptus.apipractice_20200613.data.Topic

class TopicAdapter(val mContext : Context , val resId : Int , val mList : List<Topic>) : ArrayAdapter<Topic>(mContext,resId,mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null){

            tempRow = inf.inflate(R.layout.topic_list_item,null)


        }

        val row = tempRow!!

        val topicImg = row.findViewById<ImageView>(R.id.topicImg)
        val topicTitleTxt = row.findViewById<TextView>(R.id.topicTitleTxt)

        val data = mList[position]

        topicTitleTxt.text = data.title

        Glide.with(mContext).load(data.imageUrl).into(topicImg)

        return row


    }

}