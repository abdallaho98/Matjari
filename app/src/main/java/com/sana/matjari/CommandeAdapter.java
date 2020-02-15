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

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.ViewHolder> {

    private Context mContext;
    private List<Commande>  commandesList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Views declarations.
        public TextView titre, productAndAmount, date;
        public TextView marketImage;

        @SuppressLint("CutPasteId")
        public ViewHolder(View itemView) {
            super(itemView);

            /*** ID flow control 'post_card' **/

            // Views id combinations.
            titre = (TextView) itemView.findViewById(R.id.titre_commande);
            productAndAmount = (TextView) itemView.findViewById(R.id.produit_amount);
            date = (TextView) itemView.findViewById(R.id.date);
            marketImage = (TextView) itemView.findViewById(R.id.image_name);

        }
    }

    public CommandeAdapter(Context mContext, List<Commande> commandesList) {
        this.mContext = mContext;
        this.commandesList = commandesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.commande_card, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("ASSERT", "onCreateViewHolder");

        // Here is all the post controls
        Commande commande= commandesList.get(position);
        Animation shake = AnimationUtils.loadAnimation(this.mContext, R.anim.shake);
        holder.titre.setText(String.format("Commande de %s à %s", commande.getClient().nom, commande.getVendeur().getMarketName()));
        holder.productAndAmount.setText(String.format("%s • %d pièces", commande.getProduct().getName(), commande.getAmount()));
        holder.marketImage.setText((String)(commande.getVendeur().getMarketName().charAt(0) + "" + commande.getVendeur().getMarketName().charAt(1)).toUpperCase());
        holder.productAndAmount.setAnimation(shake);
    }

    @Override
    public int getItemCount() {
        return commandesList.size();
    }

}
