package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.Models.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {
    RecyclerView recyclerView;
    CustomAdapter adapter;


    private int currentAdapterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);

        //Possible saved instances. Lets check them
        if(savedInstanceState != null) {
            // Assigning the adapter position from the bundle
           currentAdapterPosition = savedInstanceState.getInt("CurrentAdapterPos", 0);
        }
        // Logging out the adapter position, good for debugging.
        Log.d("onCreateAdapterPos", String.valueOf(currentAdapterPosition));
    }


    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            // Calling showNews();
            showNews(list);
            // If there were possible bundle data for the last adapter position,
            // this is where we would put the screen position back where it was before.
            recyclerView.scrollToPosition(currentAdapterPosition);

        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                .putExtra("data", headlines));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Getting the current adapter position for bundle
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        assert layoutManager != null;
        currentAdapterPosition = layoutManager.findFirstVisibleItemPosition();
        // Assigning the position for the bundle.
        outState.putInt("CurrentAdapterPos", currentAdapterPosition);

    }
}
