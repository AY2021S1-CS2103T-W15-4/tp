package seedu.zookeep.model.animal;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.zookeep.commons.util.StringUtil;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;

/**
 * Tests that any of an {@code Animal}'s fields matches any of the keywords given.
 */
public class AnimalContainsKeywordsPredicate implements Predicate<Animal> {
    private final List<String> keywords;

    public AnimalContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if the keyword matches any of the strings in an animal's feed times.
     * @param feedTimes A set containing all of the animal's feed times.
     * @param word A given keyword.
     * @return The boolean that checks if the keyword matches.
     */
    public boolean containsFeedTime(Set<FeedTime> feedTimes, String word) {
        if (!FeedTime.isValidFeedTime(word)) {
            return false;
        }

        String concat = "";
        for (FeedTime time : feedTimes) {
            concat += time.feedTime + " ";
        }
        return StringUtil.containsWordIgnoreCase(concat, word);
    }

    /**
     * Checks if the keyword matches any of the strings in an animal's medical conditions
     * @param medicalConditions A set containing all of the animal's medical conditions.
     * @param word A given keyword.
     * @return The boolean that checks if the keyword matches.
     */
    public boolean containsMedicalCondition(Set<MedicalCondition> medicalConditions, String word) {
        String concat = "";
        for (MedicalCondition condition : medicalConditions) {
            concat += condition.medicalConditionName + " ";
        }
        return StringUtil.containsWordIgnoreCase(concat, word);
    }

    @Override
    public boolean test(Animal animal) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(animal.getName().fullName, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(animal.getId().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(animal.getSpecies().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> containsFeedTime(animal.getFeedTimes(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> containsMedicalCondition(animal.getMedicalConditions(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnimalContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AnimalContainsKeywordsPredicate) other).keywords)); // state check
    }

}
