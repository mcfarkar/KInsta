package com.codepath.mcfarkar.kinsta;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * Created by mcfarkar on 29,October,2020
 */
public class PostDiffCallback extends DiffUtil.Callback {

    private List<Post> oldList;
    private List<Post> newList;

    public PostDiffCallback(List<Post> oldList, List<Post> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // add a unique ID property on Post and expose a getId() method
        return oldList.get(oldItemPosition).getUser() == newList.get(newItemPosition).getUser();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Post oldPost = oldList.get(oldItemPosition);
        Post newPost = newList.get(newItemPosition);

//        if (oldPost.getDescription() == newPost.getDescription() && oldPost.getImage() == newPost.getImage()) {
        if (oldPost.getDescription().equals(newPost.getDescription())) {
            return true;
        }
        return false;
    }


}
