package com.jacob.zuimei.indicator;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

public class ViewPagerFragment extends Fragment  {

    private static final String ARGUMENT = "argument";

    public static ViewPagerFragment newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT,title);
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView  mTextView = (TextView) view.findViewById(R.id.textView);
        if (getArguments() != null){
            mTextView.setText(getArguments().getString(ARGUMENT));
        }
    }

}
