package com.example.kyachallahai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// 4a280822e4ca4ec692ca888d1b29a232 - Api Key

public class MainActivity extends AppCompatActivity implements CategoryRVAdaptor.CategoryClickInterface {
    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    ArrayList<Articles> articlesArrayList;
    ArrayList<CategoryRVModal> categoryRVModalsArrayList;
    CategoryRVAdaptor categoryRVAdaptor;
    NewsRVAdaptor newsRVAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV=findViewById(R.id.RvNews);
        categoryRV=findViewById(R.id.RvCategories);
        loadingPB=findViewById(R.id.PdLoading);
        articlesArrayList=new ArrayList<>();
        categoryRVModalsArrayList=new ArrayList<>();
        newsRVAdaptor=new NewsRVAdaptor(articlesArrayList,this);
        categoryRVAdaptor=new CategoryRVAdaptor(categoryRVModalsArrayList,this, this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        newsRV.setLayoutManager(linearLayoutManager);
        newsRV.setAdapter(newsRVAdaptor);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categoryRV.setLayoutManager(linearLayoutManager1);
        categoryRV.setAdapter(categoryRVAdaptor);
        getCategory();
        getNews("All");
        newsRVAdaptor.notifyDataSetChanged();

    }
    private void getCategory(){
        categoryRVModalsArrayList.add(new CategoryRVModal("All","https://images.unsplash.com/photo-1546422904-90eab23c3d7e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8bmV3c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Science","https://images.unsplash.com/photo-1614935151651-0bea6508db6b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fHNjaWVuY2V8ZW58MHx8MHx8&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Entertainment","https://images.unsplash.com/photo-1567593810070-7a3d471af022?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8RW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Business","https://images.unsplash.com/photo-1665686306574-1ace09918530?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGJ1c2luZXNzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1532938911079-1b06ac7ceec7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhlYWx0aHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Sports","https://images.unsplash.com/photo-1530549387789-4c1017266635?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHNwb3J0c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Education","https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8ZWR1Y2F0aW9ufGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalsArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1503428593586-e225b39bddfe?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Z2VuZXJhbCUyMG5ld3N8ZW58MHx8MHx8&auto=format&fit=crop&w=600&q=60"));
        categoryRVAdaptor.notifyDataSetChanged();
    }
    private  void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryUrl="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=4a280822e4ca4ec692ca888d1b29a232 ";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=4a280822e4ca4ec692ca888d1b29a232 ";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFitAPI retroFitAPI=retrofit.create(RetroFitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call=retroFitAPI.getAllNews(url);
        }
        else{
            call=retroFitAPI.getNewsByCategory(categoryUrl);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal=response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles=newsModal.getArticles();
                for(int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }
                newsRVAdaptor.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed in fetching the news ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category=categoryRVModalsArrayList.get(position).getCategory();
        getNews(category);
    }
}