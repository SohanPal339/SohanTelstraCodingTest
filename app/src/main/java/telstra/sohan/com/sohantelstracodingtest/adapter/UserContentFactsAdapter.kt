package telstra.sohan.com.sohantelstracodingtest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import telstra.sohan.com.sohantelstracodingtest.R
import telstra.sohan.com.sohantelstracodingtest.model.FactsDataDto
import telstra.sohan.com.sohantelstracodingtest.utility.PicasoClient
import java.util.*


class UserContentFactsAdapter(private val context: Context, listFactsDataDto: List<FactsDataDto>) : RecyclerView.Adapter<UserContentFactsAdapter.MyViewHolder>() {
    private var listFactsDataDto = ArrayList<FactsDataDto>()

    init {
        this.listFactsDataDto = listFactsDataDto as ArrayList<FactsDataDto>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserContentFactsAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserContentFactsAdapter.MyViewHolder, position: Int) {

        if (listFactsDataDto[position].title != null) {
            holder.mTextTitle.text = listFactsDataDto[position].title
        } else {
            holder.mTextTitle.text = "N/A"
        }
        if (listFactsDataDto[position].description != null) {
            holder.mTextDescription.text = listFactsDataDto[position].description
        } else {
            holder.mTextDescription.text = "N/A"
        }
        PicasoClient.downloadImage(context, listFactsDataDto[position].imageHref, holder.mImg)


    }

    override fun getItemCount(): Int {
        return listFactsDataDto.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var mTextTitle: TextView
        internal var mTextDescription: TextView
        internal var mImg: ImageView

        init {
            mTextTitle = view.findViewById<View>(R.id.textTitle) as TextView
            mTextDescription = view.findViewById<View>(R.id.textDescription) as TextView
            mImg = view.findViewById<View>(R.id.img) as ImageView
        }
    }
}
