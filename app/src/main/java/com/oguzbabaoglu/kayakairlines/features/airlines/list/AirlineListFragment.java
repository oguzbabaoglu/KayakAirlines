package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oguzbabaoglu.kayakairlines.R;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.util.Dagger;
import com.oguzbabaoglu.kayakairlines.util.DividerItemDecoration;
import com.oguzbabaoglu.kayakairlines.util.ListUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineListFragment extends Fragment implements AirlineListView {

  private static final String KEY_AIRLINES = "KEY_AIRLINES";

  @BindView(R.id.airline_list_recyclerview) RecyclerView airlineRecyclerView;

  @Inject AirlineListPresenter presenter;

  public static AirlineListFragment newInstance(List<Airline> airlines) {
    Bundle args = new Bundle();
    args.putParcelableArrayList(KEY_AIRLINES, ListUtil.asArrayList(airlines));
    AirlineListFragment fragment = new AirlineListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dagger.INJECTOR.airlineComponent().inject(this);
    presenter.setView(this);
  }

  @Nullable
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_airline_list, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    airlineRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    airlineRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

    AirlineListAdapter adapter = new AirlineListAdapter(position -> {
    });

    adapter.setAirlines(getArguments().getParcelableArrayList(KEY_AIRLINES));
    airlineRecyclerView.setAdapter(adapter);
  }
}
