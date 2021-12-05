package com.src.roadsafety;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class mirosScreenViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "patientDetailsViewHolder";

    TextView Articletitle;
    TextView Article;
    TextView Date;
    ImageView Image;
    public mirosScreenViewHolder(View v) {
        super(v);
        Articletitle = (TextView) itemView.findViewById(R.id.ArticleTitle);
        Article = (TextView) itemView.findViewById(R.id.Article);
        Date = (TextView) itemView.findViewById(R.id.Date);
        Image = (ImageView) itemView.findViewById(R.id.ArticleImage);
    }

    public void bindMessage(MirosList miroslist) {
        if (miroslist.getArticle() != null) {
            Articletitle.setText(miroslist.getTitle());
            Article.setText(miroslist.getArticle());
            Date.setText(miroslist.getDate());

            Articletitle.setVisibility(TextView.VISIBLE);
            Article.setVisibility(TextView.VISIBLE);
            Date.setVisibility(TextView.VISIBLE);


        }

    }
}