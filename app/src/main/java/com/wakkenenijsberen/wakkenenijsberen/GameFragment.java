package com.wakkenenijsberen.wakkenenijsberen;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringDef;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

/**
 * Created by vwitt on 8-12-2016.
 */

public class GameFragment extends Fragment {

    private Player objPlayer;
    private int[] dobbleNumbers;
    private int amountOFCorrects = 0;
    private int amountOFWrongs = 0;
    private boolean canThrow = true;
    private boolean canCheck = false;

    private Level objLevel1;
    private Level objLevel2;
    private Level objLevel3;

    private boolean isTimerOn = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, container, false);

        final Bundle args = this.getArguments();

        objPlayer = (Player) args.getSerializable("Name");

        final DBHandler dbHandler = new DBHandler(getActivity(), null, null, 1);

        final Level level = dbHandler.GetLevel(objPlayer.getPlayerID(), objPlayer.getCurrentLevel().toString());

        ImageButton ibtnRevert_gameFragment = (ImageButton) view.findViewById(R.id.ibtnRevert_gameFragment);
        final ImageView ivDobbel1_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel1_gameFragment);
        final ImageView ivDobbel2_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel2_gameFragment);
        final ImageView ivDobbel3_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel3_gameFragment);
        final ImageView ivDobbel4_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel4_gameFragment);
        final ImageView ivDobbel5_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel5_gameFragment);
        final ImageView ivDobbel6_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel6_gameFragment);
        final ImageView ivDobbel7_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel7_gameFragment);
        final ImageView ivDobbel8_gameFragment = (ImageView) view.findViewById(R.id.ivDobbel8_gameFragment);
        final EditText edWakken_gamefragment = (EditText) view.findViewById(R.id.edWakken_gamefragment);
        final EditText edPolarBears_gamefragment = (EditText) view.findViewById(R.id.edPolarBears_gamefragment);
        final EditText edPinguïns_gamefragment = (EditText) view.findViewById(R.id.edPinguïns_gamefragment);
        Button btnThrow_gamefragment = (Button) view.findViewById(R.id.btnThrow_gamefragment);
        Button btnCheckAnswer_gamefragment = (Button) view.findViewById(R.id.btnCheckAnswer_gamefragment);
        final TextView tvLevel_gameFragment = (TextView) view.findViewById(R.id.tvLevel_gameFragment);
        final Chronometer chTimer_gameFragment = (Chronometer) view.findViewById(R.id.chTimer_gameFragment);
        final TextView tvWrong_gameFragment = (TextView) view.findViewById(R.id.tvWrong_gameFragment);
        final TextView tvCorrect_gameFragment = (TextView) view.findViewById(R.id.tvCorrect_gameFragment);

        if (objPlayer.getPinguïnsUse() != true) {
            edPinguïns_gamefragment.setEnabled(false);
        }

        tvLevel_gameFragment.setText(objPlayer.getCurrentLevel());

        ibtnRevert_gameFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (args != null) {
                    objPlayer = (Player) args.getSerializable("Name");
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
                } else {
                    android.app.FragmentTransaction ft;
                    ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    ft.replace(R.id.activity_menu, new StartFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        btnThrow_gamefragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canThrow == true) {
                    Dice diceThrow = new Dice();
                    dobbleNumbers = diceThrow.Throw(objPlayer.getAmountOfDobbles());

                    if(isTimerOn){
                        chTimer_gameFragment.setBase(SystemClock.elapsedRealtime());
                        chTimer_gameFragment.start();
                        isTimerOn = false;
                    }

                    //image 1
                    if (dobbleNumbers.length >= 1) {
                        if (dobbleNumbers[0] == 1) {
                            ivDobbel1_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[0] == 2) {
                            ivDobbel1_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[0] == 3) {
                            ivDobbel1_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[0] == 4) {
                            ivDobbel1_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[0] == 5) {
                            ivDobbel1_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[0] == 6) {
                            ivDobbel1_gameFragment.setImageResource(R.drawable.d6);
                        }

                        ivDobbel2_gameFragment.setImageDrawable(null);
                        ivDobbel3_gameFragment.setImageDrawable(null);
                        ivDobbel4_gameFragment.setImageDrawable(null);
                        ivDobbel5_gameFragment.setImageDrawable(null);
                        ivDobbel6_gameFragment.setImageDrawable(null);
                        ivDobbel7_gameFragment.setImageDrawable(null);
                        ivDobbel8_gameFragment.setImageDrawable(null);
                    }

                    //image 2
                    if (dobbleNumbers.length >= 2) {
                        if (dobbleNumbers[1] == 1) {
                            ivDobbel2_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[1] == 2) {
                            ivDobbel2_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[1] == 3) {
                            ivDobbel2_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[1] == 4) {
                            ivDobbel2_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[1] == 5) {
                            ivDobbel2_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[1] == 6) {
                            ivDobbel2_gameFragment.setImageResource(R.drawable.d6);
                        }

                        ivDobbel3_gameFragment.setImageDrawable(null);
                        ivDobbel4_gameFragment.setImageDrawable(null);
                        ivDobbel5_gameFragment.setImageDrawable(null);
                        ivDobbel6_gameFragment.setImageDrawable(null);
                        ivDobbel7_gameFragment.setImageDrawable(null);
                        ivDobbel8_gameFragment.setImageDrawable(null);
                    }


                    //image 3
                    if (dobbleNumbers.length >= 3) {
                        if (dobbleNumbers[2] == 1) {
                            ivDobbel3_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[2] == 2) {
                            ivDobbel3_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[2] == 3) {
                            ivDobbel3_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[2] == 4) {
                            ivDobbel3_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[2] == 5) {
                            ivDobbel3_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[2] == 6) {
                            ivDobbel3_gameFragment.setImageResource(R.drawable.d6);
                        }

                        ivDobbel4_gameFragment.setImageDrawable(null);
                        ivDobbel5_gameFragment.setImageDrawable(null);
                        ivDobbel6_gameFragment.setImageDrawable(null);
                        ivDobbel7_gameFragment.setImageDrawable(null);
                        ivDobbel8_gameFragment.setImageDrawable(null);
                    }

                    //image 4
                    if (dobbleNumbers.length >= 4) {
                        if (dobbleNumbers[3] == 1) {
                            ivDobbel4_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[3] == 2) {
                            ivDobbel4_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[3] == 3) {
                            ivDobbel4_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[3] == 4) {
                            ivDobbel4_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[3] == 5) {
                            ivDobbel4_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[3] == 6) {
                            ivDobbel4_gameFragment.setImageResource(R.drawable.d6);
                        }

                        ivDobbel5_gameFragment.setImageDrawable(null);
                        ivDobbel6_gameFragment.setImageDrawable(null);
                        ivDobbel7_gameFragment.setImageDrawable(null);
                        ivDobbel8_gameFragment.setImageDrawable(null);
                    }

                    //image 5
                    if (dobbleNumbers.length >= 5) {
                        if (dobbleNumbers[4] == 1) {
                            ivDobbel5_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[4] == 2) {
                            ivDobbel5_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[4] == 3) {
                            ivDobbel5_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[4] == 4) {
                            ivDobbel5_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[4] == 5) {
                            ivDobbel5_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[4] == 6) {
                            ivDobbel5_gameFragment.setImageResource(R.drawable.d6);
                        }

                        ivDobbel6_gameFragment.setImageDrawable(null);
                        ivDobbel7_gameFragment.setImageDrawable(null);
                        ivDobbel8_gameFragment.setImageDrawable(null);
                    }

                    //image 6
                    if (dobbleNumbers.length >= 6) {
                        if (dobbleNumbers[5] == 1) {
                            ivDobbel6_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[5] == 2) {
                            ivDobbel6_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[5] == 3) {
                            ivDobbel6_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[5] == 4) {
                            ivDobbel6_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[5] == 5) {
                            ivDobbel6_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[5] == 6) {
                            ivDobbel6_gameFragment.setImageResource(R.drawable.d6);
                        }

                        ivDobbel7_gameFragment.setImageDrawable(null);
                        ivDobbel8_gameFragment.setImageDrawable(null);
                    }

                    //image 7
                    if (dobbleNumbers.length >= 7) {
                        if (dobbleNumbers[6] == 1) {
                            ivDobbel7_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[6] == 2) {
                            ivDobbel7_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[6] == 3) {
                            ivDobbel7_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[6] == 4) {
                            ivDobbel7_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[6] == 5) {
                            ivDobbel7_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[6] == 6) {
                            ivDobbel7_gameFragment.setImageResource(R.drawable.d6);
                        }

                        ivDobbel8_gameFragment.setImageDrawable(null);
                    }

                    //image 8
                    if (dobbleNumbers.length >= 8) {
                        if (dobbleNumbers[7] == 1) {
                            ivDobbel8_gameFragment.setImageResource(R.drawable.d1);
                        } else if (dobbleNumbers[7] == 2) {
                            ivDobbel8_gameFragment.setImageResource(R.drawable.d2);
                        } else if (dobbleNumbers[7] == 3) {
                            ivDobbel8_gameFragment.setImageResource(R.drawable.d3);
                        } else if (dobbleNumbers[7] == 4) {
                            ivDobbel8_gameFragment.setImageResource(R.drawable.d4);
                        } else if (dobbleNumbers[7] == 5) {
                            ivDobbel8_gameFragment.setImageResource(R.drawable.d5);
                        } else if (dobbleNumbers[7] == 6) {
                            ivDobbel8_gameFragment.setImageResource(R.drawable.d6);
                        }
                    }

                    canThrow = false;
                    canCheck = true;
                }
            }
        });

        ibtnRevert_gameFragment.setImageResource(R.drawable.ic_menu_revert);

        ivDobbel1_gameFragment.setImageResource(R.drawable.d1);
        ivDobbel2_gameFragment.setImageResource(R.drawable.d1);
        ivDobbel3_gameFragment.setImageResource(R.drawable.d1);
        ivDobbel4_gameFragment.setImageResource(R.drawable.d1);
        ivDobbel5_gameFragment.setImageResource(R.drawable.d1);
        ivDobbel6_gameFragment.setImageResource(R.drawable.d1);
        ivDobbel7_gameFragment.setImageResource(R.drawable.d1);
        ivDobbel8_gameFragment.setImageResource(R.drawable.d1);


        //hier word er gecheck of je het level hebt gehaald
        btnCheckAnswer_gamefragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canCheck) {
                    if (objPlayer.getPinguïnsUse()) {
                        if (!TextUtils.isEmpty(edWakken_gamefragment.getText()) && !TextUtils.isEmpty(edPolarBears_gamefragment.getText()) && !TextUtils.isEmpty(edPinguïns_gamefragment.getText())) {
                            if (level.CheckAnswers(Integer.parseInt(edWakken_gamefragment.getText().toString()),
                                    Integer.parseInt(edPolarBears_gamefragment.getText().toString()),
                                    Integer.parseInt(edPinguïns_gamefragment.getText().toString()), dobbleNumbers)) {
                                amountOFCorrects++;

                                if (amountOFCorrects == 1)
                                    Toast.makeText(getActivity(), "Your answers are correct get it right two more times without mistakes and proceed to the next level", Toast.LENGTH_LONG).show();

                                if (amountOFCorrects == 2)
                                    Toast.makeText(getActivity(), "Your answers are correct get it right one more times without mistakes and proceed to the next level", Toast.LENGTH_LONG).show();

                                if (amountOFCorrects == 3) {
                                    objLevel1 = dbHandler.GetLevel(objPlayer.getPlayerID(), "level 1");
                                    objLevel2 = dbHandler.GetLevel(objPlayer.getPlayerID(), "level 2");
                                    objLevel3 = dbHandler.GetLevel(objPlayer.getPlayerID(), "level 3");
                                    if (objLevel1.getIsUnlocked()) {
                                        dbHandler.unlockLevel(objPlayer.getPlayerID(), "level 2");
                                        Toast.makeText(getActivity(), "You have successfully cleared this level!, You will now proceed to the next one.", Toast.LENGTH_LONG).show();

                                        addScoreP();

                                        objPlayer.setCurrentLevel("level 2");
                                        tvLevel_gameFragment.setText(objPlayer.getCurrentLevel());
                                    } else if (objLevel2.getIsUnlocked()) {
                                        dbHandler.unlockLevel(objPlayer.getPlayerID(), "level 3");
                                        Toast.makeText(getActivity(), "You have successfully cleared this level!, You will now proceed to the next one.", Toast.LENGTH_LONG).show();

                                        addScoreP();

                                        objPlayer.setCurrentLevel("level 3");
                                        tvLevel_gameFragment.setText(objPlayer.getCurrentLevel());
                                    } else if (objLevel3.getIsUnlocked()) {
                                        Toast.makeText(getActivity(), "You have successfully cleared the game! Well done.", Toast.LENGTH_LONG).show();

                                        addScoreP();
                                    }

                                    chTimer_gameFragment.stop();
                                    isTimerOn = true;

                                    amountOFCorrects = 0;
                                    amountOFWrongs = 0;
                                }
                            } else {
                                amountOFCorrects = 0;
                                amountOFWrongs++;
                                Toast.makeText(getActivity(), "Oops you quest wrong! better luck next time", Toast.LENGTH_LONG).show();

                                if (amountOFWrongs == 5 && objPlayer.getCurrentLevel().equals("level 1")) {
                                    Toast.makeText(getActivity(), R.string.stringHint1, Toast.LENGTH_LONG).show();
                                } else if (amountOFWrongs == 5 && objPlayer.getCurrentLevel().equals("level 2")) {
                                    Toast.makeText(getActivity(), R.string.stringHint2, Toast.LENGTH_LONG).show();
                                } else if (amountOFWrongs == 5 && objPlayer.getCurrentLevel().equals("level 3")) {
                                    Toast.makeText(getActivity(), R.string.stringHint3, Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(getActivity(), "make sure you fill in the textboxs", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (!TextUtils.isEmpty(edWakken_gamefragment.getText()) && !TextUtils.isEmpty(edPolarBears_gamefragment.getText())) {
                            if (level.CheckAnswers(Integer.parseInt(edWakken_gamefragment.getText().toString()),
                                    Integer.parseInt(edPolarBears_gamefragment.getText().toString()), dobbleNumbers)) {
                                amountOFCorrects++;

                                if (amountOFCorrects == 1)
                                    Toast.makeText(getActivity(), "Your answers are correct get it right two more times without mistakes and proceed to the next level", Toast.LENGTH_LONG).show();

                                if (amountOFCorrects == 2)
                                    Toast.makeText(getActivity(), "Your answers are correct get it right one more times without mistakes and proceed to the next level", Toast.LENGTH_LONG).show();

                                if (amountOFCorrects == 3) {
                                    objLevel1 = dbHandler.GetLevel(objPlayer.getPlayerID(), "level 1");
                                    objLevel2 = dbHandler.GetLevel(objPlayer.getPlayerID(), "level 2");
                                    objLevel3 = dbHandler.GetLevel(objPlayer.getPlayerID(), "level 3");
                                    if (objLevel1.getIsUnlocked()) {
                                        dbHandler.unlockLevel(objPlayer.getPlayerID(), "level 2");
                                        Toast.makeText(getActivity(), "You have successfully cleared this level!, You will now proceed to the next one.", Toast.LENGTH_LONG).show();

                                        addScore();

                                        objPlayer.setCurrentLevel("level 2");
                                        tvLevel_gameFragment.setText(objPlayer.getCurrentLevel());
                                    } else if (objLevel2.getIsUnlocked()) {
                                        dbHandler.unlockLevel(objPlayer.getPlayerID(), "level 3");
                                        Toast.makeText(getActivity(), "You have successfully cleared this level!, You will now proceed to the next one.", Toast.LENGTH_LONG).show();

                                        addScore();

                                        objPlayer.setCurrentLevel("level 3");
                                        tvLevel_gameFragment.setText(objPlayer.getCurrentLevel());
                                    } else if (objLevel3.getIsUnlocked()) {
                                        Toast.makeText(getActivity(), "You have successfully cleared the game! Well done.", Toast.LENGTH_LONG).show();

                                        addScore();
                                    }

                                    chTimer_gameFragment.stop();
                                    isTimerOn = true;

                                    amountOFCorrects = 0;
                                    amountOFWrongs = 0;
                                }
                            } else {
                                amountOFCorrects = 0;
                                amountOFWrongs++;
                                Toast.makeText(getActivity(), "Oops you quest wrong! better luck next time", Toast.LENGTH_LONG).show();

                                if (amountOFWrongs >= 5 && objPlayer.getCurrentLevel().equals("level 1")) {
                                    Toast.makeText(getActivity(), R.string.stringHint1, Toast.LENGTH_LONG).show();
                                } else if (amountOFWrongs >= 5 && objPlayer.getCurrentLevel().equals("level 2")) {
                                    Toast.makeText(getActivity(), R.string.stringHint2, Toast.LENGTH_LONG).show();
                                } else if (amountOFWrongs >= 5 && objPlayer.getCurrentLevel().equals("level 3")) {
                                    Toast.makeText(getActivity(), R.string.stringHint3, Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(getActivity(), "make sure you fill in the textboxs", Toast.LENGTH_SHORT).show();
                        }
                    }

                    edPinguïns_gamefragment.setText(null);
                    edPolarBears_gamefragment.setText(null);
                    edWakken_gamefragment.setText(null);

                    canThrow = true;
                    canCheck = false;
                    tvWrong_gameFragment.setText(String.valueOf(amountOFWrongs));
                    tvCorrect_gameFragment.setText(String.valueOf(amountOFCorrects));
                }
            }

            public void addScoreP(){
                long elapsedMillis = SystemClock.elapsedRealtime() - chTimer_gameFragment.getBase();
                Time objTime = new Time(elapsedMillis);

                int duration = (objTime.getMinutes() * 60) + objTime.getSeconds();

                //double scorePoints = ((duration / objPlayer.getAmountOfDobbles()) * (amountOFWrongs * 0.2)) * 0.75;
                double scorePoints = (duration / (objPlayer.getAmountOfDobbles() + amountOFWrongs)) * 0.75;

                int level = Integer.parseInt(objPlayer.getCurrentLevel().substring(6));

                Score objScore = new Score(scorePoints, amountOFWrongs, objTime, objPlayer.getAmountOfDobbles(),
                        level, objPlayer.getPinguïnsUse(), objPlayer.getPlayerID());

                dbHandler.makeScore(objScore);
            }

            public void addScore(){
                long elapsedMillis = SystemClock.elapsedRealtime() - chTimer_gameFragment.getBase();
                Time objTime = new Time(elapsedMillis);

                int duration = (objTime.getMinutes() * 60) + objTime.getSeconds();

                //double scorePoints = (duration / objPlayer.getAmountOfDobbles()) * (amountOFWrongs * 0.2);
                double scorePoints = (duration / (objPlayer.getAmountOfDobbles() + amountOFWrongs));

                int level = Integer.parseInt(objPlayer.getCurrentLevel().substring(6));

                Score objScore = new Score(scorePoints, amountOFWrongs, objTime, objPlayer.getAmountOfDobbles(),
                        level, objPlayer.getPinguïnsUse(), objPlayer.getPlayerID());

                dbHandler.makeScore(objScore);
            }
        });
        return view;
    }
}