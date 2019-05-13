package net.eaustria.fragmentsdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RightFragment extends Fragment {
    public final static String TAG = RightFragment.class.getSimpleName();
    private TextView txt1;
    private TextView txt2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: entered");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        intializeViews(view);
        return view;
    }
    private void intializeViews(View view) {
        Log.d(TAG, "intializeViews: entered");
        txt1 = view.findViewById(R.id.txtview_1);
        txt2 = view.findViewById(R.id.txtview_2);
        txt1.setText("Erste Zeile....");
        txt2.setText("Zweite Zeile....");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: entered");
    }

    public void show(int pos, String item) {
        Log.d(TAG, "show: entered");
        txt1.setText(""+pos);
        txt2.setText(item);
    }
}
