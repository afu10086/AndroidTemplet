package com.lidaofu.android.mode;

import java.util.ArrayList;

/**
 * Created by LiDaofu on 16/7/11.
 */
public class SingleArray  extends ArrayList{

    private static SingleArray instance;

    private SingleArray(){

    }

    public static  SingleArray singleArrayInstance(){
        if(instance==null){
            instance=new SingleArray();
        }
        return instance;
    }

}
