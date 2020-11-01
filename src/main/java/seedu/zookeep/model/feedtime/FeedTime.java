package seedu.zookeep.model.feedtime;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.commons.util.AppUtil.checkArgument;

/**
 * Represents a FeedTime in the ZooKeep Book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidFeedTime(String)}
 */
public class FeedTime implements Comparable<FeedTime> {
    public static final String MESSAGE_CONSTRAINTS =
            "Feed times should be in the format HHmm, and it should not be blank";
    public static final String VALIDATION_REGEX = "[0-2][0-9][0-5][0-9]";
    public final String feedTime;

    /**
     * Constructs a {@code FeedTime}.
     *
     * @param feedTimeText A valid feeding time.
     */
    public FeedTime(String feedTimeText) {
        requireNonNull(feedTimeText);
        checkArgument(isValidFeedTime(feedTimeText), MESSAGE_CONSTRAINTS);
        this.feedTime = feedTimeText;
    }

    /**
     * Returns true if a given string is a valid feeding time.
     */
    public static boolean isValidFeedTime(String feedTimeText) {
        return feedTimeText.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FeedTime // instanceof handles nulls
                && feedTime.equals(((FeedTime) other).feedTime)); // state check
    }

    @Override
    public int hashCode() {
        return feedTime.hashCode();
    }

    @Override
    public int compareTo(FeedTime ft) {
        return Integer.compare(
                Integer.parseInt(this.feedTime),
                Integer.parseInt(ft.feedTime));
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + feedTime + ']';
    }
}
