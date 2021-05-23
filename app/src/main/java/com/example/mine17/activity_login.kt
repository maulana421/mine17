package com.example.mine17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mine17.FullAPI.APIService
import com.example.mine17.FullAPI.Constant
import com.example.mine17.databinding.ActivityLoginBinding
import com.example.mine17.model.User
import com.example.mine17.response.SingleRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SignUp()
        ButtonSignIn()
    }
    override fun onResume() {
        super.onResume()
        IsLogin()

    }
    private fun SignUp(){
        binding.ButtonSignUp.setOnClickListener {
            startActivity(Intent(this,activity_signup::class.java))
            finish()
        }
    }
    private fun ReqLogin(){
        val username = binding.ETUsername.text.toString()
        val password = binding.ETPassword.text.toString()
        APIService.APIEndpoint().SignIn(username, password).enqueue(object :
            Callback<SingleRespon<User>> {
            override fun onResponse(call: Call<SingleRespon<User>>, response: Response<SingleRespon<User>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        Constant.SetToken(this@activity_login,body.data.token)
                        Toast.makeText(applicationContext," Hii ${body.data.name}", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@activity_login,MainActivity::class.java))
                        finish()
                    }
                }else{
                    Toast.makeText(applicationContext,"Login Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SingleRespon<User>>, t: Throwable) {
                println(t.message)
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun IsLogin(){
        val token = Constant.GetToken(this)
        if(!token.equals("Undefined")){
            startActivity(Intent(this,MainActivity::class.java).also {
                finish()
            })

        }
    }
    private fun ButtonSignIn(){
        binding.ButtonSignIn.setOnClickListener {
            ReqLogin()

        }
    }
}