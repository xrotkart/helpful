package pl.slivka.githubprojv1.serialparcel;

/**
 * Created by Olej on 2017-01-19.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class ObjectPassDemo1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView mTextView = new TextView(this);
        Person mPerson = (Person)getIntent().getSerializableExtra(ObjectPassDemo.SER_KEY);
        mTextView.setText("You name is: " + mPerson.getName() + "/n"+
                "You age is: " + mPerson.getAge());

        setContentView(mTextView);
    }
}