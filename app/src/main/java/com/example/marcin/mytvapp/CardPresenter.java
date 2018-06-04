package com.example.marcin.mytvapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;

public class CardPresenter extends Presenter {
    private static Context mContext;
    private static int CARD_WIDTH = 313;
    private static int CARD_HEIGHT = 176;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mContext = parent.getContext();

        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(mContext.getResources().getColor(R.color.search_opaque));
        cardView.setInfoAreaBackgroundColor(mContext.getResources().getColor(R.color.search_opaque));
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        Movie movie = (Movie)item;
        ((ViewHolder)viewHolder).setMovie(movie);
        ((ViewHolder)viewHolder).cardView.setTitleText(movie.getTitle());
        ((ViewHolder)viewHolder).cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        ((ViewHolder)viewHolder).cardView.setMainImage(mContext.getDrawable((int)movie.getId()));
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }

    static class ViewHolder extends Presenter.ViewHolder{
        private Movie movie;
        private ImageCardView cardView;
        private Drawable defaultCardImage;

        public ViewHolder(View view) {
            super(view);
            cardView = (ImageCardView)view;
            defaultCardImage = mContext.getResources().getDrawable(R.drawable.movie);
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

    }
}
