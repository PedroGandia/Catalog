package android.test.catalog.mainscreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.test.catalog.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pedrog295@gmail.com on 21/10/2017.
 */

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> implements View.OnClickListener , View.OnLongClickListener{

    private View.OnClickListener onClickListener;

    private View.OnLongClickListener onLongClickListener;

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

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView txtItemNameCategory, txtItemNumber;

        CategoryViewHolder(View itemView) {
                super(itemView);
                txtItemNameCategory = itemView.findViewById(R.id.txt_item_name_category);
                txtItemNumber = itemView.findViewById(R.id.txt_item_number);
        }

    }

    private List<String> categoryItems;

     public CategoryRecyclerAdapter(List<String> categoryItems, Context context){
         this.categoryItems = categoryItems;
         this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder categoryViewHolder, final int position) {
        String category = categoryItems.get(position);
        if (category == null || category.isEmpty()){
            category = context.getString(R.string.title_other);
        }

        String categoryName = category.substring(0, 1).toUpperCase() + category.substring(1);
        categoryViewHolder.txtItemNameCategory.setText(categoryName);
        String number = String.valueOf((position + 1));
        categoryViewHolder.txtItemNumber.setText(number);
    }




    @Override
    public int getItemCount() {
        if(categoryItems != null) {
            return categoryItems.size();
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
