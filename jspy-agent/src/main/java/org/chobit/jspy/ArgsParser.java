package org.chobit.jspy;


import static org.chobit.jspy.core.utils.Strings.isBlank;

/**
 * main方法参数解析工具
 */
abstract class ArgsParser {

    static CommandLineArgs parse(String args) {

        CommandLineArgs cla = new CommandLineArgs();

        if (isBlank(args)) {
            return cla;
        }

        String[] arr = args.split(",");

        for (String arg : arr) {
            arg = arg.trim();
            if (arg.startsWith("--")) {
                String opText = arg.substring(2);
                String opName;
                String opValue = null;
                if (opText.contains("=")) {
                    opName = opText.substring(0, opText.indexOf('='));
                    opValue = opText.substring(opText.indexOf('=') + 1);
                } else {
                    opName = opText;
                }
                if (opName.isEmpty() || (null != opValue && opValue.isEmpty())) {
                    throw new IllegalArgumentException("Invalid argument syntax: " + arg);
                }
                cla.addOptionArgs(opName.trim(), null == opValue ? null : opValue.trim());
            } else {
                cla.addNonOptionArg(arg.trim());
            }
        }

        return cla;
    }


    private ArgsParser() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
