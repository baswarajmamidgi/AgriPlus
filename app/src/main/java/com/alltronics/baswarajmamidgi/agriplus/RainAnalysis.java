package com.alltronics.baswarajmamidgi.agriplus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class RainAnalysis extends Fragment {

private String districts[];
    public RainAnalysis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_rain_analysis, container, false);
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




return view;
    }

    }


