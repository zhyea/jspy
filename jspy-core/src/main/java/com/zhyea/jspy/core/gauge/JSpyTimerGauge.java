package com.zhyea.jspy.core.gauge;

import com.zhyea.jspy.commons.model.TimerEntry;
import com.zhyea.jspy.commons.tools.TimerClerk;

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
