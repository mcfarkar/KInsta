package com.codepath.mcfarkar.kinsta;

import android.media.Image;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

/**
 * Created by mcfarkar on 24,October,2020
 */
@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_PROFILE_IMAGE = "profileImage";
    public ParseUser user;

    // define getters and setters


    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }


    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public ParseFile getProfileImage() {

        user = getUser();
        return user.getParseFile(KEY_PROFILE_IMAGE);
    }

//    public void setProfileImage(ParseFile profileImage) {
//        user = getUser();
//        user.put(KEY_PROFILE_IMAGE, profileImage);
//    }







}
