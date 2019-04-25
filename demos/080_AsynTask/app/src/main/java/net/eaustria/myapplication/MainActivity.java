package net.eaustria.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static TextView mTextView;
    private static ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.txt_msg);
    }

    public void startWithoutThread(View view) {
        try {
            Thread.sleep(5000);
            showToast("Thread Ready");
        } catch (InterruptedException e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void startSimpleThread(View view) {
        Thread thread = new Thread("new Thread") {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getLocalizedMessage());
                }
                TextView textView = findViewById(R.id.txt_msg);
                textView.setText("Thread Ready");
                Log.d(TAG, "Thread Ready");
            }
        };
        thread.start();
    }

    public void startAsyncTask(View view) {
        ServerTask task = new ServerTask();
        task.execute("AsyncTask Thread ready");
    }

    private class ServerTask extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            // here we could do some UI manipulation before the worker
            // thread starts
            mProgressBar = findViewById(R.id.progressBar);
            mProgressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // do some UI manipulation while progress is modified
            mProgressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            // workhorse methode
            for (int i=0 ; i<10 ; i++) {
                publishProgress(i*10);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Log.d(TAG, e.getLocalizedMessage());
                }
            }
            String msg = strings[0];
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            // called after doInBackground finishes
            mProgressBar.setVisibility(View.INVISIBLE);
            mTextView.setText(s);
            showToast(s);
            super.onPostExecute(s);
        }
    }
}
