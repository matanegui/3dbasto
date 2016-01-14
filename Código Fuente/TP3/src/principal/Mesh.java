/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Matías
 */
public class Mesh {
    private static final double MINVALOR=-9999999;
    private static final double MAXVALOR=9999999;
    private Vector<Vertex> vertices;
    private Vector<Triangle> triangulos;
    private Vertex centro;
    private double x_min = MAXVALOR;
    private double y_min = MAXVALOR;
    private double x_max = MINVALOR;
    private double y_max = MINVALOR;
    private double z_min = MAXVALOR;
    private double z_max = MINVALOR;
    
    private int cantTriangulos=0;
    
    private double alto=0;
    private double ancho=0;
    private double profundidad=0;
    
    private double dimension;
    
    public Mesh(){
        vertices = new Vector<Vertex>();
        triangulos = new Vector<Triangle>();
        centro = new Vertex(0,0,0);
    }

    public Vector<Vertex> getVertices() {
        return vertices;
    }
    
    public Vertex getVertice(int pos){
        return (vertices.get(pos));
    }

    public void setVertices(Vector<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Vector<Triangle> getTriangulos() {
        return triangulos;
    }

    public void setTriangulos(Vector<Triangle> triangulos) {
        this.triangulos = triangulos;
    }

    public double getX_min() {
        return x_min;
    }

    public void setX_min(double x_min) {
        this.x_min = x_min;
    }

    public double getY_min() {
        return y_min;
    }

    public void setY_min(double y_min) {
        this.y_min = y_min;
    }

    public double getX_max() {
        return x_max;
    }

    public void setX_max(double x_max) {
        this.x_max = x_max;
    }

    public double getY_max() {
        return y_max;
    }

    public void setY_max(double y_max) {
        this.y_max = y_max;
    }

    public double getZ_min() {
        return z_min;
    }

    public void setZ_min(double z_min) {
        this.z_min = z_min;
    }

    public double getZ_max() {
        return z_max;
    }

    public void setZ_max(double z_max) {
        this.z_max = z_max;
    }
    
    public void actualizarMinMax(){
        x_min = MAXVALOR;
        y_min = MAXVALOR;
        x_max = MINVALOR;
        y_max = MINVALOR;
        z_min = MAXVALOR;
        z_max = MINVALOR;
        for (Vertex v: vertices){
            double a = v.getX();
            double b = v.getY();
            double c = v.getZ();
            if (a<x_min){
                x_min=a;
            }
            if (a>x_max){
                x_max=a;
            }
            if (b<y_min){
                y_min=b;
            }
            if (b>y_max){
                y_max=b;
            }
            if (c<z_min){
                z_min=c;
            }
            if (c>z_max){
                z_max=c;
            }
        }
        this.actualizarValores();
    }
    
    public void actualizarValores(){
        ancho=x_max-x_min;
        alto=y_max-y_min;
        profundidad=z_max-z_min;
        
        dimension=Math.max(ancho,alto);
        dimension=Math.max(dimension,profundidad);
        
    }
    
    public void normalizar(){
        for (Vertex v: vertices){
            v.setX(v.getX()/dimension);
            v.setY(v.getY()/dimension);
            v.setZ(v.getZ()/dimension);
        }
        
        x_min=x_min/dimension;
        x_max=x_max/dimension;
        y_min=y_min/dimension;
        y_max=y_max/dimension;
        z_min=z_min/dimension;
        z_max=z_max/dimension;
        
        ancho=ancho/dimension;
        alto=alto/dimension;
        profundidad=profundidad/dimension;
        
        
        dimension=1;
    
    }

    public Vertex getCentro() {
        return centro;
    }

    public void setCentro(Vertex centro) {
        this.centro = centro;
    }
    
    public void centrar(){
        
        double despx= (double)0.5-(ancho/2)-x_min;
        double despy= (double)0.5-(alto/2)-y_min;
        double despz= (double)0.5-(profundidad/2);
        for (Vertex v : vertices){
            v.setX(v.getX()+despx);
            v.setY(v.getY()+despy);
            v.setZ(v.getZ()+despz);
        }
        x_min=x_min+despx;
        x_max=x_max+despx;
        y_min=y_min+despy;
        y_max=y_max+despy;
        z_min=z_min+despz;
        z_max=z_max+despz;
        
        centro = new Vertex((ancho/2)+x_min,(alto/2)+y_min,(profundidad/2)+z_min);
        actualizarValores();
        

    }
    
    public void cargarMesh(File archivo) throws FileNotFoundException, IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String line = br.readLine();
        
        vertices= new Vector<>();
        triangulos = new Vector<>();
        //transformacion = new Matriz();
        
        //lectura del archivo
        
        //leo los datos de element groups
        while (!line.equals("*ELEMENT GROUPS")) {
            line = br.readLine();
        }
        line = br.readLine();
        line = br.readLine();
        Scanner s = new Scanner(line);
        s.nextInt();
        cantTriangulos=s.nextInt();
           
        //leo los triangulos
        while (!line.equals("*INCIDENCE")) {
            line = br.readLine();
        }
        line = br.readLine();
        line = br.readLine();
        
        while (!line.equals("*COORDINATES")) {
            
            if (line.equals("") || line.equals("\n")){
                
            }
            else{
                s = new Scanner(line);
                Triangle t = new Triangle(s.nextInt(),s.nextInt(),s.nextInt());
                triangulos.add(t);
            }
            line = br.readLine();
        }
        
        line = br.readLine();
        s = new Scanner(line);
        
        //CANT DE POLIGONOS
        cantTriangulos = s.nextInt();
        
        line = br.readLine();
        
        double a;
        double b;
        double c;
        int pos=0;
        x_min = 999999;
        y_min = 999999;
        x_max = -9999999;
        y_max = -9999999;
        z_min = 9999999;
        z_max = -9999999;
        
        vertices.add(new Vertex(0,0,0));
        
        while (line!=null && !line.equals("*FRONT_VOLUME")) {
           
            s = new Scanner(line);
            pos=s.nextInt();
            a = Float.parseFloat(s.next());
            b = Float.parseFloat(s.next());
            c = Float.parseFloat(s.next());
            
            if (a<x_min){
                x_min=a;
            }
            if (a>x_max){
                x_max=a;
            }
            if (b<y_min){
                y_min=b;
            }
            if (b>y_max){
                y_max=b;
            }
            if (c<z_min){
                z_min=c;
            }
            if (c>z_max){
                z_max=c;
            }
            Vertex v = new Vertex(a,b,c);
            vertices.add(pos, v);
            
            line = br.readLine();
        } 
         
      br.close();
      s.close();
      
     
    
    //CENTO EL OBJETO EN 0
    actualizarValores();

    normalizar();

    centrar();

   
    
    Collections.sort(triangulos, new Comparador(vertices));
    }
    }

    public int getCantTriangulos() {
        return cantTriangulos;
    }

    public void setCantTriangulos(int cantTriangulos) {
        this.cantTriangulos = cantTriangulos;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    public double getDimension() {
        return dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }
    
    public void rotar(Vertex r){
        ubicarEnCero();
        
        Matriz transformacion = new Matriz();
        transformacion.rotacionX(r.getX());
        transformacion.rotacionY(r.getY());
        transformacion.rotacionZ(r.getZ());
        
        for (Vertex v : vertices){
            
            Vertex aux = transformacion.aplicarRotacion(v);
            v.setX(aux.getX()+centro.getX());
            v.setY(aux.getY()+centro.getY());
            v.setZ(aux.getZ()+centro.getZ());
          
        }
        //ordeno por Z
       Collections.sort(triangulos, new Comparador(vertices));
      

    }
    
    public void transladar(Vertex trans){

          for (Vertex v : vertices){
            v.setX(v.getX()+trans.getX());
            v.setY(v.getY()+trans.getY());
            v.setZ(v.getZ()+trans.getZ());
          
        }
          
      actualizarMinMax();
      recalcularCentro();
    }
    
    public void escalar(Vertex trans){
        Matriz transformacion = new Matriz();
        transformacion.escalar(trans.getX());
        
        for (Vertex v : vertices){
            Vertex aux = new Vertex(0,0,0);
            aux=transformacion.aplicarEscalar(v);
            v.setX(aux.getX());
            v.setY(aux.getY());
            v.setZ(aux.getZ());
        }
        
        actualizarMinMax();
        reubicar();
    }
    
    public void reubicar(){
        for (Vertex v : vertices){
            v.setX(v.getX()+centro.getX()-ancho/2-x_min);
            v.setY(v.getY()+centro.getY()-alto/2-y_min);
            v.setZ(v.getZ()+centro.getZ()-profundidad/2-z_min);
        }
    }
    
    public void ubicarEnCero(){
        for (Vertex v : vertices){
            v.setX(v.getX()-centro.getX());
            v.setY(v.getY()-centro.getY());
            v.setZ(v.getZ()-centro.getZ());
        }
    }
    
    public void recalcularCentro(){
        centro = new Vertex((ancho/2)+x_min,(alto/2)+y_min,(profundidad/2)+z_min);
    }
    

}
