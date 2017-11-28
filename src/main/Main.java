/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.Mapa;
import UI.MenuPole;
import UI.SeznamVychodu;
import UI.VeciVBatohu;
import UI.VeciVProstoru;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author xzenj02/sram02
 */
public class Main extends Application {

    private Mapa mapa;
    private MenuPole menu;
    private IHra hra;
    private Stage primaryStage;
    private SeznamVychodu seznamVychodu;
    private TextArea centralText = new TextArea();
    private TextField zadejPrikazTextField = new TextField();
    private VeciVProstoru veciVProstoru;
    private VeciVBatohu veciVBatohu;
    
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(hra, this);
        seznamVychodu   = new SeznamVychodu(hra, centralText);
        veciVProstoru   = new VeciVProstoru(hra, centralText);
        veciVBatohu     = new VeciVBatohu(hra, centralText);
        
        BorderPane borderPane = new BorderPane();
        
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        
        
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String zadanyPrikaz = zadejPrikazTextField.getText();
                String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                
                centralText.appendText("\n" + zadanyPrikaz + "\n");
                centralText.appendText("\n" + odpoved + "\n");
                
                zadejPrikazTextField.setText("");
                
                if(hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                }
                
            }
        });
        
        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        
        borderPane.setCenter(centralText);
        borderPane.setBottom(dolniPanel);
        borderPane.setLeft(levyPanel());
        borderPane.setRight(pravyPanel());
        borderPane.setTop(menu);
        
        Scene scene = new Scene(borderPane, 1400, 1000);

        primaryStage.setTitle("Moje Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
    }
    
      /**
     * nastavení levého panelu
     */
        private BorderPane levyPanel(){
             
        BorderPane vychody = new BorderPane();
        Label textSeznamVychodu = new Label("Seznam východů:");
        textSeznamVychodu.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        vychody.setTop(textSeznamVychodu);
        vychody.setCenter(seznamVychodu);
       
        BorderPane levyPanel = new BorderPane();
        levyPanel.setTop(mapa);
        levyPanel.setBottom(vychody);
        
        return levyPanel;
    }
        
     /**
     * nastavení pravého panelu
     */
     private BorderPane pravyPanel(){
        
           
        BorderPane veci = new BorderPane();
        veci.setPrefWidth(420);
        
            veci.setCenter(veciVProstoru);

            BorderPane seznamVeciVBatohu = new BorderPane();
            Label textVeciVBatohu = new Label("\nSeznam věcí v batohu:");
            textVeciVBatohu.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            seznamVeciVBatohu.setTop(textVeciVBatohu);
            seznamVeciVBatohu.setCenter(veciVBatohu);
        
        veci.setCenter(seznamVeciVBatohu);
        
        BorderPane seznamVeciVProstoru = new BorderPane();
        Label textveciVProstoru = new Label("Seznam věcí v prostoru:");
        textveciVProstoru.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        seznamVeciVProstoru.setTop(textveciVProstoru);
        seznamVeciVProstoru.setCenter(veciVProstoru); 

        
        BorderPane pravyPanel = new BorderPane();
        pravyPanel.setTop(veci);
        pravyPanel.setCenter(seznamVeciVProstoru);
        return pravyPanel;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    public void novaHra() {
        hra = new Hra();
        centralText.setText(hra.vratUvitani());
        //to same pro vsechny observery
        mapa.novaHra(hra);
    }
    
        /**
     * Vrací odkaz na objekt se seznamem východů
     *
     * @return seznam východů
     */
    public SeznamVychodu getSeznamVychodu() {
        return seznamVychodu;
    }
    
        public TextArea getCentralText() {
        return centralText;
    }
        
            /**
     * Metoda nastavuje hru
     *
     * @param instance hry
     */
    public void setHra(IHra hra) {
        this.hra = hra;
    }  
        /**
     * Vrací odkaz na mapu prostorů
     *
     * @return mapa
     */
    public Mapa getMapa() {
        return mapa;
    }
    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
        /**
     * Vrací odkaz na primární stage
     *
     * @return primaryStage
     */
    public Stage getStage() {
        return primaryStage;
    }  
    
        /**
     * Vrací odkaz na objekt se seznamem věcí v prostoru
     *
     * @return věci v prostoru
     */
    public VeciVProstoru getVeciVProstoru() {
        return veciVProstoru;
    }
    
        /**
     * Vrací odkaz objekt se seznamem věcí v batohu
     *
     * @return věcí v batohu
     */
    public VeciVBatohu getVeciVBatohu() {
        return veciVBatohu;
    }
    


}
