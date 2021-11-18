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

class ItemAdapter(private val data: List<Words>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var titles = arrayOf("ord1", "ord2", Words("ord3"))
    var hemmeligtOrd = ""

    private val items: MutableList<Words>

    init {
        this.items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {

        /*val dataFromMemory = Memory().loadWords()
        hemmeligtOrd = dataFromMemory.random().toString()*/

        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.tvItem.text = data[position].text

    }

    override fun getItemCount(): Int {
        return data.size
        //return titles.size
    }

    inner class ViewHolder
    internal constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView = itemView.findViewById(R.id.tvItem)
    }




}