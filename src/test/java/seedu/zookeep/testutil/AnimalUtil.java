package seedu.zookeep.testutil;

import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_FEED_TIME;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_SPECIES;

import java.util.Set;

import seedu.zookeep.logic.commands.AddCommand;
import seedu.zookeep.logic.commands.EditAnimalDescriptor;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;


/**
 * A utility class for Animal.
 */
public class AnimalUtil {

    /**
     * Returns an add command string for adding the {@code animal}.
     */
    public static String getAddCommand(Animal animal) {
        return AddCommand.COMMAND_WORD + " " + getAnimalDetails(animal);
    }

    /**
     * Returns the part of command string for the given {@code animal}'s details.
     */
    public static String getAnimalDetails(Animal animal) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + animal.getName().fullName + " ");
        sb.append(PREFIX_ID + animal.getId().value + " ");
        sb.append(PREFIX_SPECIES + animal.getSpecies().value + " ");
        animal.getMedicalConditions().stream().forEach(
            s -> sb.append(PREFIX_MEDICAL_CONDITION + s.medicalConditionName + " ")
        );
        animal.getFeedTimes().stream().forEach(
            s -> sb.append(PREFIX_FEED_TIME + s.feedTime + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAnimalDescriptor}'s details.
     */
    public static String getEditAnimalDescriptorDetails(EditAnimalDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getId().ifPresent(id -> sb.append(PREFIX_ID).append(id.value).append(" "));
        descriptor.getSpecies().ifPresent(species -> sb.append(PREFIX_SPECIES).append(species.value).append(" "));
        if (descriptor.getMedicalConditions().isPresent()) {
            Set<MedicalCondition> medicalConditions = descriptor.getMedicalConditions().get();
            if (medicalConditions.isEmpty()) {
                sb.append(PREFIX_MEDICAL_CONDITION);
            } else {
                medicalConditions.forEach(s -> sb.append(PREFIX_MEDICAL_CONDITION)
                        .append(s.medicalConditionName).append(" "));
            }
        }
        if (descriptor.getFeedTimes().isPresent()) {
            Set<FeedTime> feedTimes = descriptor.getFeedTimes().get();
            if (feedTimes.isEmpty()) {
                sb.append(PREFIX_FEED_TIME);
            } else {
                feedTimes.forEach(s -> sb.append(PREFIX_FEED_TIME)
                        .append(s.feedTime).append(" "));
            }
        }
        return sb.toString();
    }
}
