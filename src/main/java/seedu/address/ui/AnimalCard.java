package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.animal.Animal;

/**
 * An UI component that displays information of a {@code Animal}.
 */
public class AnimalCard extends UiPart<Region> {

    private static final String FXML = "AnimalListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Animal animal;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label index;
    @FXML
    private Label identity;
    @FXML
    private Label species;
    @FXML
    private Label medicalCondition;
    @FXML
    private FlowPane medicalConditions;

    /**
     * Creates a {@code AnimalCode} with the given {@code Animal} and index to display.
     */
    public AnimalCard(Animal animal, int displayedIndex) {
        super(FXML);
        this.animal = animal;
        index.setText(displayedIndex + ". ");
        name.setText(animal.getName().fullName);
        identity.setText("ID: " + animal.getId().value);
        species.setText("Species: " + animal.getSpecies().value);
        medicalCondition.setText("Medical details: ");
        animal.getMedicalConditions().stream()
                .sorted(Comparator.comparing(medicalCondition -> medicalCondition.medicalConditionName))
                .forEach(medicalCondition -> medicalConditions.getChildren()
                        .add(new Label(medicalCondition.medicalConditionName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnimalCard)) {
            return false;
        }

        // state check
        AnimalCard card = (AnimalCard) other;
        return index.getText().equals(card.index.getText())
                && animal.equals(card.animal);
    }
}