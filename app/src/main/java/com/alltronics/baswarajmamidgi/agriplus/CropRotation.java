package com.alltronics.baswarajmamidgi.agriplus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CropRotation extends Fragment {
    private String districts[]={};
    private RecyclerView recyclerView;
    private List<CardViewClass> list;
    private CardViewAdapter adapter;



    public CropRotation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_crop_rotation, container, false);
        final Spinner statespinner=view.findViewById(R.id.select_state);
        final Spinner districtspinner=view.findViewById(R.id.select_district);
        String states[]=new String[]{"Andhra Pradhesh","Karnataka","Kerala","Tamil Nadu","Telangana"};
        ArrayAdapter statesadapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,states);
        statesadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statespinner.setAdapter(statesadapter);

        statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        districts = new String[]{"Hyderbad", "sangareddy", "medak", "warangal"};
                        break;

                    case 1:
                        districts = new String[]{"chennai", "kokulur", "palem"};
                        break;
                }

                ArrayAdapter districtadapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,districts);
                districtadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtspinner.setAdapter(districtadapter);
                districtadapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recyclerView= (RecyclerView)view.findViewById(R.id.recycler_view);
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


        return view;
    }

}
