package org.chobit.jspy.core.gauge;

import org.chobit.jspy.commons.model.TimerEntry;
import org.chobit.jspy.commons.tools.TimerClerk;

import java.util.Collection;

public class JSpyTimerGauge implements Gauge {

    @Override
    public String name() {
        return "JSpy Timer";
    }

    @Override
    public Collection<TimerEntry> value() {
        return TimerClerk.all();
    }
}
