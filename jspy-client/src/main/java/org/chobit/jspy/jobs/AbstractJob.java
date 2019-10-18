package org.chobit.jspy.jobs;


import org.chobit.jspy.jobs.internal.MessageContainer;
import org.chobit.jspy.model.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractJob {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    abstract String name();


    abstract void collect();


    protected MessagePack messagePack() {
        return MessageContainer.getMessages();
    }


    protected MessagePack dumpMessages() {
        return MessageContainer.dumpMessages();
    }



}
