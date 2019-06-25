package com.example.myshoppingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myshoppingapp.R;
import com.example.myshoppingapp.adapters.TestAdapter;
import com.example.myshoppingapp.models.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    TestAdapter testAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Image> arrayList = new ArrayList<>();

//    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        fetchData();
        Log.i("Mytag","list: " + arrayList) ;
        init();
    }

    private void fetchData() {
        String url = "https://jsonplaceholder.typicode.com/photos";

       JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                            Log.i("hi",response.toString());
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = (JSONObject) response.get(i);
                                    Image image = new Image();
                                    image.setImage(jsonObject.getString("url"));
                                    image.setTitle(jsonObject.getString("title"));
                                    arrayList.add(image);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                }
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setAdapter(testAdapter);
                            //adapterTest.setData(list);
                   }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("hi", "error");
                        Log.i("hi", "error: "+ error.getMessage());
                        Toast.makeText(TestActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(request);

    }


    private void init() {
        progressBar=findViewById(R.id.test_activity_progress_bar);
        recyclerView=findViewById(R.id.test_activity_recycler_view);
        layoutManager=new GridLayoutManager(this,2);
        testAdapter=new TestAdapter(this,arrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(testAdapter);
    }
}
