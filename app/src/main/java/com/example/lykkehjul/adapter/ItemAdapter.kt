package com.example.lykkehjul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lykkehjul.R
import com.example.lykkehjul.data.Memory
import com.example.lykkehjul.model.Words
import org.w3c.dom.Text
import java.lang.StringBuilder

class ItemAdapter(private val context: Context, private val dataset: List<Words>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var titles = arrayOf("ord1", "ord2", Words("ord3"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position].toString()


    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
        }

    }



}