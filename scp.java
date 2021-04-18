import java.util.*;
import java.lang.*;
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

    public static void main(String[] args) throws Exception {
        ArrayList<String[]> storedValues = returnScanner();

        float[] arr=new float[52*2];

        int j=0,k=0;
        while(j<52){
            String[] firstLine = storedValues.get(j);
            for (int i = 1; i < firstLine.length; i++) {
                arr[k++]=Float.parseFloat(firstLine[i]);
            }
            j++;
        }

        for(int i=0;i<52*2;i=i+1){
            System.out.println(arr[i]);
        }

    }

}