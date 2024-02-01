package com.example.proyecto5_juego;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

public class HCallback extends DiffUtil.Callback{

    private final ArrayList<String> mOldHistory;
    private final ArrayList<String> mNewHistory;

    public HCallback(ArrayList<String> oldEmployeeList, ArrayList<String> newEmployeeList) {
        this.mOldHistory = oldEmployeeList;
        this.mNewHistory = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return mOldHistory.size();
    }

    @Override
    public int getNewListSize() {
        return mNewHistory.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldHistory.get(oldItemPosition) == mOldHistory.get(
                newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final String oldHistory = mOldHistory.get(oldItemPosition);
        final String newHistory = mNewHistory.get(newItemPosition);

        return oldHistory.equals(newHistory);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}