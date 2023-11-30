import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SvrConfig
    {
        public static void main(String args[]) 
            {
                String connData[] = new String[4];
                int i=0;
                try
                    {
                        File conf = new File("config.txt");
                        Scanner sc = new Scanner(conf);

                        while(sc.hasNext())
                            {
                                connData[i]=sc.next();
                                i++;
                            }
                        sc.close();
                        
                    }
                
                catch(FileNotFoundException noFIle)
                    {
                        System.out.println("The config file does not exist, the program cannot run smoothly");
                    }
                

                 catch(NoSuchElementException ex)
                    {
                        System.out.println("The file has been modified, please download the file again");
                    }

            }
        
    }
