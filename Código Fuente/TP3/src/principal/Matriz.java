/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;


/**
 *
 * @author Matías
 */
public class Matriz {
    private double mat[][];
    private double rot_x[][];
    private double rot_y[][];
    private double rot_z[][];
    
    private int size=4;
    
    @SuppressWarnings("empty-statement")
   
    public Matriz(){
        mat = new double[size][size];
        identar(mat);
        rot_x = new double[size][size];
        identar(rot_x);
        rot_y = new double[size][size];
        identar(rot_y);
        rot_z = new double[size][size];
        identar(rot_z);
     
        
    }
    
    public void identar(double[][] matriz){
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (i==j)
                    matriz[i][j]=(double)(1);
                else matriz[i][j]=(double)(0);
            }
        }
    }
   
    
    public void escalar(double escala){
        identar(mat);
        mat[0][0] = (double)(escala);
        mat[1][1] = (double)(escala);
        mat[2][2] = (double)(escala);
    }
    
    public void rotacionZ(double angulo){
        identar(rot_z);
        rot_z[0][0]=(double)(Math.cos(angulo));
        rot_z[0][1]=(double)(-Math.sin(angulo));
        rot_z[1][0]=(double)(Math.sin(angulo));
        rot_z[1][1]= (double)(Math.cos(angulo));
    }
    
    public void rotacionX(double angulo){
        identar(rot_x);
        rot_x[1][1]=(double)(Math.cos(angulo));
        rot_x[1][2]=(double)(-Math.sin(angulo));
        rot_x[2][1]=(double)(Math.sin(angulo));
        rot_x[2][2]= (double)(Math.cos(angulo));
    }
    
    public void rotacionY(double angulo){
        identar(rot_y);
        rot_y[0][0]=(double)(Math.cos(angulo));
        rot_y[2][0]=(double)(-Math.sin(angulo));
        rot_y[0][2]=(double)(Math.sin(angulo));
        rot_y[2][2]= (double)(Math.cos(angulo));
    }
   
   
    
    public Vertex aplicarRotacion(Vertex v){
        double primera[][] = productoMatrizPorMatriz(rot_x,rot_y);
        double compuesta[][] = productoMatrizPorMatriz(primera,rot_z);
        return (productoMatrizPorVertice(v,compuesta));
        
    }
    
    public Vertex aplicarEscalar(Vertex v){
        return (productoMatrizPorVertice(v,mat));
    }
    
    private static double[][] productoMatrizPorMatriz(double[][] M1, double[][] M2) {
		double res[][] = new double[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++)
					res[i][j] = res[i][j] + M1[i][k] * M2[k][j];
			}
		}
		return res;
	}

    private Vertex productoMatrizPorVertice(Vertex v, double[][] matriz) {
		Vertex res = new Vertex(0,0,0);
		res.setX(matriz[0][0] * v.getX() + matriz[0][1] * v.getY() + matriz[0][2] * v.getZ() + matriz[0][3] * 1);
		res.setY(matriz[1][0] * v.getX() + matriz[1][1] * v.getY()+ matriz[1][2] *v.getZ()  + matriz[1][3] * 1);
		res.setZ(matriz[2][0] * v.getX() + matriz[2][1] * v.getY() + matriz[2][2] * v.getZ() + matriz[2][3] * 1);
		return res; 
	}
    
    
}
