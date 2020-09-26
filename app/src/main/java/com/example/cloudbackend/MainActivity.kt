package com.example.cloudbackend

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences :SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        registerHere.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }
        Login.setOnClickListener {
            loginUser()
            editor.putBoolean("State",true).apply()
        }
        sharedPreferences = applicationContext.getSharedPreferences("LoginPreference",Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val checkShared = sharedPreferences.getBoolean("State",false)
        if (checkShared){
            if (auth.currentUser != null){
                startActivity(Intent(applicationContext,ForBackend::class.java))
                finish()
            }
        }
    }
    private fun loginUser(){
        val email = LoginEmailAddress.text.toString()
        val password = LoginPassWord.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@MainActivity,"Logged in Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity,ForBackend::class.java)
                        startActivity(intent)
                        finish()
                    }
                    }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        error.text = "Email and password does not match"
                    }
                    }
                }
            }
        }
}