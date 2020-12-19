package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class information implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea textarea;

    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resultselector.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textarea.setText(resultselector.texttodisplay);
    }
}