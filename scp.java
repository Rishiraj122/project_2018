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

    static int[] facilityPointGenerator(int[][] sortedIndex){
        Random random=new Random();
        int count=0;
        int[] status=new int[52];
        int[] facilityPoints=new int[52];
        Arrays.fill(status, 0);
        HashSet<Integer> s=new HashSet<>();
        while(s.size()!=52){
            int initial=random.nextInt(52);
            if(!s.contains(initial)){
                s.add(initial);
                for(int i=0;i<10;i=i+1){
                    int temp=sortedIndex[initial][i];
                    status[temp]=1;
                    s.add(temp);
                }
                facilityPoints[count]=initial;
                count++;
            }
        }
        return facilityPoints;
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

        //sorting
        Map<Float,Integer> mp=new HashMap<>();
        int[][] sortedIndex=new int[52][52];

        l=0;
        m=0;

        for(int i=0;i<52;i=i+1){
            for(j=0;j<52;j=j+1){
                mp.put(distanceMatrix[i][j],j);
            }

            Map<Float, Integer> map = new TreeMap<Float, Integer>(mp);
            for (Map.Entry<Float, Integer> entry : map.entrySet())
            {
                sortedIndex[l][m++]=(int)entry.getValue();
            }
            l++;
            m=0;
            mp.clear();
            
        }

        for(int i=0;i<52;i=i+1){
            for(j=0;j<5;j=j+1){
                System.out.print(sortedIndex[i][j]+" ");
            }
            System.out.println("----row "+i+" -------");
        }

        //-----------------------*#*-------------------------//

        //Generating Path (Step III)
        int[] facilityPoints=new int[55];
        int[][] pathMatrix=new int[101][55];
        //initially fill the array with 0s
        int q=0;

        while(q<=100){
            facilityPoints=facilityPointGenerator(sortedIndex);
            for(int i=0;i<facilityPoints.length;i=i+1){
                pathMatrix[q][i]=facilityPoints[i];
            }
            q++;
        }

        //---------Facility Points-----------//
        System.out.println("Facility Points");
        for(j=0;j<=100;j=j+1){
            System.out.println("\n Path No. "+j);
            for(int i=0;i<10;i=i+1){
                System.out.print(pathMatrix[j][i]+" ");
            }
        }
    }

}