import java.io.File;
import java.util.ArrayList;

public class Parser {
    private String[] args;
    private String cmd;
    Terminal t = new Terminal();

    public Parser() {
        args = new String[10];
        cmd = null;
    }

    public boolean parse(String input) {
        String Command = null;
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                index = i - 1;
                break;
            }
        }

        if (!input.contains(" "))
            Command = input;
        else
            Command = input.substring(0, index + 1);
        if (Command.equalsIgnoreCase("ls")) {
            cmd = Command;
            File checks;
            String[] Words = input.split(" ");
            if (input.length() > 2 && input.charAt(index + 2) != ' ') { // if anything more than the word "ls" was written
                if (Words[1].contains(">") || Words[1].contains(">>")) {
                    args[0] = Words[1];
                    if (Words[2].contains(":")) {
                        checks = new File(Words[2]);

                    } else {
                        checks = new File(Terminal.currentDirectory + Words[2]);

                    }
                    args[1] = checks.getAbsolutePath();
                } else {
                    System.out.println("Too much arguments (No arguments needed for ls)");
                    return false;
                }

            }
            t.ls(args);
            return true;
        } else if (Command.equalsIgnoreCase("cd")) { // missed .. .
            cmd = Command;
            if (input.length() == 2) { //if he wrote cd only
                args[0] = "D:\\";
                Terminal.currentDirectory = "D:\\";
                t.cd(getArguments()[0]);
                return true;
            } else {
                String checkPath = input.substring(index + 2, input.length()); // to start from the begining of the argument to the end of it
                File check;
                if (checkPath.contains(":")) { // if its an absolute path
                    check = new File(checkPath);
                } else if (checkPath.contains("..")) {
                    File parent = new File(Terminal.currentDirectory);
                    String paarent = new String(parent.getParent());
                    check = new File(paarent);


                } else if (checkPath.contains(".")) {
                    check = new File(Terminal.currentDirectory);

                } else {
                    check = new File(Terminal.currentDirectory + checkPath);
                }
                if (check.exists()) {
                    args[0] = check.getAbsolutePath();
                    t.cd(getArguments()[0]);
                    return true;
                } else //if it doesn't exist
                    System.out.println("The path u want to go to doesn't exist!");
                return false;
            }
        } else if (Command.equalsIgnoreCase("clear")) {
            cmd = Command;
            if (input.length() > 5) { // if anything more than clear was written
                System.out.println("Too much arguments (No arguments needed for clear)");
                return false;
            }
            t.clear();
            return true;
        } else if (Command.equalsIgnoreCase("pwd")) {
            cmd = Command;
            String[] Words = input.split(" ");
            if (Words.length > 1) {
                System.out.println("too few arguments or too many arguments (pwd)");
                return false;
            }
            t.pwd();
            return true;
        } else if (Command.equalsIgnoreCase("cp")) { // works if there is no spaces in the paths
            cmd = Command;
            String[] Words = input.split(" ");
            if (Words.length != 3) {
                System.out.println("too few arguments or too many arguments (cp)");
                return false;
            }
            File sourceFile, destinationFile;
            if (Words[1].contains(":")) {
                sourceFile = new File(Words[1]);
            } else {
                sourceFile = new File(Terminal.currentDirectory + Words[1]);
            }
            if (Words[2].contains(":")) {
                destinationFile = new File(Words[2]);
            } else {
                destinationFile = new File(Terminal.currentDirectory + Words[2]);
            }
            if (sourceFile.exists()) {
                if (Words[1].contains(":")) {
                    args[0] = Words[1];
                } else {
                    args[0] = Terminal.currentDirectory + Words[1];
                }
                args[0] = sourceFile.getAbsolutePath();
                if (Words[2].contains(":")) {
                    args[1] = Words[2];
                } else {
                    args[1] = Terminal.currentDirectory + Words[2];
                }
                t.cp(args[0], args[1]);
                return true;
            } else {
                System.out.println("does not exist");
                return false;
            }
//            int space=0;
//            ArrayList<Integer> spaceIndices = new ArrayList<>();
//            for(int i=0; i<input.length(); i++){
//                if(input.charAt(i) == ' '){
//                    space++;
//                    spaceIndices.add(i+1);
//                }
//                if(space == 2)
//                    break;
//            }
//
//            if(space == 2){
//                File check1 = new File(input.substring(spaceIndices.get(0), spaceIndices.get(1)-1));
//                File check2 = new File(input.substring(spaceIndices.get(1), input.length()));
//                if (check1.exists()){
//                    cmd = Command;
//                    args[0] = check1.getAbsolutePath();
//                    args[1] = check2.getAbsolutePath();
//                    return true;
//                }
//            }
//            else{
//                System.out.println("Too much or too few arguments (2 arguments needed only)");
//                return false;
//            }
        } else if (Command.equalsIgnoreCase("help")) {
            cmd = Command;
            if (input.length() > 4) { // if anything more than the word "help" is entered
                System.out.println("Too much arguments (No arguments needed for help)");
                return false;
            }
            t.help();
            return true;
        } else if (Command.equalsIgnoreCase("date")) {
            cmd = Command;
            if (input.length() > 4) { // if anything more than the word "date" is entered
                System.out.println("Too much arguments (No arguments needed for date)");
                return false;
            }
            t.date();
            return true;
        } else if (Command.equalsIgnoreCase("args")) {
            cmd = Command;
            if (input.length() > 4) { // if anything more than the word "args" is entered
                System.out.println("Too much arguments (No arguments needed for args)");
                return false;
            }
            t.args();
            return true;
        } else if (Command.equalsIgnoreCase("mkdir")) {
            cmd = Command;
            String[] Words = input.split(" ");
            if (Words.length != 2) {
                System.out.println("too few arguments or too many arguments (mkdir)");
                return false;
            } else {
                if (Words[1].contains(":"))
                    args[0] = Words[1];
                else
                    args[0] = Terminal.currentDirectory + Words[1];
            }
            t.mkdir(args[0]);
            return true;

        } else if (Command.equalsIgnoreCase("rmdir")) {
            cmd = Command;
            String[] Words = input.split(" ");
            if (Words.length != 2) {
                System.out.println("too few arguments or too many arguments (rmdir)");
                return false;
            } else {
                if (Words[1].contains(":"))
                    args[0] = Words[1];
                else
                    args[0] = Terminal.currentDirectory + Words[1];
            }
            t.rmdir(args[0]);
            return true;
        } else if (Command.equalsIgnoreCase("rm")) {
            cmd = Command;
            String[] Words = input.split(" ");
            if (Words.length != 2) {
                System.out.println("too few arguments or too many arguments (rm)");
                return false;
            } else {
                if (Words[1].contains(":"))
                    args[0] = Words[1];
                else
                    args[0] = Terminal.currentDirectory + Words[1];
                t.rm(args[0]);
                return true;
            }
        } else if (Command.equalsIgnoreCase("mv")) {
            cmd = Command;
            String[] Words = input.split(" ");
            if (Words.length != 3) {
                System.out.println("too few arguments or too many arguments (mv)");
                return false;
            }
            File sourceFile, destinationFile;
            if (Words[1].contains(":")) {
                sourceFile = new File(Words[1]);
            } else {
                sourceFile = new File(Terminal.currentDirectory + Words[1]);
            }
            if (Words[2].charAt(1) == ':') {
                destinationFile = new File(Words[2]);
            } else {
                destinationFile = new File(Terminal.currentDirectory + Words[2]);
            }
            if (sourceFile.exists()) {
                if (Words[1].contains(":")) {
                    args[0] = Words[1];
                } else {
                    args[0] = Terminal.currentDirectory + Words[1];
                }
                args[0] = sourceFile.getAbsolutePath();
                if (Words[2].contains(":")) {
                    args[1] = Words[2];
                } else {
                    args[1] = Terminal.currentDirectory + Words[2];
                }
                t.mv(args[0], args[1]);
                return true;
            } else {
                System.out.println("does not exist");
                return false;
            }
//            cmd = Command;
//            String [] Words = input.split(" ");
//            if (Words.length != 3){
//                System.out.println("too few arguments or too many arguments (mv requires 2 arguments)");
//                return false;
//            }
//            else{
//                args[0] = Words[1];
//                args[1] = Words[2];
//                return true;
//            }
        } else if (Command.equalsIgnoreCase("cat")) {
            cmd = Command;
            String[] Words = input.split(" ");
            File checks;
            for (int i = 1; i < Words.length; i++) {
                if (Words[i].contains(":")) {
                    checks = new File(Words[i]);
                } else if (Words[i].contains(">>") || Words[i].contains(">")) {
                    if (Words[i + 1].contains(":")) {
                        checks = new File(Words[i + 1]);

                    } else {
                        checks = new File(Terminal.currentDirectory + Words[i + 1]);

                    }
                    args[0] = Words[i];
                    if (checks.exists()) {
                        if (checks.isFile()) {
                            args[1] = checks.getAbsolutePath();
                            t.cat(args);
                            return true;
                        } else {
                            System.out.println("this isn't a file");
                            return false;
                        }
                    } else {
                        System.out.println("this file doesn't exist");
                        return false;
                    }
                } else {
                    checks = new File(Terminal.currentDirectory + Words[i]);
                }
                if (checks.exists()) {
                    if (checks.isFile()) {
                        if (Words[i].contains(":")) {
                            args[i - 1] = Words[i];
                        } else {
                            args[i - 1] = Terminal.currentDirectory + Words[i];
                        }
                    } else {
                        System.out.println("this isn't a file");
                        return false;
                    }
                } else {
                    System.out.println("this file doesn't exist");
                    return false;
                }
            }
            t.cat(args);
            return true;
        } else if (Command.equalsIgnoreCase("more")) {
            cmd = Command;
            File checks;
            String[] Words = input.split(" ");
            for (int i = 1; i < Words.length; i++) {
                if (Words[i].contains(":")) {
                    checks = new File(Words[i]);
                } else {
                    checks = new File(Terminal.currentDirectory + Words[i]);
                }
                if (checks.exists()) {
                    if (checks.isFile()) {
                        args[i - 1] = checks.getAbsolutePath();
                    } else {
                        System.out.println("This is not a file!");
                        return false;
                    }
                } else {
                    System.out.println("The file " + checks.getName() + " doesn't exist");
                    return false;
                }
            }
                t.more(getArguments());
                return true;


        }
        return false;
    }
    public String getCmd() {
        return cmd;
    }

    public String[] getArguments() {
        return args;
    }

    public void setArgumentsNull() {
        args[0] = null;
        args[1] = null;
        args[2] = null;
    }
}
