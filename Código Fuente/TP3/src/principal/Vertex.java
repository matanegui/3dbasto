/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;


public class Vertex {
    private double x;
    private double y;
    private double z;
    public double modulo;
    
    public Vertex(double posx, double posy, double posz){
        x=posx;
        y=posy;
        z=posz;
        modulo =(double) (Math.sqrt(x * x + y * y + z * z));
        
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setModulo(double modulo) {
        this.modulo = modulo;
    }

    public double getModulo() {
        return modulo;
    }

    @Override
    public String toString() {
        return "Vertex{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
    public Vertex productoVectorial(Vertex v) {
		double a = y * v.z - v.y * z;
		double b = -(x * v.z - v.x * z);
		double c = x * v.y - v.x * y;
		return new Vertex(a, b, c);
	}
    
    public Vertex resta(Vertex v) {
		double a = x - v.x;
		double b = y - v.y;
		double c = z - v.z;
		return new Vertex(a, b, c);
	}
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public void normalizar() {
		x /= modulo;
		y /= modulo;
		z /= modulo;
		modulo = 1;
	}
    
    public double productoEscalar(Vertex v) {
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
    
    public double getCosAnguloCon(Vertex v) {
		double productoEscalar = productoEscalar(v);
		return (double)(productoEscalar / (modulo * v.getModulo()));
	}
}
