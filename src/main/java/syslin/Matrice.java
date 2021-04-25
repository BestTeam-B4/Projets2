package syslin ;
/**
 *
 * @author thiba
 */
public class Matrice {
    //--------------- partie 1.1

    //Définition des attributs de la classe

    int nbrLig; //nombre de ligne
    int nbrCol; //nombre de colonne
    double[][] coeffs; //matrice
    
    //Constructeur
    Matrice(int nl, int nc)
    {
        this.nbrLig= nl;
        this.nbrCol= nc;
        this.coeffs=  new double[nl][nc];
    }
    
    public static void afficheTableau(Matrice m, int nl, int nc){
        for(int i=0; i<nl; i++){
             for(int j=0;j<nc;j++){
                    System.out.print(m.coeffs[i][j]+" ");
        }
        System.out.println();
    }
        
    }
    public double epsilon_pivot=10^-8;

   //Méthode .get
    public int getnbrCol(){
        return this.nbrCol;
    }
    public int getnbrLig(){
        return this.nbrLig;
    }
    public double[][] getcoeff(){
    double coeff[][]= this.coeffs;
    
    return coeff;
    }

    
    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

   //Méthode .set
   public void set(int i,int j ,double k){
       this.coeffs[i][j]=k;
       
   } 
   
   public static String formatDoubleFixe(double x) {
    return String.format("%+4.2E", x);
}
   public static String formatDouble(double x) {
    return formatDoubleFixe(x);
}
    public String toString() {
    
    String res = "";
    for (int i = 0; i < nbrLig; i++) {
        res = res + "[";
        for (int j = 0; j < nbrCol; j++) {
            res = res + formatDouble(this.get(i, j));
            if (j < nbrCol - 1) {
                res = res + " ";
            }
        }
        res = res + "]\n";
    }
    return res;
}
   //Méthode identité
    static Matrice identite(int n){
        Matrice id = new Matrice (n,n) ;
            for(int i = 0;i<n; i++){
               
                id.coeffs[i][i]=1;
            }
            return id;
    }
    //--------------- partie 1.2

    public Matrice MatriceNulle(int nbrLig, int nbrCol) {
        this.nbrLig = nbrLig;
        this.nbrCol = nbrCol;
        this.coeffs = new double[nbrLig][nbrCol];
        // il est inutile en java d'initialiser explicitement les coeffs à
        // zéro : java initialise automatiquement à zéro les tableaux
        // numériques.
        // !!! ce ne serait pas forcément le cas dans d'autres langages (ex C)
        return this;
    }
    
    //Méthode matTest1
    static Matrice matTest1(int n){
    Matrice m = new Matrice (n, n);
    double coeff=0;
    for (int i=0; i<n ; i++){
        for(int j=0; j<n; j++){
            m.coeffs[i][j]=coeff;
            coeff+=1;
    //équivalent à coeff=coeff+1
        }
    }
            
    return m;
    }
   
    //Méthode matTest2
    static Matrice matTest2(int n){
    Matrice m = matTest1(n);
    for(int i=0; i<n; i++){
        m.coeffs[i][i]=m.coeffs[i][i]*(-1);
    }
            
    return m;
    }
    
    //Méthode aleaUnOuDeux
    static Matrice aleaUnOuDeux(int nl, int nc){
        
        Matrice id= new Matrice(nl,nc);
        for(int i=0;i<nl;i++)
        {
            for(int j=0;j<nc;j++)
            {
                id.coeffs[i][j]=((int)(Math.random()*10))%2+1;

            }
        }
        return id;   
      }
    
    //Méthode matAleaZeroUnDeux
    static Matrice matAleaZeroUnDeux(int nl, int nc, double pz){
        Matrice m = new Matrice(nl, nc);
        for(int i=0; i<nl; i++) {
            for(int j=0; j<nc; j++) {
                
                m.coeffs[i][j]=Math.random();
                
                if (m.coeffs[i][j]<=pz) { 
                   m.coeffs[i][j]=0;
                }else{
                   m.coeffs[i][j]=((int)(Math.random()*10)%2)+1;
                }
            }
        }
        return m;
    }
    
