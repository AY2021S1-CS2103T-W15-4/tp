package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.TypicalAnimals;

public class SnapCommandTest {
    private static final String FILE_FORMAT = ".json";

    @Test
    public void constructor_nullFileName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SnapCommand(null));
    }

    @Test
    public void execute_validFileName_success() throws Exception {
        ModelStub modelStub = new ModelStub();
        String validFileName = "zookeepbook_19-10-2020";

        CommandResult commandResult = new SnapCommand(validFileName).execute(modelStub);

        assertEquals(String.format(SnapCommand.MESSAGE_SUCCESS, validFileName + FILE_FORMAT),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        final String fileName = "zookeepbook_19-10-2020";
        SnapCommand snapCommand = new SnapCommand(fileName);

        // same object -> returns true
        assertTrue(snapCommand.equals(snapCommand));

        // same values -> returns true
        SnapCommand snapCommandCopy = new SnapCommand(fileName);
        assertTrue(snapCommand.equals(snapCommandCopy));

        // different file names -> returns false
        SnapCommand snapCommandWithDifferentFileName = new SnapCommand(fileName + "x");
        assertFalse(snapCommand.equals(snapCommandWithDifferentFileName));

        // different types -> returns false
        assertFalse(snapCommand.equals(1));

        // null -> returns false
        assertFalse(snapCommand.equals(null));
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            UserPrefs userPrefs = new UserPrefs();
            userPrefs.setGuiSettings(new GuiSettings(1000, 500, 300, 100));
            userPrefs.setZooKeepBookFilePath(Paths.get("zookeepbook.json"));
            return userPrefs;
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getZooKeepBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setZooKeepBookFilePath(Path zooKeepBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAnimal(Animal animal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setZooKeepBook(ReadOnlyZooKeepBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyZooKeepBook getZooKeepBook() {
            return TypicalAnimals.getTypicalZooKeepBook();
        }

        @Override
        public boolean hasAnimal(Animal animal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAnimal(Animal target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAnimal(Animal target, Animal editedAnimal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Animal> getFilteredAnimalList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAnimalList(Predicate<Animal> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }
}
