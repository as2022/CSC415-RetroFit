package com.csc415.smithsona2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smithsona2.retrofit.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
			.addConverterFactory(GsonConverterFactory.create()).build()
			.create(PostService::class.java)

		retrofit.getPosts().enqueue(PostCallback())
	}

	inner class PostCallback : Callback<List<LatinText>>
	{
		override fun onResponse(call: Call<List<LatinText>>, response: Response<List<LatinText>>)
		{
			if (response.isSuccessful)
			{
				findViewById<RecyclerView>(R.id.recycler_view).apply {
					adapter = LatinAdapter(response.body()!!, this@MainActivity)
					layoutManager = LinearLayoutManager(this@MainActivity)
				}
			}
			else
			{
				Toast.makeText(
					applicationContext,
					"Something went wrong fetching the data. Please try again later.",
					Toast.LENGTH_LONG
				).show()
			}
		}

		override fun onFailure(call: Call<List<LatinText>>, t: Throwable)
		{
			Toast.makeText(
				applicationContext,
				"Something went wrong fetching the data. Make sure you have a good Internet connection.",
				Toast.LENGTH_LONG
			).show()
			Log.e("test", t.message, t)
		}
	}
}