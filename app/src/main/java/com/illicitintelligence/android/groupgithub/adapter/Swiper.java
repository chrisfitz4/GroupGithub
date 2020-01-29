package com.illicitintelligence.android.groupgithub.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


// Class to handle swiping of the user.
// Add as an ItemTouchHelper to the recyclerview in the activity/fragment containing it.
// Implement the SwipeListener interface in the class to customize what the rv should do to that swiped item (ie delete).
public class Swiper extends ItemTouchHelper.Callback {

    public interface SwipeListener{
        void itemSelected(int position);
    }

    private SwipeListener swipeListener;

    public Swiper(SwipeListener swipeListener) {
        this.swipeListener = swipeListener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int selected = viewHolder.getAdapterPosition();
        swipeListener.itemSelected(selected);
    }
}
