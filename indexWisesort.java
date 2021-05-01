import java.util.*;
import java.lang.*;
import java.io.*;

class indexWisesort {
    public static void main(String args[]){
        Random rand=new Random();
        Scanner sc=new Scanner(System.in);
        int[][] arr=new int[5][5];
        int[][] arrSorted=new int[5][5];

        for(int i=0;i<5;i=i+1){
            for(int j=0;j<5;j=j+1){
                arr[i][j]=rand.nextInt(500);
            }
        }

        //Printing the original array
        for(int i=0;i<5;i=i+1){
            for(int j=0;j<5;j=j+1){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

        Map<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<5;i=i+1){
            for(int j=0;j<5;j=j+1){

                mp.put(arr[i][j],j);
            }

            Map<Integer, Integer> map = new TreeMap<Integer, Integer>(mp);
            for (Map.Entry<Integer, Integer> entry : map.entrySet())
            {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

            mp.clear();
            
        }
        

    }
}
