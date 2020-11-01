package seedu.zookeep.model.feedtime;

import static seedu.zookeep.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FeedTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FeedTime(null));
    }

    @Test
    public void constructor_invalidFeedTimeValue_throwsIllegalArgumentException() {
        String invalidFeedTimeValue = "";
        assertThrows(IllegalArgumentException.class, () -> new FeedTime(invalidFeedTimeValue));
    }

    @Test
    public void isValidFeedTimeValue() {
        // null feedTime value
        assertThrows(NullPointerException.class, () -> FeedTime.isValidFeedTime(null));
    }

}
