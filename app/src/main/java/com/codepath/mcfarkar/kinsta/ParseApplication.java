package com.codepath.mcfarkar.kinsta;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by mcfarkar on 24,October,2020
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qoGHKHZlVgtFHdjOLhTtwKqeMsWRM0OdkS6oB5Wo")
                .clientKey("mWROIB9IqvGUQK04giDy0zYCZgYuOGYNg9KzptAp")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
