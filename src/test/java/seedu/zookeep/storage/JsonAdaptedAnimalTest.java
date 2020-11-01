package seedu.zookeep.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zookeep.storage.JsonAdaptedAnimal.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.zookeep.testutil.Assert.assertThrows;
import static seedu.zookeep.testutil.TypicalAnimals.BUTTERCUP;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.zookeep.commons.exceptions.IllegalValueException;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;

public class JsonAdaptedAnimalTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ID = "+651234";
    private static final String INVALID_SPECIES = " ";
    private static final String INVALID_MEDICAL_CONDITION = "#dead";
    private static final String INVALID_FEED_TIME = "+1200";

    private static final String VALID_NAME = BUTTERCUP.getName().toString();
    private static final String VALID_ID = BUTTERCUP.getId().toString();
    private static final String VALID_SPECIES = BUTTERCUP.getSpecies().toString();
    private static final List<JsonAdaptedMedicalCondition> VALID_MEDICAL_CONDITIONS =
            BUTTERCUP.getMedicalConditions().stream()
            .map(JsonAdaptedMedicalCondition::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedFeedTime> VALID_FEED_TIMES =
            BUTTERCUP.getFeedTimes().stream()
            .map(JsonAdaptedFeedTime::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAnimalDetails_returnsAnimal() throws Exception {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(BUTTERCUP);
        assertEquals(BUTTERCUP, animal.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(INVALID_NAME, VALID_ID, VALID_SPECIES, VALID_MEDICAL_CONDITIONS,
                        VALID_FEED_TIMES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(null, VALID_ID, VALID_SPECIES, VALID_MEDICAL_CONDITIONS,
                VALID_FEED_TIMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, INVALID_ID, VALID_SPECIES, VALID_MEDICAL_CONDITIONS,
                        VALID_FEED_TIMES);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(VALID_NAME, null, VALID_SPECIES, VALID_MEDICAL_CONDITIONS,
                VALID_FEED_TIMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, INVALID_SPECIES, VALID_MEDICAL_CONDITIONS,
                        VALID_FEED_TIMES);
        String expectedMessage = Species.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(VALID_NAME, VALID_ID, null, VALID_MEDICAL_CONDITIONS,
                VALID_FEED_TIMES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidMedicalConditions_throwsIllegalValueException() {
        List<JsonAdaptedMedicalCondition> invalidMedicalConditions = new ArrayList<>(VALID_MEDICAL_CONDITIONS);
        invalidMedicalConditions.add(new JsonAdaptedMedicalCondition(INVALID_MEDICAL_CONDITION));
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, VALID_SPECIES, invalidMedicalConditions, VALID_FEED_TIMES);
        assertThrows(IllegalValueException.class, animal::toModelType);
    }

    @Test
    public void toModelType_invalidFeedTimes_throwsIllegalValueException() {
        List<JsonAdaptedFeedTime> invalidFeedTimes = new ArrayList<>(VALID_FEED_TIMES);
        invalidFeedTimes.add(new JsonAdaptedFeedTime(INVALID_FEED_TIME));
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, VALID_SPECIES, VALID_MEDICAL_CONDITIONS, invalidFeedTimes);
        assertThrows(IllegalValueException.class, animal::toModelType);
    }

}
