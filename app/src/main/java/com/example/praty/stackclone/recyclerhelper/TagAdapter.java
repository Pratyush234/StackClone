package com.example.praty.stackclone.recyclerhelper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.praty.stackclone.R;
import com.example.praty.stackclone.model.Tags;

import java.util.List;

//Adapter for Tags and recyclerview
public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

    private List<Tags> mTags;
    private ItemClickListener mClickListener;

    public TagAdapter(List<Tags> mTags, ItemClickListener mClickListener) {
        this.mTags = mTags;
        this.mClickListener = mClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tagrow, parent, false);
        final ViewHolder holder=new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tagName=mTags.get(position).getName();
        String capitalTag=tagName.toUpperCase().charAt(0)+ tagName.substring(1);
        holder.tag.setText(capitalTag);

    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tag;

        public ViewHolder(View itemView) {
            super(itemView);

            tag=itemView.findViewById(R.id.my_tag);
        }
    }
}
