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
import telstra.sohan.com.sohantelstracodingtest.utility.GlideDownloadImage
import java.util.*


class UserContentFactsAdapter(context: Context, listFactsDataDto: List<FactsDataDto>) : RecyclerView.Adapter<UserContentFactsAdapter.MyViewHolder>() {
    private var listFactsDataDto = ArrayList<FactsDataDto>()
    private var context: Context? = null

    init {
        this.listFactsDataDto = listFactsDataDto as ArrayList<FactsDataDto>
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        listFactsDataDto[position].title?.let {
            holder.mTextTitle.text = listFactsDataDto[position].title

        } ?: let {
            holder.mTextTitle.text = "N/A"

        }
        listFactsDataDto[position].description?.let {
            holder.mTextDescription.text = listFactsDataDto[position].description
        } ?: let {
            holder.mTextDescription.text = "N/A"
        }

        context?.let { GlideDownloadImage.downloadImage(it, listFactsDataDto[position].imageHref, holder.mImg) }


    }

    override fun getItemCount(): Int {
        return listFactsDataDto.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTextTitle: TextView = view.findViewById<View>(R.id.textTitle) as TextView
        var mTextDescription: TextView = view.findViewById<View>(R.id.textDescription) as TextView
        var mImg: ImageView = view.findViewById<View>(R.id.img) as ImageView

    }
}
