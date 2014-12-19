package hw2;

/**
 *
 * @author dm
 */
public class TableDeHachage {
    Liste[] lists;

    public TableDeHachage(int n) {
        lists = new Liste[n];
        for(int i = 0; i < lists.length; i++) lists[i] = new Liste();
    }
    
    public void ajoute(Objet o){
        int m = o.hash() % lists.length;
        if(m < 0) m += lists.length;
        
        lists[m].ajouteTete(o);
    }
    
    public boolean contient(Objet o){
        
        for(Liste l : lists){
            if(l.contient(o)){
                return true;
            }
        }
        return false;
    }
    
    
    public int[] remplissageMax(){
        int m = 0, i = 0;
        
        for(int x = 0; x < lists.length; x++){
            if(lists[x].longueur() > m){
                m = lists[x].longueur();
                i = x;
            }
        }
        
        return new int[]{i,m};
    }
    
}
