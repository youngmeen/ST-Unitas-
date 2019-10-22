package com.example.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example.R;

import java.util.ArrayList;

public class RecyClerViewAdapter extends RecyclerView.Adapter<RecyClerViewAdapter.ViewHolder> {

    private ArrayList<SearchVO> data; // 모델화된 데이터들을 리스트로 받아옴
    private Context context;

    public RecyClerViewAdapter(Context context, ArrayList<SearchVO> data) { // 생성자
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyClerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Adapter 내부에 정의된 ViewHolder에 정의된 레이아웃을 inflate해서 반환
        return new RecyClerViewAdapter.ViewHolder(inflater.inflate(R.layout.list_view, parent, false));
    }

    @Override
    public int getItemCount() { // 아이템의 개수 반환
        return data.size() ;
    }

    @Override
    public void onBindViewHolder(RecyClerViewAdapter.ViewHolder holder, int position) {
        // ViewHolder에 정의된 텍스트뷰에 데이터의 텍스트를 출력
        /*holder.rv_title.setText(data.get(position).getCollection());*/
        // ViewHolder에 정의된 이미지뷰에 데이터의 이미지 경로의 이미지 출력
        Glide.with(context).load(data.get(position).getImage_url()).into(holder.rv_image);

    }

    // ViewHolder 클래스 정의를 통해 Adapter에서 사용할 뷰들을 연결
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rv_title;
        ImageView rv_image;
        ViewHolder(View itemView) {
            super(itemView);
/*
            rv_title = itemView.findViewById(R.id.rv_title);
*/
            rv_image = itemView.findViewById(R.id.rv_image);
        }
    }
}
