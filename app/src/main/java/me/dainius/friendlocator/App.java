package me.dainius.friendlocator;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * App - Called before any Activity
 */
public class App extends Application {

    private static Context context;
    private static String ACTIVITY = "App";

    /**
     * Initializer
     */
    public App() {
        context = this;
    }

    /**
     * onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(ACTIVITY, "onCreate()");

        ParseObject.registerSubclass(FriendInvitation.class);
        ParseObject.registerSubclass(Friends.class);
        ParseObject.registerSubclass(ActiveConnection.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("YwmvPX6KH9WAelqYFGUZFnIGeSduAnRtgzPzw1MY")
                .clientKey("m45RpaZT5hmqHv40durnG8TREVWbT6MW166ygKZW")
                .server("https://parseapi.back4app.com/") // The trailing slash is important.


        .build()
        );


        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d(ACTIVITY, "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e(ACTIVITY, "failed to subscribe for push: " + e.getLocalizedMessage());
                }
            }
        });

    }

    /**
     * getAppContext()
     * @return Context
     */
    public static Context getAppContext() {
        return App.context;
    }
}