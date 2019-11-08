package telstra.sohan.com.sohantelstracodingtest.utility

import android.content.Context
import android.widget.ImageView

import com.squareup.picasso.Picasso

import telstra.sohan.com.sohantelstracodingtest.R


object PicassoClient {

    fun downloadImage(c: Context, url: String?, img: ImageView) {
        url?. let {
            Picasso.with(c).load(url).placeholder(R.drawable.ic_launcher_background).into(img)
        } ?: let {
            Picasso.with(c).load(R.drawable.ic_launcher_background).into(img)
        }
    }
}
