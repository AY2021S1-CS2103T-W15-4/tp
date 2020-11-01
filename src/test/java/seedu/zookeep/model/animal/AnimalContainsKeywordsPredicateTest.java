package seedu.zookeep.model.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.zookeep.testutil.AnimalBuilder;

public class AnimalContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AnimalContainsKeywordsPredicate firstPredicate = new AnimalContainsKeywordsPredicate(firstPredicateKeywordList);
        AnimalContainsKeywordsPredicate secondPredicate =
                new AnimalContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AnimalContainsKeywordsPredicate firstPredicateCopy =
                new AnimalContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different animal -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_animalContainsKeywords_returnsTrue() {
        // One keyword
        AnimalContainsKeywordsPredicate predicate = new AnimalContainsKeywordsPredicate(
                Collections.singletonList("Ahmeng"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));

        // Multiple keywords
        predicate = new AnimalContainsKeywordsPredicate(Arrays.asList("Ahmeng", "Buttercup"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));

        // Multiple keywords which match in different fields
        predicate = new AnimalContainsKeywordsPredicate(Arrays.asList("123", "Reticulated", "Python"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Buttercup").withId("123")
                .withSpecies("Reticulated Python").build()));

        // Only one matching keyword
        predicate = new AnimalContainsKeywordsPredicate(Arrays.asList("Buttercup", "Coco"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Buttercup Coco").build()));

        // Mixed-case keywords
        predicate = new AnimalContainsKeywordsPredicate(Arrays.asList("ahMeNg", "buTTercup"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AnimalContainsKeywordsPredicate predicate = new AnimalContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AnimalBuilder().withName("Ahmeng").build()));

        // Non-matching keyword
        predicate = new AnimalContainsKeywordsPredicate(Arrays.asList("Coco"));
        assertFalse(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));

        // Multiple keywords, with one keyword partially matching
        predicate = new AnimalContainsKeywordsPredicate(Arrays.asList("123", "Reti", "Anaconda"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Buttercup").withId("124")
                .withSpecies("Reticulated Python").build()));

        // Multiple non-matching keywords
        predicate = new AnimalContainsKeywordsPredicate(Arrays.asList("123", "Lopez", "Anaconda"));
        assertFalse(predicate.test(new AnimalBuilder().withName("Buttercup").withId("124")
                .withSpecies("Reticulated Python").build()));
    }
}
