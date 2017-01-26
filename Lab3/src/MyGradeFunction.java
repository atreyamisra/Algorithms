//am73676_Misra_Atreya
public class MyGradeFunction implements GradeFunction{
	private int numClasses;
    private int maxGrade;
    
    public MyGradeFunction(int n, int g){
	    this.numClasses = n;
	    this.maxGrade = g;
    }
	
	public int grade(int classID, int hours) {
		return Math.min(hours,maxGrade);
	}

}
