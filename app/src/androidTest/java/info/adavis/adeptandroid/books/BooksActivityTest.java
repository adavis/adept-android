package info.adavis.adeptandroid.books;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import info.adavis.adeptandroid.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class BooksActivityTest {

    @Rule
    public ActivityTestRule<BooksActivity> booksActivityTestRule =
            new ActivityTestRule<>(BooksActivity.class);

    @Test
    public void listIsPresent() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

}
