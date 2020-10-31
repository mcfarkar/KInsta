package com.codepath.mcfarkar.kinsta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.parse.ParseFile;

import java.text.SimpleDateFormat;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by mcfarkar on 26,October,2020
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
// 3. Added PostsAdapter.Viewholder above

// 4 Create 2 parameters needed for constructor
    private Context context;
    private List<Post> posts;

// 5 create a constructor
    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 7 create layout inflator which will return a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 8 get post located at this position
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        // 6 follows from constructor
        return posts.size();
    }

    // 1 need to define a viewholder before updating function header to reference vh
    class ViewHolder extends  RecyclerView.ViewHolder {
        // 2 Add a constructor

        // 10 get references need for bind function
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvCreatedDate;
        private ImageView ivProfileImage;

        public ViewHolder(@NonNull View itemView) {


            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedDate = itemView.findViewById(R.id.tvCreatedDate);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);

        }

        public void bind(Post post) {
            // 9 Bind the post data into the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy hh:mm aaa");
            tvCreatedDate.setText(formatter.format(post.getCreatedAt()));

//            int radius = 30; // corner radius, higher value = more rounded
//            int margin = 10; // crop margin, set to 0 for corners with no crop

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

            ParseFile profileImage = post.getProfileImage();
            if (profileImage != null) {
                Glide.with(context).load(profileImage.getUrl()).transform(new CircleCrop()).into(ivProfileImage);
            }
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }



}
