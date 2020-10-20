package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.HistoryStack;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyZooKeepBook;
import seedu.address.model.UpdateStack;
import seedu.address.model.ZooKeepBook;
import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;


public class RedoCommandTest {

    @Test
    public void execute_redoSuccessful() throws Exception {
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        UpdateStack updateStack = UpdateStack.getUpdateStack();
        historyStack.clearHistory();
        updateStack.clearUpdates();

        ZooKeepBook bookA = new ZooKeepBook();
        bookA.addAnimal(new AnimalBuilder().withName("Bob").build());

        ZooKeepBook bookB = new ZooKeepBook();
        bookB.addAnimal(new AnimalBuilder().withName("Bob").build());
        bookB.addAnimal(new AnimalBuilder().withName("Tom").withId("1234").build());

        historyStack.addToHistory(bookA);
        historyStack.addToHistory(bookB);

        ModelStub modelStub = new ModelStub();

        CommandResult testResult = new UndoCommand().execute(modelStub);
        CommandResult commandResult = new RedoCommand().execute(modelStub);

        assertEquals(RedoCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(updateStack.getSize(), 0);
    }

    @Test
    public void equals() {
        HistoryStack historyStack = HistoryStack.getHistoryStack();
        historyStack.clearHistory();
        ZooKeepBook book = new ZooKeepBook();
        book.addAnimal(new AnimalBuilder().withName("Bob").build());
        historyStack.addToHistory(book);

        RedoCommand redoCommand = new RedoCommand();

        // same object -> returns true
        assertTrue(redoCommand.equals(redoCommand));

        // same values -> returns true
        RedoCommand redoCommandCopy = new RedoCommand();
        assertTrue(redoCommand.equals(redoCommandCopy));

        // different types -> returns false
        assertFalse(redoCommand.equals(1));

        // null -> returns false
        assertFalse(redoCommand.equals(null));

    }

    private class ModelStub implements Model {

        private ZooKeepBook zooKeepBook = new ZooKeepBook();

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
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
        public void setZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) {
            this.zooKeepBook.resetData(zooKeepBook);
        }

        @Override
        public ReadOnlyZooKeepBook getZooKeepBook() {
            throw new AssertionError("This method should not be called.");
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
