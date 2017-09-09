package com.alltronics.baswarajmamidgi.agriplus;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class RainAnalysis extends Fragment {
    public static final String SOILTEST_URL="http://alltronics.in/agriplus/rainanalysis.php";
    Button button;

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
        button=view.findViewById(R.id.getdata);
        String states[]=new String[]{"AndhraPradhesh","telangana"};
        final ArrayAdapter statesadapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,states);
        statesadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statespinner.setAdapter(statesadapter);
        statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        districts = new String[]{"Amaravathi", "vijaywada", "Vishakapatnam", "Tirupathi"};
                        break;

                    case 1:
                        districts = new String[]{"Hyderbad", "Medak", "Sangareddy"};
                        break;
                }

                ArrayAdapter districtadapter=new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,districts);
                districtadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                districtspinner.setAdapter(districtadapter);
                districtadapter.notifyDataSetChanged();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String state=statespinner.getSelectedItem().toString();
                        String district=districtspinner.getSelectedItem().toString();
                        getRainData(state.toLowerCase(),district.toLowerCase());

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




return view;
    }
    private void getRainData(final String state, final String district) {
        Log.i("log",state+district);
        handleSSLHandshake();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SOILTEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        parseJSON(response);
                        Log.i("log",response.toString());
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("log",error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> prams = new HashMap<>();
                prams.put("state", state); //state namee hardcoded
                prams.put("district", district);  //district name hardcoded
                return prams;
            }


        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void parseJSON(String response)  {
        String rain="";
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            JSONObject object=jsonArray.getJSONObject(0);
            rain=object.getString("rainfall");
            Toast.makeText(getContext(), rain, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    }


