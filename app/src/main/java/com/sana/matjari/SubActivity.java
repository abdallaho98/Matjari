package com.sana.matjari;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity implements OnMapReadyCallback{
    private RecyclerView recyclerView;
    private com.sana.matjari.ProductAdapter adapterProduct;
    private List<Product> productsList;
    public Vendeur vendeur;
    private SupportMapFragment mapFragment;
    private TextView region,wilaya,marketType,workTime,phone,email,imagename;
    /**
     *
     * */

    //private ImageView marketImage= (ImageView) findViewById(R.id.image_name);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
               .findFragmentById(R.id.mapGoogle);
        mapFragment.getMapAsync(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view3);
        region=(TextView) findViewById(R.id.region);
        imagename=(TextView) findViewById(R.id.image_name);
        wilaya=(TextView) findViewById(R.id.wilaya);
        marketType=(TextView) findViewById(R.id.type_market);
        workTime=(TextView) findViewById(R.id.work_time);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView) findViewById(R.id.email);
        productsList = new ArrayList<>();
        adapterProduct = new com.sana.matjari.ProductAdapter(this, productsList);
        recyclerView.setAdapter(adapterProduct);
        Vendeur vendeur = (Vendeur) getIntent().getSerializableExtra("Vendeur");
        if (vendeur != null){
        region.setText(vendeur.getRegion());
        wilaya.setText(vendeur.getWilaya());
        marketType.setText(vendeur.getMarketType());
        imagename.setText(vendeur.getMarketName());
        phone.setText(vendeur.getTel());
        }






         RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
         recyclerView.setLayoutManager(mLayoutManager);
         //recyclerView.addItemDecoration(new S.GridSpacingItemDecoration(2, dpToPx(0), true));
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         recyclerView.setAdapter(adapterProduct);

        //prepareProducts();
    }

    private void prepareProducts() {

        Product p1= new Product("Laptop ASUS i7 3.5GHZ", 70000);
        Product p2= new Product("Laptop HP i5 2.5GHZ", 5000);

        Vendeur market1 = new Vendeur();
        Vendeur market2 = new Vendeur();


        market1.setMarketName("AMOKRANE d'Informatique");
        market1.setMarketType("Boutique informatique");
        market1.setRegion("Rue Didouche Mourad N°15");
        market1.setWilaya("Alger");
        market1.setEvaluation((float) 2.5);
        // ###############
        market2.setMarketName("Magasin Gautier");
        market2.setMarketType("Boutique de meubles contemporains");
        market2.setRegion("Dely Brahim");
        market2.setWilaya("Alger");
        market2.setEvaluation((float) 4);
        // ###############

        p1.setVendeur(market1);
        p2.setVendeur(market2);


        productsList.add(p1);
        productsList.add(p2);

        adapterProduct.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(36.75,3.64);
        googleMap.setMaxZoomPreference(20);    googleMap.setMinZoomPreference(1);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title(""));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
 /*   public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

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
    /*private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }*/

}

