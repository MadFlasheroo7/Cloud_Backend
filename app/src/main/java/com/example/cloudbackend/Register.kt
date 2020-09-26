package com.example.cloudbackend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        register.setOnClickListener {
            registerUser()
        }
    }
    private fun registerUser(){
        val email = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassWord.text.toString()
        val confirmPassword = confirmPassword.text.toString()
        if (password != confirmPassword){
            Toast.makeText(this,"Password dose not match with confirm password",Toast.LENGTH_LONG).show()
        }else{
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        auth.createUserWithEmailAndPassword(email,confirmPassword).await()
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@Register,"Registered Successfully",Toast.LENGTH_LONG).show()
                            val intent = Intent(this@Register,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }catch (e:Exception){
                        Toast.makeText(this@Register,"Registration was UnSuccessful",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}