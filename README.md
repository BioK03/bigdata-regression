<img src="http://gblogs.cisco.com/fr-datacenter/wp-content/uploads/sites/14/2013/09/hadoop-elephant_logo.png" alt="Hadoop Logo" height="200"/>

# hadoop-regression

This repo is an example of Hadoop Map-Combiner-Reduce operation over CSV files.

Regression is a Java program that gathers tree data from arbres2.csv file, representing a tree sample of Paris, FR.

The goal of this is to calculate a covariance between the age and the height of the tree.

The covariance of this example is about 54, so the two variables are linked.

## File structure

The arbres2.csv is structured like this :

Geopoint | District | Type | Species | Family | Plantation year | Height | Circonference | Address | Common name | Variety | Object Id | Place
--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---

## Main goal

The main goal of this project is to calculate a correlation coefficient between :
 * Age and Circumference
 * Age and Height
 * Height and Circumference

## Raw results

Note that any illogical result was not included. That's why the sample size (n) is different for each analysis, and results for the same variable are not the same.

### Age and Circumference

X = Age

Y = Circumference
```
n	71.0
Sx	141555.0
Sx²	2.82228565E8
X Variance	81.35092243552208
Sy	24851.0
Sy²	1.0252641E7
Sum X x Y	4.9485892E7
X average	1993.7323943661972
X² average	3975050.2112676054
Y average	350.01408450704224
Y² average	144403.39436619717
X x Y average	696984.3943661972
X variance	81.35092243552208
Y variance	21893.535012894266
Covariance	-850.024399920716
Corrélation r	-0.6369307352754149
β0	21182.244763474915
β1	-10.448860006405418
yi = 21182.244763474915 + -10.448860006405418 * xi + ei	0.0
```

### Age and Height

X = Age

Y = Height
```
n	76.0
Sx Age	151558.0
Sx² Age	3.02240804E8
Variance Age	82.01869806088507
Sx Height	142109.0
Sx² Height	2.65895843E8
Sum Age x Height	2.83395664E8
Age average	1994.1842105263158
Age² average	3976852.6842105263
Height average	1869.8552631578948
Height² average	3498629.513157895
Age x Height average	3728890.3157894737
Age variance	82.01869806088507
Height variance	2270.8079986148514
Covariance	54.474030470941216
Corrélation r	0.12622426956149366
β0	545.3859163238353
β1	0.6641659982276654
yi = 545.3859163238353 + 0.6641659982276654 * xi + ei	0.0
```

### Height and Circumference

X = Height

Y = Circumference
```
n	71.0
Sx	132666.0
Sx²	2.48054524E8
X Variance	2301.85439396929
Sy	24851.0
Sy²	1.0252641E7
Sum X x Y	4.6260578E7
X average	1868.5352112676057
X² average	3493725.6901408453
Y average	350.01408450704224
Y² average	144403.39436619717
X x Y average	651557.4366197183
X variance	2301.85439396929
Y variance	21893.535012894266
Covariance	-2456.204721285496
Corrélation r	-0.34599330287741664
β0	2343.843503008878
β1	-1.067054774498593
yi = 2343.843503008878 + -1.067054774498593 * xi + ei	0.0
```

## Possible Interpretation

 :warning: Due to a large diversity of the trees' species in Paris, these results are quite different (Or even the opposite) that with a realistic sample.
 
 
 * In Paris, an older tree has a smaller circumference that a young one.
 * Paris' trees get taller with the time.
 * In this sample, a taller one has a smaller circumference that a smaller one.
 
These conclusions might be conducted with the following facts :
 * Trees planted along streets rarely exceed 80 years.
 * Trees are cut. Their aerial part is controlled in particular.
 * 1 500 trees are replaced every year, 1 500 others are planted.
 * The first planting roadside trees in Paris date back to 1597.
 * 93 700 trees are planted in Greater Paris.
 * Paris want to diversify tree species to limitate the spread of epidemics.
 * Many ochards in Paris' schools.
 * The older tree in Paris is a Robinier planted in 1601 (Height: 15m, Circumference : 3.5m)
 
Source : http://www.paris.fr/arbres 

