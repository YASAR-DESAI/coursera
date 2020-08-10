
/**
 * Write a description of BabyNames here.
 * 
 * @author YASAR
 * @version 10/08/2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyNamesEdited {
    
    public void totalBirths()
    {
        int totalnumber=0;
        int totalgirls=0;
        int totalboys=0;
        
        
        FileResource fr = new FileResource();
        
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
             totalnumber = totalnumber + numBorn ;
             
             if (rec.get(1).contains("M"))
             {
                 totalboys += numBorn ; 
 
             }
            
             else 
             {
                 totalgirls += numBorn ;
             }
        }
            
         System.out.println("The total number of children born is " + totalnumber);
         System.out.println("The total number of boys born is " + totalboys);
         System.out.println("The total number of girls born is " + totalgirls);
        
    }
    
    // test for total births
    public void testtotalBirths()
    {
        totalBirths();
    }
    
    
    //Method to calculate total number of girls in that particular year
    
    public int totalGirls()
    {
        int girls =1;
        FileResource fr = new FileResource();
        
        for (CSVRecord rec : fr.getCSVParser())
        {
            if ( rec.get(1).equals("F"))
            {
                girls = girls+1;
            }
        }
  
        return girls;
        
    }
    
     public int totalBoys()
    {
        int boys =0;
        FileResource fr = new FileResource();
        
        for (CSVRecord rec : fr.getCSVParser())
        {
            if ( rec.get(1).equals("M"))
            {
               boys = boys+1;
            }
        }
  
        return boys;
    }
    
    // Method to test both total number of boy's names and girl's names
    public void testBoysGirls()
    {
        int totalBoys = totalBoys();
        int totalGirls = totalGirls();
        System.out.println("Total number of Boys =" + totalBoys + " and Girls = " +totalGirls);

    }
        
    
    
    
    // method to get Rank of a name.
    public int getRank (Integer year ,String name, String gender)
    {
            int count = 0;
            FileResource fr = new FileResource("yob"+year+".csv");
            
            CSVParser parser =fr.getCSVParser(false); // false because there's no header in the file.
           for ( CSVRecord rec : parser)
           {
               
               if ( rec.get(1).equals(gender))
               {
                count++;
               }
               
                if (rec.get(0).equals(name) && rec.get(1).equals(gender))
                {
                    //System.out.println("The Rank of name "+name+" in year "+year+" is " + count);
                    return count;
                }
        
            }
         
        return -1;
    
  }



    
    public void testgetRank (Integer year ,String name, String gender)
       {
        int count =getRank(year, name,gender);
        System.out.println("The Rank of  "+name+" is " + count);
       }
    
    
    
    public String getName( Integer year, Integer rank, String gender)
    {
        int nameRank =0;
        FileResource fr = new FileResource("yob"+year+".csv");
        
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if (rec.get(1).equals(gender))
            {
              nameRank++;   //the counter increments only if the gender matches.
            }
            
            if (nameRank == rank && rec.get(1).equals(gender))
            {
                return rec.get(0);
                
            }
         
        }
        return "No Name";
      }
    
    public void testgetName(Integer year, Integer rank ,String Gender)
     
    {
      String name = getName( year,rank,Gender);
      System.out.println(" Name at rank " + rank + " in year " + year+ " is " + name);
    }
   
   
   public void whatNameInYear(String name, Integer year, Integer newyear ,String gender)
   {
       int nameRank =0;
       nameRank = getRank (year,name,gender);
       String newName = getName (newyear,nameRank,gender);
       System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newyear);
       
    }
    
    public void testwhatNameInYear(String name, Integer year, Integer newyear ,String gender )
    {
        whatNameInYear(name,year,newyear,gender);
    }
 
    
    public String yearOfHighestRank(String name, String gender)
    {
        int currentRank = -1;
        int highestRank =0;
         String fileWithHighestRank = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles())
        { 
            
            String filename= f.getName();
            filename =filename.replaceAll("yob","");                        // Replacing all non-numeric characters.
            filename= filename.replaceAll(".csv","");                    //getting year integer from a string filename;
           FileResource fr = new FileResource (f);
           CSVParser parser = fr.getCSVParser(false);
           
           for( CSVRecord  rec : parser)
           {
               
               if (rec.get(0).equals(name) && rec.get(1).equals(gender))
               {
                   if (highestRank==0)
                {
                 highestRank=   getRank(Integer.parseInt(filename),name,gender);
                 fileWithHighestRank = filename;
                }
                 else {
                    
                   currentRank = getRank(Integer.parseInt(filename),name,gender);
                   if ( currentRank < highestRank )
                   {
                       highestRank = currentRank;
                       fileWithHighestRank = filename;
                       
                   }

                  }
              }
         
             }
        
            }
           
        return fileWithHighestRank;
    }
    
    public void testyearOfHighestRank(String name, String gender)
    {
        String yearWithHighestRank = yearOfHighestRank(name,gender);
        
        System.out.println(" The year of highest rank for name "+name+ " is " +yearWithHighestRank );
        
        
    }
    
    
    
    public double getAverageRank(String name, String gender)
    {
        double currentRank = -1;
        double highestRank =0;
        double rank;
        double count = 0;
        double average = 0.0;
         String fileWithHighestRank = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles())
        { 
            count++;                                                   // count will increment for each loop.
            String filename= f.getName();
            filename =filename.replaceAll("yob","");                        // Replacing all non-numeric characters.
            filename= filename.replaceAll(".csv","");                    //getting year integer from a string filename;
           FileResource fr = new FileResource (f);
           CSVParser parser = fr.getCSVParser(false);
           
           for( CSVRecord  rec : parser)
           {
               
               if (rec.get(0).equals(name) && rec.get(1).equals(gender))    // if the fi
               { 
                   
                   rank =getRank(Integer.parseInt(filename),name,gender); // getting rank of the name from each file.
                   
                 
                   average += rank ;
                   
              /*     
                   if (highestRank==0)
                {
                 highestRank=   getRank(Integer.parseInt(filename),name,gender);
                 fileWithHighestRank = filename;
                 average += (double)highestRank;
                }
                 else {
                    
                   currentRank = getRank(Integer.parseInt(filename),name,gender);
                   if ( currentRank < highestRank )
                   {
                       highestRank = currentRank;
                       fileWithHighestRank = filename;
                  
                    }
                
                    }
                    */
              }
          
             }
        
           }
           
        return (double)average/(double)count;
        
    }
    
    public void testgetAverageRank (String name , String gender)
    {
        double average = getAverageRank( name , gender);
        System.out.println("The Average is "+ average);
        
    }
    
    
   
    
    public long getTotalBirthsHigherRanked( Integer year, String name, String gender)
    {      FileResource fr = new FileResource("yob" +year+ ".csv");
        long births = 0;
        int personsRank = getRank(year, name, gender);         //given persons rank in the given year

        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                if(personsRank  > getRank(year, rec.get(0), gender)){
                    births += Integer.parseInt(rec.get(2));
                }
            }
        }
        return births;
        
    }
    
    public void testgetTotalBirthsHigherRanked(Integer year, String name ,String gender){
     
        long totalNum = getTotalBirthsHigherRanked(year ,name ,gender);
        System.out.println("The total number of higher ranked children is "+ totalNum); 
    
    }
    
    /*
    public void testAll()
    {
        testgetTotalBirthsHigherRanked(2020,"Mason" ,"M");
        testgetAverageRank ("Susan","F");
        testyearOfHighestRank("Genevieve","F");
        testwhatNameInYear("Owen",1974,2014,"M");
        testgetName( 1982, 450, "M");
        testgetRank (1971 ,"Frank","M");
        
    }
    */
       
    public void finaltestgetAverageRank ()
    {
        testgetAverageRank ("Susan","F");
        
    }
    
    
    public void finaltestyearOfHighestRank()
    {
        testyearOfHighestRank("Genevieve","F");
        
    }
    
    public void finaltestwhatNameInYear()
    {
         testwhatNameInYear("Owen",1974,2014,"M");
        
    }
    
    public void  finaltestgetName()
    {
      testgetName( 1982, 450, "M");   
    }
    
    
    public void  finaltestgetRank()
    {
      testgetRank (1971 ,"Frank","M");   
    }
  
    public void  finaltestgetTotalBirthsHigherRanked()
    {
       testgetTotalBirthsHigherRanked(1990,"Emily" ,"F");  
    }
  
   }

    
