package com.mingle.chatappcs.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mingle.chatappcs.Fragments.CallsFragment;
import com.mingle.chatappcs.Fragments.ChatsFragment;
import com.mingle.chatappcs.Fragments.StatusFragment;

public class FragmentAdapter  extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ChatsFragment();
            case 1: return new StatusFragment();
            case 2: return new CallsFragment();
            default: return new ChatsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String Title = null;
        if(position==0)
            Title = "Chats";
        if(position==1)
            Title = "Status";
        if(position==2)
            Title = "Calls";
        return Title;
    }
}
