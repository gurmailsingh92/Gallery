package com.media.gallery;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListenerGrid extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerOnScrollListenerGrid.class.getSimpleName();

    private boolean flag = false;
    private boolean updateFlag = false;

    //private int recentFlag=0;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    protected int visibleThreshold = Constants.INSTANCE.getPAGE_LIMIT(); // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 0;

    private GridLayoutManager mGridLayoutManager;

    public EndlessRecyclerOnScrollListenerGrid(GridLayoutManager gridLayoutManager) {
        this(gridLayoutManager, Constants.INSTANCE.getPAGE_LIMIT());
    }

    public EndlessRecyclerOnScrollListenerGrid(GridLayoutManager gridLayoutManager, int visibleThreshold) {
        this.mGridLayoutManager = gridLayoutManager;
        this.visibleThreshold = visibleThreshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mGridLayoutManager.getItemCount();
        firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();
        System.out.println("==visibleItemCount==totalItemCount==firstVisibleItem"+visibleItemCount+"="+totalItemCount+"="+firstVisibleItem);
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            current_page++;
            flag=true;
            updateFlag=true;

            onLoadMore(current_page);

            loading = true;
        }


    }

    public void reset() {
        this.previousTotal = 0;
        this.loading = false;
        this.current_page = 0;
    }

    public abstract void onLoadMore(int current_page);




    public void decreasePagingCount() {
        current_page--;
    }
}