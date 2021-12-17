package com.csc415.smithsona2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.smithsona2.retrofit.R

class LatinAdapter(val words: List<LatinText>, private val context: Context) : RecyclerView.Adapter<LatinAdapter.LatinHolder>()
{
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatinHolder
	{
		val view: View = LayoutInflater.from(context).inflate(R.layout.preview_card, parent, false)
		return LatinHolder(view)
	}

	override fun onBindViewHolder(holder: LatinHolder, position: Int)
	{
		holder.title.text = words[position].title
	}

	override fun getItemCount(): Int = words.size

	inner class LatinHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
	{
		val title: Button
			get() = itemView.findViewById(R.id.card_title)

		init
		{
			title.setOnClickListener {
				val intent = Intent(context, LatinActivity::class.java).apply {
					putExtra(LatinActivity.POST_ID, words[layoutPosition].id.toString())
				}
				context.startActivity(intent)
			}
		}
	}
}