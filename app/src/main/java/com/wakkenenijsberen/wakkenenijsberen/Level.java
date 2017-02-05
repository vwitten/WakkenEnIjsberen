package com.wakkenenijsberen.wakkenenijsberen;

/**
 * Created by anferney on 7-12-2016.
 */
public class Level {
    private int levelID;
    private boolean isUnlocked;
    private int playerID;
    private String omschrijving;

    public Level(boolean _isUnlocked, String _omschrijving, int _playerID){
        this.isUnlocked = _isUnlocked;
        this.omschrijving = _omschrijving;
        this.playerID = _playerID;
    }

    public Level(int _levelID, String _omschrijving, boolean _isUnlocked, int _playerID){
        this.levelID = _levelID;
        this.isUnlocked = _isUnlocked;
        this.omschrijving = _omschrijving;
        this.playerID = _playerID;
    }

    public boolean CheckAnswers(int _wakken, int _ijsberen, int[] thrown){
        int amountWakken = 0;
        int amountPolarBears = 0;
        if(omschrijving.equals("level 1") && isUnlocked){
                int amountDices = thrown.length;

                //Wakken
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 1 | thrown[x] == 3 | thrown[x] == 5){
                        amountWakken++;
                    }
                }
                //Polar Bears
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 3 | thrown[x] == 5){
                        amountPolarBears += thrown[x]-1;
                    }
                }
            if(amountPolarBears == _ijsberen && amountWakken == _wakken){
                return true;
            }
            else{
                return false;
            }
        }
        else if(omschrijving.equals("level 2") && isUnlocked){
                int amountDices = thrown.length;

                //Wakken
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 4 | thrown[x] == 5 | thrown[x] == 6){
                        amountWakken++;
                    }
                }
                //Polar bears
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 2 | thrown[x] == 4 | thrown[x] == 6){
                        amountPolarBears += thrown[x]-(thrown[x]/2);
                    }
                }
            if(amountPolarBears == _ijsberen && amountWakken == _wakken){
                return true;
            }
            else{
                return false;
            }
        }
        else if(omschrijving.equals("level 3") && isUnlocked){
                int amountDices = thrown.length;

                //Wakken
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] != 1){
                        amountWakken++;
                    }
                }
                //polar bears
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 2 | thrown[x] == 3){
                        amountPolarBears++;
                    }
                    else if (thrown[x] == 4 | thrown[x] == 5 | thrown[x] == 6){
                        amountPolarBears += 2;
                    }
                }
            if(amountPolarBears == _ijsberen && amountWakken == _wakken){
                return true;
            }
            else{
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean CheckAnswers(int _wakken, int _ijsberen, int _pinguins, int[] thrown){
        int amountWakken = 0;
        int amountPolarBears = 0;
        int amountPinguins = 0;
        if(omschrijving.equals("level 1") && isUnlocked){
                int amountDices = thrown.length;

                //Wakken
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 1 | thrown[x] == 3 | thrown[x] == 5){
                        amountWakken++;
                    }
                }
                //Polar Bears
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 3 | thrown[x] == 5){
                        amountPolarBears += thrown[x]-1;
                    }
                }
                //Pinguins
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 1 | thrown[x] == 3 | thrown[x] == 5){
                        amountPinguins += 7-thrown[x];
                    }
                }
            if(amountPolarBears == _ijsberen && amountWakken == _wakken && amountPinguins == _pinguins){
                return true;
            }
            else{
                return false;
            }
        }
        else if(omschrijving.equals("level 2") && isUnlocked){
                int amountDices = thrown.length;

                //Wakken
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 4 | thrown[x] == 5 | thrown[x] == 6){
                        amountWakken++;
                    }
                }
                //Polar bears
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 2 | thrown[x] == 4 | thrown[x] == 6){
                        amountPolarBears += thrown[x]-(thrown[x]/2);
                    }
                }
                //Pinguins
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 1 | thrown[x] == 2 | thrown[x] == 4 | thrown[x] == 6){
                        amountPinguins += 7-thrown[x];
                    }
                }
            if(amountPolarBears == _ijsberen && amountWakken == _wakken && amountPinguins == _pinguins){
                return true;
            }
            else{
                return false;
            }
        }
        else if(omschrijving.equals("level 3") && isUnlocked){
                int amountDices = thrown.length;

                //Wakken
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] != 1){
                        amountWakken++;
                    }
                }
                //polar bears
                for(int x = 0; x < amountDices; x++){
                    if(thrown[x] == 2 | thrown[x] == 3){
                        amountPolarBears++;
                    }
                    else if (thrown[x] == 4 | thrown[x] == 5 | thrown[x] == 6){
                        amountPolarBears += 2;
                    }
                }
                //Pinguins
                for(int x = 0; x < amountDices; x++){
                    amountPinguins += 7-thrown[x];
                }
        }
        if(amountPolarBears == _ijsberen && amountWakken == _wakken && amountPinguins == _pinguins){
            return true;
        }
        else{
            return false;
        }
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public boolean getIsUnlocked() {
        return isUnlocked;
    }

    public void setIsUnlocked(boolean isUnlocked) {
        this.isUnlocked = isUnlocked;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public int getPlayerID(){
        return playerID;
    }

    public void setPlayerID(int _playerID){
        this.playerID = _playerID;
    }
}
