package com.niuwa.my

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Base64
import com.niuwa.api.ApiResponse
import com.niuwa.MainApplication
import com.niuwa.R
import com.niuwa.api.RetrofitClient
import kotlinx.android.synthetic.main.activity_join_us.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class JoinUsActivity : WearableActivity() {
    var imageUrl = "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"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_us)
//        if (Build.VERSION.SDK_INT > 9) {
//            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//        }
        // Enables Always-on
        setAmbientEnabled()

        img_qrcode.setImageBitmap(convertStringToIcon(imageUrl))
        val app = application as MainApplication
        val requestService = RetrofitClient().create()
        requestService.getQRCode(app.getUserId()).enqueue(object :
            Callback<ApiResponse<QRCodeBean>> {
            override fun onResponse(
                call: Call<ApiResponse<QRCodeBean>>,
                response: Response<ApiResponse<QRCodeBean>>
            ) {
//                img_qrcode.setImageBitmap(convertStringToIcon(response.body()?.data?.QRCode));
            }

            override fun onFailure(call: Call<ApiResponse<QRCodeBean>>, t: Throwable) {

            }
        })


    }
    fun convertStringToIcon(output: String?): Bitmap? {
// OutputStream out;
        var bitmap: Bitmap? = null
        return try {
            val bitmapArray: ByteArray
            bitmapArray = Base64.decode(output, Base64.DEFAULT)
            bitmap = BitmapFactory.decodeByteArray(
                bitmapArray, 0,
                bitmapArray.size
            )
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            bitmap
        } catch (e: Exception) {
            null
        }
    }
    fun returnBitMap(url: String?): Bitmap? {
        var myFileUrl: URL? = null
        var bitmap: Bitmap? = null
        try {
            myFileUrl = URL(url)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            val conn: HttpURLConnection = myFileUrl?.openConnection() as HttpURLConnection
            conn.setDoInput(true)
            conn.connect()
            val `is`: InputStream = conn.getInputStream()
            bitmap = BitmapFactory.decodeStream(`is`)
            `is`.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }
}