    static Matrice creeVecteur(double[] t){
        Matrice m= new Matrice(1,t.length);
        for(int i=0;i<t.length;i++){
            m.coeffs[0][i] = t[i];        
        
    }
        return m;
}
    public Matrice concatLig(Matrice m ){

        if (this.getnbrCol() != m.getnbrCol()) {
            throw new Error("les matrices doivent avoir même nombre de colonnes");
        }
        Matrice res = new Matrice(this.getnbrLig() + m.getnbrLig(), this.getnbrCol());
        for (int i = 0; i < res.getnbrLig(); i++) {
            for (int j = 0; j < res.getnbrCol(); j++) {
                if (i < this.getnbrLig()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m.get(i - this.getnbrLig(), j));
                }
            }
        }
        return res;
    }

    public Matrice concatCol(Matrice m2) {
      
        if (this.getnbrLig() != m2.getnbrLig()) {
            throw new Error("les matrices doivent avoir même nombre de lignes");
        }
        Matrice res = new Matrice(this.getnbrLig(), this.getnbrCol() + m2.getnbrCol());
        for (int i = 0; i < res.getnbrLig(); i++) {
            for (int j = 0; j < res.getnbrCol(); j++) {
                if (j < this.getnbrCol()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m2.get(i, j - this.getnbrCol()));
                }
            }
        }
        return res;
    }
    //--------------- partie 2.2

    public Matrice subLignes(int ligMin, int ligMax) {
        if (ligMin < 0 || ligMax < ligMin || ligMax >= this.getnbrLig()) {
            throw new Error("indices lignes invalides");
        }
        Matrice res = new Matrice(ligMax - ligMin + 1, this.getnbrCol());
        for (int lig = 0; lig < res.getnbrLig(); lig++) {
            for (int col = 0; col < res.getnbrCol(); col++) {
                res.set(lig, col, this.get(ligMin + lig, col));
            }
        }
        return res;
    }

    public Matrice subCols(int colMin, int colMax) {
        if (colMin < 0 || colMax < colMin || colMax >= this.getnbrCol()) {
            throw new Error("indices colonnes invalides");
        }
        Matrice res = new Matrice(this.getnbrLig(), colMax - colMin + 1);
        for (int lig = 0; lig < res.getnbrLig(); lig++) {
            for (int col = 0; col < res.getnbrCol(); col++) {
                res.set(lig, col, this.get(lig, colMin + col));
            }
        }
        return res;
    }

    public Matrice copie() {
        return this.subLignes(0, this.getnbrLig() - 1);
    }

    //--------------- partie 2.3
    public Matrice transposee() {
        Matrice res = new Matrice(this.getnbrCol(), this.getnbrLig());
        for (int i = 0; i < res.getnbrLig(); i++) {
            for (int j = 0; j < res.getnbrCol(); j++) {
                res.set(i, j, this.get(j, i));
            }
        }
        return res;
    }

    //--------------- partie 2.4
    public Matrice metAuCarre() {
        return this.concatCol(Matrice.identite(this.getnbrLig())).concatLig(
                Matrice.identite(this.getnbrCol()).concatCol(this.transposee()));
    }

    public static int intAlea(int bmin, int bmax) {
        return (int) (Math.random() * (bmax - bmin + 1)) + bmin;
    }

    public static void test2() {
        System.out.println("----------- test 2 --------------");
        int nl = intAlea(1, 4), nc = intAlea(1, 4);
        double pz = 0.33;
        Matrice m = Matrice.matAleaZeroUnDeux(nl, nc, pz);
        Matrice mc = m.metAuCarre();
        Matrice mbis = mc.subLignes(0, m.getnbrLig() - 1).subCols(0, m.getnbrCol() - 1);
        System.out.println("m : matrice de base : ");
        System.out.println(m);
        System.out.println("mc : mise au carré : ");
        System.out.println(mc);
        System.out.println("mbis : (sub de mc) : ");
        System.out.println(mbis);
    }

    //--------------- partie 3.1
   
    public Matrice add(Matrice m2) {
        if (this.getnbrLig() != m2.getnbrLig() || this.getnbrCol() != m2.getnbrCol()) {
            throw new Error("tailles incompatibles pour add");
        }
        Matrice m = new Matrice(this.getnbrLig(), this.getnbrCol());
        for (int i = 0; i < m.getnbrLig(); i++) {
            for (int j = 0; j < m.getnbrCol(); j++) {
                m.set(i, j, this.get(i, j) + m2.get(i, j));
            }
        }
        return m;
    }

    //--------------- partie 3.2
    
    public Matrice opp() {
        Matrice res = new Matrice(this.getnbrLig(), this.getnbrCol());
        for (int i = 0; i < res.getnbrLig(); i++) {
            for (int j = 0; j < res.getnbrCol(); j++) {
                res.set(i, j, -this.get(i, j));
            }
        }
        return res;
    }

    //--------------- partie 3.3
   
    public Matrice moins(Matrice m2) {
        return this.add(m2.opp());
    }

    //--------------- partie 3.4
    public Matrice mult(Matrice m2) {
        if (this.getnbrCol() != m2.getnbrLig()) {
            throw new Error("tailles incompatibles pour mult");
        }
        Matrice m = new Matrice(this.getnbrLig(), m2.getnbrCol());
        for (int i = 0; i < m.getnbrLig(); i++) {
            for (int j = 0; j < m.getnbrCol(); j++) {
                double nb = 0;
                for (int k = 0; k < this.getnbrCol(); k++) {
                    nb = nb + this.get(i, k) * m2.get(k, j);
                }
                m.set(i, j, nb);
            }
        }
        return m;
    }
    //--------------- partie 3.5

    public static void test3() {
        System.out.println("----------- test 3 --------------");
        Matrice m = Matrice.matTest1(3);
        System.out.println("mat M : ");
        System.out.println(m);
        System.out.println("M + M^2 :");
        System.out.println(m.add(m.mult(m)));
    }

    //PIVOT DE GAUSS
    //partie 4


    public int permutLigne(int i1, int i2){

        int p=0;

        for( int j = 0; j<this.nbrCol; j++){

            if (this.coeffs[i1][j] ==this.coeffs[i2][j]){
                p=1;
            }else{
                p = -1;
                break;
            }
        }
       
        return p;
    }

    public void permutLign(int l1,int l2){

    double[] temp=new double[this.nbrCol];

        for(int i=0;i<this.nbrCol;i++){
            temp[i]=this.coeffs[l1][i];
            this.coeffs[l1][i]=this.coeffs[l2][i];
            this.coeffs[l2][i]=temp[i];

        }

    }

    public Matrice transvection(int i1, int i2){

        if (this.coeffs[i1][i1] == 0){
            throw new Error("Division par 0");

        }
        double p = (this.coeffs[i2][i1])/(this.coeffs[i1][i1]);

        for (int j=0 ; j< this.nbrCol; j++){
            if(j==i1){
                this.coeffs[i2][j] = 0;
            }else{
                this.coeffs[i2][j]=this.coeffs[i2][j]-p*this.coeffs[i1][j];
            }
        }
        return this;
    }

    public int lignePlusGrandPivot(int e){
    
    int i=e;
    double imax=this.coeffs[i][e];
    int compteur0=0;
    int compteurl=0;
    int test=0;

    while ((test !=-1)&& (i<this.nbrLig)){
      
        if (this.coeffs[i][e]>imax){
            imax=this.coeffs[i][e];
            compteurl=i;
        }else{
            if(this.coeffs[i][e]==0){
                compteur0++;
            }
        }
        i++;
    }
    if(compteur0==this.nbrLig-e){
        test=-1;
    }else{
        test=compteurl;
    }

    if (test<=epsilon_pivot){
        test=-1;
    }
    
    return test;

        
    }

    public ResGauss descenteGauss(){
        
        int rang;
        int signature;
        int lpivot;
        
        //Pour chaque colonne, on cherche le plus grand pivot
        for(int i=0;i<this.nbrCol;i++){
            
            lpivot=lignePlusGrandPivot(i);
            

        //On permute ensuite les ligne afin de mettre le pivot au bon endroit
            this.permutLign(lpivot,i);
        //transvection entre la ligne afin de "supprimer" les éléments 
          

        
        
        }
        ResGauss r= new ResGauss(1,1);

        return r;
    }
    
    public static void testdebutpartie4(Matrice m){
        m.transvection(0,2);
        System.out.println();
        afficheTableau(m,m.getnbrLig(),m.getnbrLig());
  
  
        int test;
        int l1 = 1;
        int l2 = 2;
       test= m.permutLigne(l1,l2);
       System.out.println(test);
      
    }
    public static void testpivot(Matrice m){
        int t;
        t=m.lignePlusGrandPivot(1);
        System.out.println(t);
    }

    //Méthode principale main
    
    public static void main(String[] args){
            int nl=3, nc=3;
          Matrice m = new Matrice(nl,nc);
          //Affiche du tableau des coefficients
          for (int i=0; i< nl; i++){
              for (int j=0; j< nc; j++){
                  
                  m.set(i,j,Lire.d());
                
              }
        
      }
      
      afficheTableau(m,m.getnbrLig(),m.getnbrLig());
      testpivot(m);
     
}
}