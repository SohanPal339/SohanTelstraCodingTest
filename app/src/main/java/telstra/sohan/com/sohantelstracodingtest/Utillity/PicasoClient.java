package telstra.sohan.com.sohantelstracodingtest.Utillity;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import telstra.sohan.com.sohantelstracodingtest.R;


public class PicasoClient {

    public static void downloadImage(Context c, String url, ImageView img){
     if (url!=null && url.length()>0){
         Picasso.with(c).load(url).placeholder(R.drawable.ic_launcher_background).into(img);
     }else{
         Picasso.with(c).load(R.drawable.ic_launcher_background).into(img);
     }
    }
}
