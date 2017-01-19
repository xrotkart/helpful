package pl.slivka.githubprojv1.serialparcel;

/**
 * Created by Olej on 2017-01-19.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import pl.slivka.githubprojv1.R;

public class ObjectPassDemo extends Activity implements OnClickListener {

    private Button sButton,pButton;
    public  final static String SER_KEY = "com.easyinfogeek.objectPass.ser";
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_parcel);
        setupViews();

    }

    public void setupViews(){
        sButton = (Button)findViewById(R.id.button1);
        pButton = (Button)findViewById(R.id.button2);
        sButton.setOnClickListener(this);
        pButton.setOnClickListener(this);
    }

    public void SerializeMethod(){
        Person mPerson = new Person();
        mPerson.setName("Leon");
        mPerson.setAge(25);
        Intent mIntent = new Intent(this,ObjectPassDemo1.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(SER_KEY,mPerson);
        mIntent.putExtras(mBundle);

        startActivity(mIntent);
    }

    public void PacelableMethod(){
        Book mBook = new Book();
        mBook.setBookName("Android Developer Guide");
        mBook.setAuthor("Leon");
        mBook.setPublishTime(2014);
        Intent mIntent = new Intent(this,ObjectPassDemo2.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(PAR_KEY, mBook);
        mIntent.putExtras(mBundle);

        startActivity(mIntent);
    }

    public void onClick(View v) {
        if(v == sButton){
            SerializeMethod();
        }else{
            PacelableMethod();
        }
    }
}
