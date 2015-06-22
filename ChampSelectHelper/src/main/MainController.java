package main;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class MainController implements Initializable {

    private Stage stage;

    public void init(Stage stage){
        this.stage = stage;
    }

    @FXML
    private ToggleButton topButton = new ToggleButton();
    @FXML
    private ToggleButton jungleButton = new ToggleButton();
    @FXML
    private ToggleButton midButton = new ToggleButton();
    @FXML
    private ToggleButton adcButton = new ToggleButton();
    @FXML
    private ToggleButton supportButton = new ToggleButton();
    @FXML
    private ToggleButton banButton = new ToggleButton();
    @FXML
    private ToggleButton custom1Button = new ToggleButton();
    @FXML
    private ToggleButton custom2Button = new ToggleButton();

    @FXML
    private ObservableList<String> topList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> jungleList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> midList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> adcList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> supportList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> banList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> custom1List = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> custom2List = FXCollections.observableArrayList();

    @FXML
    private Label errorLabel = new Label();

    @FXML
    private ObservableList<String> toSearchString = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ListView<String> selectedList;

    @FXML
    ObservableList<String> champList = FXCollections.observableArrayList(
            "Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie",
            "Ashe", "Azir", "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn",
            "Cassiopeia", "Cho'Gath", "Corki", "Darius", "Diana", "Dr. Mundo",
            "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks",
            "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas",
            "Graves", "Hecarim", "Heimerdinger", "Irelia", "Janna", "Jarvan IV",
            "Jax", "Jayce", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin", "Katarina",
            "Kayle", "Kennen", "Kha'Zix", "Kog'Maw", "Leblanc", "Lee Sin",
            "Leona", "Lissandra", "Lucian", "Lulu", "Lux", "Malphite", "Malzahar",
            "Maokai", "Master Yi", "Miss Fortune", "Mordekaiser", "Morgana", "Nami",
            "Nasus", "Nautilus", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna",
            "Pantheon", "Poppy", "Quinn", "Rammus", "Rek'Sai", "Renekton", "Rengar",
            "Riven", "Rumble", "Ryze", "Sejuani", "Shaco", "Shen", "Shyvana", "Singed",
            "Sion", "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Syndra", "Talon",
            "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "Twisted Fate",
            "Twitch", "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "Vel'Koz", "Vi", "Viktor",
            "Vladimir", "Volibear", "Warwick", "Wukong", "Xerath", "Xin Zhao", "Yasuo",
            "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra"
    );

    private String selectedRoleButton;

    public void loadFile(String path) {
        errorLabel.setVisible(false);
        File file = new File(path);

        try {   //Creates a file with path of last loaded file
            FileWriter fileWriter = new FileWriter("runinfo.txt");

            fileWriter.write(file.getAbsolutePath() + System.getProperty("line.separator"));

            fileWriter.close();
        } catch (Exception e1) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e1);
        }

        if(file != null) {
            try {
                Scanner sc = new Scanner(file);
                String s;
                String str;
                while (sc.hasNextLine()) {
                    s = sc.nextLine();                  //Loads a line at a time

                    if(s.startsWith("Top:")) {
                        str = s.replace("[", "");       //Removes symbols from String autogenerated by
                        str = str.replace("]", "");     //observableArrayList
                        str = str.replace("Top: ", "");

                        //Splits String by commas and spaces
                        topList.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < topList.size(); i++)
                            topList.set(i, topList.get(i).trim());  //Removes unecessary spaces
                    }
                    else if(s.startsWith("Jungle:")) {
                        str = s.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace("Jungle: ", "");

                        jungleList.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < jungleList.size(); i++)
                            jungleList.set(i, jungleList.get(i).trim());
                    }
                    else if(s.startsWith("Mid:")) {
                        str = s.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace("Mid: ", "");

                        midList.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < midList.size(); i++)
                            midList.set(i, midList.get(i).trim());
                    }
                    else if(s.startsWith("ADC:")) {
                        str = s.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace("ADC: ", "");

                        adcList.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < adcList.size(); i++)
                            adcList.set(i, adcList.get(i).trim());
                    }
                    else if(s.startsWith("Support:")) {
                        str = s.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace("Support: ", "");

                        supportList.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < supportList.size(); i++)
                            supportList.set(i, supportList.get(i).trim());
                    }
                    else if(s.startsWith(("Bans:"))) {
                        str = s.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace("Bans: ", "");

                        banList.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < banList.size(); i++)
                            banList.set(i, banList.get(i).trim());
                    }
                    else if(s.startsWith(("Custom #1:"))) {
                        str = s.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace("Custom #1: ", "");

                        custom1List.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < custom1List.size(); i++)
                            custom1List.set(i, custom1List.get(i).trim());
                    }
                    else if(s.startsWith("Custom #2:")) {
                        str = s.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace("Custom #2: ", "");

                        custom2List.setAll(new ArrayList<String>(Arrays.asList(str.split("\\s*,\\s*"))));
                        for(int i = 0; i < custom2List.size(); i++)
                            custom2List.set(i, custom2List.get(i).trim());
                    }

                    //Adds items if there's a selected role button
                    selectedList.setItems(findSelectedRoleButton());
                }
                sc.close();
            }
            catch(Exception e3) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e3);
            }
        }
    }

    public void loadStartup() {
        try {
            if (fileExists("runinfo.txt")) { //If there has been a previously loaded file
                BufferedReader br = new BufferedReader(new FileReader("runinfo.txt"));
                String s = br.readLine();
                if(!s.equals(null) && fileExists(s)) //If previously loaded file exists
                    loadFile(s);
            }
        } catch(IOException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean fileExists(String path) {
        try { //Tries to manipulate file to see if it exists
            BufferedReader br = new BufferedReader (new FileReader(path));
            br.readLine();
        } catch(Exception e1) {
            return false;
        }
        return true;
    }

    @FXML
    public void newFile() { //Called by File->New MenuButton
        selectedRoleButton = null;
        selectedList.getItems().clear();
        toSearchString.clear();
        topList.clear();
        jungleList.clear();
        midList.clear();
        adcList.clear();
        supportList.clear();
        banList.clear();
        custom1List.clear();
        custom2List.clear();
    }

    @FXML
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showOpenDialog(stage);
        loadFile(file.getAbsolutePath()); //Calls load
    }

    @FXML
    public void saveFileAs() {
        //If a role is selected (tells whether or not file has been edited, you can't edit without selecting a role
        //And there's elements in the list
        if(selectedRoleButton != null && selectedList.getItems().size() > 0) {
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter exFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(exFilter);

            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = fileChooser.showSaveDialog(stage);

            save(file);
        }
        else if(selectedRoleButton == null) {
            errorLabel.setVisible(true);
            errorLabel.setText("Select a role");
            errorLabel.setTextFill(Color.web("#F0706A"));
        }
        else if(selectedList.getItems().size() == 0) {
            errorLabel.setVisible(true);
            errorLabel.setText("Select at least one champion");
            errorLabel.setTextFill(Color.web("#F0706A"));
        }
        else {
            errorLabel.setVisible(true);
            errorLabel.setText("What are you trying to do here?");
            errorLabel.setTextFill(Color.web("#F0706A"));
        }
    }

    @FXML
    public void callSave() {
        //As long as a role is selected and there's elements in the list
        if(selectedRoleButton != null) {
            try {
                if(fileExists("runinfo.txt")) {
                    BufferedReader br = new BufferedReader(new FileReader("runinfo.txt"));
                    String s = br.readLine();
                    if (!s.equals(null) && fileExists(s)) {
                        File file = new File(s);
                        save(file);
                    } else
                        saveFileAs();
                }
                else {
                    saveFileAs();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            errorLabel.setVisible(true);
            errorLabel.setText("Select a role");
            errorLabel.setTextFill(Color.web("#F0706A"));
        }
    }

    @FXML
    public void clearRole() {
        if(findSelectedRoleButton() != null) {
            findSelectedRoleButton().clear();
            selectedList.getItems().clear();
            toSearchString.clear();
        }
    }

    public void save(File file) {
        errorLabel.setVisible(false);
        try {
            FileWriter fileWriter = new FileWriter(file);

            try {
                FileWriter fileWriter2 = new FileWriter("runinfo.txt");

                fileWriter2.write(file.getAbsolutePath() + System.getProperty("line.separator"));

                fileWriter2.close();
            } catch (Exception e1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e1);
            }

            if (topList.size() > 0)
                fileWriter.write("Top: " + topList + " " + System.getProperty("line.separator"));
            if (jungleList.size() > 0)
                fileWriter.write("Jungle: " + jungleList + " " + System.getProperty("line.separator"));
            if (midList.size() > 0)
                fileWriter.write("Mid: " + midList + " " + System.getProperty("line.separator"));
            if (adcList.size() > 0)
                fileWriter.write("ADC: " + adcList + " " + System.getProperty("line.separator"));
            if (supportList.size() > 0)
                fileWriter.write("Support: " + supportList + " " + System.getProperty("line.separator"));
            if (banList.size() > 0)
                fileWriter.write("Bans: " + banList + " " + System.getProperty("line.separator"));
            if (custom1List.size() > 0)
                fileWriter.write("Custom #1: " + custom1List + " " + System.getProperty("line.separator"));
            if (custom2List.size() > 0)
                fileWriter.write("Custom #2: " + custom2List + " " + System.getProperty("line.separator"));

            errorLabel.setText("File saved to\n" + file.getPath());
            errorLabel.setTextFill(Color.web("6A8759"));
            errorLabel.setVisible(true);

            fileWriter.close();
        } catch (Exception e2) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e2);
        }
    }

    public ObservableList<String> findSelectedRoleButton() {
        if(selectedRoleButton != null) {
            if (selectedRoleButton.equals("top"))
                return topList;
            else if (selectedRoleButton.equals("jungle"))
                return jungleList;
            else if (selectedRoleButton.equals("mid"))
                return midList;
            else if (selectedRoleButton.equals("adc"))
                return adcList;
            else if (selectedRoleButton.equals("support"))
                return supportList;
            else if (selectedRoleButton.equals("bans"))
                return banList;
            else if (selectedRoleButton.equals("custom1"))
                return custom1List;
            else if (selectedRoleButton.equals("custom2"))
                return custom2List;
        }
        return null;
    }

    @FXML
    public void doExit() {
        Platform.exit();
    }

    @FXML
    public void addedChampion() { //Called when an element is selected from ComboBox
        errorLabel.setVisible(false);

        if(selectedRoleButton != null) { //if a role is selected
            findSelectedRoleButton().add(comboBox.getSelectionModel().getSelectedItem());
            selectedList.setItems(findSelectedRoleButton());
        }
        else {
            errorLabel.setVisible(true);
            errorLabel.setText("Select a role first, you nib");
            errorLabel.setTextFill(Color.web("#F0706A"));
        }
        addToString(); //Adds list to string with correct RegEx
    }

    @FXML
    public void removeFromList() {
        errorLabel.setVisible(false);

        //If there's an item selected
        if(selectedList.getSelectionModel().getSelectedItem() != null) { //Removes selected item from all lists
            String selected = selectedList.getSelectionModel().getSelectedItem();
            selectedList.getItems().remove(selectedList.getSelectionModel().getSelectedItem());

            for (int i = 0; i < findSelectedRoleButton().size(); i++)
                if (selected.equals(findSelectedRoleButton().get(i)))
                    findSelectedRoleButton().remove(i);
        }
        else
            return;
    }

    public void addToString() {
        errorLabel.setVisible(false);
        toSearchString.clear();

        if(findSelectedRoleButton() != null) {
            String s;
            for (int i = 0; i < findSelectedRoleButton().size(); i++) { //Adds RegEx
                s = findSelectedRoleButton().get(i);
                if (i == findSelectedRoleButton().size() - 1 && s.equalsIgnoreCase("vi"))
                    toSearchString.add(s + "$");
                else if (s.equalsIgnoreCase("vi"))
                    toSearchString.add(s + "$|");
                else if (i == findSelectedRoleButton().size() -1)
                    return;
                else
                    toSearchString.add(s + "|");
            }
        }
    }

    @FXML
    public void copyToClipboard() {
        errorLabel.setVisible(false);
        addToString();

        StringBuilder builder = new StringBuilder();
        toSearchString.forEach(builder::append);

        StringSelection stringSelection = new StringSelection (builder.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit ().getSystemClipboard ();
        clipboard.setContents (stringSelection, null);
    }

    @FXML
    public void roleButtonClicked(ActionEvent e) {
        errorLabel.setVisible(false);

        if(e.getSource() == topButton) {
            buttonSelection(topButton);
            selectedRoleButton = "top";
            selectedList.setItems(topList);
        }
        else if (e.getSource() == jungleButton) {
            buttonSelection(jungleButton);
            selectedRoleButton = "jungle";
            selectedList.setItems(jungleList);
        }
        else if (e.getSource() == midButton) {
            buttonSelection(midButton);
            selectedRoleButton = "mid";
            selectedList.setItems(midList);
        }
        else if (e.getSource() == adcButton) {
            buttonSelection(adcButton);
            selectedRoleButton = "adc";
            selectedList.setItems(adcList);
        }
        else if (e.getSource() == supportButton) {
            buttonSelection(supportButton);
            selectedRoleButton = "support";
            selectedList.setItems(supportList);
        }
        else if (e.getSource() == banButton) {
            buttonSelection(banButton);
            selectedRoleButton = "bans";
            selectedList.setItems(banList);
        }
        else if (e.getSource() == custom1Button) {
            buttonSelection(custom1Button);
            selectedRoleButton = "custom1";
            selectedList.setItems(custom1List);
        }
        else if (e.getSource() == custom2Button) {
            buttonSelection(custom2Button);
            selectedRoleButton = "custom2";
            selectedList.setItems(custom2List);
        }

        addToString();
        copyToClipboard();
    }

    public void buttonSelection(ToggleButton selected) {
        if(selected == topButton) {
            topButton.setDisable(true);
            jungleButton.setDisable(false);
            midButton.setDisable(false);
            adcButton.setDisable(false);
            supportButton.setDisable(false);
            banButton.setDisable(false);
            custom1Button.setDisable(false);
            custom2Button.setDisable(false);
        }
        else if(selected == jungleButton) {
            topButton.setDisable(false);
            jungleButton.setDisable(true);
            midButton.setDisable(false);
            adcButton.setDisable(false);
            supportButton.setDisable(false);
            banButton.setDisable(false);
            custom1Button.setDisable(false);
            custom2Button.setDisable(false);
        }
        else if(selected == midButton) {
            topButton.setDisable(false);
            jungleButton.setDisable(false);
            midButton.setDisable(true);
            adcButton.setDisable(false);
            supportButton.setDisable(false);
            banButton.setDisable(false);
            custom1Button.setDisable(false);
            custom2Button.setDisable(false);
        }
        else if(selected == adcButton) {
            topButton.setDisable(false);
            jungleButton.setDisable(false);
            midButton.setDisable(false);
            adcButton.setDisable(true);
            supportButton.setDisable(false);
            banButton.setDisable(false);
            custom1Button.setDisable(false);
            custom2Button.setDisable(false);
        }
        else if(selected == supportButton) {
            topButton.setDisable(false);
            jungleButton.setDisable(false);
            midButton.setDisable(false);
            adcButton.setDisable(false);
            supportButton.setDisable(true);
            banButton.setDisable(false);
            custom1Button.setDisable(false);
            custom2Button.setDisable(false);
        }
        else if(selected == banButton) {
            topButton.setDisable(false);
            jungleButton.setDisable(false);
            midButton.setDisable(false);
            adcButton.setDisable(false);
            supportButton.setDisable(false);
            banButton.setDisable(true);
            custom1Button.setDisable(false);
            custom2Button.setDisable(false);
        }
        else if(selected == custom1Button) {
            topButton.setDisable(false);
            jungleButton.setDisable(false);
            midButton.setDisable(false);
            adcButton.setDisable(false);
            supportButton.setDisable(false);
            banButton.setDisable(false);
            custom1Button.setDisable(true);
            custom2Button.setDisable(false);
        }
        else if(selected == custom2Button) {
            topButton.setDisable(false);
            jungleButton.setDisable(false);
            midButton.setDisable(false);
            adcButton.setDisable(false);
            supportButton.setDisable(false);
            banButton.setDisable(false);
            custom1Button.setDisable(false);
            custom2Button.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(champList);
        selectedList.setItems(findSelectedRoleButton());
        errorLabel.setVisible(false);

        loadStartup(); //Loads previous loaded lists
    }
}