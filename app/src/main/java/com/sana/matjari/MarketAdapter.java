package com.sana.matjari;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;

/**
 * Created by asus on 12/03/2018.
 */

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.ViewHolder> {

    private Context mContext;
    private List<Vendeur>  vendeursList;

    public MarketAdapter(Context mContext, List<Vendeur> vendeursList) {
        this.mContext = mContext;
        this.vendeursList = vendeursList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.market_card, parent, false);
        return new ViewHolder(itemView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // Views declarations.
        public List<TextView> tagList=new ArrayList<>();
        public TextView userImage, marketName, region, wilaya, marketType, tag0, tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9;
        public ImageView star1, star2, star3, star4,star5;
        public List<ImageView> starList=new ArrayList<>();
        public CardView cardContiner;

        @SuppressLint("CutPasteId")
        public ViewHolder(View itemView) {
            super(itemView);


            /*** ID flow control 'post_card' **/

            // Views id combinations.
            userImage = (TextView) itemView.findViewById(R.id.image_name);
            marketName = (TextView) itemView.findViewById(R.id.market_name);
            region = (TextView) itemView.findViewById(R.id.region);
            wilaya = (TextView) itemView.findViewById(R.id.wilaya);
            marketType = (TextView) itemView.findViewById(R.id.type_market);
            cardContiner = (CardView) itemView.findViewById(R.id.card_container);

            // Stars.
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
            star4 = itemView.findViewById(R.id.star4);
            star5 = itemView.findViewById(R.id.star5);

            starList.add(star1);
            starList.add(star2);
            starList.add(star3);
            starList.add(star4);
            starList.add(star5);

            // Tags.
            tag0 = (TextView) itemView.findViewById(R.id.tag0);
            tag1 = (TextView) itemView.findViewById(R.id.tag1);
            tag2 = (TextView) itemView.findViewById(R.id.tag2);
            tag3 = (TextView) itemView.findViewById(R.id.tag3);
            tag4 = (TextView) itemView.findViewById(R.id.tag4);
            tag5 = (TextView) itemView.findViewById(R.id.tag5);
            tag6 = (TextView) itemView.findViewById(R.id.tag6);
            tag7 = (TextView) itemView.findViewById(R.id.tag7);
            tag8 = (TextView) itemView.findViewById(R.id.tag8);
            tag9 = (TextView) itemView.findViewById(R.id.tag9);

            tagList.add(tag0);
            tagList.add(tag1);
            tagList.add(tag2);
            tagList.add(tag3);
            tagList.add(tag4);
            tagList.add(tag5);
            tagList.add(tag6);
            tagList.add(tag7);
            tagList.add(tag8);
            tagList.add(tag9);

            cardContiner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, SubActivity.class));
                }
            });

            /****/
        }
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("ASSERT", "onCreateViewHolder");

        // Here is all the post controls
        Animation shake = AnimationUtils.loadAnimation(this.mContext, R.anim.shake);
        Vendeur market= vendeursList.get(position);
        holder.marketName.setText(market.getMarketName());
        holder.marketName.setAnimation(shake);
        holder.userImage.setText((String)(market.getMarketName().charAt(0) + "" + market.getMarketName().charAt(1)).toUpperCase());
        holder.userImage.setAnimation(shake);
        holder.marketType.setText(market.getMarketType());
        holder.region.setText(market.getRegion());
        holder.wilaya.setText(market.getWilaya());
        for (int i=0; i<5; i++) {
            if(i < market.getEvaluation()) {
                holder.starList.get(i).setImageResource(R.drawable.ic_star_24dp);
            } else {
                holder.starList.get(i).setImageResource(R.drawable.ic_star_border_24dp);
            }
        }
        if (market.getEvaluation() - (int)market.getEvaluation() != 0) {
            holder.starList.get((int)market.getEvaluation()).setImageResource(R.drawable.ic_star_half_24dp);
        }
        int i = 0;
        for (MarketTag tag:
                market.getMarketTags()) {
            holder.tagList.get(i).setText(tag.getTag());
            holder.tagList.get(i).setVisibility(View.VISIBLE);
            holder.tagList.get(i).startAnimation(shake);
            i++;
        }

    }

    @Override
    public int getItemCount() {
        return vendeursList.size();
    }

}
