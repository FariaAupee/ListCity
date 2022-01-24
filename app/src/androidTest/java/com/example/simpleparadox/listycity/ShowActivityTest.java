package com.example.simpleparadox.listycity;
import android.app.Activity;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/**
 * Test class for MainActivity. All the UI tests are written here. Robotium test framework is
 used
 */
@RunWith(AndroidJUnit4.class)
public class ShowActivityTest{
    private Solo solo;
    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }

    @Test
    public void SwitchActivity(){
        ListView lst = (ListView) solo.getView(R.id.city_list);
        View view = lst.getChildAt(4);
        solo.clickOnView(view);
        solo.assertCurrentActivity("Activity didn't switch",ShowActivity.class);

    }
    @Test
    public void CheckNameConsistency(){
        ListView lst = (ListView) solo.getView(R.id.city_list);
        View view = lst.getChildAt(4);
        solo.clickOnView(view);
        TextView txt = (TextView) solo.getView(R.id.textView);
        String cityname = txt.getText().toString();
        assertEquals("Berlin",cityname);
    }
    @Test
    public void CheckButton(){
        ListView lst = (ListView) solo.getView(R.id.city_list);
        View view = lst.getChildAt(4);
        solo.clickOnView(view);
        solo.waitForActivity(ShowActivity.class);
        Button button = (Button) solo.getView(R.id.button);
        solo.clickOnView(button);
        solo.assertCurrentActivity("Activity didn't switch",MainActivity.class);
    }
    /**
     * Close activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception{
        solo.waitForActivity("ShowActivityTest",1500);
        solo.finishOpenedActivities();
    }
}
