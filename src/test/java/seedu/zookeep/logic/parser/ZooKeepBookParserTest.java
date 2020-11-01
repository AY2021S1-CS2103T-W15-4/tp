package seedu.zookeep.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.zookeep.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.zookeep.testutil.Assert.assertThrows;
import static seedu.zookeep.testutil.TypicalAnimals.AHMENG;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.zookeep.logic.commands.AddCommand;
import seedu.zookeep.logic.commands.AppendCommand;
import seedu.zookeep.logic.commands.ClearCommand;
import seedu.zookeep.logic.commands.DeleteCommand;
import seedu.zookeep.logic.commands.EditAnimalDescriptor;
import seedu.zookeep.logic.commands.ExitCommand;
import seedu.zookeep.logic.commands.FindCommand;
import seedu.zookeep.logic.commands.HelpCommand;
import seedu.zookeep.logic.commands.ListCommand;
import seedu.zookeep.logic.commands.RedoCommand;
import seedu.zookeep.logic.commands.ReplaceCommand;
import seedu.zookeep.logic.commands.SnapCommand;
import seedu.zookeep.logic.commands.SortCommand;
import seedu.zookeep.logic.commands.UndoCommand;
import seedu.zookeep.logic.parser.exceptions.ParseException;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.animal.AnimalComparator;
import seedu.zookeep.model.animal.AnimalContainsKeywordsPredicate;
import seedu.zookeep.model.animal.Id;
import seedu.zookeep.testutil.AnimalBuilder;
import seedu.zookeep.testutil.AnimalUtil;
import seedu.zookeep.testutil.EditAnimalDescriptorBuilder;

public class ZooKeepBookParserTest {

    private final ZooKeepBookParser parser = new ZooKeepBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Animal animal = new AnimalBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(AnimalUtil.getAddCommand(animal));
        assertEquals(new AddCommand(animal), command);
    }

    @Test
    public void parseCommand_append() throws Exception {
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withMedicalConditions("obese").build();
        AppendCommand command = (AppendCommand) parser.parseCommand(AppendCommand.COMMAND_WORD + " "
                + AHMENG.getId().value + " " + AnimalUtil.getEditAnimalDescriptorDetails(descriptor));
        assertEquals(new AppendCommand(AHMENG.getId(), descriptor), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + "1234");
        assertEquals(new DeleteCommand(new Id("1234")), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new AnimalContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_replace() throws Exception {
        EditAnimalDescriptor descriptor = new EditAnimalDescriptorBuilder().withMedicalConditions("obese").build();
        ReplaceCommand command = (ReplaceCommand) parser.parseCommand(ReplaceCommand.COMMAND_WORD + " "
                + AHMENG.getId().value + " " + AnimalUtil.getEditAnimalDescriptorDetails(descriptor));
        assertEquals(new ReplaceCommand(AHMENG.getId(), descriptor), command);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        SortCommand command = (SortCommand) parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + "name");
        assertEquals(new SortCommand(AnimalComparator.createAnimalNameComparator()), command);
    }

    @Test
    public void parseCommand_snap() throws Exception {
        String fileName = "zookeepbook_19-10-2020";
        Path savePath = Path.of("data", fileName + SnapCommand.FILE_FORMAT);
        SnapCommand command = (SnapCommand) parser.parseCommand(SnapCommand.COMMAND_WORD + " " + fileName);
        assertEquals(new SnapCommand(savePath, fileName), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
