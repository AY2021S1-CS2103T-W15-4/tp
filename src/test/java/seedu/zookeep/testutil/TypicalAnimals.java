package seedu.zookeep.testutil;

import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_EVENING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_FEED_TIME_MORNING;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_ID_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_ID_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_ARTHRITIS;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_MEDICAL_CONDITION_OBESE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_NAME_BAILEY;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_SPECIES_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.VALID_SPECIES_BAILEY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.animal.Animal;

/**
 * A utility class containing a list of {@code Animal} objects to be used in tests.
 */
public class TypicalAnimals {

    public static final Animal AHMENG = new AnimalBuilder().withName("Ahmeng")
            .withId("123")
            .withSpecies("Orangutan")
            .withMedicalConditions("Deceased").build();
    public static final Animal BUTTERCUP = new AnimalBuilder().withName("Buttercup")
            .withId("456")
            .withSpecies("Reticulated Python")
            .withMedicalConditions("Skin Infection", "Inclusion Body Disease")
            .withFeedTimes("1300", "1800").build();
    public static final Animal COCO = new AnimalBuilder().withName("Coco")
            .withId("125")
            .withSpecies("Chihuahua")
            .withFeedTimes("1200").build();
    public static final Animal ESTHER = new AnimalBuilder().withName("Esther")
            .withId("126")
            .withSpecies("Pig")
            .withMedicalConditions("Swine Flu").build();
    public static final Animal GRECIA = new AnimalBuilder().withName("Grecia")
            .withId("134")
            .withSpecies("Toucan")
            .withFeedTimes("0600", "1200", "1800").build();
    public static final Animal NEMO = new AnimalBuilder().withName("Nemo")
            .withId("1212")
            .withSpecies("Clownfish")
            .withFeedTimes("0800", "1300", "1900").build();
    public static final Animal PASHA = new AnimalBuilder().withName("Pasha")
            .withId("1234")
            .withSpecies("White Tiger")
            .withMedicalConditions("Obese").build();

    // Manually added
    public static final Animal JIAJIA = new AnimalBuilder().withName("JiaJia")
            .withId("111")
            .withSpecies("Giant Panda").build();
    public static final Animal KAIKAI = new AnimalBuilder().withName("KaiKai")
            .withId("233")
            .withSpecies("Giant Panda").build();

    // Manually added - Animal's details found in {@code CommandTestUtil}
    public static final Animal ARCHIE = new AnimalBuilder().withName(VALID_NAME_ARCHIE).withId(VALID_ID_ARCHIE)
            .withSpecies(VALID_SPECIES_ARCHIE).withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS)
            .withFeedTimes(VALID_FEED_TIME_MORNING).build();
    public static final Animal BAILEY = new AnimalBuilder().withName(VALID_NAME_BAILEY).withId(VALID_ID_BAILEY)
            .withSpecies(VALID_SPECIES_BAILEY)
            .withMedicalConditions(VALID_MEDICAL_CONDITION_ARTHRITIS, VALID_MEDICAL_CONDITION_OBESE)
            .withFeedTimes(VALID_FEED_TIME_MORNING, VALID_FEED_TIME_EVENING).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAnimals() {} // prevents instantiation

    /**
     * Returns an {@code ZooKeepBook} with all the typical animals.
     */
    public static ZooKeepBook getTypicalZooKeepBook() {
        ZooKeepBook ab = new ZooKeepBook();
        for (Animal animal : getTypicalAnimals()) {
            ab.addAnimal(animal);
        }
        return ab;
    }

    public static List<Animal> getTypicalAnimals() {
        return new ArrayList<>(Arrays.asList(AHMENG, BUTTERCUP, COCO, ESTHER, GRECIA, NEMO, PASHA));
    }
}
