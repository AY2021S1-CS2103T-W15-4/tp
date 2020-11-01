package seedu.zookeep.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_MORNING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;
import static seedu.zookeep.testutil.Assert.assertThrows;
import static seedu.zookeep.testutil.TypicalAnimals.AHMENG;
import static seedu.zookeep.testutil.TypicalAnimals.BAILEY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.zookeep.model.animal.exceptions.AnimalNotFoundException;
import seedu.zookeep.model.animal.exceptions.DuplicateAnimalException;
import seedu.zookeep.testutil.AnimalBuilder;

public class UniqueAnimalListTest {

    private final UniqueAnimalList uniqueAnimalList = new UniqueAnimalList();

    @Test
    public void contains_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.contains(null));
    }

    @Test
    public void contains_animalNotInList_returnsFalse() {
        assertFalse(uniqueAnimalList.contains(AHMENG));
    }

    @Test
    public void contains_animalInList_returnsTrue() {
        uniqueAnimalList.add(AHMENG);
        assertTrue(uniqueAnimalList.contains(AHMENG));
    }

    @Test
    public void contains_animalWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAnimalList.add(AHMENG);
        Animal editedAhmeng = new AnimalBuilder(AHMENG).withSpecies(VALID_SPECIES_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS)
                .withFeedTimes(VALID_FEED_TIME_MORNING)
                .build();
        assertTrue(uniqueAnimalList.contains(editedAhmeng));
    }

    @Test
    public void add_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.add(null));
    }

    @Test
    public void add_duplicateAnimal_throwsDuplicateAnimalException() {
        uniqueAnimalList.add(AHMENG);
        assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.add(AHMENG));
    }

    @Test
    public void setAnimal_nullTargetAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimal(null, AHMENG));
    }

    @Test
    public void setAnimal_nullEditedAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimal(AHMENG, null));
    }

    @Test
    public void setAnimal_targetAnimalNotInList_throwsAnimalNotFoundException() {
        assertThrows(AnimalNotFoundException.class, () -> uniqueAnimalList.setAnimal(AHMENG, AHMENG));
    }

    @Test
    public void setAnimal_editedAnimalIsSameAnimal_success() {
        uniqueAnimalList.add(AHMENG);
        uniqueAnimalList.setAnimal(AHMENG, AHMENG);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(AHMENG);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimal_editedAnimalHasSameIdentity_success() {
        uniqueAnimalList.add(AHMENG);
        Animal editedAhmeng = new AnimalBuilder(AHMENG).withSpecies(VALID_SPECIES_BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS)
                .withFeedTimes(VALID_FEED_TIME_MORNING)
                .build();
        uniqueAnimalList.setAnimal(AHMENG, editedAhmeng);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(editedAhmeng);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimal_editedAnimalHasDifferentIdentity_success() {
        uniqueAnimalList.add(AHMENG);
        uniqueAnimalList.setAnimal(AHMENG, BAILEY);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(BAILEY);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimal_editedAnimalHasNonUniqueIdentity_throwsDuplicateAnimalException() {
        uniqueAnimalList.add(AHMENG);
        uniqueAnimalList.add(BAILEY);
        assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.setAnimal(AHMENG, BAILEY));
    }

    @Test
    public void remove_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.remove(null));
    }

    @Test
    public void remove_animalDoesNotExist_throwsAnimalNotFoundException() {
        assertThrows(AnimalNotFoundException.class, () -> uniqueAnimalList.remove(AHMENG));
    }

    @Test
    public void remove_existingAnimal_removesAnimal() {
        uniqueAnimalList.add(AHMENG);
        uniqueAnimalList.remove(AHMENG);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimals_nullUniqueAnimalList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimals((UniqueAnimalList) null));
    }

    @Test
    public void setAnimals_uniqueAnimalList_replacesOwnListWithProvidedUniqueAnimalList() {
        uniqueAnimalList.add(AHMENG);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(BAILEY);
        uniqueAnimalList.setAnimals(expectedUniqueAnimalList);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimals_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimals((List<Animal>) null));
    }

    @Test
    public void setAnimals_list_replacesOwnListWithProvidedList() {
        uniqueAnimalList.add(AHMENG);
        List<Animal> animalList = Collections.singletonList(BAILEY);
        uniqueAnimalList.setAnimals(animalList);
        UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
        expectedUniqueAnimalList.add(BAILEY);
        assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
    }

    @Test
    public void setAnimals_listWithDuplicateAnimals_throwsDuplicateAnimalException() {
        List<Animal> listWithDuplicateAnimals = Arrays.asList(AHMENG, AHMENG);
        assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.setAnimals(listWithDuplicateAnimals));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAnimalList.asUnmodifiableObservableList().remove(0));
    }
}
