package com.example.mine17.FullAPI

import com.example.mine17.model.User
import com.example.mine17.response.SingleRespon
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIEndpoint {
    @FormUrlEncoded
    @POST("auth/sign-up")
    fun SignUp(@Field("name")name :String,
               @Field("username")username : String,
               @Field("email")email : String,
               @Field("password")password : String) : Call<SingleRespon<User>>

    @FormUrlEncoded
    @POST("auth/sign-in")
    fun SignIn(@Field("username")username : String,
               @Field("password")password : String) : Call<SingleRespon<User>>
}