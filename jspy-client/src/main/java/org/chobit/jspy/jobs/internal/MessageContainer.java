package org.chobit.jspy.jobs.internal;

import org.chobit.jspy.model.MessagePack;

public final class MessageContainer {

    private static MessagePack[] messagePack = new MessagePack[2];

    private static volatile int currIdx = 0;

    public synchronized static MessagePack getMessages() {
        if (null == messagePack[currIdx]) {
            messagePack[currIdx] = new MessagePack();
        }
        return messagePack[currIdx];
    }


    public synchronized static MessagePack dumpMessages() {
        int lastIdx = currIdx;
        currIdx = Math.abs(currIdx - 1);
        MessagePack pack = messagePack[lastIdx];
        messagePack[lastIdx] = new MessagePack();
        return pack;
    }

}
