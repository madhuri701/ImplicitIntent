package com.example.android.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickOpenWebpage(View v)
    {
        String urlAsString = "http://www.google.com";
        openWebPage(urlAsString);
    }
    public void onClickOpenAddressButton(View v)
    {
        String addressString = "1688 AmphiTheatre Parkway, CA ";

        Uri.Builder builder = new Uri.Builder();

        builder.scheme("geo").path("8,8").appendQueryParameter("q",addressString);

        Uri addressUri = builder.build();

        showMap(addressUri);
    }
    public void onClickShareTextButton(View v)
    {

        //look at sharecomapct documentation
    }
    public void onClickCreateYourOwn(View v)
    {
        String textYouWantToShare = "it is me learning share text intent";

        //share that text to our method

        shareText(textYouWantToShare);
    }

    //to open webpage see documentation

    private void openWebPage(String url)
    {
        /*
        we wanted to demonstrate the Uri.parse because its usage occurs frequently.You
        could have just as easily passed in a Uri as the parameter of this method
         */
        Uri webPage = Uri.parse(url);


        Intent intent = new Intent(Intent.ACTION_VIEW,webPage);

        //if that partcular inent doesn't exist

        if(intent.resolveActivity(getPackageManager()) !=null)
        {
            startActivity(intent);
        }
    }

    private void showMap(Uri geoLocation)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(geoLocation);
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
    }

    private void shareText(String textToShare)
    {
        //read mime type document

        String mimeType = "text/plain";

        //this is just the title of the window that will pop up when we call startActivity
        String title = "Learning how to share";

        //shareCompat.IntentBuilder provides a fluent API for creating Intents
        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle(title).setText(textToShare).startChooser();
                //from method specifies the context from which this share is coming from

    }

}