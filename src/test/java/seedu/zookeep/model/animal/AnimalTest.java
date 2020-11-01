package seedu.zookeep.model.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_EVENING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_OBESE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;
import static seedu.zookeep.testutil.Assert.assertThrows;
import static seedu.zookeep.testutil.TypicalAnimals.ARCHIE;
import static seedu.zookeep.testutil.TypicalAnimals.BAILEY;

import org.junit.jupiter.api.Test;

import seedu.zookeep.testutil.AnimalBuilder;

public class AnimalTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Animal animal = new AnimalBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> animal.getMedicalConditions().remove(0));
    }

    @Test
    public void isSameAnimal() {
        // same object -> returns true
        assertTrue(ARCHIE.isSameAnimal(ARCHIE));

        // null -> returns false
        assertFalse(ARCHIE.isSameAnimal(null));

        // different ID and species -> returns false
        Animal editedArchie = new AnimalBuilder(ARCHIE).withId(VALID_ID_BAILEY)
                .withSpecies(VALID_SPECIES_BAILEY).build();
        assertFalse(ARCHIE.isSameAnimal(editedArchie));

        // different name -> returns true
        editedArchie = new AnimalBuilder(ARCHIE).withName(VALID_NAME_BAILEY).build();
        assertTrue(ARCHIE.isSameAnimal(editedArchie));

        // same name, same ID, different attributes (medical conditions and feed times) -> returns true
        editedArchie = new AnimalBuilder(ARCHIE).withSpecies(VALID_SPECIES_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE)
                .withFeedTimes(VALID_FEED_TIME_EVENING).build();
        assertTrue(ARCHIE.isSameAnimal(editedArchie));

        // same name, different ID, same attributes (medical conditions and feed times) -> returns false
        editedArchie = new AnimalBuilder(ARCHIE).withId(VALID_ID_BAILEY).build();
        assertFalse(ARCHIE.isSameAnimal(editedArchie));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Animal archieCopy = new AnimalBuilder(ARCHIE).build();
        assertTrue(ARCHIE.equals(archieCopy));

        // same object -> returns true
        assertTrue(ARCHIE.equals(ARCHIE));

        // null -> returns false
        assertFalse(ARCHIE.equals(null));

        // different type -> returns false
        assertFalse(ARCHIE.equals(5));

        // different animal -> returns false
        assertFalse(ARCHIE.equals(BAILEY));

        // different name -> returns false
        Animal editedArchie = new AnimalBuilder(ARCHIE).withName(VALID_NAME_BAILEY).build();
        assertFalse(ARCHIE.equals(editedArchie));

        // different ID -> returns false
        editedArchie = new AnimalBuilder(ARCHIE).withId(VALID_ID_BAILEY).build();
        assertFalse(ARCHIE.equals(editedArchie));

        // different species -> returns false
        editedArchie = new AnimalBuilder(ARCHIE).withSpecies(VALID_SPECIES_BAILEY).build();
        assertFalse(ARCHIE.equals(editedArchie));

        // different medicalConditions -> returns false
        editedArchie = new AnimalBuilder(ARCHIE).withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE).build();
        assertFalse(ARCHIE.equals(editedArchie));

        // different feedTimes -> returns false
        editedArchie = new AnimalBuilder(ARCHIE).withFeedTimes(VALID_FEED_TIME_EVENING).build();
        assertFalse(ARCHIE.equals(editedArchie));
    }
}
