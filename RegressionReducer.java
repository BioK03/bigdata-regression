import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


/**
 * Cette classe effectue la réduction de toutes les paires ("V", (n, Σx, Σx²)),
 * calcule la variance et émet une paire ("V", Vx)
 */
public class RegressionReducer
        extends Reducer<Text, Regression, Text, DoubleWritable>
{
    /* valeur de sortie, la clé est la même qu'en entrée */
    private DoubleWritable valeurS = new DoubleWritable();

    /** traite une liste de paires produites par tous les mappers et combiners */
    @Override
    public void reduce(Text cleI, Iterable<Regression> valeursI, Context context)
            throws IOException, InterruptedException
    {
        
        // calculer la valeur de sortie
        Regression regression = new Regression();
        for (Regression valeurI : valeursI) {
            regression.add(valeurI);
            
        }
        
        // 1a
        if(regression.getN() < 2){
        	valeurS.set(0.0);
        	context.write(new Text("n < 2"), valeurS);
        	return;
        }
        
        valeurS.set(regression.getN());
        context.write(new Text("n"), valeurS);
        
        // 1b
        valeurS.set(regression.getSxAge());
        context.write(new Text("Sx Age"), valeurS);
        
        // 1c
        valeurS.set(regression.getSx2Age());
        context.write(new Text("Sx² Age"), valeurS);
        
        // Variance
        valeurS.set(regression.getAgeVariance());
        context.write(new Text("Variance Age"), valeurS);
        
        // 1d
        valeurS.set(regression.getSxHeight());
        context.write(new Text("Sx Height"), valeurS);
        
        // 	1e
        valeurS.set(regression.getSx2Height());
        context.write(new Text("Sx² Height"), valeurS);
        
        // 1f 
        valeurS.set(regression.getSumAgexTaille());
        context.write(new Text("Sum Age x Height"), valeurS);
        
        // 2a
        valeurS.set(regression.getAgeMoyenne());
        context.write(new Text("Age average"), valeurS);
        
        // 2b
        valeurS.set(regression.getAge2Moyenne());
        context.write(new Text("Age² average"), valeurS);
        
        // 	2c
        valeurS.set(regression.getHeightMoyenne());
        context.write(new Text("Height average"), valeurS);
        
        // 	2d
        valeurS.set(regression.getHeight2Moyenne());
        context.write(new Text("Height² average"), valeurS);
        
        // 2e
        valeurS.set(regression.getSumAgexTaille() / regression.getN());
        context.write(new Text("Age x Height average"), valeurS);
        
        // 3a
        valeurS.set(regression.getAgeVariance());
        context.write(new Text("Age variance"), valeurS);
        
        // 3b
        valeurS.set(regression.getHeightVariance());
        context.write(new Text("Height variance"), valeurS);
        
        // 3c
        valeurS.set(regression.covariance());
        context.write(new Text("Covariance"), valeurS);
    }
}
