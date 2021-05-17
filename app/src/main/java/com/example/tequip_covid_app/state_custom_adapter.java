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

public class state_custom_adapter extends ArrayAdapter<Model_class_states> {
    private Context context;
    private List<Model_class_states> state_model_list;
    private List<Model_class_states> state_model_list_filtered;
    public state_custom_adapter(Context context,  List<Model_class_states> state_model_list) {
        super(context,  R.layout.list_item, state_model_list);
        this.context = context;
        this.state_model_list = state_model_list;
        this.state_model_list_filtered = state_model_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, true);
        TextView state_name = view.findViewById(R.id.Country_name_textview_id);
        state_name.setText(state_model_list_filtered.get(position).getState());
        return view;
    }
    @Override
    public int getCount() {

        return state_model_list_filtered.size();
    }

    @Nullable
    @Override
    public Model_class_states getItem(int position) {
        return state_model_list_filtered.get(position);
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
                    filterResults.count = state_model_list.size();
                    filterResults.values = state_model_list;
                } else {
                    List<Model_class_states> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (Model_class_states itemsModel : state_model_list) {
                        if (itemsModel.getState().toLowerCase().contains(searchStr)) {
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
                state_model_list_filtered = (List<Model_class_states>) results.values;
                States_list.states_model_list = (List<Model_class_states>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
