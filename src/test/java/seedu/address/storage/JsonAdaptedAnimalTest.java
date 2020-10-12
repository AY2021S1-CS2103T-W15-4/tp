package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAnimal.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;

public class JsonAdaptedAnimalTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ID = "+651234";
    private static final String INVALID_SPECIES = " ";
    private static final String INVALID_MEDICAL_CONDITION = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_SPECIES = BENSON.getSpecies().toString();
    private static final List<JsonAdaptedMedicalCondition> VALID_MEDICAL_CONDITIONS =
            BENSON.getMedicalConditions().stream()
            .map(JsonAdaptedMedicalCondition::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAnimalDetails_returnsAnimal() throws Exception {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(BENSON);
        assertEquals(BENSON, animal.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(INVALID_NAME, VALID_ID, VALID_SPECIES, VALID_MEDICAL_CONDITIONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(null, VALID_ID, VALID_SPECIES, VALID_MEDICAL_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, INVALID_ID, VALID_SPECIES, VALID_MEDICAL_CONDITIONS);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(VALID_NAME, null, VALID_SPECIES, VALID_MEDICAL_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, INVALID_SPECIES, VALID_MEDICAL_CONDITIONS);
        String expectedMessage = Species.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_nullSpecies_throwsIllegalValueException() {
        JsonAdaptedAnimal animal = new JsonAdaptedAnimal(VALID_NAME, VALID_ID, null, VALID_MEDICAL_CONDITIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Species.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, animal::toModelType);
    }

    @Test
    public void toModelType_invalidMedicalConditions_throwsIllegalValueException() {
        List<JsonAdaptedMedicalCondition> invalidMedicalConditions = new ArrayList<>(VALID_MEDICAL_CONDITIONS);
        invalidMedicalConditions.add(new JsonAdaptedMedicalCondition(INVALID_MEDICAL_CONDITION));
        JsonAdaptedAnimal animal =
                new JsonAdaptedAnimal(VALID_NAME, VALID_ID, VALID_SPECIES, invalidMedicalConditions);
        assertThrows(IllegalValueException.class, animal::toModelType);
    }

}