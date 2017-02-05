package com.wakkenenijsberen.wakkenenijsberen;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vwitt on 8-12-2016.
 */

public class RankingFragment extends Fragment {

    private Player objPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_fragment, container, false);

        final DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);
        final Bundle args = this.getArguments();

        objPlayer = null;

        ImageButton ibtnRevert_rankingFragment = (ImageButton) view.findViewById(R.id.ibtnRevert_rankingFragment);
        ListView lvRanking_rankingFragment = (ListView) view.findViewById(R.id.lvRanking_rankingFragment);

        final Score[] scoresList = dbHandler.GetRanking();

        final ArrayList<String> scoresString = new ArrayList<String>();

        for(Score s : scoresList) {
            scoresString.add("Player Name: " + s.getPlayerName() + "\n" + "Score Points: " + s.getScorePoints() + "\n" + "Level: " + s.getLevelID() + "\n" + "Duration: " + String.valueOf(s.getDuration()) +
                    "\n" + "Dice: " + s.getAmountOfDices() + "\n" + "Pinguins: " + String.valueOf(s.getIsPinguinUse()) + "\n" + "Wrong: " + s.getScoreWrong() + "\n");
        }

        final ArrayAdapter<String> playerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, scoresString);

        lvRanking_rankingFragment.setAdapter(playerAdapter);

        ibtnRevert_rankingFragment.setOnClickListener(new View.OnClickListener() {
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

        ibtnRevert_rankingFragment.setImageResource(R.drawable.ic_menu_revert);

        return view;
    }
}
