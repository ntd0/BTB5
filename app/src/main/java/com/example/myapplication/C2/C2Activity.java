package com.example.myapplication.C2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.Album;
import com.example.myapplication.AlbumAdapter;
import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class C2Activity extends AppCompatActivity {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private RecyclerView rcyAlbum;
    private AlbumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c2);

        rcyAlbum = findViewById(R.id.rcy_album);
        rcyAlbum.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        // Tạo đối tượng Retrofit từ RetrofitClient
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL);

        // Tạo đối tượng ApiService từ Retrofit
        ApiService apiService = retrofit.create(ApiService.class);

        // Gửi yêu cầu mạng
        Call<List<Album>> call = apiService.getAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful()) {
                    List<Album> albums = response.body();
                    adapter = new AlbumAdapter(albums);
                    rcyAlbum.setAdapter(adapter);
                } else {
                    // Xử lý khi có lỗi trong phản hồi từ máy chủ
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                // Xử lý khi có lỗi kết nối hoặc yêu cầu thất bại
            }
        });
    }
}