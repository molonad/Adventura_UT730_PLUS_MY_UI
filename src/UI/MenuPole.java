/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *
 * @author sram02
 */
public class MenuPole extends MenuBar{
    
    private Main main;
    private IHra hra;
    
    public MenuPole(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
        
    }

    
    private void init(){
        Menu menuSoubor = new Menu("Hra");
        
        MenuItem itemNovaHra = new MenuItem("Nová hra");
        itemNovaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        
        MenuItem itemKonec = new MenuItem("Konec");
        
        
        Menu menuHelp = new Menu("Help");
        MenuItem itemOProgramu = new MenuItem("O programu");
        MenuItem itemNapoveda = new MenuItem("Napoveda");
        
        
        menuSoubor.getItems().addAll(itemNovaHra, itemKonec);
        
        itemNovaHra.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        hra = new Hra();
                        main.setHra(hra);
                        main.getMapa().novaHra(hra);
                        main.getVeciVBatohu().novaHra(hra);
                        main.getVeciVProstoru().novaHra(hra);
                        main.getSeznamVychodu().novaHra(hra);
                        main.getCentralText().setText(hra.vratUvitani());
                    }
                }); 
        
        menuHelp.getItems().addAll(itemOProgramu, itemNapoveda);
        
        this.getMenus().addAll(menuSoubor, menuHelp);
        
        
        
        itemOProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("O Adventure");
                alert.setHeaderText("Toto je ma adventura");
                alert.setContentText("Graficka verze adventury");
                alert.initOwner(main.getPrimaryStage());
                alert.showAndWait();
            }
        });
        
        itemNapoveda.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                WebView webview = new WebView();
                
                webview.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            }
        });
        
        itemKonec.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        
    }
    
}
