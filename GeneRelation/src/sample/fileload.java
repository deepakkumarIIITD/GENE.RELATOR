package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class fileload implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Circle filesselected;

    public static List<File> Files;

    @FXML
    void proceed(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resultselector.fxml"));
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }
    @FXML
    void selectfile(MouseEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        Files = fileChooser.showOpenMultipleDialog(null);
        System.out.println(Files);
        algocaller();
        filesselected.setFill(javafx.scene.paint.Color.rgb(5,255,46));
    }
    public static void algocaller() throws IOException {
        database.codonmaker();
        for(int i = 0 ; i < Files.size() ; i++){
            for(int j = i + 1 ; j < Files.size() ; j++){
                database.AllResults.add(new result(filenamegetter(Files.get(i).toString()) , filenamegetter(Files.get(j).toString()) , Files.get(i) , Files.get(j)));
            }
        }
    }
    public static String filenamegetter(String file){
        int lastbar = 0;
        for(int i = file.length() - 1 ; i >= 0 ; i--){
            if(file.charAt(i) == '\\'){
                lastbar = i;
                break;
            }
        }
        int lastdot = 0;
        for(int i = file.length() - 1 ; i >= 0 ; i--){
            if(file.charAt(i) == '.'){
                lastdot = i;
                break;
            }
        }
        return file.substring(lastbar+1,lastdot);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}