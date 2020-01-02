package org.chobit.jspy;


import java.util.*;

/**
 * 命令行参数管理类
 */
class CommandLineArgs {

    private final Map<String, String> optionArgs = new HashMap<>(8);

    private final List<String> nonOptionArgs = new LinkedList<>();


    void addOptionArgs(String optionName, String optionValue) {
        if (null != optionValue) {
            this.optionArgs.put(optionName, optionValue);
        }
    }


    Set<String> getOptionNames() {
        return Collections.unmodifiableSet(this.optionArgs.keySet());
    }

    boolean containsOption(String optionName) {
        return this.optionArgs.containsKey(optionName);
    }


    String getOptionValue(String optionName) {
        return this.optionArgs.get(optionName);
    }


    void addNonOptionArg(String value) {
        this.nonOptionArgs.add(value);
    }


    List<String> getNonOptionArgs() {
        return Collections.unmodifiableList(this.nonOptionArgs);
    }


}
