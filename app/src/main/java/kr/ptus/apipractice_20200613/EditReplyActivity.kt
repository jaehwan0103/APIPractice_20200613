package kr.ptus.apipractice_20200613

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_reply.*
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject

class EditReplyActivity : BaseActivity() {

    var mTopicId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_reply)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

        postReplyBtn.setOnClickListener {

            val editpost = editPostTxt.text.toString()

            if (editpost.length < 10 ){

                Toast.makeText(mContext,"의견은 최소 10글자 이상이어야합니다.", Toast.LENGTH_SHORT).show()
            }


            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("내용 변경 불가 안내")
            alert.setMessage("한번 등록한 의견은 내용을 수정할 수 없습니다. 의견을 등록한 뒤에는 재투표가 불가합니다.")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

                ServerUtil.postRequestReply(mContext , mTopicId, editpost , object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        val code = json.getInt("code")

                        if (code == 200){

                            runOnUiThread {

                                Toast.makeText(mContext,"의견등록 완료", Toast.LENGTH_SHORT).show()
                                finish()
                            }

                        }
                        else{
                            runOnUiThread {
                                Toast.makeText(mContext,"의견등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                })

            })

            alert.setNegativeButton("취소",null)
            alert.show()


        }



    }

    override fun setValues() {

        topicTitleTxt.text = intent.getStringExtra("topicTitle")
        selectedSideTitleTxt.text = intent.getStringExtra("selectedSideTitle")
        mTopicId = intent.getIntExtra("topicId", -1)

        if (mTopicId == -1){

            Toast.makeText(mContext, "잘못된 접근입니다.",Toast.LENGTH_SHORT).show()
            finish()

        }

    }
}
