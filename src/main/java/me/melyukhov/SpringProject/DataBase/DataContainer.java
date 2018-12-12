package me.melyukhov.SpringProject.DataBase;

import java.util.ArrayList;

public class DataContainer
{
    private static DataContainer instance;
    public ArrayList<Data> recipes;

    private DataContainer(){
        this.recipes = new ArrayList<>();
    }

    public static DataContainer getInstance() {
        if(instance == null)
            instance = new DataContainer();
        return instance;
    }
}

