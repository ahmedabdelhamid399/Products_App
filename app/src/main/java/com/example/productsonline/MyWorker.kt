package com.example.productsonline

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import retrofit2.Retrofit
import java.io.File
import java.io.FileOutputStream


class MyWorker(private var context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            val response = apiService.getProducts().execute()

            if (response.isSuccessful) {
                val data = response.body()?.bytes()

                val file = File(applicationContext.filesDir, "data.txt")
                val fileOutputStream = FileOutputStream(file)
                fileOutputStream.write(data)
                fileOutputStream.close()

                return Result.success()
            } else {
                return Result.failure()
            }
        } catch (e: Exception) {
            return Result.failure()
        }
    }

}