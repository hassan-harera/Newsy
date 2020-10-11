package Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harera.parsewebsitedata.News;
import com.harera.parsewebsitedata.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.logging.LogRecord;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<News> list;



    public NewsRecyclerViewAdapter(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    public void update(News news){
        list.add(news);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        try {
            Picasso.get().load(list.get(position).getImgUrl()).fit().into(holder.news_img);
            holder.news_title.setText(list.get(position).getTitle());

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(list.get(position).getNewsUrl()));
                    context.startActivity(intent);
                }
            });


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView news_title;
        ImageView news_img;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_img = itemView.findViewById(R.id.news_img);
            news_title = itemView.findViewById(R.id.news_title);
            linearLayout = itemView.findViewById(R.id.ll);
        }
    }
}