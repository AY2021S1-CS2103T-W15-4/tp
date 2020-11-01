package seedu.zookeep.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zookeep.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.zookeep.logic.commands.CommandTestUtil.ID_DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.NAME_DESC_ARCHIE;
import static seedu.zookeep.logic.commands.CommandTestUtil.SPECIES_DESC_ARCHIE;
import static seedu.zookeep.testutil.Assert.assertThrows;
import static seedu.zookeep.testutil.TypicalAnimals.ARCHIE;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.zookeep.logic.commands.AddCommand;
import seedu.zookeep.logic.commands.CommandResult;
import seedu.zookeep.logic.commands.ListCommand;
import seedu.zookeep.logic.commands.exceptions.CommandException;
import seedu.zookeep.logic.parser.exceptions.ParseException;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.ModelManager;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.UserPrefs;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.storage.JsonUserPrefsStorage;
import seedu.zookeep.storage.JsonZooKeepBookStorage;
import seedu.zookeep.storage.StorageManager;
import seedu.zookeep.testutil.AnimalBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonZooKeepBookStorage zooKeepBookStorage =
                new JsonZooKeepBookStorage(temporaryFolder.resolve("zooKeepBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(zooKeepBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonZooKeepBookIoExceptionThrowingStub
        JsonZooKeepBookStorage zooKeepBookStorage =
                new JsonZooKeepBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionZooKeepBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(zooKeepBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_ARCHIE + ID_DESC_ARCHIE
                + SPECIES_DESC_ARCHIE;
        Animal expectedAnimal = new AnimalBuilder(ARCHIE).withMedicalConditions().withFeedTimes().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addAnimal(expectedAnimal);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredAnimalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredAnimalList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getZooKeepBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonZooKeepBookIoExceptionThrowingStub extends JsonZooKeepBookStorage {
        private JsonZooKeepBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
