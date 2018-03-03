package DateParser;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.css.Match;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserController {
    @FXML
    private ListView<String> datesListView;
    @FXML
    private TextArea textArea;

    private String currentWord = "";
    private ListProperty<String> listProperty = new SimpleListProperty<>();
    private Pattern pattern = Pattern.compile("(([0-9]{4})[-./]((1[012])|(0[1-9]))[-./]((0[1-9])|(1[0-9])|(2[0-9])|3[01]))|" +
            "(((1[012])|(0[1-9]))[-./]((0[1-9])|(1[0-9])|(2[0-9])|3[01])[-./]([0-9]{4}))|" +
            "(((0[1-9])|(1[0-9])|(2[0-9])|3[01])[-./]((1[012])|(0[1-9]))[-./]([0-9]{4}))");

    private Set<String> tempSet = new HashSet<>();

    @FXML
    public void initialize() {
        datesListView.itemsProperty().bind(Bindings.createObjectBinding(() -> {
            SimpleListProperty<String> value = new SimpleListProperty<>();
            
            value.set(FXCollections.observableArrayList());
            Matcher m = pattern.matcher(textArea.getText());
            while (m.find()) {
                value.add(m.group());
            }
            return value;
        }, textArea.textProperty()));
    }
}