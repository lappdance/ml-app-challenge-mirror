package com.lappdance.mlappchallenge;

import android.content.ComponentName;
import android.content.Intent;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SplashActivityTest {

    private ActivityController<SplashActivity> mController;

    @Before
    public void setUp() {
        mController = Robolectric.buildActivity(SplashActivity.class);
    }

    @Test
    public void onCreate_loadsView() {
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        assertThat("no open button!",
                openButton, is(notNullValue()));
    }

    @Test
    public void openButton_clicked_opensAccountActivity() {
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        ShadowActivity shadowActivity = shadowOf(mController.get());
        final Intent intent = shadowActivity.getNextStartedActivity();
        assertThat("no Activity started on button press",
                intent, is(notNullValue()));

        final ComponentName name = intent.getComponent();
        assertThat("no Activity specified in intent",
                name, is(notNullValue()));
        assertThat("wrong Activity started",
                name.getClassName(), is(AccountActivity.class.getName()));
    }

    @Test
    public void openButton_clicked_finishesActivity() {
        mController.setup();

        final Button openButton = mController.get().findViewById(R.id.open);
        openButton.performClick();

        assertThat(mController.get().isFinishing(), is(true));
    }
}
