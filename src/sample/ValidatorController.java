package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;

import javax.naming.Binding;
import java.util.concurrent.Callable;

public class ValidatorController {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField textField;
    @FXML
    private ObservableList<String> comboBoxValues;
    @FXML
    private ImageView imageIndicator;

    private BooleanProperty flag;
    private Image success, error;
    private Validator validator;

    public ValidatorController() {
        flag = new SimpleBooleanProperty();
        validator = new Validator();
        error = new Image(getClass().getResourceAsStream("resources/error.png"));
        success = new Image(getClass().getResourceAsStream("resources/success.png"));
    }

    @FXML
    private void initialize() {
        imageIndicator.imageProperty().bind(Bindings.when(flag).then(success).otherwise(error));
        comboBoxValues = comboBox.getItems();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            flag.setValue(validator.isValid(newValue, comboBox.getSelectionModel().getSelectedItem()));
        });

        comboBox.valueProperty().addListener(x -> {
            flag.setValue(validator.isValid(textField.getText(), comboBox.getSelectionModel().getSelectedItem()));
        });
    }
}
