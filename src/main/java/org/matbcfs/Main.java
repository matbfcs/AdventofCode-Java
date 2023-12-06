package org.matbcfs;

import java.io.*;
import java.util.*;


public class Main {

    public static int extractDataFromFile(String path) throws IOException{

        //path= new File("").getAbsolutePath()+"\\"+path;
        System.out.println(path);
        int counter=0;
        File file = new File(path);
        if(file.exists() )
        {
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            int sum =0;
            int numberFromASingleLine =0;

            String line = br.readLine();
            while (line != null){
                numberFromASingleLine= Integer.parseInt(findFirstAndLastNumber(line));
                System.out.println("[row-"+counter+"]\t"+line+"\tsum:"+String.valueOf(numberFromASingleLine));
                sum += numberFromASingleLine;

                line = br.readLine();
                counter++;
            }
            System.out.println("Total sum is "+sum);

        }
        else {
            System.out.println("Not found");
        }
        return counter;
    }

    private static String findFirstAndLastNumber(String line) {

        ArrayList<NumberFound> numberFoundList = new ArrayList<>();
        int numberExtracted;
        String[] digit = {"0","1","2","3","4","5","6","7","8","9"};
        String[] words = {"none","one","two","three","four","five","six","seven","eight","nine"};

        for(int i=0;i<10;i++){
            SearchNumberInASingleLine(digit[i],numberFoundList, line, i);
            SearchNumberInASingleLine(words[i],numberFoundList, line, i);
        }
        numberExtracted = ExtractFirstAndLastNumber(numberFoundList);

        return String.valueOf(numberExtracted);


    }

    private static int ExtractFirstAndLastNumber(List<NumberFound> tempNumberFoundList) {
        int tempMaxIndex =0;
        int tempMinIndex =100;
        int tempMin=0;
        int tempMax=0;
        int resultNumber;

        for (NumberFound number : tempNumberFoundList) {
            if(number.getIndex()<tempMinIndex){
                tempMinIndex = number.getIndex();
                tempMin = number.getValue();
                if (tempNumberFoundList.size() == 1) return tempMin*10+tempMin;
            }
            if (number.getIndex()>tempMaxIndex){
                tempMaxIndex = number.getIndex();
                tempMax = number.getValue();
            }
        }
        resultNumber = tempMin*10 + tempMax;
        return resultNumber;

    }

    private static void SearchNumberInASingleLine(String number,List<NumberFound> numberFoundList, String line, int value){
        int tempIndex = 0;
        NumberFound tempNumberFound = new NumberFound();
        do{
            tempIndex=line.indexOf(number,tempIndex);
            if (tempIndex!=-1) {
                tempNumberFound.setValue(value);
                tempNumberFound.setIndex(tempIndex);
                //tempNumberFound.printData();
                numberFoundList.add(tempNumberFound);
                tempIndex++;
            }
        }while(tempIndex!=-1);
    }

    public static void main(String[] args) {

        try {
            System.out.println (extractDataFromFile("Data.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}