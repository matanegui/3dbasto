/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.util.Vector;

/**
 *
 * @author Matías
 */
public class Triangle {
    int v1;
    int v2;
    int v3;

    public Triangle(int v1, int v2, int v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public int getV3() {
        return v3;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public void setV3(int v3) {
        this.v3 = v3;
    }
    
    public Vertex getNormal(Vector<Vertex> ver){
	Vertex u = ver.get(v2).resta(ver.get(v1));
	Vertex w= ver.get(v3).resta(ver.get(v1));
	return (u.productoVectorial(w));
	}
       
}
