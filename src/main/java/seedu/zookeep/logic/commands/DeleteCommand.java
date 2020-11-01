package seedu.zookeep.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.zookeep.model.Model.PREDICATE_SHOW_ALL_ANIMALS;

import java.util.List;

import seedu.zookeep.commons.core.Messages;
import seedu.zookeep.logic.commands.exceptions.CommandException;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.Id;

/**
 * Deletes an animal identified using it's displayed index from the zookeep book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the animal identified by the ID in the displayed animal list.\n"
            + "Parameters: ID (must be a positive integer > 3 digits)\n"
            + "Example: " + COMMAND_WORD + " 123";

    public static final String MESSAGE_DELETE_ANIMAL_SUCCESS = "Deleted Animal\n%1$s";

    private final Id targetID;

    public DeleteCommand(Id targetID) {
        this.targetID = targetID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Animal> animalList = model.getZooKeepBook().getAnimalList();
        Animal animalToDelete = null;
        for (Animal animal : animalList) {
            if (animal.getId().equals(targetID)) {
                animalToDelete = animal;
                break;
            }
        }

        if (animalToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIMAL_DISPLAYED_ID);
        }
        model.deleteAnimal(animalToDelete);
        model.updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return new CommandResult(String.format(MESSAGE_DELETE_ANIMAL_SUCCESS, animalToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetID.equals(((DeleteCommand) other).targetID)); // state check
    }
}
