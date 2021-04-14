package com.example.cinehub.NavigationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cinehub.R;
import com.example.cinehub.databinding.FragmentChatBinding;
import com.example.cinehub.databinding.FragmentMessageBinding;

public class ChatFragment extends Fragment {

    private FragmentChatBinding databinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false);

        return  databinding.getRoot();
    }
}
