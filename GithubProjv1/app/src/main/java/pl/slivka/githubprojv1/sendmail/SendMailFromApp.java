package pl.slivka.githubprojv1.sendmail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pl.slivka.githubprojv1.R;

/**
 * Created by Tomek on 2016-10-23.
 */
public class SendMailFromApp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_mail_activity);

        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });

        final Button send = (Button) this.findViewById(R.id.btn_sendGmail);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    GmailSender sender = new GmailSender("username@gmail.com", "password");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "user@gmail.com",
                            "user@yahoo.com");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }
        });
    }
    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        //Intent emailIntent = new Intent(Intent.ACTION_SEND);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String content = "<a href='intent:#Intent;action=com.commonsware.android.MY_ACTION;end'> Google </a>";
        //Spanned spannedContent = (Spanned)content;
        //Html htmlContent = Html.toHtml(content);

        emailIntent.setData(Uri.parse("mailto:"));
        //emailIntent.setType("text/plain");
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        //emailIntent.putExtra(Intent.EXTRA_TEXT, "<a href=\"intent:#Intent;action=com.commonsware.android.MY_ACTION;end\">Link back to URLHandler via intent: URL</a>");
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(content));

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SendMailFromApp.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

