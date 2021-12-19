package ru.vsu.cs.shevchenko_daniil;

import java.util.Dictionary;
import java.util.Hashtable;

public class ArgsParser {
    private Dictionary<String, String> argsDict = new Hashtable<>();

    public ArgsParser(String args) {
        parse(args.split(" "));
    }

    public ArgsParser(String[] args) {
        parse(args);
    }

    private void parse(String[] args) {
        // -i ./file.txt -o ./file2.txt

        String argName = null;

        for (String arg : args) {
            if (arg.startsWith("-") && arg.length() == 2 || arg.startsWith("--")) {
                // нашли название аргумента
                argName = arg;
            } else {
                // нашли значение аргумента
                if (argName != null) {
                    argsDict.put(argName, arg);
                }
            }
        }
    }

    public String getArgumentValue(String name) {
        return argsDict.get(name);
    }

    public String getArgumentValue(String shortName, String longName) {
        String value = argsDict.get(shortName);

        if (value == null) {
            value = argsDict.get(longName);
        }

        return value;
    }

    public Dictionary<String, String> getArgsDict() {
        return argsDict;
    }
}
