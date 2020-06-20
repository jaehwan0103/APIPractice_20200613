package kr.ptus.apipractice_20200613

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_reply.*

class EditReplyActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_reply)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

        postReplyBtn.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("내용 변경 불가 안내")
            alert.setMessage("한번 등록한 의견은 내용을 수정할 수 없습니다. 의견을 등록한 뒤에는 재투표가 불가합니다.")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

            })

            alert.setNegativeButton("취소",null)
            alert.show()

        }



    }

    override fun setValues() {

        topicTitleTxt.text = intent.getStringExtra("topicTitle")
        selectedSideTitleTxt.text = intent.getStringExtra("selectedSideTitle")
    }
}
