package com.sana.matjari;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by asus on 27/12/2017.
 */

public class SearchController extends Fragment {

    private RecyclerView recyclerView;
    private com.sana.matjari.MarketAdapter adapter;
    private com.sana.matjari.ProductAdapter adapterProduct;
    private List<Vendeur> vendeursList;
    private List<Product> productsList;
    private AppCompatEditText searcher;
    private DatabaseReference database,dataproduct;


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
        return inflater.inflate(R.layout.search_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /** Markets Adapter **/
        database = FirebaseDatabase.getInstance().getReference().child("stores");
        dataproduct = FirebaseDatabase.getInstance().getReference().child("products");
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        searcher = (AppCompatEditText) getView().findViewById(R.id.searcher);
        vendeursList = new ArrayList<Vendeur>();
        productsList = new ArrayList<Product>();
        adapter = new com.sana.matjari.MarketAdapter(this.getContext(), vendeursList);
        adapterProduct = new com.sana.matjari.ProductAdapter(this.getContext(), productsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SearchController.GridSpacingItemDecoration(2, dpToPx(0), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        final RadioButton vendeurRadio, productRadio;

        vendeurRadio = getView().findViewById(R.id.vendeur);

        recyclerView.setAdapter(adapter);

        vendeurRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {

                    recyclerView.setAdapter(adapter);
                   // Toast.makeText(getContext(), "Rechercher des vendeurs", Toast.LENGTH_SHORT).show();
                } else {

                    recyclerView.setAdapter(adapterProduct);
                    //Toast.makeText(getContext(), "Rechercher des produits", Toast.LENGTH_SHORT).show();
                }
            }
        });



        searcher.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            database.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    for (DataSnapshot datasnap: dataSnapshot.getChildren())
                    {

                        if(datasnap.child("nomMagasin").getValue().toString().indexOf(searcher.getText().toString()) != -1)
                        {
                            boolean o = true;
                            Vendeur vendeur = new Vendeur();
                            vendeur.setAddress(datasnap.child("adresse").getValue().toString());
                            vendeur.setMarketType(datasnap.child("category").getValue().toString());
                            vendeur.setMarketName(datasnap.child("nomMagasin").getValue().toString());
                            vendeur.setRegion(datasnap.child("city").getValue().toString());
                            vendeur.setWilaya(datasnap.child("country").getValue().toString());
                            vendeur.setTel(datasnap.child("tel").getValue().toString());

                            for(Vendeur vende :vendeursList) {if ( vende.getName() == vendeur.getName()){ o = false; }

                            }
                            if (o == true){    vendeursList.add(vendeur);adapterProduct.notifyDataSetChanged();}

                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }

        @Override
        public void afterTextChanged(Editable s) {

       vendeursList.clear();

        }
    });


                searcher.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence se, int start, int before, int count) {

                        dataproduct.addChildEventListener(new ChildEventListener() {

                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren() )
                                {

                                    if (dataSnapshot1.child("prodName").getValue().toString().indexOf(searcher.getText().toString()) != -1)
                                    {
                                              boolean o = true;
                                              Product product = new Product();
                                              product.setCoast(Float.parseFloat(dataSnapshot1.child("price").getValue().toString()));
                                              product.setDescription(dataSnapshot1.child("disc").getValue().toString());
                                              product.setImage(dataSnapshot1.child("productImg").getValue().toString());
                                              product.setName(dataSnapshot1.child("prodName").getValue().toString());


                                        for (Product produt:productsList
                                             ) {if (produt.getName() == product.getName()){ o = false; }

                                        }
                                          if (o == true){    productsList.add(product);/*adapterProduct.notifyDataSetChanged();*/}


                                    }


                                }
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }



                    @Override
                    public void afterTextChanged(Editable s) {
                productsList.clear();
                    }
                });









    //  prepareMarkets();
      //prepareProducts();



    }

    private void prepareMarkets() {

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

    private void prepareProducts() {

        Product p1= new Product("Laptop ASUS i7 3.5GHZ", 70000);
        Product p2= new Product("Jeans ZARA", 5000);

        Vendeur market1 = new Vendeur();
        Vendeur market2 = new Vendeur();


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

        p1.setVendeur(market1);
        p2.setVendeur(market2);

        productsList.add(p1);
        productsList.add(p2);

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
