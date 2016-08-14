package com.lidaofu.android;

import android.app.Application;
import android.content.Intent;
import android.test.ApplicationTestCase;

import com.lidaofu.android.ui.activity.ListActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testListActivity(){
        Intent intent=new Intent(getContext(),ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }


}