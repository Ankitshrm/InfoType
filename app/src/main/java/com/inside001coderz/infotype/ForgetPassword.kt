package com.inside001coderz.infotype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {
    lateinit var btnforgot: TextView
    lateinit var editextforgot: EditText
    lateinit var email :String
    lateinit var mauth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnforgot= findViewById(R.id.forgotbtn)
        editextforgot=findViewById(R.id.forgoteditext)


        mauth = FirebaseAuth.getInstance()

        btnforgot.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        email= editextforgot.text.toString()
        if(email.isEmpty()){
            editextforgot.setError("Required")
        }else{
            send()
        }
    }

    private fun send() {
        mauth.sendPasswordResetEmail(email)
            .addOnCompleteListener{it ->
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Check your email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,SignIn::class.java))
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Something went wrong!!", Toast.LENGTH_SHORT).show()

                }
            }
    }
}
