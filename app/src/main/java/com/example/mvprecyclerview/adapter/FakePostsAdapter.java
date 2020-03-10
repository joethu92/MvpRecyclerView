package com.example.mvprecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvprecyclerview.R;
import com.example.mvprecyclerview.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FakePostsAdapter extends RecyclerView.Adapter<FakePostsAdapter.ViewHolder> {

    private List<Post> fakeposts;

    public FakePostsAdapter(List<Post> fakeposts) {
        this.fakeposts = fakeposts;
    }


    public void setFakeposts(List<Post> fakeposts) {
        this.fakeposts = fakeposts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(fakeposts.get(position));
    }

    @Override
    public int getItemCount() {
        return fakeposts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userid)
        TextView userid;
        @BindView(R.id.titletxt)
        TextView titleTxt;
        @BindView(R.id.bodytxt)
        TextView bodytxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Post post) {
            if (post.getUserId() != 0) {
                userid.setText(String.valueOf(post.getUserId()));
            }
            if (post.getBody() != null && !post.getBody().equalsIgnoreCase("")) {
                bodytxt.setText(post.getBody());
            }
            if (post.getTitle() != null && !post.getTitle().equalsIgnoreCase("")) {
                titleTxt.setText(post.getTitle());
            }
        }
    }
}
