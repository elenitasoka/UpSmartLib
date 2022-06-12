package com.example.upsmartlib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserBorrowedBooks extends AppCompatActivity {
    private FirebaseFirestore db;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter1;
    private List<String > data;
    String userID;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_borrowed_books);
        db=FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        mFirestoreList =findViewById(R.id.firestore_listt);
        userID = fAuth.getCurrentUser().getUid();
        Query query =db.collection("users").document(userID).collection("LendedBooks");


        FirestoreRecyclerOptions<Book> options=new FirestoreRecyclerOptions.Builder<Book>()
                .setQuery(query,Book.class)
                .build();

         adapter1= new FirestoreRecyclerAdapter<Book, LendedBooksHolder>(options) {
            @NonNull
            @Override
            public LendedBooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view,parent,false);

                return new LendedBooksHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull LendedBooksHolder holder, int position, @NonNull Book model) {
                holder.title.setText(model.getTitle());
                holder.author.setText(model.getAuthor());
                //    String url = "https://firebasestorage.googleapis.com/v0/b/upsmartlib-32d0e.appspot.com/o/%CE%91%CE%BD%CE%BF%CF%81%CE%B3%CE%B1%CE%BD%CE%B7%20%CE%A7%CE%B7%CE%BC%CE%B5%CE%AF%CE%B1.PNG?alt=media&token=ca3dfdf0-bd72-48a1-8313-a7506f83761a";
              String url=model.getImage();
               Picasso.get()
                      .load(url)
                     .into(holder.image);

            }
        };
        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter1);


    }

    private class LendedBooksHolder extends  RecyclerView.ViewHolder{
        private TextView title,author;
        private ImageView image;
        public LendedBooksHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            author=itemView.findViewById(R.id.Author);
            image=itemView.findViewById(R.id.image);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }
}