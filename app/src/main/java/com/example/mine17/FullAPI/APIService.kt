package com.example.mine17.FullAPI

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIService {
    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getClient(): Retrofit {
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }

        fun APIEndpoint():APIEndpoint = getClient().create(APIEndpoint::class.java)
    }

}
class Constant{
    companion object{
        const val BASE_URL = "https://apibarang.herokuapp.com/"

        fun SetToken(context : Context, Token : String){
            val  TakeToken = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            TakeToken.edit().apply{
                putString("Token",Token)
                apply()
            }
        }
        fun GetToken(context : Context):String{
            val sharepref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            val token = sharepref.getString("Token","Undefined")
            return token!!
        }
        fun DelToken (context: Context){
            val deltoken = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            deltoken.edit().clear().apply()
        }
    }
}