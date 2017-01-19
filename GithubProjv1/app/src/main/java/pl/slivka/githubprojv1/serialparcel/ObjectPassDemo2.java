package pl.slivka.githubprojv1.serialparcel;

/**
 * Created by Olej on 2017-01-19.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class ObjectPassDemo2 extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView mTextView = new TextView(this);
        Book mBook = (Book)getIntent().getParcelableExtra(ObjectPassDemo.PAR_KEY);
        mTextView.setText("Book name is: " + mBook.getBookName()+"/n"+
                "Author is: " + mBook.getAuthor() + "/n" +
                "PublishTime is: " + mBook.getPublishTime());
        setContentView(mTextView);
    }
}