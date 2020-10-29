package com.codepath.mcfarkar.kinsta.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.mcfarkar.kinsta.Post;
import com.codepath.mcfarkar.kinsta.PostsAdapter;
import com.codepath.mcfarkar.kinsta.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(),allPosts );


//        How to Use the Recycler View
//        0 Create layout for one row in the list


//        1 Create the adapter:
//              a. PostsAdapter class
//              b. line above

//        2 Create the data source: already done previously

//        3 Set the adapter on the Recycler View
        rvPosts.setAdapter(adapter);

//        4 Set the Layout Manager on the Recycler View
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

    }

    protected void queryPosts() {

        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {

                if (e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }

                // if query succeeded, iterate through all of the posts
                for (Post post : posts){
                   Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                    Log.i(TAG, "Post: " + post.getDescription() );
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();

            }
        });
    }

}