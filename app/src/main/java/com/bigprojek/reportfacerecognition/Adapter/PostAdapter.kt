package com.bigprojek.reportfacerecognition.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bigprojek.reportfacerecognition.R
import com.bigprojek.reportfacerecognition.retrofit.PostResponse
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter (
    val tamu : ArrayList<PostResponse.Data>
): RecyclerView.Adapter<PostAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent ,false)
    )

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val data = tamu[position]
        holder.textNama.text = data.nama
        holder.textNohp.text = data.nohp
        holder.textKeperluan.text = data.keperluan
        holder.textWaktu.text = data.waktu
        holder.textMin.text = "-"
    }

    override fun getItemCount()= tamu.size

    class ViewHolder (view : View):RecyclerView.ViewHolder(view){
        val textNama = view.findViewById<TextView>(R.id.tvNama)
        val textNohp = view.findViewById<TextView>(R.id.tvNohp)
        val textKeperluan = view.findViewById<TextView>(R.id.tvKeperluan)
        val textWaktu = view.findViewById<TextView>(R.id.tvWaktu)
        val textMin = view.findViewById<TextView>(R.id.min)

    }

    public fun setData(data: List<PostResponse.Data>){
        tamu.clear()
        tamu.addAll(data)
        notifyDataSetChanged()
    }
}