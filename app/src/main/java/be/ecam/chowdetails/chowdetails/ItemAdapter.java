package be.ecam.chowdetails.chowdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder> {

    private ArrayList<Food> mData = null;
    private ItemAdapterOnClickHandler clickHandler;

    public ItemAdapter(ItemAdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public interface ItemAdapterOnClickHandler {
        void onClick(int index);
    }

    @Override
    public ItemAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.food_item,
                viewGroup, shouldAttachToParentImmediately);
        return new ItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapterViewHolder itemAdapterViewHolder, int position) {
        Food dataForThisItem = mData.get(position);
        itemAdapterViewHolder.mTextViewName.setText("Name: " + dataForThisItem.getName());
        itemAdapterViewHolder.mTextViewBrand.setText("Brand: "+ dataForThisItem.getBrand());
    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.size();
    }

    public void setData(ArrayList<Food> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public ArrayList<Food> getData() {
        return mData;
    }


    public class ItemAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView mTextViewName;
        public final TextView mTextViewBrand;

        public ItemAdapterViewHolder(View view) {
            super(view);
            mTextViewName = (TextView) view.findViewById(R.id.name);
            mTextViewBrand = (TextView) view.findViewById(R.id.brand);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(adapterPosition);
        }

    }
}