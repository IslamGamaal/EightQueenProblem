package sample;

import AlgorithmsImplementation.Genetic;
import AlgorithmsImplementation.KBeamSearch;
import AlgorithmsImplementation.csp.CSP;
import AlgorithmsImplementation.hillclimbing.Board;
import AlgorithmsImplementation.hillclimbing.HillClimbingRandomRestart;
import AlgorithmsImplementation.hillclimbing.Queen;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Controller {
    HashSet<String> queensPositions = new HashSet<>();
    int[] positions = new int[8];
    Button[][] buttons;

    @FXML
    private Button position00;

    @FXML
    private Button position01;

    @FXML
    private Button position02;

    @FXML
    private Button position03;

    @FXML
    private Button position04;

    @FXML
    private Button position05;

    @FXML
    private Button position06;

    @FXML
    private Button position07;

    @FXML
    private Button position10;

    @FXML
    private Button position11;

    @FXML
    private Button position12;

    @FXML
    private Button position13;

    @FXML
    private Button position14;

    @FXML
    private Button position15;

    @FXML
    private Button position16;

    @FXML
    private Button position17;

    @FXML
    private Button position20;

    @FXML
    private Button position21;

    @FXML
    private Button position22;

    @FXML
    private Button position23;

    @FXML
    private Button position24;

    @FXML
    private Button position25;

    @FXML
    private Button position26;

    @FXML
    private Button position27;

    @FXML
    private Button position30;

    @FXML
    private Button position31;

    @FXML
    private Button position32;

    @FXML
    private Button position33;

    @FXML
    private Button position34;

    @FXML
    private Button position35;

    @FXML
    private Button position36;

    @FXML
    private Button position37;

    @FXML
    private Button position40;

    @FXML
    private Button position41;

    @FXML
    private Button position42;

    @FXML
    private Button position43;

    @FXML
    private Button position44;

    @FXML
    private Button position45;

    @FXML
    private Button position46;

    @FXML
    private Button position47;

    @FXML
    private Button position50;

    @FXML
    private Button position51;

    @FXML
    private Button position52;

    @FXML
    private Button position53;

    @FXML
    private Button position54;

    @FXML
    private Button position55;

    @FXML
    private Button position56;

    @FXML
    private Button position57;

    @FXML
    private Button position60;

    @FXML
    private TextField expNodes;

    @FXML
    private Button position61;

    @FXML
    private Button position62;

    @FXML
    private Button position63;

    @FXML
    private Button position64;

    @FXML
    private Button position65;

    @FXML
    private Button position66;

    @FXML
    private Button position67;

    @FXML
    private Button position70;

    @FXML
    private Button position71;

    @FXML
    private Button position72;

    @FXML
    private Button position73;

    @FXML
    private Button position74;

    @FXML
    private Button position75;

    @FXML
    private Button position76;

    @FXML
    private Button position77;

    @FXML
    private TextField timeField;

    @FXML
    private TextField kField;

    @FXML
    private TextField costField;

    @FXML
    private ComboBox<String> algorithmsComboBox;
    private int bestK;

    @FXML
    void clearBoard(ActionEvent event) {
        costField.setText("0");
        timeField.setText("0");
        kField.setText("N/A");
        expNodes.setText("0");
        queensPositions = new HashSet<>();
        for(int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if ((j+i) % 2 == 0)
                    buttons[i][j].setStyle("-fx-background-color : #ebcea4");
                else
                    buttons[i][j].setStyle("-fx-background-color : #bc8358");
    }

    @FXML
    void compareResult(ActionEvent event) {

    }

    @FXML
    void solve(ActionEvent event) {
        clearBoard(null);
        int[] solution = new int[8];
        if(algorithmsComboBox.getSelectionModel().getSelectedIndex() == 0) {
            HillClimbingRandomRestart hcrr = new HillClimbingRandomRestart();
            hcrr.solve(positions);
            kField.setText("N/A");
            timeField.setText(String.valueOf(hcrr.getTotalRuntime()));
            costField.setText(String.valueOf(hcrr.getTotalCost()));
            expNodes.setText(String.valueOf(hcrr.getNumOfExpandedNodes()));
            solution = hcrr.getSolution();
        }
        else if(algorithmsComboBox.getSelectionModel().getSelectedIndex() == 1) {
            KBeamSearch[] beamSearches = new KBeamSearch[6];
            int[] kValues = {1, 4, 8, 12, 16, 20};
            int kIndex = 0;
            double max = Integer.MIN_VALUE;
            int wantedIndex = 0;
            for (int i = 0; i < beamSearches.length; i++) {
                beamSearches[i] = new KBeamSearch();
                beamSearches[i].solve(8, positions, 500, kValues[kIndex]);
                if (beamSearches[i].getTotalRunTime() > max) {
                    max = beamSearches[i].getTotalRunTime();
                    wantedIndex = kIndex;
                }
                kIndex++;
            }
            solution = beamSearches[wantedIndex].getSolution();
            bestK = kValues[wantedIndex];
            kField.setText(String.valueOf(bestK));
            timeField.setText(String.valueOf(beamSearches[wantedIndex].getTotalRunTime()));
            costField.setText(String.valueOf(beamSearches[wantedIndex].getSteps()));
            expNodes.setText(String.valueOf(beamSearches[wantedIndex].getNumOfExpandedNodes()));
        }
        else if(algorithmsComboBox.getSelectionModel().getSelectedIndex() == 2) {
            Genetic genetic = new Genetic();
            int wantedIndex = 0;
            solution = genetic.solve(8, positions, 8, 0.7, 50000);
            kField.setText("N/A");
            timeField.setText(String.valueOf(genetic.getTotalRunTime()));
            costField.setText(String.valueOf(genetic.getSteps()));
            expNodes.setText(String.valueOf(genetic.getNumOfExpandedNodes()));
        } else if(algorithmsComboBox.getSelectionModel().getSelectedIndex() == 3) {
            CSP queen = new CSP();
            boolean hasSolution = queen.solveNQ(positions);
            if(hasSolution) {
                solution = queen.getSolution();
                timeField.setText(String.valueOf(queen.getTotalTime()));
                costField.setText(String.valueOf(queen.getCost()));
                expNodes.setText(String.valueOf(queen.getCost()));
            }
        }
        if(solution == null) return;
        if(solution[0] == 0 && solution[1] == 0) return;
        String evenOrOdd;
        for(int i = 0; i < 8; i++) {
            if((solution[i]+i)%2 == 0)
                evenOrOdd = "Even";
            else
                evenOrOdd = "Odd";
            String path = "/images/queen" + evenOrOdd + ".png";
            BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource(path).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            buttons[solution[i]][i].setStyle("-fx-background-image : black");
            buttons[solution[i]][i].setBackground(background);
            queensPositions.add("position" + solution[i] +""+ i);
        }
    }


    int[] readGridIntoArray(ArrayList<String> lines) {
        clearBoard(null);
        int[] positions = new int[8];
        String evenOrOdd;
        for(int j = 0; j < 8; j++) {
            for (int i = 0; i < lines.get(j).length(); i++) {
                if (lines.get(j).charAt(i) == 'Q') {
                    positions[i] = j;
                    if((j + i) % 2 == 0)
                        evenOrOdd = "Even";
                    else
                        evenOrOdd = "Odd";
                    String path = "/images/queen" + evenOrOdd + ".png";
                    BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource(path).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                    Background background = new Background(backgroundImage);
                    buttons[j][i].setStyle("-fx-background-image : black");
                    buttons[j][i].setBackground(background);
                    queensPositions.add("position"+j +""+ i);
                }
            }
        }
        return positions;
    }

    @FXML
    void importGrid(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedfile = fileChooser.showOpenDialog(((Button)event.getSource()).getScene().getWindow());
        ArrayList<String> lines = new ArrayList<>();
        Scanner myReader = null;
        try {
            myReader = new Scanner(selectedfile);
            int l = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(data);
            }
            positions = readGridIntoArray(lines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        myReader.close();
    }

    @FXML
    void putQueenOnPosition(MouseEvent event) {
        if(queensPositions.size() == 8 && !queensPositions.contains(((Button)event.getSource()).getId())) return;
        if(queensPositions.contains(((Button)event.getSource()).getId())) {
            queensPositions.remove(((Button)event.getSource()).getId());
        } else {
            queensPositions.add(((Button) event.getSource()).getId());
            Pair<Integer, Integer> pos = getRowAndColumn(((Button) event.getSource()).getId());
            positions[pos.getValue()] = pos.getKey();
        }
    }

    @FXML
    void removeQueenFromPosition(MouseEvent event) {
        if(!queensPositions.contains(((Button)event.getSource()).getId())) {
            Pair<Integer, Integer> position = getRowAndColumn(((Button) event.getSource()).getId());
            if ((position.getKey() + position.getValue()) % 2 == 0)
                ((Button) event.getSource()).setStyle("-fx-background-color : #ebcea4");
            else
                ((Button) event.getSource()).setStyle("-fx-background-color : #bc8358");
        }
    }

    Pair<Integer, Integer> getRowAndColumn(String id) {
        id = id.replace("position",  "");
        int row = id.charAt(0) - 48;
        int column = id.charAt(1) - 48;
        return new Pair<Integer, Integer>(row, column);
    }

    @FXML
    void viewQueenOnPosition(MouseEvent event) {
        if(queensPositions.size() == 8) return;
        String id = ((Button)event.getSource()).getId();
        ((Button)event.getSource()).setStyle("-fx-background-image : black");
        String evenOrOdd;
        Pair<Integer, Integer> position = getRowAndColumn(id);
        if((position.getKey() + position.getValue()) % 2 == 0)
            evenOrOdd = "Even";
        else
            evenOrOdd = "Odd";
        String path = "/images/queen" + evenOrOdd + ".png";
        BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource(path).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        ((Button)event.getSource()).setBackground(background);
    }
    @FXML
    public void initialize() {
        algorithmsComboBox.getItems().addAll("Hill Climbing Algorithm", "K-Beam Search Algorithm.", "Genetic Algorithm.", "CSP.");
        Button[][] buttons = {
                {position00,position01,position02,position03,position04,position05,position06,position07},
                {position10,position11,position12,position13,position14,position15,position16,position17},
                {position20,position21,position22,position23,position24,position25,position26,position27},
                {position30,position31,position32,position33,position34,position35,position36,position37},
                {position40,position41,position42,position43,position44,position45,position46,position47},
                {position50,position51,position52,position53,position54,position55,position56,position57},
                {position60,position61,position62,position63,position64,position65,position66,position67},
                {position70,position71,position72,position73,position74,position75,position76,position77}
        };
        this.buttons = buttons;
    }

}
