import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import com.example.myapplication.R;

import com.example.myapplication.activity.WelcomeGuideActivity;
import com.example.myapplication.data.Lesson;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class)
public class Alltest {
    @Rule
    public ActivityTestRule<WelcomeGuideActivity> rule = new ActivityTestRule<>(WelcomeGuideActivity.class);

    @Test
    public void testall() {
        onView(withId(R.id.btn_skip)).perform(click());
        onView(withId(R.id.editText))
                .perform(
                        clearText(),
                        closeSoftKeyboard()
                );
        onView(withId(R.id.editText2))
                .perform(
                        clearText(),
                        closeSoftKeyboard()
                );
        onView(withId(R.id.SignupButton)).perform(click());
        Random rand= new Random();
        String username =(rand.nextInt(99999999 - 10000000 + 1) + 10000000)+"";
        String pass =(rand.nextInt(999999999 - 100000000 + 1) + 100000000)+"";
        onView(withId(R.id.editText)).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.editText2)).perform(typeText(pass),closeSoftKeyboard());
        onView(withId(R.id.SignupButton)).perform(click());
        onView(withId(R.id.editText))
                .perform(
                        clearText(),
                        typeText(username),
                        closeSoftKeyboard()
                );
        onView(withId(R.id.editText2))
                .perform(
                        clearText(),
                        typeText(pass),
                        closeSoftKeyboard()
                );
        onView(withId(R.id.LoginButton)).perform(click());
        onData(hasToString(startsWith("数据结构")))
                .perform(click());
        onView(withId(R.id.navigation_notifications)).perform(click());
        onView(withId(R.id.aListView)).perform(RecyclerViewActions.actionOnItemAtPosition(4,click()));
        onView(withId(R.id.banner)).perform(swipeLeft());
        onView(withId(R.id.banner)).perform(swipeRight());
    }
}
