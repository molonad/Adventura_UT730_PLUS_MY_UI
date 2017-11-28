package logika;

 


/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Alena Buchalcevova
 *@version    z kurzu 4IT101 pro školní rok 2014/2015  
 */
public class PrikazSeber implements IPrikaz
{
private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;
  /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude hledat aktuální místnost 
    */      
    public PrikazSeber(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }
    /**
     *  Provádí příkaz "seber". V aktuální místnosti hledá věc, která je předána jako parametr
     *  
     *
     *@param parametry - jako  parametr obsahuje jméno věci,
     *                          která se má sebrat.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo , tak ....
            return "Co mám sebrat? Musíš zadat jméno věci";
        }

        String jmenoVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec vec = aktualniProstor.odeberVec(jmenoVeci);

        if (vec == null) {
            return "Taková věc tu není.";
        }
        if(vec.jePrenositelna()==false)
        {
            
            aktualniProstor.vlozVec(vec);  
            return "Tato věc nelze sebrat.\n";
        }
        
          if (batoh.vlozVec(vec)) {
              
             return "Sebral jsi " + jmenoVeci;
          }
            
        else {
	     aktualniProstor.vlozVec(vec);
            return "Věc nemůžeš sebrat, máš plný batoh. Můžeš z batohu něco vyhodit.\n";
            
        }
    }
    public String getNazev() {
        return NAZEV;
    }
    @Override
    public void updateHerniPlan() {
        plan.notifyAllObservers();
    }
}

