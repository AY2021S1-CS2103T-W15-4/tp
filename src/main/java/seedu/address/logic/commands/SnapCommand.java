package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonZooKeepBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.ZooKeepBookStorage;

/**
 * Saves a snapshot of the current zookeep book with a specified file name.
 */
public class SnapCommand extends Command {
    public static final String COMMAND_WORD = "snap";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Saves a snapshot of the current ZooKeep Book with the specified file name\n"
            + "Parameters: " + COMMAND_WORD + " FILE_NAME\n"
            + "Example: " + COMMAND_WORD + " zookeepbook_19-10-2020";

    public static final String MESSAGE_CONSTRAINTS =
            "File name must contain only alphanumeric characters, \"_\" and \"-\".\n"
            + "File name must not be empty and must be at most 100 characters long.";

    public static final String MESSAGE_SUCCESS = "Current ZooKeep Book saved as %s";

    public static final String MESSAGE_ERROR = "Could not save data to file: ";

    public static final String MESSAGE_WARNING = "File name %s already exists.";

    public static final String FILE_FORMAT = ".json";

    private String fileName;
    private Path savePath;

    /**
     * Creates a SnapCommand to save the current state of the ZooKeep Book to the specified path
     */
    public SnapCommand(Path savePath, String fileName) {
        requireNonNull(savePath);
        requireNonNull(fileName);

        this.savePath = savePath;
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path zooKeepBookFilePath = model.getUserPrefs().getZooKeepBookFilePath();
        ZooKeepBookStorage zooKeepBookStorage = new JsonZooKeepBookStorage(zooKeepBookFilePath);
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(zooKeepBookFilePath);

        try {
            Storage storage = new StorageManager(zooKeepBookStorage, userPrefsStorage);
            ReadOnlyZooKeepBook zooKeepBook = model.getZooKeepBook();
            Path savePath = Path.of("data", fileName);
            assert(zooKeepBook != null);

            storage.saveZooKeepBook(zooKeepBook, savePath);
        } catch (IOException ioe) {
            throw new CommandException(String.format(MESSAGE_ERROR + ioe, ioe));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName + FILE_FORMAT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SnapCommand // instanceof handles nulls
                && fileName.equals(((SnapCommand) other).fileName));
    }
}
