package net.eaustria.fragmentsdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class LeftFragment extends Fragment {
    private static final String TAG = LeftFragment.class.getSimpleName();
    private ListView list;
    private ArrayList<String> items = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: entered");
        super.onAttach(context);
        if (context instanceof OnSelectionChangedListener) {
            listener = (OnSelectionChangedListener) context;
        } else {
            Log.d(TAG, "onAttach: Activity does not implement OnSelectionChangedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: entered");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view) {
        Log.d(TAG, "initializeViews: entered");
        list = view.findViewById(R.id.listview);
        items.add("Max");
        items.add("Anselm");
        items.add("Maria");
        items.add("Sandra");
        list.setOnItemClickListener( (parent,  view1, position, id) -> itemSelected(position));
    }

    private void itemSelected(int position) {
        String item = items.get(position);
        listener.onSelectionChanged(position, item);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: entered");
        super.onStart();
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        items
                );
        list.setAdapter(adapter);
    }

    public interface OnSelectionChangedListener {
        void onSelectionChanged( int pos, String item);
    }

    private OnSelectionChangedListener listener;
}
