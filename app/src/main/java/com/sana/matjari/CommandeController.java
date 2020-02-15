package com.sana.matjari;


import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by asus on 27/12/2017.
 */

public class CommandeController extends Fragment {


    private RecyclerView recyclerView;
    private CommandeAdapter adapter;
    private List<Commande> commandesList;


    public void NotificationController(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.commande_layout, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /** Commandes Adapter **/

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view2);
        commandesList = new ArrayList<>();
        adapter = new com.sana.matjari.CommandeAdapter(this.getContext(), commandesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CommandeController.GridSpacingItemDecoration(2, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        prepareCommands();

    }

    private void prepareCommands() {

        Commande c1=new Commande();
        Commande c2=new Commande();
        Commande c3=new Commande();

        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


       /* c1.setDate(new Date());
        c1.setAmount(4);
        c1.setProduct(new Product("Jeans", 1000));
        c1.setClient(new Client("AMOKRANE", "Abdennour"));
        c1.setVendeur(new Vendeur("PIEGE Lingerie"));

        c2.setDate(new Date());
        c2.setAmount(1);
        c2.setProduct(new Product("PC ASUS", 7000));
        c2.setClient(new Client("HEZRALLAH", "Abdellah"));
        c2.setVendeur(new Vendeur("Boutique d'Informatique (Alger)"));

        c3.setDate(new Date());
        c3.setAmount(500);
        c3.setProduct(new Product("Pomme de terre", 500));
        c3.setClient(new Client("AMOKRANE", "Abdennour"));
        c3.setVendeur(new Vendeur("Beau lieu MAGAZIN"));

        commandesList.add(c1);
        commandesList.add(c2);
        commandesList.add(c3);

        adapter.notifyDataSetChanged();*/
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = true;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
