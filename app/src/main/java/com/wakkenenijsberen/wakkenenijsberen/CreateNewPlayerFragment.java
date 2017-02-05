package com.wakkenenijsberen.wakkenenijsberen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * Created by vwitt on 8-12-2016.
 */

public class CreateNewPlayerFragment extends Fragment {

    private Player objPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_new_player_fragment, container, false);

        final DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);
        final Bundle args = this.getArguments();

        Button btnCreatePlayer_createNewPlayerFragment = (Button) view.findViewById(R.id.btnCreatePlayer_createNewPlayerFragment);
        final EditText edPlayerName_createNewPlayerFragment = (EditText) view.findViewById(R.id.edPlayerName_createNewPlayerFragment);
        ImageButton ibtnRevert_createNewPlayerFragment = (ImageButton) view.findViewById(R.id.ibtnRevert_createNewPlayerFragment);
        final Spinner spAmountOfDobbles_createNewPlayerFragment = (Spinner) view.findViewById(R.id.spAmountOfDobbles_createNewPlayerFragment);
        final Switch swPinguïns_createNewPlayerFragment = (Switch) view.findViewById(R.id.swPinguïns_createNewPlayerFragment);

        final String[] Dices = new String[]{"3", "4", "5", "6", "7", "8"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Dices);

        spAmountOfDobbles_createNewPlayerFragment.setAdapter(adapter);

        btnCreatePlayer_createNewPlayerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objPlayer = new Player(edPlayerName_createNewPlayerFragment.getText().toString(),
                        Integer.parseInt(spAmountOfDobbles_createNewPlayerFragment.getSelectedItem().toString()),
                        "level 1", swPinguïns_createNewPlayerFragment.isChecked());

                objPlayer = dbHandler.CreatePlayer(objPlayer);

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

        ibtnRevert_createNewPlayerFragment.setOnClickListener(new View.OnClickListener() {
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

        ibtnRevert_createNewPlayerFragment.setImageResource(R.drawable.ic_menu_revert);
        btnCreatePlayer_createNewPlayerFragment.setText("Create Player");

        return view;
    }
}
