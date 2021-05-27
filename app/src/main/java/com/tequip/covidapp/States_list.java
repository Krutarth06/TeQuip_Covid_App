package com.tequip.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class States_list extends AppCompatActivity {
    EditText Search_view;
    ListView StatesListView;
    SimpleArcLoader arcLoader;
    LinearLayout mainLayout;
    ImageView back_btn;

    public static List<Model_class_states> states_model_list = new ArrayList<>();
    Model_class_states ModelState;
    state_custom_adapter myCustomAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_list);
        Search_view = findViewById(R.id.search_bar_id_states);
        StatesListView = findViewById(R.id.states_list_view_id);
        arcLoader = findViewById(R.id.loader2);
        back_btn = findViewById(R.id.back_btn_id);
        mainLayout = findViewById(R.id.mainLayout_stateList_id);

        fetchData();
        StatesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), stateDetails.class).putExtra("position", position));
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
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void fetchData() {
        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        arcLoader.start();
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

                        ModelState = new Model_class_states(state, totalCasesIndia, totalCasesForeign, recovered, deaths, totalCases);
                        states_model_list.add(ModelState);
                        myCustomAdapter1 = new state_custom_adapter(States_list.this, states_model_list);
                        StatesListView.setAdapter(myCustomAdapter1);

                    }
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                    mainLayout.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    arcLoader.stop();
                    arcLoader.setVisibility(View.GONE);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                arcLoader.stop();
                arcLoader.setVisibility(View.GONE);
                Toast.makeText(States_list.this, "Please switch on your Wifi or Mobile Data and try again later", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestData);
    }
}