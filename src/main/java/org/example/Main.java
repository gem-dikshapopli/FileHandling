package org.example;
import java.io.*;
import java.util.*;

public class Main {

static HashMap file_hashMap=new HashMap();//HashMap in which we will going to store the data of the file


//------------------ExistingData(This function will going to store all the existing
// data of file so that on refreshing the data should be stored)------------------//
    static void existingData(File file,HashMap file_hashMap){
        try{
            Scanner read = new Scanner(file);
            String key_value_pair[] = new String[2];
            while(read.hasNextLine()){
                String data=read.nextLine();
                key_value_pair = data.split(":");
                file_hashMap.put(key_value_pair[0],key_value_pair[1]);

            }
        }
        catch(FileNotFoundException exception){
            exception.printStackTrace();
        }
    }


    //------------------------After storing the data in hashMap we will going to write all the data in file)--------------//
    static void putData(File file,String key,String val){
        try{
            file_hashMap.put(key,val);
            FileWriter fileWriter = new FileWriter(file);

            for(Iterator iterator=file_hashMap.keySet().iterator();iterator.hasNext();){
                String s=""+iterator.next();
                fileWriter.append(s+":"+file_hashMap.get(s)+"\n");
            }

            fileWriter.close();
        }
        catch(IOException exception){
            exception.printStackTrace();
        }

    }

    //---------------------------To find the corresponding value with respect to that key---------------------//
    static void getData(File file,String key){
        try{
            int count=0;
            for(Iterator iterator=file_hashMap.keySet().iterator();iterator.hasNext();){
                String s=""+iterator.next();
                if(s.equals(key)){
                    count=1;
                    System.out.println(file_hashMap.get(s));
                }
            }
            if(count==0){
                System.out.println("Not there");
            }
        }catch (Exception exception){
            System.out.println(exception);
        }

    }


    //----------------------if the key and value you enterd are same with change value you can change the data
    // and if not there it will add that key and value---------------------------------------//
    public static void setData(File f,String key,String val,String change_value,HashMap file_hashMap){

        int count=0;
        for(Iterator it=file_hashMap.keySet().iterator();it.hasNext();){
            String s=""+it.next();
            if(file_hashMap.containsKey(key) && file_hashMap.containsValue(val)){
                file_hashMap.put(key,change_value);
                count=1;
            }
        }
        if(count==0){
            file_hashMap.put(key,val);
        }

        try{

            FileWriter fileWriter = new FileWriter(f);

            for(Iterator iterator=file_hashMap.keySet().iterator();iterator.hasNext();){
                String s=""+iterator.next();
                fileWriter.append(s+":"+file_hashMap.get(s)+"\n");
            }

            fileWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

//------------------main function----------------//
    public static void main(String[] args) {
        try{
            //creating a new file handlingTheFile//
            Scanner scanner=new Scanner(System.in);
            File file=new File("handlingTheFile");

            if(file.createNewFile()){
                System.out.println("Created"+file.getCanonicalPath());
            }
            else{
                System.out.println("Already there"+file.getCanonicalPath());
            }

            //calling static function existingData//
            existingData(file,file_hashMap);


            //Enter the key and values//
            while(true){
                System.out.println("Enter Key");
                String key=scanner.nextLine();
                if(key.equals("quit")){
                    break;
                }

                System.out.println("Enter Value");
                String val=scanner.nextLine();

                putData(file,key,val);
            }



            //-----------------For Getting the Value-----------------//
            System.out.println("-------------getData----------------");
            System.out.println("Enter the key that you want to find");
            String value=scanner.nextLine();

            //calling getData Function
            getData(file,value);

            //----------------For Setting the Value-----------------//
            System.out.println("------------------SetData--------------");
            System.out.println("Enter the key ");
            String key=scanner.nextLine();
            System.out.println("Enter the value of the corresponding key");
            String val= scanner.nextLine();
            if(file_hashMap.containsKey(key) && file_hashMap.containsValue(val)) {
                System.out.println("Enter the changed value of the corresponding key");
                String change = scanner.nextLine();
                setData(file, key, val, change, file_hashMap);
            }
            else {
                setData(file, key, val, val, file_hashMap);
            }

            //-----------------For Getting the Value-----------------//
            System.out.println("-------------getData----------------");
            System.out.println("Enter the key that you want to find");
            String find_value=scanner.nextLine();
            getData(file,find_value);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}