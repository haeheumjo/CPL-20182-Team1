package com.example.juhyeon.waygo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.juhyeon.waygo.dummy.DummyContent;
import com.example.juhyeon.waygo.dummy.DummyContent.DummyItem;

import java.util.List;

public class placeFragment extends ListFragment {

    PlaceListViewAdapter adapter;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Adapter 생성 및 Adapter 지정.
        adapter = new PlaceListViewAdapter() ;
        setListAdapter(adapter) ;
        // 아이템추가 DB써야되는뎅
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.restaurant), "도레미파") ;
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.restaurant), "솔라시도") ;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void addItem(Drawable icon, String place) {
        adapter.addItem(icon, place);
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        PlaceListItem item = (PlaceListItem) l.getItemAtPosition(position) ;

        String placename = item.getPlace() ;
        Drawable iconimage = item.getIcon() ;

        // TODO : use item data.
    }
}
