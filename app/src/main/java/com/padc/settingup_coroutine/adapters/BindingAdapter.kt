package com.padc.settingup_coroutine.adapters

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.padc.settingup_coroutine.R
import com.padc.settingup_coroutine.data.vos.MarsProperty
import com.padc.settingup_coroutine.fragments.overivew.MarApiStatus

@BindingAdapter("listData")
fun bindRecylerView(recyclerView: RecyclerView, data: List<MarsProperty>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl : String) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions().placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
            )
            .into(imageView)
    }
}

    @BindingAdapter("marsApiStatus")
    fun bindStatus(statusImage:ImageView,status:MarApiStatus?){
        when(status){
            MarApiStatus.LOADING ->{
                statusImage.visibility = View.VISIBLE
                statusImage.setImageResource(R.mipmap.ic_launcher)
            }
            MarApiStatus.ERROR ->{
                statusImage.visibility = View.VISIBLE
                statusImage.setImageResource(R.mipmap.ic_launcher)

            }
            MarApiStatus.DONE ->{
                statusImage.visibility = View.GONE
            }
        }

    }
