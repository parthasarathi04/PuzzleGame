import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleCount {
    public static void updateCount(int game,int count){

        try {
            ArrayList<Integer> arr = HandleCount.getCount();

            int a = arr.get(0);
            int b = arr.get(1);

            if(game == 1) a = Math.min(a, count);
            else if(game == 2) b = Math.min(b, count);

            FileWriter fw = new FileWriter("count.txt",false);

            fw.write(Integer.toString(a));
            fw.write(new String("\n"));
            fw.write(Integer.toString(b));

            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Integer> getCount() {

        ArrayList<Integer> count = new ArrayList<>();
        count.add(10000);
        count.add(10000);

        try {
            File file = new File("count.txt");

            Scanner sc = new Scanner(file);

            if(sc.hasNextLine()) count.set(0, Integer.parseInt(sc.nextLine()));
            if(sc.hasNextLine()) count.set(1, Integer.parseInt(sc.nextLine()));

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = HandleCount.getCount();
        System.out.println(arr.toString());

        HandleCount.updateCount(2,10000);

        arr = HandleCount.getCount();
        System.out.println(arr.toString());
    }
}