/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author Matías
 */
public class Comparador implements Comparator{
    
    private Vector<Vertex> v;
    
    public Comparador(Vector<Vertex> lista){
        v=lista;
    }
    
    public int compare(Object o1, Object o2) {
        Triangle t1 = (Triangle)(o1);
        Triangle t2 = (Triangle)(o2);
        double media_t1 = (v.get(t1.getV1()).getZ()+v.get(t1.getV2()).getZ()+v.get(t1.getV3()).getZ())/3;
        double media_t2 = (v.get(t2.getV1()).getZ()+v.get(t2.getV2()).getZ()+v.get(t2.getV3()).getZ())/3;
        
        if (media_t1<media_t2){
            return -1;
        }
        else if (media_t1>media_t2){
            return 1;
        }
        else{return 0;}
     
    }
    
}
