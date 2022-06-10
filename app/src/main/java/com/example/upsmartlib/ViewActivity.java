/*
package com.example.upsmartlib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AllBooksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        String category=getIntent().getStringExtra("category");

        //Assigning the Recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.BooksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        //Firebase Recycler Options to get the data form firebase database using model class and reference
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AllBooks").child(category), Model.class)
                        .build();


        //Setting adapter to RecyclerView
        adapter = new AllBooksAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        //Starts listening for data from firebase when this fragment starts
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        //Stops listening for data from firebase
        adapter.stopListening();
    }

    }
}
*/