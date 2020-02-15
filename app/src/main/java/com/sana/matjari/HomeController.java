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

public class HomeController extends Fragment {

    private RecyclerView recyclerView;
    private com.sana.matjari.MarketAdapter adapter;
    private List<Vendeur> vendeursList;

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
        return inflater.inflate(R.layout.home_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /** Markets Adapter **/

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        vendeursList = new ArrayList<>();
        adapter = new com.sana.matjari.MarketAdapter(this.getContext(), vendeursList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        preparePosts();


    }

    private void preparePosts() {

        Vendeur market1= new Vendeur();
        Vendeur market2= new Vendeur();
        Vendeur market3= new Vendeur();

        market1.setMarketName("AMOKRANE d'Informatique");
        market1.setMarketType("Boutique informatique");
        market1.setRegion("Rue Didouche Mourad N°15");
        market1.setWilaya("Alger");
        market1.setEvaluation((float) 2.5);
        market1.addTag(new MarketTag("Informatique"));
        market1.addTag(new MarketTag("PC"));
        market1.addTag(new MarketTag("Ordinateur"));
        market1.addTag(new MarketTag("Electronic"));
        market1.addTag(new MarketTag("Asus"));
        // ###############
        market2.setMarketName("Magasin Gautier");
        market2.setMarketType("Boutique de meubles contemporains");
        market2.setRegion("Dely Brahim");
        market2.setWilaya("Alger");
        market2.setEvaluation((float) 4);
        market2.addTag(new MarketTag("Jeans"));
        market2.addTag(new MarketTag("Chiffons"));
        market2.addTag(new MarketTag("Zara"));
        market2.addTag(new MarketTag("KOUTA"));
        market2.addTag(new MarketTag("NIKE"));
        // ###############
        market3.setMarketName("Magasin Fabiano");
        market3.setMarketType("Prêt-à-porter et habillement");
        market3.setRegion("Fabiani-rue charras (16000)");
        market3.setWilaya("Alger");
        market3.setEvaluation((float) 2);
        market3.addTag(new MarketTag("Jeans"));
        market3.addTag(new MarketTag("Chiffons"));
        market3.addTag(new MarketTag("Zara"));
        market3.addTag(new MarketTag("KOUTA"));
        market3.addTag(new MarketTag("NIKE"));
        market3.addTag(new MarketTag("Chemise"));
        market3.addTag(new MarketTag("Costume"));

        vendeursList.add(market1);
        vendeursList.add(market2);
        vendeursList.add(market3);

        adapter.notifyDataSetChanged();
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
