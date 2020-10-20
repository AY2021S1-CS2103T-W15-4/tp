package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEED_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_CONDITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ANIMALS;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Id;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Species;
import seedu.address.model.feedtime.FeedTime;
import seedu.address.model.medicalcondition.MedicalCondition;



/**
 * Edits the details of an existing animal in the zookeep book.
 */
public class ReplaceCommand extends Command {

    public static final String COMMAND_WORD = "replace";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Replaces the details of the animal identified "
            + "by it's ID.\n"
            + "Parameters: Animal ID "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ID + "ID] "
            + "[" + PREFIX_SPECIES + "SPECIES] "
            + "[" + PREFIX_MEDICAL_CONDITION + "MEDICAL_CONDITION] "
            + "[" + PREFIX_FEED_TIME + "FEED_TIME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ID + "91234567 ";

    public static final String MESSAGE_REPLACE_ANIMAL_SUCCESS = "Replaced Animal Details: %1$s";
    public static final String MESSAGE_NOT_REPLACED = "At least one field to replace must be provided.";
    public static final String MESSAGE_DUPLICATE_ANIMAL = "This animal ID already exists in the zookeep book.";

    private final Id id;
    private final EditAnimalDescriptor editAnimalDescriptor;

    /**
     * @param id of the animal in the filtered animal list to edit
     * @param editAnimalDescriptor details to edit the animal with
     */
    public ReplaceCommand(Id id, EditAnimalDescriptor editAnimalDescriptor) {
        requireNonNull(id);
        requireNonNull(editAnimalDescriptor);

        this.id = id;
        this.editAnimalDescriptor = new EditAnimalDescriptor(editAnimalDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Animal animalToEdit = model.getAnimal(this.id).orElseThrow( () ->
            new CommandException(Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID)
        );

        Animal editedAnimal = createEditedAnimal(animalToEdit, editAnimalDescriptor);

        if (!animalToEdit.isSameAnimal(editedAnimal) && model.hasAnimal(editedAnimal)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIMAL);
        }

        model.setAnimal(animalToEdit, editedAnimal);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(String.format(MESSAGE_REPLACE_ANIMAL_SUCCESS, editedAnimal));
    }

    /**
     * Creates and returns a {@code Animal} with the details of {@code animalToEdit}
     * edited with {@code editAnimalDescriptor}.
     */
    private static Animal createEditedAnimal(Animal animalToEdit, EditAnimalDescriptor editAnimalDescriptor) {
        assert animalToEdit != null;

        Name updatedName = editAnimalDescriptor.getName().orElse(animalToEdit.getName());
        Id updatedId = editAnimalDescriptor.getId().orElse(animalToEdit.getId());
        Species updatedSpecies = editAnimalDescriptor.getSpecies().orElse(animalToEdit.getSpecies());
        Set<MedicalCondition> updatedMedicalConditions = editAnimalDescriptor.getMedicalConditions()
                .orElse(animalToEdit.getMedicalConditions());
        Set<FeedTime> updatedFeedTimes = editAnimalDescriptor.getFeedTimes()
                .orElse(animalToEdit.getFeedTimes());

        return new Animal(updatedName, updatedId, updatedSpecies, updatedMedicalConditions, updatedFeedTimes);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReplaceCommand)) {
            return false;
        }

        // state check
        ReplaceCommand e = (ReplaceCommand) other;
        return id.equals(e.id)
                && editAnimalDescriptor.equals(e.editAnimalDescriptor);
    }

}
