package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.AHMENG;
import static seedu.address.testutil.TypicalAnimals.getTypicalZooKeepBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.exceptions.DuplicateAnimalException;
import seedu.address.testutil.AnimalBuilder;

public class ZooKeepBookTest {

    private final ZooKeepBook zooKeepBook = new ZooKeepBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), zooKeepBook.getAnimalList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> zooKeepBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyZooKeepBook_replacesData() {
        ZooKeepBook newData = getTypicalZooKeepBook();
        zooKeepBook.resetData(newData);
        assertEquals(newData, zooKeepBook);
    }

    @Test
    public void resetData_withDuplicateAnimals_throwsDuplicateAnimalException() {
        // Two animals with the same identity fields
        Animal editedAlice = new AnimalBuilder(AHMENG).withSpecies(VALID_SPECIES_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS)
                .build();
        List<Animal> newAnimals = Arrays.asList(AHMENG, editedAlice);
        ZooKeepBookStub newData = new ZooKeepBookStub(newAnimals);

        assertThrows(DuplicateAnimalException.class, () -> zooKeepBook.resetData(newData));
    }

    @Test
    public void hasAnimal_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> zooKeepBook.hasAnimal(null));
    }

    @Test
    public void hasAnimal_animalNotInZooKeepBook_returnsFalse() {
        assertFalse(zooKeepBook.hasAnimal(AHMENG));
    }

    @Test
    public void hasAnimal_animalInZooKeepBook_returnsTrue() {
        zooKeepBook.addAnimal(AHMENG);
        assertTrue(zooKeepBook.hasAnimal(AHMENG));
    }

    @Test
    public void hasAnimal_animalWithSameIdentityFieldsInZooKeepBook_returnsTrue() {
        zooKeepBook.addAnimal(AHMENG);
        Animal editedAlice = new AnimalBuilder(AHMENG).withSpecies(VALID_SPECIES_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS)
                .build();
        assertTrue(zooKeepBook.hasAnimal(editedAlice));
    }

    @Test
    public void getAnimalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> zooKeepBook.getAnimalList().remove(0));
    }

    /**
     * A stub ReadOnlyZooKeepBook whose animals list can violate interface constraints.
     */
    private static class ZooKeepBookStub implements ReadOnlyZooKeepBook {
        private final ObservableList<Animal> animals = FXCollections.observableArrayList();

        ZooKeepBookStub(Collection<Animal> animals) {
            this.animals.setAll(animals);
        }

        @Override
        public ObservableList<Animal> getAnimalList() {
            return animals;
        }
    }

}
