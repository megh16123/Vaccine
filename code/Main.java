import java.io.FileReader;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;           //importing required libraries
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    //path of the files
    static String pathFile1 = "../Places.csv";
    static String pathFile2 = "../completeData.csv";
    
    // function to load all the contents of file
    static void Data_Loader() {
     
       
        String row;
        try {
            // reading file using buffer reader    
            BufferedReader csvReader = new BufferedReader(new FileReader(pathFile1));
            // interating through each row of the file
            while ((row = csvReader.readLine()) != null) {
                String[] s = row.split(",");
                System.out.println(s[0]+"\t"+s[1]+"\t"+s[3]+"\t"+s[3]+"\t"+s[4]);

            }
            //closing buffer reader
            csvReader.close();
            //reading from new file
            csvReader = new BufferedReader(new FileReader(pathFile2));
            //interating through each row of the file
            while ((row = csvReader.readLine()) != null) {
                String[] s = row.split(",");
                System.out.println(s[0]+"\t"+s[1]+"\t"+s[3]+"\t"+s[3]+"\t"+s[4]+"\t"+s[5]);
            }
            csvReader.close();
            //catching exception of the bufferReader
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // function to find total cases continent wise
    private static void Total_Number_Of_Cases(Boolean option) {

        String row = "";

        // Maps for specific continents and locations
        Map<String, Long> continents = new HashMap<String, Long>();
        Map<String, String> locations = new HashMap<String, String>();
        locations.put("World", "Globally");
        try {

            // reading file using buffer reader    
            BufferedReader csvReader = new BufferedReader(new FileReader(pathFile1));
            // reading data from file using readLine()
            row = csvReader.readLine(); 
            // loop to put the desired values from file to locations map
            while ((row = csvReader.readLine()) != null) {
                String[] s = row.split(",");
                locations.put(s[1], s[2]);

            }
            //closing buffer reader
            csvReader.close();

            // reading file using buffer reader    
            csvReader = new BufferedReader(new FileReader(pathFile2));
            // reading data from file using readLine()
            row = csvReader.readLine();
            // loop to put the desired values from file to continents map
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                
                    continents.put(locations.get(data[1]), 0l);

            

            }
            //closing buffer reader
            csvReader.close();

            // reading file using buffer reader  
            csvReader = new BufferedReader(new FileReader(pathFile2));
            // reading data from file using readLine()
            row = csvReader.readLine();
            // loop to take the desired values from file to locations map and continent map
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                    long val = 0l;
                    val = continents.get(locations.get(data[1])); // locations - >continent = number of new cases
                    val += Integer.parseInt(data[2].toString());
                    continents.put(locations.get(data[1]), val);

               

            }
            //removing values from continents map which are not needed
          
            continents.remove("Globally");

            //sorting of data
            if (option) {
                continents = continents.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));

            } else {

                continents = continents.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2,
                                LinkedHashMap::new));
            }

            //printing the desired output values
            for (String keys : continents.keySet()) {
                System.out.println(keys + " \t->\t " + continents.get(keys));
            }

            //catching the exception of buffer reader
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //function to calculate total deaths
    private static void Total_Number_Of_Deaths(Boolean option) {
        
        
        String row = "";
        // Maps for specific continents and locations
        Map<String, Long> continents = new HashMap<String, Long>();
        Map<String, String> locations = new HashMap<String, String>();
        // creating key value pair "World":"Globally"
        locations.put("World", "Globally");
        try {
            // reading file using buffer reader    
            BufferedReader csvReader = new BufferedReader(new FileReader(pathFile1));
            //reading data from file using readline
            row = csvReader.readLine();
            // iterating to each row of csv
            while ((row = csvReader.readLine()) != null) {
                //splliting each row using ,
                String[] s = row.split(",");
                //creating key value pair of countries and continent
                locations.put(s[1], s[2]);

            }
            
            //closing buffer reader
            csvReader.close();

            // reading file using buffer reader 
            csvReader = new BufferedReader(new FileReader(pathFile2));
            // reading data from file using readLine()
            row = csvReader.readLine();
            // loop to take the desired values from file to continent map from location map
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
               
                    continents.put(locations.get(data[1]), 0l);

             
        
            }
            //closing buffer reader
            csvReader.close();
            // reading file using buffer reader 
            csvReader = new BufferedReader(new FileReader(pathFile2));
            // reading data from file using readLine()
            row = csvReader.readLine();
            // loop to take the desired values from file to locations map and continent map
            while ((row = csvReader.readLine()) != null) {
                
                String[] data = row.split(",");
                    long val = 0l;
                    //adding new cases to get total cases
                    val = continents.get(locations.get(data[1]));
                    val += Integer.parseInt(data[3].toString());
                    continents.put(locations.get(data[1]), val);

                

            }
         
            continents.remove("Globally");
            //If option is true the map continent is sorted on the basis of value from lower to higher else higher to lower
            if (option) {
                continents = continents.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));

            } else {

                continents = continents.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2,
                                LinkedHashMap::new));
            }
            //iterating through the map and displaying the key values
            for (String keys : continents.keySet()) {
                System.out.println(keys + " \t->\t " + continents.get(keys));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // function to display new cases per million
    private static void Priority_Wise_Countries_Data() {
       
        String row = "";
        Map<String, Long> countries = new HashMap<String, Long>();
        Map<String, Long> locations = new HashMap<String, Long>();
        try {

            // reading file using buffer reader 
            BufferedReader Reader = new BufferedReader(new FileReader(pathFile1));
            // reading data from file using readLine()
            row = Reader.readLine();
            // loop to take the desired values from file to countries map
            while ((row = Reader.readLine()) != null) {
                String[] data = row.split(",");
                countries.put(data[1], (long) Integer.parseInt(data[4]));
            }
            //closing buffer reader
            Reader.close();
            
            // reading file using buffer reader 
            BufferedReader csvReader = new BufferedReader(new FileReader(pathFile2));
            // reading data from file using readLine()
            row = csvReader.readLine();
            // loop to take the desired values from file to locations map
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                locations.put(data[1], 0l);
            }
            //closing buffer reader
            csvReader.close();
            // reading file using buffer reader 
            csvReader = new BufferedReader(new FileReader(pathFile2));
            // reading data from file using readLine()
            row = csvReader.readLine();
            // loop to take the new cases values from file to locations map country wise
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");

                long val = 0l;
                val = Integer.parseInt(locations.get(data[1]).toString());
                val += Integer.parseInt(data[2].toString());
                locations.put(data[1], val);

            }
            
            //map to store data according new cases per million
            Map<String, Integer> Priority_per_million = new HashMap<>();
            System.out.println("New cases per million ");
            //loop to interpret the final values from the cases file and location file and put into map Priority_per_million
            for (String keys : countries.keySet()) {
                long population = countries.get(keys);
                long new_cases = locations.get(keys);
                if (population == 0) {
                    population = 1;
                }
                double population_per_million = ((double) new_cases / population) * 1000000;

                Priority_per_million.put(keys, ((int) population_per_million));
            }

            //sorting final data
            Priority_per_million = Priority_per_million.entrySet().stream().sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2,
                            LinkedHashMap::new));
            //loop to print the require output
            for (String key : Priority_per_million.keySet()) {
                System.out.println(key + " \t->\t " + Priority_per_million.get(key));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //Driver Code
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        int choice = -1;
        System.out.println("*************************************************************");
        System.out.println("****COVID 19 Global Statistics Menu (LAst Update May 3rd)****");
        System.out.println("*************************************************************");
        do {
            System.out.println("Menu For the Program :");
            System.out.println("[1] Load Data From Files");
            System.out.println("[2] Print Continents Total Cases (Lowest to Highest)");
            System.out.println("[3] Print Continents Total Cases (Highest to Lowest)");
            System.out.println("[4] Print Continents Total Deaths (Lowest to Highest)");
            System.out.println("[5] Print Continents Total Deaths (Highest to Lowest)");
            System.out.println("[6] Prioritize top countries for testing based on new cases per 1 million");
            System.out.println("[7] To Exit");
            System.out.println("Please enter your choice:");
            choice = scnr.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Loading files ...");
                    Data_Loader();
                    System.out.println("Files loaded successfully!");
                    break;
                case 2:
                    Total_Number_Of_Cases(true);
                    break;
                case 3:
                    Total_Number_Of_Cases(false);
                    break;
                case 4:
                    Total_Number_Of_Deaths(true);
                    break;
                case 5:
                    Total_Number_Of_Deaths(false);
                    break;
                case 6:
                    Priority_Wise_Countries_Data();
                    break;
                case 7:
                    System.out.println("Thank you for using our system..Goodbye!");
                    break;
                default:
                    System.out.println("Please a choice 1 - 7");
                    break;
            }
        } while (choice != 7);
        scnr.close();
    }

}
