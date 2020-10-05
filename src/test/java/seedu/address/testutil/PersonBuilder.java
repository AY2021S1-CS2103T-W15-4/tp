package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Species;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_ID = "85355255";
    public static final String DEFAULT_SPECIES = "Panthera leo";

    private Name name;
    private Id id;
    private Species species;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new Id(DEFAULT_ID);
        species = new Species(DEFAULT_SPECIES);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        id = personToCopy.getId();
        species = personToCopy.getSpecies();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Person} that we are building.
     */
    public PersonBuilder withSpecies(String species) {
        this.species = new Species(species);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    public Person build() {
        return new Person(name, id, species, tags);
    }

}
