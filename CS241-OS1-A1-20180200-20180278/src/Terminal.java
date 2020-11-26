import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Date;


public class Terminal {
    public static String currentDirectory = "D:\\";

    public void cd(String destinationPath){ // missed
        if(destinationPath.equalsIgnoreCase("") || destinationPath.equalsIgnoreCase(".")){
            Terminal.currentDirectory = "C:\\";
            System.out.println("We are now in " + Terminal.currentDirectory);
            return;
        }
        else{
            File file = new File(destinationPath);
            if (file.isDirectory()) {
                Terminal.currentDirectory = file.getAbsolutePath();
                Terminal.currentDirectory += "\\";
                System.out.println("we are now in " + Terminal.currentDirectory);
            } else {
                System.out.println("No such directory " + destinationPath);
            }
        }

    }
    public void cp(String sourcePath, String destinationPath ){
//        Scanner source;
//        Formatter destination;
        try {
            Scanner source = new Scanner(new File(sourcePath));
            Formatter destination = new Formatter(destinationPath);

            while(source.hasNext()){
                String word = source.next(); // copy from source
                destination.format("%s ", word); // paste to destination
            }
            source.close();
            destination.close();
        }

        catch(Exception e){
            System.out.println("Couldn't Open the source or the destination File");
        }
    }
    public void pwd(){
//        String pwd = System.getProperty("user.dir");
//        System.out.println(pwd);
        System.out.println(currentDirectory);
    }
    public void ls(String[] args)  {
        if (args[0] == null){
            File file = new File(Terminal.currentDirectory);
            String []items = file.list();
            for(String item : items)
                System.out.println(item);
        }else {
            if (args[0].equalsIgnoreCase(">>")){
                File listFilesToFile = new File(Terminal.currentDirectory);
                String[] list = listFilesToFile.list();
                try {
                    FileWriter append = new FileWriter(args[1] , true);
                    Formatter output = new Formatter(append);
                    for (int i =0 ; i < list.length ; i++){
                        output.format("%s " , list[i]);
                    }
                    output.close();
                }catch(Exception e){
                    System.out.println("Couldn't Open the source or the destination File");
                }

            }else if (args[0].equalsIgnoreCase(">")){
                File listFilesToFile = new File(Terminal.currentDirectory);
                String[] list = listFilesToFile.list();
                try {
                    Formatter output = new Formatter(args[1]);
                    for (int i =0 ; i < list.length ; i++){
                        output.format("%s " , list[i]);
                    }
                    output.close();
                }catch(Exception e){
                    System.out.println("Couldn't Open the source or the destination File");
                }
            }
        }


    }
    public void clear(){
//        System.out.print("\033[H\033[2J");
//        System.out.flush();

        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public void help(){
        Date date = new Date();
        System.out.println("args: \n" +
                "cp: copy and paste (cp file1.txt file2.txt)\n" +
                "mkdir: makes a new directory (mkdir newDirectory)\n" +
                "cd: change directory (cd C:\\directory)\n" +
                "mv: cut (mv sourceFile DestinationFile)\n" +
                "cat It will show content of given filename (cat filename) \n" +
                "rm: removes a file (rm file) \n" +
                "rmdir: removes a directory (rmdir directoryName) \n" +
                "pwd: returns current directory (pwd) \n" +
                "ls: lists all the items in the current directory (ls) \n" +
                "clear: clears the terminal window (clear) \n" +
                "more: used to view the text files in the command prompt (more  fileName) \n" +
                "Pipe Operator is used to combine two or more commands (command | anotherCommand)\n" +
                "Redirect Operator > ()\n" +
                "Redirect Operator >> ()\n" +
                "Date: " + date.toString());
    }
    public void date(){
        Date date = new Date();
        System.out.println(date.toString());
    }
    public void mkdir(String dirName){

        File folder = new File(dirName);
        folder.mkdir();

    }
    public void rmdir(String dirName){
        File rmFolder = new File(dirName);
        if (rmFolder.exists()){
            if(rmFolder.isDirectory()){
                rmFolder.delete();
            }
            else{
                System.out.println("This isn't a directory!");
            }
        }else {
            System.out.println("This directory doesn't exist");
        }
    }

    public void args(){
        System.out.println("args: \n" +
                "cp: copy and paste (cp file1.txt file2.txt)\n" +
                "mkdir: makes a new directory (mkdir newDirectory)\n" +
                "cd: change directory (cd C:\\directory)\n" +
                "mv: cut (mv sourceFile DestinationFile)\n" +
                "cat It will show content of given filename (cat filename) \n" +
                "rm: removes a file (rm file) \n" +
                "rmdir: removes a directory (rmdir directoryName) \n" +
                "pwd: returns current directory (pwd) \n" +
                "ls: lists all the items in the current directory (ls) \n" +
                "clear: clears the terminal window (clear) \n" +
                "more: used to view the text files in the command prompt (more  fileName) \n" +
                "Pipe Operator is used to combine two or more commands (command | anotherCommand)\n" +
                "Redirect Operator > ()\n" +
                "Redirect Operator >> ()");
    }
    public void mv(String sourcePath, String destinationPath){
        cp(sourcePath, destinationPath);
        rm(sourcePath);
        System.out.println("move have been DOne");
    }
    public void cat(String[] paths) {
        try {
            Scanner writing;
            if (paths[0].contains(">>")){
                writing = new Scanner(System.in);
                FileWriter appendFile = new FileWriter(paths[1], true);
                Formatter output = new Formatter(appendFile);
                while(writing.hasNext()){

                    String word = writing.next(); // copy from source

                    if (word.equalsIgnoreCase("^z"))break;

                    output.format("%s ", word); // paste to destination
                }
                output.close();



            }else if (paths[0].contains(">")){
                writing = new Scanner(System.in);
                Formatter output = new Formatter(paths[1]);
                while(writing.hasNext()){

                    String word = writing.next(); // copy from source

                    if (word.equalsIgnoreCase("^z"))break;

                    output.format("%s ", word); // paste to destination
                }
                output.close();
            }else {
                for (int i = 0; i < paths.length && paths[i] != null; i++) {
                    writing = new Scanner(new File(paths[i]));
                    while (writing.hasNext()) {
                        String word = writing.next(); // copy from source
                        System.out.println(word);
                    }
                }
            }


        }
        catch(Exception e){
            System.out.println("Couldn't Open the source or the destination File");
        }

    }

    public void rm(String sourceName){
        File rmFile = new File(sourceName);
        if (rmFile.exists()){
            if(rmFile.isFile()){
                rmFile.delete();
            }
            else
                System.out.println("This is not a file!");
        }else {
            System.out.println("This file doesn't exist");
        }
    }

    public void more(String [] args){
        try{
            Scanner readFile;
            Scanner in = new Scanner(System.in);
            String input;
            for(int i=0; i<args.length && args[i] != null; i++){
                readFile = new Scanner(new File(args[i]));
                input = "";
                while(input.equalsIgnoreCase("")){
                    input = in.nextLine();
                    if(readFile.hasNext()){
                        System.out.println(readFile.next());
                    }
                    else
                        break;
                }
            }
        }
        catch (Exception e){
            System.out.println("Couldn't open the file");
        }
    }
}
