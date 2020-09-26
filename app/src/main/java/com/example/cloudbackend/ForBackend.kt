package com.example.cloudbackend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_for_backend.*

class ForBackend : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_backend)
        auth = FirebaseAuth.getInstance()
        val home = Home()
        val cart = Cart()
        val profile = Profile()
        setCurrentFragment(home)
        bottomNavigation = bottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.menuHome -> setCurrentFragment(home)
                R.id.menuCart -> setCurrentFragment(cart)
                R.id.menuProfile -> setCurrentFragment(profile)
            }
            true
        }
//        signout.setOnClickListener(View.OnClickListener {
//            signOut()
//        })
    }

    private fun setCurrentFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentHolder,fragment)
            commit()
        }
    }
//    private fun signOut() {
//        auth.signOut()
//        Toast.makeText(this,"Logged out Successfully", Toast.LENGTH_SHORT).show()
//        val intent = Intent(this,MainActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
}