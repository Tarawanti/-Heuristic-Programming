import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
public class Testing{

   public static void main(String[] args) throws Exception {
	   int selection=0;
	   J48   j48tree = new J48();    
	  
	   System.out.println("Enter training file name");
		   Scanner scanner1 = new Scanner(System.in); 
	       String trainfile =" ", testfile =" ";
	       if(scanner1.hasNext())
		       {
	    	   		trainfile = scanner1.nextLine();
		    	  
		       }
       System.out.println("....................."+trainfile);
	       Reader r = new FileReader(trainfile.trim());
	       Instances train = new Instances(r);
	       int lastIndex = train.numAttributes() - 1;
	       train.setClassIndex(lastIndex);
	       j48tree.buildClassifier(train);
       
       System.out.println("Enter test file name");
       
	       if(scanner1.hasNext())
		       {
	    	   		testfile = scanner1.nextLine();	   
		       }
	       r = new FileReader(testfile.trim());
	       Instances test = new Instances(r);
	       test.setClassIndex(lastIndex);
	       Evaluation eval = new Evaluation(test);
	       eval.evaluateModel(j48tree, test);
 
	       
while(selection != 6)
	   { 
		   
		   Scanner sc = new Scanner(System.in);
		   System.out.println("\n\n\n 1:	Learn a decision tree and save the tree\n "
    			+ "2:	"+ "Testing accuracy of the decision tree \n "
    			+ "3:	Applying the decision tree to new cases \n"
    			+ " 4.	Load a tree model and apply to new cases interactively as in menu 3 \n "
    			+ "5.	Quit ");
    	
	   selection = sc.nextInt();
	   switch( selection)
	    {
	    
	    case 1:
	    	
	    	 System.out.println(eval);
	    	 System.out.println("Enter the path for save file");
		        Scanner FilePath = new Scanner(System.in); 
		        String path="";
			       
		         if (FilePath.hasNext());
		         
		          {
		        	  path = FilePath.nextLine();
		                    
		          }
		       
		        String filename="/Decision-tree.txt";
		        PrintWriter output = new PrintWriter( path+filename ); 
		        System.out.println("................."+path+filename);
		        output.println(j48tree.graph());
		        output.close();
		       	 break;
	 	     
	     
	    case 2:
	    	System.out.println("=============");
	    	double[][] ConfusionMetrix = eval.confusionMatrix();
	         for (int i = 0; i<ConfusionMetrix.length; i++)
	         {
	             for (int j = 0; j<ConfusionMetrix[i].length; j++)
				{
					System.out.print(ConfusionMetrix[i][j] + "\t");
	            
				}	System.out.println( "  "+"\n" );
				 
	         }
	         System.out.println("=============");
	    	break;
	   
	    case 3:
	    	 String submenu ="";  
	   	  	 int menuback=0;
	   	  	 Scanner scanner = new Scanner(System.in); 
	   	  	 while(menuback != 2)
	   	  	 {
		    		System.out.println("\n3.1  Enter a new case interactively. \n"+ "3.2  Quit");
		    		       if(scanner.hasNext())
		    		       {
		    		    	   submenu = scanner.next();   
		    		       }
			    	if(submenu.equals("3.1")) 
			    	{			    		 
				    		String nodes[] = j48tree.graph().split("\n");
				    		String chdNode ="";
				    		String condition_value ="Initial";
				    		for(int j =1; j<nodes.length-1;j++) 
				    		{
				    			if(nodes[j].indexOf("shape=box") !=-1)
				    			{
				    				System.out.println("The decision Attribute is : " +nodes[j].substring(nodes[j].indexOf("=")+2, nodes[j].indexOf(" (")) + "\n");
				    				break;
				    			}
				    			System.out.println("Enter the value for "+ nodes[j].substring(nodes[j].indexOf("=")+2, nodes[j].indexOf("]")-2));
				    			j++;
				    			if(scanner.hasNext())
				    		    {
				    				condition_value = scanner.next();	
				    		    }
				    			while((nodes[j].indexOf(condition_value) == -1)&& j<nodes.length-1)
				    			{
				    				j++;
				    			}
				    			chdNode = nodes[j].substring(0, nodes[j].indexOf(" "));
				    			chdNode = chdNode.substring(chdNode.indexOf("->")+2);
				    			while((!chdNode.equals(nodes[j+1].substring(0,nodes[j+1].indexOf(" ")))) && j+1<nodes.length-1)
				    			{
				    				j++;
				    			}
				    		}	    		
				    	}
			    	
						    	if(submenu.equals("3.2")) 
							    {
							   		break;
							   	}
		       		 
					    	  else
					    		  System.out.print("Invalid Input\n");
	   	  	 		}
		   break;
	    	case 4:
	    		  
	    	   System.out.println("Enter Decision Tree file name");
	   		   Scanner model = new Scanner(System.in); 
	   	       String ModelFile =" ";
	   	       if(model.hasNext())
	   		       {
	   	    	   		ModelFile = model.nextLine();
	   	    	
	   		       }
	   	       
	   	    System.out.println("....................."+ModelFile);
	   	    
	   	    BufferedReader mdlfile=new BufferedReader(new FileReader(ModelFile));
	  	 	   String line;
	  	 	   String node=  "";
	  	 	   while((line=mdlfile.readLine())!=null)
	  	 	   {
	  	 		   node=node+line+ "\n";
	  	 	   }
	  	 		   
	  	 		   
	  	 		String nodes[] = node.split("\n");
	    		String chdNode ="";
	    		String condition_value ="Initial";
	    		for(int j =1; j<nodes.length-1;j++) 
	    		{
	    			if(nodes[j].indexOf("shape=box") !=-1)
	    			{
	    				System.out.println("The decision Attribute is : " +nodes[j].substring(nodes[j].indexOf("=")+2, nodes[j].indexOf(" (")) + "\n");
	    				break;
	    			}
	    			System.out.println("Enter the value for "+ nodes[j].substring(nodes[j].indexOf("=")+2, nodes[j].indexOf("]")-2));
	    			j++;
	    			if(model.hasNext())
	    		    {
	    				condition_value = model.next();	
	    		    }
	    			while((nodes[j].indexOf(condition_value) == -1)&& j<nodes.length-1)
	    			{
	    				j++;
	    			}
	    			chdNode = nodes[j].substring(0, nodes[j].indexOf(" "));
	    			chdNode = chdNode.substring(chdNode.indexOf("->")+2);
	    			while((!chdNode.equals(nodes[j+1].substring(0,nodes[j+1].indexOf(" ")))) && j+1<nodes.length-1)
	    			{
	    				j++;
	    			}
	    		}	    		
	  	 		  
	  	 	   
	  	 	   
		    	break;
		    
		    
		    case 5:
		    	 System.exit(0); 
		    	 System.out.println("I am number 5");
		    	break;
	    	
		    default: 
	    		System.out.println("invalid selection");
	    	break; 
	    	  }    
		   }      
   		}

   public void ReadFile() throws IOException
   {
   String line;
   BufferedReader in;
	 
   System.out.println("Enter the path for save file");
   Scanner file4 = new Scanner(System.in); 
   
            
   	 String  path4="";
   	 path4= file4.nextLine();
       
    
   in = new BufferedReader(new FileReader(path4));
   line = in.readLine();

   while(line != null)
   {
          System.out.println(line);
          line = in.readLine();
   }

   System.out.println(line);
   
   in.close();
}
   

   
}

//C:\Users\TK\Desktop\2-Master\AI\P1-3265\TestingFiles\weather.nominal.arff
//C:\Users\TK\Desktop\2-Master\AI\P1-3265\TestingFiles\weather.nomina-testl.arff

//C:/Users/TK/Desktop/weather.nomina-testl.arff
//C:/Users/TK/Desktop/weather.nominal.arff
//C:/Users/TK/Desktop/Dtree.arff
//C:/Users/TK/Desktop/Btree.arff
//C:\Users\TK\Desktop\GA