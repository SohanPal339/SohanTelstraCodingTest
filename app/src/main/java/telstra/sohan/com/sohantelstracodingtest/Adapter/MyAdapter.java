package telstra.sohan.com.sohantelstracodingtest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import telstra.sohan.com.sohantelstracodingtest.Model.RowsDataDto;
import telstra.sohan.com.sohantelstracodingtest.R;
import telstra.sohan.com.sohantelstracodingtest.Utillity.PicasoClient;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<RowsDataDto> list = new ArrayList<>();
    public MyAdapter(Context context, List<RowsDataDto> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {

        if(list.get(position).getTitle()!=null){
            holder.mTextTitle.setText(list.get(position).getTitle().trim());
        }
        if(list.get(position).getDescription()!=null){
            holder.mTextDescription.setText(list.get(position).getDescription().trim());
        }
        if(list.get(position).getImageHref()!=null){
            PicasoClient.downloadImage(context,list.get(position).getImageHref(), holder.mImg);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextTitle,mTextDescription; ImageView mImg;
        public MyViewHolder(View view) {
            super(view);
            mTextTitle = (TextView) view.findViewById(R.id.textTitle);
            mTextDescription = (TextView) view.findViewById(R.id.textDescription);
            mImg = (ImageView) view.findViewById(R.id.img);
        }
    }
}
