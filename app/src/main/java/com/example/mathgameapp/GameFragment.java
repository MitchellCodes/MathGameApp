package com.example.mathgameapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView textView = view.findViewById(R.id.txtEquationOutput);

        GameFragmentArgs args = GameFragmentArgs.fromBundle(getArguments());
        String[] operators = args.getOperators();
        String operatorsToUse = "";

        for (String operator : operators) {
            operatorsToUse += operator;
        }

        TextView textView1 = view.findViewById(R.id.txtEquationOutput);
        textView1.setText(operatorsToUse);
    }
}