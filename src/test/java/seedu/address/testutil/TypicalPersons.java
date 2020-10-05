package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withSpecies("123, Jurong West Ave 6, #08-111")
            .withId("94351253")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withSpecies("311, Clementi Ave 2, #02-25")
            .withId("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withId("95352563")
            .withSpecies("wall street").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withId("87652533")
            .withSpecies("10th street").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withId("9482224")
            .withSpecies("michegan ave").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withId("9482427")
            .withSpecies("little tokyo").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withId("9482442")
            .withSpecies("4th street").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withId("8482424")
            .withSpecies("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withId("8482131")
            .withSpecies("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withId(VALID_ID_AMY)
            .withSpecies(VALID_SPECIES_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withId(VALID_ID_BOB)
            .withSpecies(VALID_SPECIES_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
