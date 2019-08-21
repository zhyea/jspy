package org.chobit.jspy.core.info;

import org.chobit.jspy.core.model.Item;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.LinkedList;
import java.util.List;

public abstract class SystemInfo {


    public static List<Item> sysInfo() {
        List<Item> items = new LinkedList<>();

        oshi.SystemInfo sys = new oshi.SystemInfo();

        OperatingSystem os = sys.getOperatingSystem();

        //硬件信息
        HardwareAbstractionLayer hal = sys.getHardware();

        //计算机信息总览
        final ComputerSystem cs = hal.getComputerSystem();
        Item deviceSummary = new Item("设备信息总览");
        deviceSummary.add("制造商", cs.getManufacturer());
        deviceSummary.add("型号", cs.getModel());
        deviceSummary.add("S/N序列号", cs.getSerialNumber());
        //固件信息
        Firmware firmware = cs.getFirmware();



        return items;
    }


    private SystemInfo() {
    }

}
