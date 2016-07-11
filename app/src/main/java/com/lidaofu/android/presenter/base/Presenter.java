package com.lidaofu.android.presenter.base;

/**
 * Created by LiDaofu on 16/7/11.
 *
 */
public class Presenter<V>{

    public V view;

    public Presenter(V view){
        this.view=view;
    }

    public void resume() {
    }

    public void pause() {
    }

    public void stop() {
    }

    public void destroy() {
        this.view=null;
    }

    public void onError(String message) {
    }



}
