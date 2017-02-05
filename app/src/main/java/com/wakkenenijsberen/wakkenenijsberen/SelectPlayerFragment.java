package com.wakkenenijsberen.wakkenenijsberen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vwitt on 8-12-2016.
 */

public class SelectPlayerFragment extends Fragment{

    private Player objPlayer;
    private Player[] playerArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_player_fragment, container, false);

        final Bundle args = this.getArguments();
        DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);

        playerArray = dbHandler.GetAllPlayers();

        ArrayList<String> names = new ArrayList<String>();

        for(Player objP : playerArray){
            names.add(objP.getName());
        }

        ImageButton ibtnRevert_selectPlayerFragment = (ImageButton) view.findViewById(R.id.ibtnRevert_selectPlayerFragment);
        ListView lvSelectPlayer_selectPlayerFragment = (ListView) view.findViewById(R.id.lvSelectPlayer_selectPlayerFragment);
        Button btnCreateNewPlayer_selectPlayerFragment = (Button) view.findViewById(R.id.btnCreateNewPlayer_selectPlayerFragment);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, names );

        lvSelectPlayer_selectPlayerFragment.setAdapter(arrayAdapter);

        ibtnRevert_selectPlayerFragment.setOnClickListener(new View.OnClickListener() {
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

        lvSelectPlayer_selectPlayerFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                objPlayer = playerArray[i];

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
        });

        btnCreateNewPlayer_selectPlayerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(args != null){
                    objPlayer = (Player)args.getSerializable("Name");
                    CreateNewPlayerFragment myFragment = new CreateNewPlayerFragment();
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
                    ft.replace(R.id.activity_menu, new CreateNewPlayerFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        btnCreateNewPlayer_selectPlayerFragment.setText("Create New Player");
        ibtnRevert_selectPlayerFragment.setImageResource(R.drawable.ic_menu_revert);

        return view;
    }
}
