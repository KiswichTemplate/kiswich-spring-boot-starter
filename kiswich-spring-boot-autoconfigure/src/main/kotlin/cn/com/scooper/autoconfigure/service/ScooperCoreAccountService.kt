package cn.com.scooper.autoconfigure.service

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.commonG.core.entity.AccountVo
import cn.com.scooper.commonG.resp.APIObjectJsonG
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ScooperCoreAccountService {
    @GET("data/system/authManage/tokenVerify")
    fun verifyToken(@Query("token") token: String): Call<APIRespJson>

    @POST("data/system/authManage/loginTo")
    //    @Headers({"Accept: application/json","Content-type: application/json"})
    fun login(@Query("accUsername") accUsername: String, @Query("accPassword") accPassword: String): Call<APIRespJson>

    @GET("data/system/accountManage/listAccount")
    fun listAccount(): Call<APIObjectJsonG<List<AccountVo>>>


    @GET("data/system/accountManage/getAccountDetail")
    fun getAccountDetail(@Query("id") id: Int): Call<APIObjectJsonG<AccountVo>>
}
