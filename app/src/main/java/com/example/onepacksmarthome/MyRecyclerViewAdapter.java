package com.example.onepacksmarthome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mData;
    private LayoutInflater mInflater;
    private static ClickListener mClickListener;
    static boolean bool = true;
    Context context;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, ArrayList<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context=context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row, parent, false);


        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // String animal = mData.get(position);
        // holder.myTextView.setText(animal);
        final ImageView b = holder.imageView;
        final Data[] o1 = new Data[8];
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mData.get(position).equals("1")) {

                    mData.remove(position);
                    mData.add(position, "0");
                    notifyDataSetChanged();
                } else {

                    mData.remove(position);
                    mData.add(position, "1");
                    notifyDataSetChanged();
                }

                for (int i = 0; i < mData.size(); i++) {
                    Data os1 = new Data();

                    os1.setId("stat_" + (i + 1));
                    os1.setState(mData.get(i));
                    o1[i] = os1;
                }

                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i <mData.size() ; i++) {
                    try {
                        jsonObject.put(o1[i].getId(),o1[i].getState());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                updateProductAtPID(jsonObject.toString());









            }
        });


        if (mData.get(position).equals("1")) {


            b.setImageResource(R.drawable.open);

        } else {
            holder.imageView.setImageResource(R.drawable.close);

        }

    }
    private void updateProductAtPID(final String pid) {
        bool =false;
        String updateProductUrl = Server.URL_UPDATE;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateProductUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                        bool=true;

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("pid",pid);

                return params;
            }
        };
        ;
        Volley.newRequestQueue(context).add(stringRequest);


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageView imageView;

        ViewHolder(final View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.im);

        }

    }
    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setOnItemClickListener(ClickListener clickListener) {
        MyRecyclerViewAdapter.mClickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}