package com.example.mfahad.twitter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by M.FAHAD on 8/16/2017.
 */

public class ShowTweets extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        ListView listView = (ListView) findViewById(R.id.androidlist);
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(getIntent().getStringExtra("screenName"))
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        listView.setAdapter(adapter);
    }
}
