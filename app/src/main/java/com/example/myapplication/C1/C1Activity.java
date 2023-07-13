package com.example.myapplication.C1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.myapplication.Album;
import com.example.myapplication.AlbumAdapter;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class C1Activity extends AppCompatActivity {
    private final String URL_ALBUM = "https://jsonplaceholder.typicode.com/photos";
    private RecyclerView rcyAlbum;
    URL url;
    HttpsURLConnection connection;
    StringBuffer buffer;
    List<Album> list;
    AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        rcyAlbum = (RecyclerView) findViewById(R.id.rcy_album);
        rcyAlbum.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        AsyncTask<String, Integer, List<Album>> asyncTask = new AsyncTask<String, Integer, List<Album>>() {
            @Override
            protected List<Album> doInBackground(String... strings) {
                try {
                    String line;
                    list = new ArrayList<>();
                    url = new URL(strings[0]);
                    connection = (HttpsURLConnection) url.openConnection();

                    int statusRes = connection.getResponseCode();
                    if (statusRes == connection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        buffer = new StringBuffer();

                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }

                        JSONArray jsonArray = new JSONArray(buffer.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            Album album = new Album();
                            album.setUrl(jsonObject.getString("url"));
                            album.setAlbumId(jsonObject.getInt("albumId"));
                            album.setId(jsonObject.getInt("id"));
                            album.setTitle(jsonObject.getString("title"));
//                            album.setUrl(jsonObject.getString("url"));
                            album.setThumbnailUrl(jsonObject.getString("thumbnailUrl"));
                            list.add(album);
                        }
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return list;
            }

            @Override
            protected void onPostExecute(List<Album> unused) {
                super.onPostExecute(unused);

                adapter = new AlbumAdapter(unused);
                rcyAlbum.setAdapter(adapter);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        };

        asyncTask.execute(URL_ALBUM);
    }
}