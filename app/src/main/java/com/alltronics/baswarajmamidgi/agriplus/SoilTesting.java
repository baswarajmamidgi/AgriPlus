package com.alltronics.baswarajmamidgi.agriplus;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class SoilTesting extends Fragment {
    private RecyclerView recyclerView;
    private List<CardViewClass> list;
    private CardViewAdapter adapter;
    public static final String SOILTEST_URL="http://alltronics.in/agriplus/soiltest.php";





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
        getSoilData();

return layout;
    }


    private void getSoilData() {
        handleSSLHandshake();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SOILTEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                           //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();

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

                return super.getParams();
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
