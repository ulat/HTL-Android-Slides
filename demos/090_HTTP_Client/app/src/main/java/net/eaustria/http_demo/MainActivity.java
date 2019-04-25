package net.eaustria.http_demo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final Object URL = "https://api.github.com/users/";
    private EditText mUserName;
    private EditText mRepoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserName = findViewById(R.id.text_username);
        mRepoList = findViewById(R.id.text_repos);
    }

    public void showGitHubRepos(View view) {
        GitHubServerTask task = new GitHubServerTask();
        task.execute(mUserName.getText().toString());
    }

    private class GitHubServerTask extends AsyncTask<String, Integer, String> {

        private final String TAG = GitHubServerTask.class.getSimpleName();

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "entered doInBackground");
            String username = strings[0];
            Log.d(TAG, "url: "+URL+username+"/repos");
            String sJson = "";
            try {
                HttpURLConnection connection =
                        (HttpURLConnection) new URL(URL+username+"/repos").openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    sJson = readResponseStream(reader);
                }
            } catch (IOException e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return sJson;
        }

        private String readResponseStream(BufferedReader reader) throws IOException {
            Log.d(TAG, "entered readResponseStreaulat");
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ( (line=reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "entered onPostExecute");
            outputRepoNames(s, mRepoList);
            super.onPostExecute(s);
        }

        private void outputRepoNames(String s, EditText mRepoList) {
            Log.d(TAG, "entered outputRepoNames");
            Log.d(TAG, s);
            StringBuilder stringBuilder = new StringBuilder();
            try {
                JSONArray array = new JSONArray(s);
                for (int i=0 ; i < array.length() ; i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    stringBuilder.append(jsonObject.optString("full_name"));
                    stringBuilder.append(("\n\n"));
                }
            } catch (JSONException e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            mRepoList.setText(stringBuilder.toString());
        }
    }
}
