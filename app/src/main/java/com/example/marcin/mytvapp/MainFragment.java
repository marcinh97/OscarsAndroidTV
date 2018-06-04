package com.example.marcin.mytvapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BrowseFragment{
    private SimpleBackgroundManager simpleBackgroundManager;

    private static final int NUMBER_OF_CATEGORIES = 3;
    private static final int NUMBER_OF_MOVIES = 5;

    private static final String CATEGORY_PREFIX = "category_";
    private static final String MOVIE_SUFFIX = "_movie";


    private List<String> oscarCategories = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCategories();
        setupUIElements();
        loadRows();
        setUpEventListeners();
        simpleBackgroundManager = new SimpleBackgroundManager(getActivity());
    }

    private void setCategories(){
        for (int i=1; i<=NUMBER_OF_CATEGORIES; i++){
            String categoryId = CATEGORY_PREFIX+Integer.toString(i);
            int id = getActivity().getApplicationContext().getResources().getIdentifier(categoryId, "string", getActivity().getPackageName());
            oscarCategories.add(getString(id));
        }
    }

    private void setUpEventListeners() {
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener{

        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof String){
                simpleBackgroundManager.clearBackground();
            }
            else if (item instanceof Movie){
                simpleBackgroundManager.updateBackground(getActivity().getDrawable((int)((Movie) item).getId()));
            }
        }
    }

    private void setupUIElements(){
        setTitle(getString(R.string.oscars_title));
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        setBrandColor(getResources().getColor(R.color.fastlane_background));
        setSearchAffordanceColor(getResources().getColor(R.color.search_opaque));
    }

    private void loadRows(){
        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setImagesToAdapter(rowsAdapter);
        setAdapter(rowsAdapter);
    }

    private void setImagesToAdapter(ArrayObjectAdapter rowsAdapter){
        HeaderItem cardPresenterHeader;
        CardPresenter cardPresenter;
        ArrayObjectAdapter cardRowAdapter;
        for (int j=0; j<NUMBER_OF_CATEGORIES; j++){
            cardPresenterHeader = new HeaderItem(j, oscarCategories.get(j));
            cardPresenter = new CardPresenter();
            cardRowAdapter = new ArrayObjectAdapter(cardPresenter);
            for(int i=0; i<NUMBER_OF_MOVIES; i++) {
                Movie movie = new Movie();
                String movieId = CATEGORY_PREFIX + Integer.toString(j + 1) + MOVIE_SUFFIX + Integer.toString(i + 1);
                int id = getActivity().getApplicationContext().getResources().getIdentifier(movieId, "string", getActivity().getPackageName());
                movie.setTitle(getString(id));
                movieId = CATEGORY_PREFIX + Integer.toString(j + 1) + MOVIE_SUFFIX + Integer.toString(i + 1);
                id = getActivity().getApplicationContext().getResources().getIdentifier(movieId, "drawable", getActivity().getPackageName());
                movie.setId(id);
                cardRowAdapter.add(movie);
            }
            rowsAdapter.add(new ListRow(cardPresenterHeader, cardRowAdapter));
        }

    }

}
