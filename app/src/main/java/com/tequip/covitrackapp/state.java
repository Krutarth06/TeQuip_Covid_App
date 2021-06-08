package com.tequip.covitrackapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class state extends Fragment {
    EditText Search_view;
    ListView StatesListView;
    ProgressBar loader;
    LinearLayout mainLayout;

    public static List<Model_class_states> states_model_list = new ArrayList<>();
    Model_class_states ModelState;
    state_custom_adapter myCustomAdapter1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_state, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Search_view = view.findViewById(R.id.search_bar_id);
        StatesListView = view.findViewById(R.id.Country_name_textview_id);
        loader = view.findViewById(R.id.main_loader_state);
        mainLayout = view.findViewById(R.id.mainLayout_stateList_id);

        fetchData();

        StatesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), stateDetails.class).putExtra("position", position));
            }
        });

        Search_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myCustomAdapter1.getFilter().filter(s);
                myCustomAdapter1.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void fetchData() {
        loader.animate();

        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        StringRequest requestData = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONArray regionalArray = data.getJSONArray("regional");
                    for (int i = 0; i < regionalArray.length(); i++) {

                        JSONObject regionalObject = regionalArray.getJSONObject(i);
                        String state = regionalObject.getString("loc");
                        String totalCasesIndia = regionalObject.getString("confirmedCasesIndian");
                        String totalCasesForeign = regionalObject.getString("confirmedCasesForeign");
                        String recovered = regionalObject.getString("discharged");
                        String deaths = regionalObject.getString("deaths");
                        String totalCases = regionalObject.getString("totalConfirmed");

                        if (getActivity() != null) {
                            ModelState = new Model_class_states(state, totalCasesIndia, totalCasesForeign, recovered, deaths, totalCases);
                            states_model_list.add(ModelState);
                            myCustomAdapter1 = new state_custom_adapter(getActivity(), states_model_list);
                            StatesListView.setAdapter(myCustomAdapter1);
                        }

                    }
                    loader.clearAnimation();
                    loader.setVisibility(View.GONE);
                    mainLayout.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    loader.clearAnimation();
                    loader.setVisibility(View.GONE);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.clearAnimation();
                loader.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Please switch on your Wifi or Mobile Data and try again later", Toast.LENGTH_SHORT).show();
            }
        });
        if (getActivity() != null) {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(requestData);
        }
    }
}