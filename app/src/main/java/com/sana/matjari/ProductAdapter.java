package com.sana.matjari;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mContext;
    private List<Product>  productsList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Views declarations.
        public TextView productImage, productName, productCoast, marketName, region, wilaya, marketType;

        @SuppressLint("CutPasteId")
        public ViewHolder(View itemView) {
            super(itemView);

            /*** ID flow control 'post_card' **/

            // Views id combinations.
            productImage = (TextView) itemView.findViewById(R.id.image_name);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productCoast = (TextView) itemView.findViewById(R.id.product_coast);
            marketName = (TextView) itemView.findViewById(R.id.market_name);
            region = (TextView) itemView.findViewById(R.id.region);
            wilaya = (TextView) itemView.findViewById(R.id.wilaya);
            marketType = (TextView) itemView.findViewById(R.id.type_market);

            /****/
        }
    }

    public ProductAdapter(Context mContext, List<Product> productsList) {
        this.mContext = mContext;
        this.productsList = productsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("ASSERT", "onCreateViewHolder");

        // Here is all the post controls
        Animation shake = AnimationUtils.loadAnimation(this.mContext, R.anim.shake);
        Product product= productsList.get(position);
        Vendeur market=product.getVendeur();
        holder.marketName.setText(market.getMarketName()+ "");
        holder.marketName.setAnimation(shake);
        holder.productName.setText(product.getName()+ "");
        holder.productCoast.setText(String.valueOf(product.getCoast()) + " DA");
        holder.productCoast.setAnimation(shake);
        holder.productImage.setText((String)(product.getName().charAt(0) + "" + product.getName().charAt(1)).toUpperCase());
        holder.productImage.setAnimation(shake);
        holder.marketType.setText(market.getMarketType() + "");
        holder.region.setText(market.getRegion() + "");
        holder.wilaya.setText(market.getWilaya());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

}
