package com.example.mine17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mine17.FullAPI.Constant
import com.example.mine17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Logout()
    }
    private fun Logout(){
        binding.ButtonLogout.setOnClickListener {
            Constant.DelToken(this)
            startActivity(Intent(this,activity_login::class.java))
            finish()

        }
    }
}