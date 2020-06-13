package kr.ptus.apipractice_20200613

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.ptus.apipractice_20200613.util.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        signupBtn.setOnClickListener {  }

        nickNameTxt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nickNameResultTxt.text = "중복확인을 해주세요."
            }


        })

        emailEdt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                Log.d("변경된내용",s.toString())
                emailCheckResultTxt.text = "중복확인을 해주세요."

            }

        })

        nickNameBtn.setOnClickListener {

            val inputNickname = nickNameTxt.text.toString()

            ServerUtil.getRequestDuplicatedCheck(
                mContext,
                "NICKNAME",
                inputNickname,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {

                        val code = json.getInt("code")
                        runOnUiThread() {
                            if (code == 200) {
                                nickNameResultTxt.text = "사용해도 좋습니다."
                            } else {
                                nickNameResultTxt.text = "중복된 닉네임입니다."
                            }
                        }
                    }
                })
        }

        emailCheckBtn.setOnClickListener {

            val inputEmail = emailEdt.text.toString()

            ServerUtil.getRequestDuplicatedCheck(
                mContext,
                "EMAIL",
                inputEmail,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        val code = json.getInt("code")
                        runOnUiThread {
                            if (code == 200) {
                                emailCheckResultTxt.text = "사용해도 좋습니다."
                            } else {
                                emailCheckResultTxt.text = "이미 사용중입니다. 다른이메일로 다시 체크해주세요."
                            }
                        }
                    }
                })

        }

    }

    override fun setValues() {

    }
}
