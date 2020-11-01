package seedu.zookeep.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.logic.commands.CommandTestUtil.DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.DESC_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_EVENING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_OBESE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;

import org.junit.jupiter.api.Test;

import seedu.zookeep.testutil.EditAnimalDescriptorBuilder;

public class EditAnimalDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAnimalDescriptor descriptorWithSameValues = new EditAnimalDescriptor(DESC_ARCHIE);
        assertTrue(DESC_ARCHIE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ARCHIE.equals(DESC_ARCHIE));

        // null -> returns false
        assertFalse(DESC_ARCHIE.equals(null));

        // different types -> returns false
        assertFalse(DESC_ARCHIE.equals(5));

        // different values -> returns false
        assertFalse(DESC_ARCHIE.equals(DESC_BAILEY));

        // different name -> returns false
        EditAnimalDescriptor editedArchie = new EditAnimalDescriptorBuilder(DESC_ARCHIE)
                .withName(VALID_NAME_BAILEY).build();
        assertFalse(DESC_ARCHIE.equals(editedArchie));

        // different ID -> returns false
        editedArchie = new EditAnimalDescriptorBuilder(DESC_ARCHIE).withId(VALID_ID_BAILEY).build();
        assertFalse(DESC_ARCHIE.equals(editedArchie));

        // different species -> returns false
        editedArchie = new EditAnimalDescriptorBuilder(DESC_ARCHIE).withSpecies(VALID_SPECIES_BAILEY).build();
        assertFalse(DESC_ARCHIE.equals(editedArchie));

        // different medicalConditions -> returns false
        editedArchie = new EditAnimalDescriptorBuilder(DESC_ARCHIE)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE).build();
        assertFalse(DESC_ARCHIE.equals(editedArchie));

        // different feedTimes -> returns false
        editedArchie = new EditAnimalDescriptorBuilder(DESC_ARCHIE)
                .withFeedTimes(VALID_FEED_TIME_EVENING).build();
        assertFalse(DESC_ARCHIE.equals(editedArchie));
    }
}
