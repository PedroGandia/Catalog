package android.test.catalog.mainscreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.test.catalog.R;
import android.test.catalog.data.local.models.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class ListAppRecyclerAdapter extends RecyclerView.Adapter<ListAppRecyclerAdapter.ListAppViewHolder> implements View.OnClickListener , View.OnLongClickListener{

    private View.OnClickListener onClickListener;

    private View.OnLongClickListener onLongClickListener;

    private int heightItem;

    private List<Data> listAppItems;

    private Context context;

    @Override
    public boolean onLongClick(View view) {
        if(onLongClickListener != null){
            onLongClickListener.onLongClick(view);}

        return false;
    }

    @Override
    public void onClick(View view) {
        if(onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public static class ListAppViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameApp;
        ImageView ImgApp;
        RelativeLayout rlItem;


        ListAppViewHolder(View itemView) {
                super(itemView);
                txtNameApp = itemView.findViewById(R.id.txt_name_app);
                ImgApp = itemView.findViewById(R.id.img_app);
                rlItem = itemView.findViewById(R.id.rl_item);
        }

    }



     public ListAppRecyclerAdapter(List<Data> listAppItems, int heightItem, Context context){
         this.listAppItems = listAppItems;
         this.heightItem = heightItem;
         this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ListAppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_app, viewGroup, false);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        return new ListAppViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListAppViewHolder listAppViewHolder, final int position) {

            ViewGroup.LayoutParams params = listAppViewHolder.rlItem.getLayoutParams();
            params.height = heightItem;
            listAppViewHolder.rlItem.setLayoutParams(params);


            Data data = listAppItems.get(position);
            listAppViewHolder.txtNameApp.setText(data.getDisplayName()!=null?data.getDisplayName():"");
            Glide.with(context.getApplicationContext())
                .load(data.getIconImg())
                .error(R.mipmap.ic_launcher)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(listAppViewHolder.ImgApp);

    }




    @Override
    public int getItemCount() {
        if(listAppItems != null) {
            return listAppItems.size();
        }else{
            return 0;
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.onClickListener = listener;
    }

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        this.onLongClickListener = listener;
    }






}
