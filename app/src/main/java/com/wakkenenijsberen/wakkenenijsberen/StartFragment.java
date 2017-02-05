package com.wakkenenijsberen.wakkenenijsberen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StartFragment extends Fragment{

    private Player objPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_fragment, container, false);

        final Bundle args = this.getArguments();

        objPlayer = null;

        TextView tvSelectedPlayer_startFragment = (TextView) view.findViewById(R.id.tvSelectedPlayer_startFragment);
        Button btnSelectPlayer_startFragment = (Button) view.findViewById(R.id.btnSelectPlayer_startFragment);
        Button btnPlay_startFragment = (Button) view.findViewById(R.id.btnPlay_startFragment);
        Button btnPlayerScores_startFragment = (Button) view.findViewById(R.id.btnPlayerScores_startFragment);
        Button btnRanking_startFragment = (Button) view.findViewById(R.id.btnRanking_startFragment);
        ImageButton ibtnSettings_startFragment = (ImageButton) view.findViewById(R.id.ibtnSettings_startFragment);

        if(args != null){
            Player name = (Player)args.getSerializable("Name");
            tvSelectedPlayer_startFragment.setText(name.getName().toString());
        }

        btnSelectPlayer_startFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(args != null){
                    objPlayer = (Player)args.getSerializable("Name");
                    SelectPlayerFragment myFragment = new SelectPlayerFragment();
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
                    ft.replace(R.id.activity_menu, new SelectPlayerFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        btnPlay_startFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(args != null){
                    objPlayer = (Player)args.getSerializable("Name");
                    GameFragment myFragment = new GameFragment();
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
                    Toast.makeText(getActivity(), "Select A Player First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPlayerScores_startFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(args != null){
                    objPlayer = (Player)args.getSerializable("Name");
                    SelectedPlayerScoresFragment myFragment = new SelectedPlayerScoresFragment();
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
                    Toast.makeText(getActivity(), "Select A Player First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRanking_startFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(args != null){
                    objPlayer = (Player)args.getSerializable("Name");
                    RankingFragment myFragment = new RankingFragment();
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
                    ft.replace(R.id.activity_menu, new RankingFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        ibtnSettings_startFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(args != null){
                    objPlayer = (Player)args.getSerializable("Name");
                    PlayerSettingsFragment myFragment = new PlayerSettingsFragment();
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
                    Toast.makeText(getActivity(), "Select A Player First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ibtnSettings_startFragment.setImageResource(R.drawable.ic_menu_manage);

        return view;
    }
}
