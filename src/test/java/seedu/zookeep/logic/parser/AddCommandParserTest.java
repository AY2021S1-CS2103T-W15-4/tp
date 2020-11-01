package seedu.zookeep.logic.parser;

import static seedu.zookeep.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zookeep.logic.commands.CommandTestUtil.FEED_TIME_DESC_EVENING;
import static seedu.zookeep.logic.commands.CommandTestUtil.FEED_TIME_DESC_MORNING;
import static seedu.zookeep.logic.commands.CommandTestUtil.ID_DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.ID_DESC_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.INVALID_FEED_TIME_DESC;
import static seedu.zookeep.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.zookeep.logic.commands.CommandTestUtil.INVALID_MEDICAL_CONDITION_DESC;
import static seedu.zookeep.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.zookeep.logic.commands.CommandTestUtil.INVALID_SPECIES_DESC;
import static seedu.zookeep.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_ARTHRITIS;
import static seedu.zookeep.logic.commands.CommandTestUtil.MEDICAL_CONDITION_DESC_OBESE;
import static seedu.zookeep.logic.commands.CommandTestUtil.NAME_DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.NAME_DESC_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.zookeep.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.zookeep.logic.commands.CommandTestUtil.SPECIES_DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.SPECIES_DESC_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_EVENING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_MORNING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_OBESE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;
import static seedu.zookeep.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.zookeep.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.zookeep.testutil.TypicalAnimals.ARCHIE;
import static seedu.zookeep.testutil.TypicalAnimals.BAILEY;

import org.junit.jupiter.api.Test;

import seedu.zookeep.logic.commands.AddCommand;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;
import seedu.zookeep.testutil.AnimalBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Animal expectedAnimal = new AnimalBuilder(BAILEY).withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE)
                .withFeedTimes(VALID_FEED_TIME_EVENING).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BAILEY + ID_DESC_BAILEY
                + SPECIES_DESC_BAILEY + MEDICAL_CONDITION_DESC_OBESE
                + FEED_TIME_DESC_EVENING, new AddCommand(expectedAnimal));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_ARCHIE + NAME_DESC_BAILEY + ID_DESC_BAILEY
                + SPECIES_DESC_BAILEY + MEDICAL_CONDITION_DESC_OBESE
                + FEED_TIME_DESC_EVENING, new AddCommand(expectedAnimal));

        // multiple ids - last id accepted
        assertParseSuccess(parser, NAME_DESC_BAILEY + ID_DESC_ARCHIE + ID_DESC_BAILEY
                + SPECIES_DESC_BAILEY + MEDICAL_CONDITION_DESC_OBESE
                + FEED_TIME_DESC_EVENING, new AddCommand(expectedAnimal));

        // multiple species - last species accepted
        assertParseSuccess(parser, NAME_DESC_BAILEY + ID_DESC_BAILEY + SPECIES_DESC_ARCHIE
                + SPECIES_DESC_BAILEY + MEDICAL_CONDITION_DESC_OBESE
                + FEED_TIME_DESC_EVENING, new AddCommand(expectedAnimal));

        // multiple medicalConditions - all accepted
        Animal expectedAnimalMultipleMedicalConditions = new AnimalBuilder(BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE, VALID_MEDICAL_CONDITION_ARTHRITIS)
                .withFeedTimes(VALID_FEED_TIME_EVENING)
                .build();

        assertParseSuccess(parser, NAME_DESC_BAILEY + ID_DESC_BAILEY + SPECIES_DESC_BAILEY
                + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS + FEED_TIME_DESC_EVENING,
                new AddCommand(expectedAnimalMultipleMedicalConditions));

        // multiple feedTimes - all accepted
        Animal expectedAnimalMultipleFeedTimes = new AnimalBuilder(BAILEY)
                .withMedicalConditions(VALID_MEDICAL_CONDITION_OBESE)
                .withFeedTimes(VALID_FEED_TIME_MORNING, VALID_FEED_TIME_EVENING)
                .build();

        assertParseSuccess(parser, NAME_DESC_BAILEY + ID_DESC_BAILEY + SPECIES_DESC_BAILEY
                + MEDICAL_CONDITION_DESC_OBESE + FEED_TIME_DESC_MORNING + FEED_TIME_DESC_EVENING,
                new AddCommand(expectedAnimalMultipleFeedTimes));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero medicalConditions and feedTimes
        Animal expectedAnimal = new AnimalBuilder(ARCHIE).withMedicalConditions().withFeedTimes().build();
        assertParseSuccess(parser, NAME_DESC_ARCHIE + ID_DESC_ARCHIE + SPECIES_DESC_ARCHIE,
                new AddCommand(expectedAnimal));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BAILEY + ID_DESC_BAILEY + SPECIES_DESC_BAILEY,
                expectedMessage);

        // missing id prefix
        assertParseFailure(parser, NAME_DESC_BAILEY + VALID_ID_BAILEY + SPECIES_DESC_BAILEY,
                expectedMessage);

        // missing species prefix
        assertParseFailure(parser, NAME_DESC_BAILEY + ID_DESC_BAILEY + VALID_SPECIES_BAILEY,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BAILEY + VALID_ID_BAILEY + VALID_SPECIES_BAILEY,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BAILEY + SPECIES_DESC_BAILEY
                + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS
                + FEED_TIME_DESC_MORNING + FEED_TIME_DESC_EVENING, Name.MESSAGE_CONSTRAINTS);

        // invalid id
        assertParseFailure(parser, NAME_DESC_BAILEY + INVALID_ID_DESC + SPECIES_DESC_BAILEY
                + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS
                + FEED_TIME_DESC_MORNING + FEED_TIME_DESC_EVENING, Id.MESSAGE_CONSTRAINTS);

        // invalid species
        assertParseFailure(parser, NAME_DESC_BAILEY + ID_DESC_BAILEY + INVALID_SPECIES_DESC
                + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS
                + FEED_TIME_DESC_MORNING + FEED_TIME_DESC_EVENING, Species.MESSAGE_CONSTRAINTS);

        // invalid medicalCondition
        assertParseFailure(parser, NAME_DESC_BAILEY + ID_DESC_BAILEY + SPECIES_DESC_BAILEY
                + INVALID_MEDICAL_CONDITION_DESC + VALID_MEDICAL_CONDITION_OBESE
                + FEED_TIME_DESC_MORNING + FEED_TIME_DESC_EVENING, MedicalCondition.MESSAGE_CONSTRAINTS);

        // invalid feedTime
        assertParseFailure(parser, NAME_DESC_BAILEY + ID_DESC_BAILEY + SPECIES_DESC_BAILEY
                + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS
                + INVALID_FEED_TIME_DESC + FEED_TIME_DESC_EVENING, FeedTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + ID_DESC_BAILEY + INVALID_SPECIES_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BAILEY + ID_DESC_BAILEY
                + SPECIES_DESC_BAILEY + MEDICAL_CONDITION_DESC_OBESE + MEDICAL_CONDITION_DESC_ARTHRITIS
                + FEED_TIME_DESC_MORNING + FEED_TIME_DESC_EVENING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
