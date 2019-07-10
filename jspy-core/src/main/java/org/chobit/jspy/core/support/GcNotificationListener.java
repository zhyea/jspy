package org.chobit.jspy.core.support;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.InstanceNotFoundException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

import static com.sun.management.GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION;

public final class GcNotificationListener implements NotificationListener {


    private GcCollector gcCollector;

    public GcNotificationListener(GcCollector gcCollector) {
        this.gcCollector = gcCollector;
    }

    /**
     * 应用GC监听器
     */
    public void apply() throws InstanceNotFoundException {
        for (GarbageCollectorMXBean gcMbean : ManagementFactory.getGarbageCollectorMXBeans()) {
            ManagementFactory.getPlatformMBeanServer().
                    addNotificationListener(gcMbean.getObjectName(), this, null, null);

        }
    }


    @Override
    public void handleNotification(Notification notification, Object handback) {
        /**
         * GC 通知信息参考java 文档
         *
         * https://docs.oracle.com/javase/8/docs/jre/api/management/extension/com/sun/management/GarbageCollectionNotificationInfo.html
         */
        if (GARBAGE_COLLECTION_NOTIFICATION.equals(notification.getType())) {
            CompositeData cd = (CompositeData) notification.getUserData();
            GarbageCollectionNotificationInfo gcNotificationInfo = GarbageCollectionNotificationInfo.from(cd);
            gcCollector.add(gcNotificationInfo);
        }
    }

}
