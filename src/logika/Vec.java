package logika;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Observer;
import utils.Subject;

 

/*******************************************************************************
 * Třída Vec ...
 *
 *@author     Alena Buchalcevova
 *@version    z kurzu 4IT101 pro školní rok 2014/2015
 */
public class Vec
{
//== Datové atributy (statické i instancí)======================================
    private String jmeno;
    private boolean prenositelna;
    private Map<String, Vec> seznamVeci;

//##############################################################################
//== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *
     */
    public Vec (String jmeno, boolean prenositelna) {
		this.jmeno = jmeno;
		this.prenositelna = prenositelna;
                seznamVeci          = new HashMap<>();
	}



//== Nesoukromé metody (instancí i třídy) ===============================================
//== Soukromé metody (instancí i třídy) ===========================================
    public String getJmeno () {
		return jmeno;
	}
	public boolean jePrenositelna() {
		return prenositelna;
	}
    /**
     *  Metoda vloží věc do seznamu věcí ve věci
     *
     *@param  věc, kterou chceme vložit
     */
    public void vlozVec(Vec vec){   
        seznamVeci.put(vec.getJmeno(), vec);
    }
    /**
     * metoda vrací, seznam věcí ve věci
     * 
     * @return   seznam věcí
     */
    public Map<String, Vec> vratSeznamVeci(){
        return seznamVeci;
    }

}

