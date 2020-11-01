package seedu.zookeep.logic.commands;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import seedu.zookeep.commons.util.CollectionUtil;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.feedtime.FeedTimeComparator;
import seedu.zookeep.model.medicalcondition.MedicalCondition;



/**
 * Stores the details to edit the animal with. Each non-empty field value will replace the
 * corresponding field value of the animal.
 */
public class EditAnimalDescriptor {
    private Name name;
    private Id id;
    private Species species;
    private Set<MedicalCondition> medicalConditions;
    private Set<FeedTime> feedTimes;

    public EditAnimalDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code medicalConditions} is used internally.
     */
    public EditAnimalDescriptor(seedu.zookeep.logic.commands.EditAnimalDescriptor toCopy) {
        setName(toCopy.name);
        setId(toCopy.id);
        setSpecies(toCopy.species);
        setMedicalConditions(toCopy.medicalConditions);
        setFeedTimes(toCopy.feedTimes);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, id, species, medicalConditions, feedTimes);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Optional<Id> getId() {
        return Optional.ofNullable(id);
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Optional<Species> getSpecies() {
        return Optional.ofNullable(species);
    }

    /**
     * Sets {@code medicalConditions} to this object's {@code medicalConditions}.
     * A defensive copy of {@code medicalConditions} is used internally.
     */
    public void setMedicalConditions(Set<MedicalCondition> medicalConditions) {
        this.medicalConditions = (medicalConditions != null) ? new HashSet<>(medicalConditions) : null;
    }

    /**
     * Returns an unmodifiable medicalCondition set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code medicalConditions} is null.
     */
    public Optional<Set<MedicalCondition>> getMedicalConditions() {
        return (medicalConditions != null) ? Optional.of(Collections.unmodifiableSet(medicalConditions))
                : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.zookeep.logic.commands.EditAnimalDescriptor)) {
            return false;
        }

        // state check
        seedu.zookeep.logic.commands.EditAnimalDescriptor e = (seedu.zookeep.logic.commands.EditAnimalDescriptor) other;

        return getName().equals(e.getName())
                && getId().equals(e.getId())
                && getSpecies().equals(e.getSpecies())
                && getMedicalConditions().equals(e.getMedicalConditions())
                && getFeedTimes().equals(e.getFeedTimes());
    }

    /**
     * Sets {@code feedTimes} to this object's {@code feedTimes}.
     * A defensive copy of {@code feedTimes} is used internally.
     */
    public void setFeedTimes(Set<FeedTime> feedTimes) {
        if (feedTimes != null) {
            TreeSet<FeedTime> treeFeedTimes = new TreeSet<>(new FeedTimeComparator());
            treeFeedTimes.addAll(feedTimes);
            this.feedTimes = treeFeedTimes;
        } else {
            this.feedTimes = null;
        }
    }

    /**
     * Returns an unmodifiable feedTimes set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code feedTimes} is null.
     */
    public Optional<Set<FeedTime>> getFeedTimes() {
        return (feedTimes != null) ? Optional.of(Collections.unmodifiableSet(feedTimes))
                : Optional.empty();
    }
}
