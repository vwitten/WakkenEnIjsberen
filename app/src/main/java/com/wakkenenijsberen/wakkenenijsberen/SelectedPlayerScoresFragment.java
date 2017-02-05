package com.wakkenenijsberen.wakkenenijsberen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by vwitt on 8-12-2016.
 */

public class SelectedPlayerScoresFragment extends Fragment {

    private Player objPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_player_scores_fragment, container, false);

        final DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);
        final Bundle args = this.getArguments();

        objPlayer = (Player)args.getSerializable("Name");

        ImageButton ibtnRevert_selectPlayerScoresFragment = (ImageButton) view.findViewById(R.id.btnRevert_selectPlayerScoresFragment);
        ListView lvPlayerScores_selectPlayerScoresFragment = (ListView) view.findViewById(R.id.lvPlayerScores_selectPlayerScoresFragment);


        final Score[] scoresList = dbHandler.getPlayerScores(objPlayer);
        final ArrayList<String> scoresString = new ArrayList<String>();
        for(Score s : scoresList){
            scoresString.add("Score Points: " + s.getScorePoints() + "\n" + "Level: " + s.getLevelID() + "\n" + "Duration: " + String.valueOf(s.getDuration()) +
                    "\n" + "Dice: " + s.getAmountOfDices() + "\n" + "Pinguins: " + String.valueOf(s.getIsPinguinUse()) + "\n" + "Wrong: " + s.getScoreWrong() + "\n");
        }

        final ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, scoresString);

        lvPlayerScores_selectPlayerScoresFragment.setAdapter(playerAdapter);

        ibtnRevert_selectPlayerScoresFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(args != null){
                    objPlayer = (Player)args.getSerializable("Name");
                    StartFragment myFragment = new StartFragment();
                    android.app.FragmentTransaction ft;
                    ft = getFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    args.putSerializable("Name", objPlayer);
                    myFragment.setArguments(args);
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.activity_menu, myFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else{
                    android.app.FragmentTransaction ft;
                    ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.activity_menu, new StartFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        ibtnRevert_selectPlayerScoresFragment.setImageResource(R.drawable.ic_menu_revert);

        return view;
    }
}
