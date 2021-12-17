package com.csc415.smithsona2

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smithsona2.retrofit.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LatinActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_fullText)

		if (intent.extras!!.containsKey(POST_ID))
		{
			val postId = intent.getStringExtra(POST_ID)!!

			val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
				.addConverterFactory(GsonConverterFactory.create()).build()
				.create(PostService::class.java)

			retrofit.getPost(postId.toInt()).enqueue(PostCallback())
		}
	}

	companion object
	{
		const val POST_ID = "com.csc415.smithsona2.POST_ID"
	}

	inner class PostCallback : Callback<List<LatinText>>
	{
		override fun onResponse(call: Call<List<LatinText>>, response: Response<List<LatinText>>)
		{
			if (response.isSuccessful)
			{
				if (response.body()!!.isNotEmpty())
				{
					response.body()!!.let {
						findViewById<TextView>(R.id.title).text = it[0].title
						findViewById<TextView>(R.id.body).text = it[0].body
					}
				}
				else
				{
					Toast.makeText(
						applicationContext,
						"Could not find post.",
						Toast.LENGTH_LONG
					).show()
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