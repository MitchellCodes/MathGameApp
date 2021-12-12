package com.example.mathgameapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setUpHomeToGameButton(view);
        setUpHomeToStatsButton(view);

        return view;
    }

    private void setUpHomeToGameButton(View view) {
        Button btnHomeToGame = view.findViewById(R.id.btnHomeToGame);
        btnHomeToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] operators = getSelectedOperators().toArray(new String[0]);
                HomeFragmentDirections.ActionHomeFragmentToGameFragment action =
                        HomeFragmentDirections.actionHomeFragmentToGameFragment(operators);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private ArrayList<String> getSelectedOperators () {
        ToggleButton[] buttons = getOperatorButtons();
        ArrayList<String> selectedOperators = new ArrayList<>();

        for (ToggleButton currentButton : buttons) {
            if (currentButton.isChecked()) {
                selectedOperators.add(currentButton.getText().toString());
            }
        }

        return selectedOperators;
    }

    private ToggleButton[] getOperatorButtons() {

        return new ToggleButton[]{
                getView().findViewById(R.id.btnAddition),
                getView().findViewById(R.id.btnSubtraction),
                getView().findViewById(R.id.btnMultiplication),
                getView().findViewById(R.id.btnDivision)
        };
    }

    private void setUpHomeToStatsButton(View view) {
        Button btnHomeToStats = view.findViewById(R.id.btnHomeToStats);
        btnHomeToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_statisticsFragment);
            }
        });
    }
}