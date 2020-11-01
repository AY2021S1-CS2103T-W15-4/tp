package seedu.zookeep.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_FEED_TIME;
import static seedu.zookeep.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.zookeep.model.Model.PREDICATE_SHOW_ALL_ANIMALS;

import java.util.HashSet;
import java.util.Set;

import seedu.zookeep.commons.core.Messages;
import seedu.zookeep.logic.commands.exceptions.CommandException;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.model.animal.Name;
import seedu.zookeep.model.animal.Species;
import seedu.zookeep.model.feedtime.FeedTime;
import seedu.zookeep.model.medicalcondition.MedicalCondition;

/**
 * Appends additional details to the fields of an existing animal in the zookeep book.
 */
public class AppendCommand extends Command {

    public static final String COMMAND_WORD = "append";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Appends information to the details of the animal identified "
            + "by it's ID.\n"
            + "Parameters: Animal ID "
            + "[" + PREFIX_MEDICAL_CONDITION + "MEDICAL_CONDITION] "
            + "[" + PREFIX_FEED_TIME + "FEED_TIME]\n"
            + "Example: " + COMMAND_WORD + " 1234 "
            + PREFIX_FEED_TIME + "1234 ";

    public static final String MESSAGE_APPEND_ANIMAL_SUCCESS = "Appended Animal Details\n%1$s";
    public static final String MESSAGE_NOT_APPENDED = "At least one field to append must be provided.";

    private final Id id;
    private final EditAnimalDescriptor editAnimalDescriptor;

    /**
     * @param id of the animal in the filtered animal list to edit
     * @param editAnimalDescriptor details to edit the animal with
     */
    public AppendCommand(Id id, EditAnimalDescriptor editAnimalDescriptor) {
        requireNonNull(id);
        requireNonNull(editAnimalDescriptor);

        this.id = id;
        this.editAnimalDescriptor = new EditAnimalDescriptor(editAnimalDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Animal animalToEdit = model.getAnimal(this.id).orElseThrow(() ->
            new CommandException(Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID)
        );

        Animal editedAnimal = createEditedAnimal(animalToEdit, editAnimalDescriptor);

        model.setAnimal(animalToEdit, editedAnimal);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(String.format(MESSAGE_APPEND_ANIMAL_SUCCESS, editedAnimal));
    }

    /**
     * Creates and returns an {@code Animal} with the details of {@code animalToEdit}
     * edited with {@code editAnimalDescriptor}.
     */
    private static Animal createEditedAnimal(Animal animalToEdit, EditAnimalDescriptor editAnimalDescriptor) {
        assert animalToEdit != null;

        Name updatedName = animalToEdit.getName();
        Id updatedId = animalToEdit.getId();
        Species updatedSpecies = animalToEdit.getSpecies();
        Set<MedicalCondition> updatedMedicalConditions = editAnimalDescriptor.getMedicalConditions()
                .map(updatedMedical -> {
                    Set<MedicalCondition> combinedMedicalConditions = new HashSet<>();
                    combinedMedicalConditions.addAll(updatedMedical);
                    combinedMedicalConditions.addAll(animalToEdit.getMedicalConditions());
                    return combinedMedicalConditions;
                }).orElse(animalToEdit.getMedicalConditions());
        Set<FeedTime> updatedFeedTimes = editAnimalDescriptor.getFeedTimes()
                .map(updatedFeed -> {
                    Set<FeedTime> combinedFeedTimes = new HashSet<>();
                    combinedFeedTimes.addAll(updatedFeed);
                    combinedFeedTimes.addAll(animalToEdit.getFeedTimes());
                    return combinedFeedTimes;
                }).orElse(animalToEdit.getFeedTimes());

        return new Animal(updatedName, updatedId, updatedSpecies, updatedMedicalConditions, updatedFeedTimes);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppendCommand)) {
            return false;
        }

        // state check
        AppendCommand otherAppendCommand = (AppendCommand) other;
        return id.equals(otherAppendCommand.id)
                && editAnimalDescriptor.equals(otherAppendCommand.editAnimalDescriptor);
    }

}
