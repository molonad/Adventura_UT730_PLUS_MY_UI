package logika;

 

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import utils.Subject;
import utils.Observer;
/**
 *  Trida Batoh 
 *
 *
 *@author     Michal Šráček
 */

public class Batoh implements Subject
{
private int KAPACITA = 3 ;
private Map<String, Vec> seznamVeci ;   // seznam věcí v batohu
private List<Observer> listObserveru = new ArrayList<Observer>();
     

      /**
     * vytváří batoh do ktereho se vkladaji veci
     */

    public Batoh ()     {
        this.KAPACITA = KAPACITA;
        seznamVeci = new HashMap<>();
        
    }

     /**
     * Vrací řetězec názvů věcí, které jsou v batohu

     *@return            řetězec názvů
     */
    public String veciVBatohu () {
        String nazvy = "věci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            	nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }
 /**
     * Vloží věc do batohu
     *
     *@param  vec  instance věci, která se má vložit
     */
   public boolean vlozVec (Vec vec){
       notifyAllObservers();
     if(seznamVeci.size() < KAPACITA)
        
     { 
            
            seznamVeci.put(vec.getJmeno(), vec);
            
            if(seznamVeci.containsKey(vec.getJmeno())){
                notifyAllObservers();
                return true;
            }
            return false;
        }
        else
        {   
            return false;
        } 
     
    }

    /**
     *  Metoda položí věc z batohu
     *
     *@param  název věci, kterou chceme vyhodit
     *
     *@return  Odkaz na vyhozenou věc
     */ 
   
   public Vec polozVec (String nazevVeci)
    {  
        Vec vec =  seznamVeci.remove(nazevVeci);
        notifyAllObservers();
        return vec;            
    }
        /**
     *  Metoda zjistí, jestli je daná věc v batohu
     *
     *@param  název věci
     *
     *@return  true - věc je v batohu, false - věc není v batohu
     */     
    public boolean obsahujeVec (String nazev){
        
        return seznamVeci.containsKey(nazev); 
    }
        /**
     *  Metoda vybere věc v batohu
     *
     *@param  název věci, kterou chceme vybrat
     *
     *@return  vybraná věc
     */ 
    public Vec vyberVec(String nazevVeci){
        
        return seznamVeci.get(nazevVeci);
    }
    
        public Collection<String> getVeciVBatohu() {
        return Collections.unmodifiableCollection(seznamVeci.keySet());
    }
        /**
     * Registrace observeru
     * @param observer Observer
     */
    
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }
    /**
     * Zrušení observeru
     * @param observer Observer
     */
    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }
    /**
     * Oznámení observeru
     */   
    @Override
    public void notifyAllObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
    
    

}



