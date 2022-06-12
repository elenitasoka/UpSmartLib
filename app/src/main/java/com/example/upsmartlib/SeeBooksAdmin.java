/*package com.example.upsmartlib;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeeBooksAdmin extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView mFirestoreList;
    private  FirestoreRecyclerAdapter adapter;
    private List<String >data;


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
       adapter = new FirestoreRecyclerAdapter<Book, BookViewHolder>(options)  {
            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view,parent,false);
                return new BookViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Book model) {
            holder.title.setText(model.getTitle());
            holder.author.setText(model.getAuthor());
            //    String url = "https://firebasestorage.googleapis.com/v0/b/upsmartlib-32d0e.appspot.com/o/%CE%91%CE%BD%CE%BF%CF%81%CE%B3%CE%B1%CE%BD%CE%B7%20%CE%A7%CE%B7%CE%BC%CE%B5%CE%AF%CE%B1.PNG?alt=media&token=ca3dfdf0-bd72-48a1-8313-a7506f83761a";
                    String url=model.getImage();
                Picasso.get()
                        .load(url)
                        .into(holder.image);
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SeeBooksAdmin.this, LendBook.class);

                        Book book=new Book(model.getTitle(), model.getAuthor(), model.getImage(), model.getPages(), model.getAvailableCopys(),model.getBookId());

                       intent.putExtra("book",book);
                        overridePendingTransition(0,0);
                        startActivity(intent);
                    }
                });



            }
        };

       mFirestoreList.setHasFixedSize(true);
       mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
       mFirestoreList.setAdapter(adapter);
    }

   class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView title,author;
        private ImageView  image;
        Book book;

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


*/
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeeBooksAdmin extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView mFirestoreList;
    private  FirestoreRecyclerAdapter adapter;
    private List<String >data;


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
        adapter = new FirestoreRecyclerAdapter<Book, BookViewHolder>(options)  {
            @NonNull
            @Override
            public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view,parent,false);
                return new BookViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Book model) {
                holder.title.setText(model.getTitle());
                holder.author.setText(model.getAuthor());
                //    String url = "https://firebasestorage.googleapis.com/v0/b/upsmartlib-32d0e.appspot.com/o/%CE%91%CE%BD%CE%BF%CF%81%CE%B3%CE%B1%CE%BD%CE%B7%20%CE%A7%CE%B7%CE%BC%CE%B5%CE%AF%CE%B1.PNG?alt=media&token=ca3dfdf0-bd72-48a1-8313-a7506f83761a";
                String url=model.getImage();
           Picasso.get()
                    .load(model.getImage())
                    .into(holder.image);
                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SeeBooksAdmin.this, LendBook.class);

                        Book book=new Book(model.getTitle(), model.getAuthor(), model.getImage(), model.getPages(), model.getAvailableCopys(),model.getBookId());

                        intent.putExtra("book",book);
                        overridePendingTransition(0,0);
                        startActivity(intent);
                    }
                });



            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView title,author;
        private ImageView  image;
        Book book;

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