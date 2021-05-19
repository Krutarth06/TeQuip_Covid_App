package com.example.tequip_covid_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class custom_adapter extends ArrayAdapter<Model_class_country> {
    private Context context;
    private List<Model_class_country> country_model_list;
    private List<Model_class_country> country_model_list_filtered;

    public custom_adapter(Context context, List<Model_class_country> country_model_list) {
        super(context, R.layout.list_item, country_model_list);

        this.context = context;
        this.country_model_list = country_model_list;
        this.country_model_list_filtered = country_model_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, true);
        TextView country_name = view.findViewById(R.id.Country_name_textview_id);
        country_name.setText(country_model_list_filtered.get(position).getCountry());
        return view;

    }

    @Override
    public int getCount() {

        return country_model_list_filtered.size();
    }

    @Nullable
    @Override
    public Model_class_country getItem(int position) {
        return country_model_list_filtered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = country_model_list.size();
                    filterResults.values = country_model_list;
                } else {
                    List<Model_class_country> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (Model_class_country itemsModel : country_model_list) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                country_model_list_filtered = (List<Model_class_country>) results.values;
                Countries_list.country_model_list = (List<Model_class_country>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}

