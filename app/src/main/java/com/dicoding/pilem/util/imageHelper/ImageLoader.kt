package com.dicoding.pilem.util.imageHelper

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.dicoding.pilem.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun imageLoader(imagePath : String, context: Context): RequestBuilder<Drawable>
    = withContext(Dispatchers.IO){
        return@withContext Glide.with(context)
            .load(imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
}