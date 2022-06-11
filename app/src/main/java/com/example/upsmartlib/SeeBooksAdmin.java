package com.example.upsmartlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SeeBooksAdmin extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView mFirestoreList;
    private  FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_books_admin);
        db=FirebaseFirestore.getInstance();
        mFirestoreList =findViewById(R.id.firestore_list);



        Query query =db.collection("books");

        FirestoreRecyclerOptions<Book> options= new FirestoreRecyclerOptions.Builder<Book>()
                .setQuery(query,Book.class)
                .build();
       adapter = new FirestoreRecyclerAdapter<Book, BookViewHolder>(options) {
            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view,parent,false);
                return new BookViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull Book model) {
            holder.title.setText(model.getTitle());
            holder.author.setText(model.getAuthor());
            }
        };

       mFirestoreList.setHasFixedSize(true);
       mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
       mFirestoreList.setAdapter(adapter);
    }

    private class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView title,author;
        private ImageView  image;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            author=itemView.findViewById(R.id.Author);
            image=itemView.findViewById(R.id.image);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}