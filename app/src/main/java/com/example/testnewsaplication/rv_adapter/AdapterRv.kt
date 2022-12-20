package com.example.testnewsaplication.rv_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testnewsaplication.databinding.ItemRvModelBinding
import com.example.testnewsaplication.model_response.NewsContentResponse
import com.example.testnewsaplication.rv_adapter.AdapterRv.*
import com.squareup.picasso.Picasso

interface SetOnClick{
    fun onClickElement(mappingModel: NewsContentResponse)
}

class AdapterRv(
    private val setOnClick: SetOnClick
):PagingDataAdapter<NewsContentResponse, MayViewHolder>(diffCallback),View.OnClickListener {

    inner class MayViewHolder( val binding: ItemRvModelBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val mappingModel:NewsContentResponse= v.tag as NewsContentResponse
        setOnClick.onClickElement(mappingModel)
    }

    companion object{
        val diffCallback= object: DiffUtil.ItemCallback<NewsContentResponse>(){
            override fun areItemsTheSame(oldItem: NewsContentResponse, newItem: NewsContentResponse): Boolean {
                return oldItem.title==newItem.title
            }

            override fun areContentsTheSame(oldItem: NewsContentResponse, newItem: NewsContentResponse): Boolean {
                return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MayViewHolder, position: Int) {
        val currentItem = getItem(position)

        var image:String?=currentItem?.urlToImage
        val date:String?=currentItem?.publishedAt?.substring(0, 10)
        val hours:String?=currentItem?.publishedAt?.substring(11, 19)
        if (image=="")
            image=null


        holder.binding.apply {
            titleTextView.text = currentItem?.title
            descriptionTextView.text = currentItem?.description
            Picasso.get().load(image).into(imageUrlImageView)
            dateTextView.text = "$date $hours"
            root.tag=currentItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MayViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ItemRvModelBinding.inflate(inflater,parent,false)
        binding.root.setOnClickListener(this)

        return MayViewHolder(binding)
    }
}