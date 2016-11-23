package com.duast.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.duast.game.actors.Square;

/**
 * Created by alex on 11/19/16.
 */

public class SaveFile {
    enum Fields {THEME, DIFF, TIME, CROSS}
    private FileHandle file;
    private String[] values;

    public SaveFile() {
        file = Gdx.files.local("savefile.txt");
        if(file.exists()) values = file.readString().split("\n");
    }

    public boolean exists() {
        return file.exists();
    }

    public int getTheme() {
        if(values != null) {
            int theme;
            theme = Integer.parseInt(values[Fields.THEME.ordinal()]);

            return theme;
        }
        return C.LIGHT;
    }

    public int getDifficulty() {
        if(values != null) {
            int diff;
            diff = Integer.parseInt(values[Fields.DIFF.ordinal()]);
            return diff;
        }
        return C.EASY;
    }

    public String getTime() {
        if(values != null) {
            return values[Fields.TIME.ordinal()];
        }
        return "00:00";
    }

    public int[][] getCross() {
        if(values != null ) {
            if(!values[Fields.CROSS.ordinal()].equals("null")) {
                int[][] arr;
                String cross = values[Fields.CROSS.ordinal()];
                int l = (int) Math.sqrt(cross.length());
                arr = new int[l][l];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length; j++) {
                        arr[i][j] = Integer.parseInt(String.valueOf(cross.charAt(i * arr.length + (j))));
                    }
                }
                return arr;
            }
            return null;
        }
        return null;
    }

    public void save(int theme, int diff, String time, Square[][] cross) {
        String s = "";
        s += theme + "\n";
        s += diff + "\n";
        s += time + "\n";
        if(cross != null) {
            for (int i = 0; i < cross.length; i++) {
                for (int j = 0; j < cross.length; j++) {
                    if (cross[i][j] == null) s += "0";
                    else if (cross[i][j].getColor().equals(C.RED)) s += "1";
                    else if (cross[i][j].getColor().equals(C.VIOLET)) s += "2";
                    else if (cross[i][j].getColor().equals(C.BLUE)) s += "3";
                    else if (cross[i][j].getColor().equals(C.GREEN)) s += "4";
                    else if (cross[i][j].getColor().equals(C.YELLOW)) s += "5";
                }
            }
        }else{
            s += "null";
        }
        file.writeString(s, false);
    }
}
