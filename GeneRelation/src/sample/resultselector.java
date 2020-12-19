package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class resultselector implements Initializable {

    public static String selecteditem;

    public static String texttodisplay;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//    @FXML
//    private Label numfiledisp;

    @FXML
    private ComboBox<String> results;

    @FXML
    void proceed(MouseEvent event) throws IOException, InterruptedException {
        selecteditem = results.getValue();
        if(selecteditem.equals("GET RESULTS IN A FOLDER")){
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(null);
            String folderpath = selectedDirectory.getAbsolutePath().replace("\\","\\\\");
//            numfiledisp.setText(database.AllResults.size() + " ARE TO BE MADE WAIT FOR SOME TIME");
//            Thread.sleep(3000);
            (((Node) event.getSource())).getScene().getWindow().hide();
            long startTime = System.currentTimeMillis();
            for(int i = 0; i < database.AllResults.size() ; i++){
                FileWriter fw = new FileWriter((folderpath + "\\" + database.AllResults.get(i).getName() +".txt"));
                if(database.AllResults.get(i).viewd) {
                    fw.write(database.AllResults.get(i).getCompleteresult());
                    database.AllResults.get(i).setCompleteresult();
                }else{
                    database.AllResults.get(i).findSimilarity();
                    fw.write(database.AllResults.get(i).getCompleteresult());
                    database.AllResults.get(i).setCompleteresult();
                }
                System.out.println((i+1) + " : " + database.AllResults.get(i).getName());
                fw.close();
            }
            long endTime = System.currentTimeMillis();
            long seconds = (endTime - startTime) / 1000;
            System.out.println(seconds + " seconds were taken to complete the process");
//            Parent root = FXMLLoader.load(getClass().getResource("filemakerpage.fxml"));
//            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            window.setScene(new Scene(root));
//            window.show();
//            System.exit(0);
        }else{
            for(int i = 0 ;i < database.AllResults.size() ; i++){
                if(database.AllResults.get(i).getName().equals(selecteditem)){
                    database.AllResults.get(i).findSimilarity();
                    database.AllResults.get(i).viewd = true;
                    texttodisplay = database.AllResults.get(i).getCompleteresult();
                    Parent root = FXMLLoader.load(getClass().getResource("information.fxml"));
                    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    window.setScene(new Scene(root));
                    window.show();
                    break;
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 0 ; i < database.AllResults.size() ; i++){
            results.getItems().add(database.AllResults.get(i).getName());
        }
        results.getItems().add("GET RESULTS IN A FOLDER");
//        results.getItems().setAll(database.);
//        results.getItems().setAll("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","SAVE ALL RESULTS IN FILE");
    }
}
