///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package GUI;
//
//import calometre.Calometre;
//import entity.exercice;
//import entity.typeExercice;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonBar;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.FileChooser;
//import service.exerciceService;
//import service.typeExerciceService;
//
///**
// *
// * @author Yassine
// */
//public class ExerciceController implements Initializable {
//
//    exerciceService fn = new exerciceService();
//    exercice test = new exercice();
//
//    typeExerciceService fn1 = new typeExerciceService();
//    typeExercice test1 = new typeExercice();
//    public static String ExerciceVideo;
//    @FXML
//    private TextField nameField;
//    @FXML
//    private TextField descriptionField;
//    @FXML
//    private TextField objectifField;
//    @FXML
//    private Button addvideoButton;
//    @FXML
//    private ComboBox typeCombo;
//    @FXML
//    private TextField newnameField;
//    @FXML
//    private TextField newdescriptionField;
//    @FXML
//    private TextField newobjectifField;
//    @FXML
//    TableView<exercice> exerciceview;
//    @FXML
//    private TableColumn<exercice, Integer> idexercice;
//    @FXML
//    private TableColumn<exercice, String> nomexercice;
//    @FXML
//    private TableColumn<exercice, String> descexercice;
//    @FXML
//    private TableColumn<exercice, String> objectifexercice;
//    ObservableList<exercice> oblist = FXCollections.observableArrayList();
//
//    @FXML
//
//    public String uploadvid() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("open");
//        File image = fileChooser.showOpenDialog(Calometre.primaryStage);
//        if (image != null) {
//            int index = (image.toString().lastIndexOf("."));
//            if (index > 0) {
//                String extension = image.toString().substring(index + 1);
//                if (!(extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("mp4"))) {
//                    System.err.println("wrong format");
//                } else {
//                    Random random = new Random();
//                    int imageName = random.nextInt(30000000);
//                    String pathname = "C:\\Users\\Souhail\\Documents\\images\\" + imageName + String.valueOf(imageName) + "." + extension;
//                    File file1 = new File(pathname);
//                    try {
//                        ExerciceController.ExerciceVideo = imageName + String.valueOf(imageName) + "." + extension;
//                        Files.copy(image.toPath(), file1.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                    } catch (IOException e) {
//                    }
//                }
//            }
//        }
//        return ExerciceController.ExerciceVideo;
//    }
//
//    private void exerciceview() {
//
//        // TODO
//        List<exercice> li = fn.readExercice();
//        li.forEach(e -> {
//            oblist.add(e);
//            idexercice.setCellValueFactory(new PropertyValueFactory<>("id"));
//            nomexercice.setCellValueFactory(new PropertyValueFactory<>("nom"));
//            descexercice.setCellValueFactory(new PropertyValueFactory<>("description"));
//            objectifexercice.setCellValueFactory(new PropertyValueFactory<>("objectif"));
//
//        });
//        exerciceview.setItems(oblist);
//    }
//
//    private void refreshtables() throws java.io.IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("exercice.fxml"));
//        Calometre.primaryStage.setScene(new Scene(root));
//        Calometre.primaryStage.show();
//    }
//
//    public void editExercice() throws java.io.IOException {
//        String selected = typeCombo.getValue().toString();
//        typeExercice typename = fn1.findByName(selected);
//        String name = nomexercice.getText();
//        String descriptione = descexercice.getText();
//        String objectif = objectifexercice.getText();
//        int x = Integer.parseInt(catname.getName());
//        int prix = Integer.parseInt(price);
//        int quantity = Integer.parseInt(qty);
//        p = prodview.getSelectionModel().getSelectedItem();
//        p.setId(p.getId());
//        p.setName(name);
//        p.setPrice(prix);
//        p.setDescription(description);
//        p.setQuantity(quantity);
//        cat.setId(x);
//        fn.updateproduct(p, cat);
//        this.refreshtables();
//    }
//
//    ;
//
//    public void addExercice() {
//        String type = typeCombo.getValue().toString();
//        String nom = nameField.getText();
//        String description = descriptionField.getText();
//        String objectif = objectifField.getText();
//
//        String selected = typeCombo.getValue().toString();
//
//        int id = Integer.parseInt(selected);
//
//        if (nom.isEmpty() || description.isEmpty() || objectif.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("null");
//            alert.setContentText("Please fill all required fields");
//            alert.showAndWait();
//        } else {
//            test.setNom(nom);
//            test.setObjectif(objectif);
//            test.setDescription(description);
//
//            test.setVideo(ExerciceVideo);
//            test1.setId(id);
//            fn.addExercice(test1, test);
//        }
//    }
//
//    public void UpdateInfo() throws IOException {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//
//        alert.setHeaderText("Êtes-vous sûr de changer vos information? ");
//
//        ButtonType buttonTypeOne = new ButtonType("Oui");
//
//        ButtonType buttonTypeCancel = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == buttonTypeOne) {
//            String type = typeCombo.getValue().toString();
//            String nom = newnameField.getText();
//            String description = newdescriptionField.getText();
//            String objectif = newobjectifField.getText();
//
//            String selected = typeCombo.getValue().toString();
//
//            int id = Integer.parseInt(selected);
//
//            test.setNom(nom);
//            test.setObjectif(objectif);
//            test.setDescription(description);
//
//            test.setVideo(ExerciceVideo);
//            test.setId(id);
//            fn.editExercice(test);
//
//        } else {
//            // ... user chose CANCEL or closed the dialog
//        }
//
//    }
//
//    ;
//
//
//
//    @Override
//
//    public void initialize(URL location, ResourceBundle resources) {
//        List<typeExercice> listExc = fn1.readType();
//        for (typeExercice list : listExc) {
//            int id = list.getId();
//            typeCombo.getItems().add(id);
//
//        }
//        this.exerciceview();
//
//    }
//
//}
