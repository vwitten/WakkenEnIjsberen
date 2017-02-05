package com.wakkenenijsberen.wakkenenijsberen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by vwitt on 8-12-2016.
 */

public class PlayerSettingsFragment extends Fragment {

    private Player objPlayer;
    private Level objLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_settings_fragment, container, false);

        final Bundle args = this.getArguments();

        final DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);

        objPlayer = (Player)args.getSerializable("Name");



        ImageButton ibtnRevert_playerSettingsFragment = (ImageButton) view.findViewById(R.id.ibtnRevert_playerSettingsFragment);
        Button btnSaveChanges_playerSettingsFragment = (Button) view.findViewById(R.id.btnSaveChanges_playerSettingsFragment);
        final Spinner spLevel_playerSettingsFragment = (Spinner) view.findViewById(R.id.spLevel_playerSettingsFragment);
        final Spinner spAmountOfDobbles_playerSettingsFragment = (Spinner) view.findViewById(R.id.spAmountOfDobbles_playerSettingsFragment);
        final Switch swPinguïns_playerSettingsFragment = (Switch) view.findViewById(R.id.swPinguïns_playerSettingsFragment);

        final String[] levels = new String[]{"level 1", "level 2", "level 3"};
        final ArrayAdapter<String> adapterLevel = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, levels);

        spLevel_playerSettingsFragment.setAdapter(adapterLevel);

        final String[] dobbels = new String[]{"3", "4", "5", "6", "7", "8"};
        final ArrayAdapter<String> adapterDobbels = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, dobbels);

        spAmountOfDobbles_playerSettingsFragment.setAdapter(adapterDobbels);

        swPinguïns_playerSettingsFragment.setChecked(objPlayer.getPinguïnsUse());
        spAmountOfDobbles_playerSettingsFragment.setSelection(objPlayer.getAmountOfDobbles() - 3);
        spLevel_playerSettingsFragment.setSelection(Integer.parseInt(objPlayer.getCurrentLevel().substring(6)) - 1);




        ibtnRevert_playerSettingsFragment.setOnClickListener(new View.OnClickListener() {
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

        btnSaveChanges_playerSettingsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                objLevel = dbHandler.GetLevel(objPlayer.getPlayerID(), levels[spLevel_playerSettingsFragment.getSelectedItemPosition()]);

                if(objLevel.getIsUnlocked()){
                    objPlayer.setCurrentLevel(levels[spLevel_playerSettingsFragment.getSelectedItemPosition()]);
                    objPlayer.setAmountOFDobbles(Integer.parseInt(spAmountOfDobbles_playerSettingsFragment.getSelectedItem().toString()));
                    objPlayer.setPinguïnsUse(swPinguïns_playerSettingsFragment.isChecked());

                    dbHandler.saveChanges(objPlayer);
                    Toast.makeText(getActivity(), "Your settings have been saved.", Toast.LENGTH_SHORT).show();

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
                else {
                    Toast.makeText(getActivity(), "You have yet to unlock this level, please choose a level that you already have unlocked", Toast.LENGTH_LONG).show();
                }
            }
        });

        ibtnRevert_playerSettingsFragment.setImageResource(R.drawable.ic_menu_revert);
        btnSaveChanges_playerSettingsFragment.setText("Save Changes");

        return view;
    }
}
