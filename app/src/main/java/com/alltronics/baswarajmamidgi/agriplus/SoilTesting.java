package com.alltronics.baswarajmamidgi.agriplus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoilTesting extends Fragment {
    private RecyclerView recyclerView;
    private List<CardViewClass> list;
    private CardViewAdapter adapter;



    public SoilTesting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentout
        View layout = inflater.inflate(R.layout.fragment_soil_testing, container, false);

        recyclerView= (RecyclerView)layout.findViewById(R.id.recycler_view);
        list=new ArrayList<>();
        adapter=new CardViewAdapter(getContext(),list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        CardViewClass workshopclass = new CardViewClass(R.drawable.black,getString(R.string.blacksoil));
        list.add(workshopclass);
        workshopclass = new CardViewClass(R.drawable.red,getString(R.string.redsoil));
        list.add(workshopclass);
        workshopclass = new CardViewClass(R.drawable.alluvial,getString(R.string.alluvialsoil));
        list.add(workshopclass);
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);

return layout;
    }

}
