package seedu.zookeep.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.zookeep.model.animal.Animal;
import seedu.zookeep.model.feedtime.FeedTimeComparator;

/**
 * An UI component that displays information of a {@code Animal}.
 */
public class AnimalCard extends UiPart<Region> {

    private static final String FXML = "AnimalListCard.fxml";
    private static final String NOT_SPECIFIED_STYLE = "-fx-font-size: 11px; -fx-text-fill: #e06060;"
            + "-fx-background-color: transparent; -fx-padding: 0;";

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
    @FXML
    private Label feedTime;
    @FXML
    private FlowPane feedTimes;

    /**
     * Creates a {@code AnimalCode} with the given {@code Animal} and index to display.
     */
    public AnimalCard(Animal animal, int displayedIndex) {
        super(FXML);
        this.animal = animal;
        index.setText(displayedIndex + ". ");
        name.setText(animal.getName().fullName);
        identity.setText(animal.getId().value);
        species.setText(animal.getSpecies().value);

        if (!animal.getMedicalConditions().isEmpty()) {
            animal.getMedicalConditions().stream()
                    .sorted(Comparator.comparing(medicalCondition ->
                            medicalCondition.medicalConditionName.toLowerCase()))
                    .forEach(medicalCondition -> medicalConditions.getChildren()
                            .add(new Label(medicalCondition.medicalConditionName)));
        } else {
            Label label = new Label("Not specified");
            label.setStyle(NOT_SPECIFIED_STYLE);
            medicalConditions.getChildren().add(label);
        }

        if (!animal.getFeedTimes().isEmpty()) {
            animal.getFeedTimes().stream()
                    .sorted(new FeedTimeComparator())
                    .forEach(feedTime -> feedTimes.getChildren()
                            .add(new Label(feedTime.feedTime + " hrs")));
        } else {
            Label label = new Label("Not specified");
            label.setStyle(NOT_SPECIFIED_STYLE);
            feedTimes.getChildren().add(label);
        }
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
