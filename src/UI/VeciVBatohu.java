/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import utils.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import logika.IHra;
import main.Main;

/**
 * Třída VeciVBatohu vytváří seznam obrázků vecí, které jsou v inventáři.
 * Aktualizuje se při nové hře, přejití do jiného prostoru a sebrání/položení
 * věci.
 *
 * @author Michal Šráček
 * @version ZS 2017
 */
public class VeciVBatohu extends ListView implements Observer {

    public IHra hra;
    public TextArea centralText;
    ObservableList<FlowPane> seznamVeciVBatohu;

    /**
    *  Konstruktor třídy
    *  
    *  @param hra 
    *  @param centralText
    */ 
    public VeciVBatohu(IHra hra, TextArea centralText) {
        this.hra = hra;
        this.centralText = centralText;
        hra.getBatoh().registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        init();
        update();
    }

    /**
     * Restartování adventury
     *
     * @param hra Nová hra
     */
    public void novaHra(IHra hra) {
        hra.getBatoh().deleteObserver(this);
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getBatoh().registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        update();
    }

    /**
     * Update seznamu věcí v batohu
     */
    @Override
    public void update() {
        seznamVeciVBatohu.clear();
        for (String vec : hra.getBatoh().getVeciVBatohu()) {

            FlowPane polozka = new FlowPane();
            
            String nazev = "/zdroje/" + vec + ".jpg";
            
            ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream(nazev), 50, 50, false, false));
            

            imageView.setOnMouseClicked(event -> {
                
                String prikaz = "poloz " + vec;
                centralText.appendText(prikaz);
                String odpoved = hra.zpracujPrikaz(prikaz);
                centralText.appendText("\n\n" + odpoved + "\n");
            });

            polozka.getChildren().add(imageView);
            

            seznamVeciVBatohu.add(polozka);
        }
    }

    /**
     * Úvodní nastavení seznamu věcí v batohu
     */
    private void init() {
        seznamVeciVBatohu= FXCollections.observableArrayList();
        this.setItems(seznamVeciVBatohu);
        this.setPrefWidth(170);
        this.setPrefHeight(400);
        
    }
}
