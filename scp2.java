import java.util.*;
import java.lang.*;
import java.lang.ref.Cleaner;
import java.io.*;

public class scp{
    ArrayList<String[]> storing;
    public scp() throws Exception{
        File file = new File("/Users/sherlock/Desktop/Infosec/project_2018/berlin52.tsp");
        Scanner sc = new Scanner(file);
        storing = new ArrayList<String[]>();
        String nextValue = null;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if("NODE_COORD_SECTION".equals(line)){
                while (sc.hasNextLine()) {
                    nextValue = sc.nextLine();
                    storing.add(nextValue.trim().split(" "));
                }
            }
        }
        sc.close();
    }

    public static ArrayList<String[]> returnScanner() throws Exception {
        scp tree = new scp();
        return tree.storing;
    }

    static float distanceFinder(float x1, float x2, float y1, float y2){
        
        float c=(float)Math.pow(x1-x2,2)+(float)Math.pow(y1-y2,2);
        return (float)Math.sqrt(c);
    
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String[]> storedValues = returnScanner();

        float[] arr=new float[52*2];
        float[][] distanceMatrix=new float[52][52];

        int j=0,k=0;
        while(j<52){
            String[] firstLine = storedValues.get(j);
            for (int i = 1; i < firstLine.length; i++) {
                arr[k++]=Float.parseFloat(firstLine[i]);
            }
            j++;
        }

        int l=0,m=0;
        for(int i=0;i<52*2;i=i+2){
            float x1=arr[i];
            float y1=arr[i+1];
            for(j=0;j<52*2;j=j+2){
                float x2=arr[j];
                float y2=arr[j+1];
                distanceMatrix[l][m]=distanceFinder(x1, x2, y1, y2);
                m=m+1;
            }
            l=l+1;
            m=0;
        }

        for(int i=0;i<52;i=i+1){
            for(j=0;j<52;j=j+1){
                System.out.print(distanceMatrix[i][j]+" ");
            }
            System.out.println("----row "+i+" -------");
        }

    }

}