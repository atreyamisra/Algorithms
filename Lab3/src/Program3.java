//am73676_Misra_Atreya
public class Program3 implements IProgram3 {

    private int numClasses;
    private int maxGrade;
    GradeFunction gf;

    public Program3() {
    	 this.numClasses = 0;
         this.maxGrade = 0;
         this.gf = null;
    }

    public void initialize(int n, int g, GradeFunction gf) {
    	 this.numClasses = n;
         this.maxGrade = g;
         this.gf = gf;
    }
    
    public int[] computeHours(int totalHours) {
    	int[] computeHours = new int[numClasses];
    	int[][] dynamic = new int[numClasses+1][totalHours+1];
    	int[][] hours = new int[numClasses+1][totalHours+1];
    	int i, j, k, h = totalHours, n = numClasses;
    	for(i=1; i <= numClasses; i++){
    		for(j = 0; j <= totalHours; j++){
    			int max1, max2 = 0, maxindex = 0;
    			for(k=0; k<=j; k++){
    				max1 = gf.grade(i-1, k) + dynamic[i-1][j - k];
    				if(max1>max2){
    					max2=max1;
    					maxindex = k;
    				}
   				}
   				dynamic[i][j] = max2;
   				hours[i][j] = maxindex;
   			}
    	}
    	while(h>=0){
    		computeHours[n-1] = hours[n][h];
    		h = h - hours[n][h];
    		n--;
    		if(n==0)
    			break;
    	}
    	return computeHours;
    }

    public int[] computeGrades(int totalHours) {
    	int[] computeGrades = new int[numClasses];
  	  	int[][] dynamic = new int[numClasses+1][totalHours+1];
  	  	int[][] hours = new int[numClasses+1][totalHours+1];
  	  	int i, j, k, h = totalHours, n = numClasses;
  	  	for(i=1; i <= numClasses; i++){
  	  		for(j = 0; j <= totalHours; j++){
  	  			int max1, max2 = 0, maxindex = 0;
  	  			for(k=0; k<=j; k++){
  	  				max1 = gf.grade(i-1, k) + dynamic[i-1][j - k];
  	  				if(max1>max2){
  	  					max2=max1;
  	  					maxindex = k;
  	  				}
  	  			}
  	  			dynamic[i][j] = max2;
  	  			hours[i][j] = maxindex;
  	  		}
  	  	}
  	  	while(h>=0){
  	  		computeGrades[n-1] = gf.grade(n-1, hours[n][h]);
  	  		h = h - hours[n][h];
  	  		n--;
  	  		if(n==0)
  	  			break;
  	  	}
  	  	return computeGrades;
    }
}
