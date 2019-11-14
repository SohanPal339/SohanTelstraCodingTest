package telstra.sohan.com.sohantelstracodingtest.utility

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import telstra.sohan.com.sohantelstracodingtest.R


object GlideDownloadImage {

    fun downloadImage(c: Context, url: String?, img: ImageView) {

        Glide.with(c)
                .load(url) // Image URL
                .centerCrop()
                .crossFade()
                .override(100, 60)
                .placeholder(R.drawable.place_holder) // Place holder image
                .error(R.drawable.place_holder) // On error image
                .into(img)

    }

}
