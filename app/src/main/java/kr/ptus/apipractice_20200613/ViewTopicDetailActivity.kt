package kr.ptus.apipractice_20200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class ViewTopicDetailActivity : BaseActivity() {

    var mTopicId = -1

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

    }
}
