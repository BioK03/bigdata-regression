import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;


/**
 * Cette classe est produite par les Mappers à destination du Reducer
 * Elle représente un triplet des sommes partielles (n, Σx, Σx²) permettant
 * de calculer la variance de n valeurs x
 */
public class Regression implements Writable
{
    /* variables membre */
    private long n;
    private double SxAge;
    private double Sx2Age;
    
    private double SxHeight;
    private double Sx2Height;
    
    private double SumAgexTaille;

    /** constructeur */
    public Regression()
    {
        n = 0L;
        SxAge = 0.0;
        Sx2Age = 0.0;
        
        SxHeight = 0.0;
        Sx2Height = 0.0;
        
        SumAgexTaille = 0.0;
    }

    /** écrit this sur sortie, méthode de l'interface Writable */
    public void write(DataOutput sortie) throws IOException
    {
        sortie.writeLong(n);
        sortie.writeDouble(SxAge);
        sortie.writeDouble(Sx2Age);
        
        sortie.writeDouble(SxHeight);
        sortie.writeDouble(Sx2Height);
        
        sortie.writeDouble(SumAgexTaille);
    }

    /** lit this à partir de l'entree, méthode de l'interface Writable */
    public void readFields(DataInput entree) throws IOException
    {
        n = entree.readLong();
        SxAge = entree.readDouble();
        Sx2Age = entree.readDouble();
        
        SxHeight = entree.readDouble();
        Sx2Height = entree.readDouble();
        
        SumAgexTaille = entree.readDouble();
    }

    /**
     * initialise this à (1, valeur, valeur²)
     * @param valeur à mettre dans this
     */
    public void set(double age, double height)
    {
        n = 1L;
        SxAge = age;
        Sx2Age = age*age;
        
        SxHeight = height;
        Sx2Height = height*height;
        
        SumAgexTaille = age*height;
    }
    

    /**
     * ajoute autre à this
     * @param autre
     */
    public void add(Regression autre)
    {
        n += autre.n;
        SxAge += autre.SxAge;
        Sx2Age += autre.Sx2Age;
        
        SxHeight += autre.SxHeight;
        Sx2Height += autre.Sx2Height;
        
        SumAgexTaille += autre.SumAgexTaille;
    }
    
    public double getSxAge(){
    	return SxAge;
    }
    
    public double getSx2Age(){
    	return Sx2Age;
    }
    
    public double getSxHeight(){
    	return SxHeight;
    }
    
    public double getSx2Height(){
    	return Sx2Height;
    }
    
    public double getSumAgexTaille(){
    	return SumAgexTaille;
    }
    

    /**
     * calcule la moyenne représentée par this
     * @return moyenne des valeurs ajoutées à this
     */
    public double getAgeMoyenne()
    {
        return SxAge/n;
    }
    
    public double getAge2Moyenne()
    {
        return Sx2Age/n;
    }
    
    public double getHeightMoyenne()
    {
        return SxHeight/n;
    }
    
    public double getHeight2Moyenne()
    {
        return Sx2Height/n;
    }

    /**
     * calcule la variance représentée par this
     * @return variance des valeurs ajoutées à this
     */
    public double getAgeVariance()
    {
        double Mx = SxAge / n;
        double Mx2 = Sx2Age / n;
        return Mx2 - Mx * Mx;
    }
    
    public double getHeightVariance()
    {
        double Mx = SxHeight / n;
        double Mx2 = Sx2Height / n;
        return Mx2 - Mx * Mx;
    }
    
    public double covariance()
    {
    	return SumAgexTaille / n - getAgeMoyenne() * getHeightMoyenne();
    }
    
    public long getN(){
    	return n;
    }
    
    
}
