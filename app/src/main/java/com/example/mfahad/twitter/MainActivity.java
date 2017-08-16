package com.example.mfahad.twitter;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {
    TwitterLoginButton twitterLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitterLoginButton);
        twitterLoginButton.setCallback(new Callback<TwitterSession>(){
           @Override
            public void success(Result<TwitterSession> result){
                TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
               TwitterAuthToken twitterAuthToken = twitterSession.getAuthToken();
               long userId= twitterSession.getUserId();
               final String username = twitterSession.getUserName();

               Dialog dialog = new Dialog(MainActivity.this);
               dialog.setTitle("Twitter Information");
               dialog.setContentView(R.layout.twitter_basic_info);
               TextView id = (TextView) dialog.findViewById(R.id.id);
               TextView name = (TextView) dialog.findViewById(R.id.name);
               id.setText("User ID : " + Long.toString(userId));
               name.setText("UserName : "+username);
               Button next = (Button) dialog.findViewById(R.id.button);
               next.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent i = new Intent(MainActivity.this ,ShowTweets.class );
                       i.putExtra("screenName" , username);
                       startActivity(i);
                   }
               });
               dialog.show();
           }
           @Override
           public void failure(TwitterException exception){
               Toast.makeText(MainActivity.this, "In Failure", Toast.LENGTH_SHORT).show();
           }
        });
    }
    @Override
    protected void onActivityResult(int requestCode , int resultCode ,Intent data){
        super.onActivityResult(requestCode , resultCode , data);
        twitterLoginButton.onActivityResult(requestCode , resultCode , data);
    }
}
