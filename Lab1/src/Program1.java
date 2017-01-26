/*
 * Name: Atreya Misra
 * EID: am73676
 */

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Matching problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    public boolean isStableMatching(Matching given_matching) {
        int i = 0;
        int j = 0;
        ArrayList<ArrayList<Integer>> apartmentPref = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        for(i =0; i < given_matching.getTenantCount(); i++){
        	tempList.add(-1);
        }
        for(i = 0; i < given_matching.getTenantCount(); i++){
        	apartmentPref.add(tempList);
        }
    	for(i = 0; i < given_matching.getLandlordCount(); i++){
        	int condos = given_matching.getLandlordOwners().get(i).size();
        	for(j = 0; j < condos; j++){
        		int whatOwn = given_matching.getLandlordOwners().get(i).get(j);
//        		for(k = 0; k < given_matching.getTenantCount(); k++){
//        			int currentPref = given_matching.getLandlordPref().get(i).get(k);
//        			System.out.println(given_matching.getLandlordPref().get(i));
//        			System.out.println(currentPref);
        			apartmentPref.set(whatOwn, given_matching.getLandlordPref().get(i));      			       			
//        		}
//       		apartmentPref.add(given_matching.getLandlordPref().get(i));
        	}
        }
    	ArrayList<Integer> matchingList = new ArrayList<Integer>();
        for(i =0; i < given_matching.getTenantCount(); i++){
        	matchingList.add(-1);
        }
    	matchingList = given_matching.getTenantMatching();
    	ArrayList<Integer> partList = new ArrayList<Integer>();
    	for(i =0; i < given_matching.getTenantCount(); i++){
        	partList.add(-1);
        }
    	int anotherone = 0;
    	for(i =0; i < given_matching.getTenantCount(); i++){
        	for(j=0; j <given_matching.getTenantCount(); j++){
        		if(j == given_matching.getTenantCount())
        			break;
    			try{
    				if(matchingList.get(j) == i){
            			anotherone = matchingList.get(j);
            			break;
            		}
    			}catch(IndexOutOfBoundsException e){
    				break;
    			}
        		
        	}
    		partList.set(anotherone, j);
        }
    	int counter = 0;

    	for(i = 0; i < given_matching.getTenantCount(); i++){
    		for(j = 0; j < given_matching.getTenantCount(); j++){
    			try{
    				if(given_matching.getTenantPref().get(i).get(matchingList.get(i)) > given_matching.getTenantPref().get(i).get(j))
            			counter++;
    			}catch(IndexOutOfBoundsException e){
    				break;
    			}
    			
        	}
    	}
    	if(counter > 0){
    		return false;
    	}
    	else
    		return true;
 /* TODO remove this line */
    }

    /**
     * Determines a solution to the Stable Matching problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableMatchingGaleShapley(Matching given_matching) {
        int i;
        int j;
        int k;
//        System.out.println(landlordPref);
//        System.out.println(given_matching.getLandlordOwners().get(0));
//        System.out.println(given_matching.getLandlordPref());
//        System.out.println(given_matching.getTenantPref());
        ArrayList<ArrayList<Integer>> apartmentPref = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        for(i =0; i < given_matching.getTenantCount(); i++){
        	tempList.add(-1);
        }
        for(i = 0; i < given_matching.getTenantCount(); i++){
        	apartmentPref.add(tempList);
        }
        for(i = 0; i < given_matching.getLandlordCount(); i++){
        	int condos = given_matching.getLandlordOwners().get(i).size();
        	for(j = 0; j < condos; j++){
        		int whatOwn = given_matching.getLandlordOwners().get(i).get(j);
//        		for(k = 0; k < given_matching.getTenantCount(); k++){
//        			int currentPref = given_matching.getLandlordPref().get(i).get(k);
//        			System.out.println(given_matching.getLandlordPref().get(i));
//        			System.out.println(currentPref);
        			apartmentPref.set(whatOwn, given_matching.getLandlordPref().get(i));      			       			
//        		}
//       		apartmentPref.add(given_matching.getLandlordPref().get(i));
        	}
        }
//        System.out.println(apartmentPref);
        ArrayList<Integer> matchingList = new ArrayList<Integer>();
        for(i =0; i < given_matching.getTenantCount(); i++){
        	matchingList.add(-1);
        }
 //       System.out.println(matchingList);
        //create arraylist so that list.get(t) returns tenant t's apartment
        //matchingList.set(t, a) sets t's apartment to a
        //given_matching.setTenantMatching(list); saves list
        int x=1;
        ArrayList<Integer> partList = new ArrayList<Integer>();
        for(i =0; i < given_matching.getTenantCount(); i++){
        	partList.add(-1);
        }
    	ArrayList<Integer> moveOn = new ArrayList<Integer>();
    	for(i =0; i < given_matching.getTenantCount(); i++){
        	moveOn.add(-1);
        }
        while(x == 1){
        	int tent = 0;
        	int best = 0;
        	int checker = 0;
        	int thishappenedsobreak = 0;       	
        	for(tent = 0; tent<given_matching.getTenantCount(); tent++){
        		if((matchingList.get(tent) != -1)){
            		tent++;
            	}
        		for(k =0; k<given_matching.getTenantCount(); k++){
            		for(i =0; i<given_matching.getTenantCount(); i++){
            			if(((matchingList.get(i) == matchingList.get(k)) || (matchingList.get(k) == -1)) && (i != k)){
            				checker = 1;
            				break;
                		}
            		}
            		if(checker == 1)
                		break;
            		checker = 2;
//            		System.out.println(matchingList);
  //          		System.out.println(partList);
            	}
            	if(checker == 2)
            		break;
        		for(i=0; i<given_matching.getTenantCount(); i++){
 //       			System.out.println(moveOn.get(tent));
            		if(given_matching.getTenantPref().get(tent).get(i) == 1 || i == moveOn.get(tent)){
//            			System.out.println(moveOn);
            			best = i;
            			moveOn.set(tent, best);
//            			System.out.println(moveOn);
            			break;
            		}
            	}
            	if((matchingList.get(tent) == -1) && (partList.get(best) == -1)){
            		matchingList.set(tent, best);
            		partList.set(best, tent);
            	}
            	else if((matchingList.get(best) != -1) && (partList.get(tent) != -1)){
            		if(apartmentPref.get(matchingList.get(best)).get(tent) < apartmentPref.get(matchingList.get(best)).get(partList.get(tent))){
                    	int iterator = 0;
                    	int ultimate = 0;
                    	for(iterator = 0; iterator <= given_matching.getTenantCount(); iterator++){
                    		
//                    		try{
                    			int catchOutOfBounds = matchingList.get(iterator);
                        		if(best == catchOutOfBounds)
                        			break;
//                			}catch(IndexOutOfBoundsException e){
//                				iterator--;
//                				int catchOutOfBounds = matchingList.get(iterator);
//                        		if(best == catchOutOfBounds)
//                        			break;
//                			}
                    	}

                    	
                    	if(ultimate == 0){
//                    		System.out.println(matchingList);
//                    		System.out.println(partList);
                    		matchingList.set(iterator, -1);
                    		matchingList.set(tent, best);
                    		partList.set(best, tent);
  //                  		System.out.println(matchingList);
    //                		System.out.println(partList);
                    	}                			               	                		
                    	if(thishappenedsobreak == 1)
                    		break;
            		}
            		else{
            			int sorry = 1;
                		for(sorry = 1; sorry < given_matching.getTenantCount(); sorry++){
                			for(best++; best<given_matching.getTenantCount(); best++){
                    			for(i=best; i<given_matching.getTenantCount(); i++){
                            		if(given_matching.getTenantPref().get(tent).get(i) == sorry){
                            			best = i;
                            			break;
                            		}
                            	}
                        		if((matchingList.get(tent) == -1) && (partList.get(best) == -1)){
                            		matchingList.set(tent, best);
                            		partList.set(best, tent);
                            		break;
                            	}
                    		}
                		}
            		}
            	}
            	else{
            		int sorry = 1;
            		for(sorry = 1; sorry < given_matching.getTenantCount(); sorry++){
            			for(best++; best<given_matching.getTenantCount(); best++){
                			for(i=best; i<given_matching.getTenantCount(); i++){
                        		if(given_matching.getTenantPref().get(tent).get(i) == sorry){
                        			best = i;
                        			break;
                        		}
                        	}
                    		if((matchingList.get(tent) == -1) && (partList.get(best) == -1)){
                        		matchingList.set(tent, best);
                        		partList.set(best, tent);
                        		break;
                        	}
                		}
            		}
            		if(thishappenedsobreak == 1)
                		break;
            		  
            	}
        	}
        	for(k =0; k<given_matching.getTenantCount(); k++){
        		for(i =0; i<given_matching.getTenantCount(); i++){
        			if(((matchingList.get(i) == matchingList.get(k)) || (matchingList.get(k) == -1)) && (i != k)){
        				checker = 1;
        				break;
            		}
        		}
        		if(thishappenedsobreak == 1)
            		break;
        		if(checker == 1)
            		break;
        		checker = 2;
//        		System.out.println(matchingList);
//        		System.out.println(partList);
        	}
        	if(thishappenedsobreak == 1)
        		break;
        	if(checker == 2)
        		break;
        }
        
    	/* TODO implement this function */
        given_matching.setTenantMatching(matchingList);
        return given_matching; /* TODO remove this line */
    }
}
