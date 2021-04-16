import java.util.*;
import java.lang.*;
import java.io.*;

class setCover{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        
        int min=Integer.MAX_VALUE;
        int totalCost=0;
        int[] cost=new int[11];
        int k=0;
        
        Random rand=new Random();
         //Defining the costs

        int temp=rand.nextInt(500);
        for(int i=0;i<11;i=i+1){
            if(i%2==0){
                temp=rand.nextInt(500);
            }
            cost[i]=temp;
        }

        System.out.println("Cost\n");
        for(int i=0;i<11;i=i+1){
            System.out.print(cost[i]+" ");
        }
 
        System.out.println();

        //Using while loop to get the best result
        while(k++<10){
        
            //To store the cost of the selected path
            Set<Integer> costSet=new HashSet<>();


            LinkedHashSet<Integer> s=new LinkedHashSet<>();
            LinkedHashSet<Integer> universe=new LinkedHashSet<>();

            List<Integer> li=new ArrayList<>();

            //Though this isn't required, it is used to 
            List<Integer> path=new ArrayList<>();


            //Defining the universe (all the sets)
            for(int i=0;i<11;i=i+1){
                universe.add(i);
            }

            //Inserting into the linkedlist
            for(int i=0;i<11;i=i+1){
                for(int j=0;j<2;j=j+1){
                    li.add(i);
                }
            }
            Collections.shuffle(li);


            //Inserting numbers in sets and comparing with the universe
            for(int i=0;i<li.size();i=i+1){

                //It the set == Universe, we've found a path, no need to any more paths
                if(s.equals(universe)==true){
                    break;
                }
                else{
                    s.add(li.get(i));
                    path.add(li.get(i));

                    //It is to add the costs of the selected path
                    costSet.add(cost[i/2]);
                }
            }
    
            System.out.println("\n\nSets");
    
            System.out.println(s);
    
            System.out.println();
    
            for(int i=0;i<path.size();i=i+1){
                if(i%2==0){
                    System.out.println();
                }
                System.out.print(path.get(i)+" ");
            }
    
            //From the costSet, adding all the costs of the selected path and storing in totalCost
            for(Integer i: costSet){
                totalCost=totalCost+i;
            }
    
            System.out.println("\ntotalcost: "+totalCost);
            
            //Compare this cost with the previous cost and select the optimal one
            min=Math.min(min,totalCost);
            totalCost=0;

        }
        
        System.out.println("Minimum cost is: "+min);

    }
}