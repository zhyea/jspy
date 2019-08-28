package org.chobit.jspy.core.info;

import org.chobit.jspy.core.model.Item;
import oshi.hardware.*;
import oshi.software.os.NetworkParams;
import oshi.software.os.OperatingSystem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.chobit.jspy.core.constants.StorageUnit.B;
import static org.chobit.jspy.core.utils.Strings.formatLong;

public abstract class Sys {

    //系统信息
    private static final oshi.SystemInfo sys = new oshi.SystemInfo();
    //操作系统信息
    private static final OperatingSystem os = sys.getOperatingSystem();
    //硬件信息
    private static final HardwareAbstractionLayer hal = sys.getHardware();
    //
    private static final ComputerSystem cs = hal.getComputerSystem();

    /**
     * 设备总览
     */
    public static Item deviceSummary() {
        //计算机信息总览
        Item deviceSummary = new Item("设备信息");
        deviceSummary.add("生产商", cs.getManufacturer());
        deviceSummary.add("型号", cs.getModel());
        deviceSummary.add("S/N", cs.getSerialNumber());

        return deviceSummary;
    }

    /**
     * 操作系统
     */
    public static Item os() {
        //操作系统
        Item operatingSys = new Item("OS信息");
        operatingSys.add("Name", os.toString());
        operatingSys.add("制造商", os.getManufacturer());
        operatingSys.add("Version", os.getVersion().toString());
        operatingSys.add("Bitness", os.getBitness());
        operatingSys.add("Family", os.getFamily());
        operatingSys.add("BootTime", TimeUnit.SECONDS.toHours(os.getSystemBootTime()) + "h");
        operatingSys.add("UpTime", TimeUnit.SECONDS.toHours(os.getSystemUptime()) + "h");
        return operatingSys;
    }


    /**
     * 固件信息
     */
    public static Item firmware() {
        final Firmware fw = cs.getFirmware();
        Item firmware = new Item("固件信息");
        firmware.add("名称", fw.getName());
        firmware.add("生产商", fw.getManufacturer());
        firmware.add("版本", fw.getVersion());
        firmware.add("发布日期", fw.getReleaseDate());
        firmware.add("描述信息", fw.getDescription());
        return firmware;
    }


    /**
     * 主板信息
     */
    public static Item baseboard() {
        final Baseboard bb = cs.getBaseboard();
        Item baseboard = new Item("主板信息");
        baseboard.add("生产商", bb.getManufacturer());
        baseboard.add("型号", bb.getModel());
        baseboard.add("版本", bb.getVersion());
        baseboard.add("S/N", bb.getSerialNumber());
        return baseboard;
    }


    /**
     * 内存
     */
    public static Item memory() {
        final GlobalMemory gm = hal.getMemory();
        Item memory = new Item("内存信息");
        memory.add("Memory Size", formatBytes(gm.getTotal()));
        VirtualMemory vm = gm.getVirtualMemory();
        memory.add("Swap Size", formatBytes(null == vm ? 0 : vm.getSwapTotal()));
        return memory;
    }


    /**
     * 中央处理器
     */
    public static Item cpu() {
        final CentralProcessor cp = hal.getProcessor();
        Item centralProcessor = new Item("中央处理器-CPU");
        centralProcessor.add("物理核心数", cp.getPhysicalProcessorCount());
        centralProcessor.add("逻辑核心数", cp.getLogicalProcessorCount());
        centralProcessor.add("Physical CPU package(s)", cp.getPhysicalPackageCount());
        centralProcessor.add("Name", cp.getName());
        centralProcessor.add("Identifier", cp.getIdentifier());
        centralProcessor.add("Model", cp.getModel());
        centralProcessor.add("Vendor", cp.getVendor());
        centralProcessor.add("Family", cp.getFamily());
        centralProcessor.add("Stepping", cp.getStepping());
        centralProcessor.add("ProcessorID", cp.getProcessorID());
        centralProcessor.add("是否64位", cp.isCpu64bit());
        return centralProcessor;
    }


    /**
     * 磁盘信息
     */
    public static List<Item> disks() {
        List<Item> items = new LinkedList<>();
        //硬盘
        final HWDiskStore[] hwds = hal.getDiskStores();
        for (int i = 0; i < hwds.length; i++) {
            HWDiskStore hd = hwds[i];
            Item diskStore = new Item("磁盘-" + i);
            diskStore.add("Name", hd.getName());
            diskStore.add("Model", hd.getModel());
            diskStore.add("Serial", hd.getSerial());
            diskStore.add("Size", formatBytes(hd.getSize()));

            HWPartition[] hwps = hd.getPartitions();
            for (HWPartition p : hwps) {
                String key = p.getIdentification().replace(" ", "") + " : " + p.getMountPoint().replace(" ", "");
                diskStore.add(key, String.format("%s / %s / size: %s", p.getName(), p.getType(), formatBytes(p.getSize())));
            }

            items.add(diskStore);
        }
        return items;
    }


    /**
     * 网络接口
     */
    public static List<Item> networkIFs() {
        List<Item> items = new LinkedList<>();
        //网络接口
        final NetworkIF[] nifs = hal.getNetworkIFs();
        for (NetworkIF net : nifs) {
            Item networkIF = new Item("网络接口-" + net.getName());
            networkIF.add("Name", String.format("%s (%s)", net.getName(), net.getDisplayName()));
            networkIF.add("Mac Address", net.getMacaddr());
            networkIF.add("IPv4", Arrays.toString(net.getIPv4addr()));
            networkIF.add("IPv6", Arrays.toString(net.getIPv6addr()));
            networkIF.add("MTU", formatLong(net.getMTU()));
            networkIF.add("Speed", formatLong(net.getSpeed()) + "bps");

            items.add(networkIF);
        }
        return items;
    }


    /**
     * 网络参数
     */
    public static Item networkParams() {
        final NetworkParams np = os.getNetworkParams();
        Item netParams = new Item("网络参数");
        netParams.add("Host Name", np.getHostName());
        netParams.add("Domain Name", np.getDomainName());
        netParams.add("IPv4 Gateway", np.getIpv4DefaultGateway());
        netParams.add("IPv6 Gateway", np.getIpv6DefaultGateway());
        netParams.add("DNS Server", Arrays.toString(np.getDnsServers()));
        return netParams;
    }


    /**
     * 通过JVM获取的NET信息
     */
    public static Item jvmNet() {
        Item item = new Item("NET(from JVM)");
        for (Net n : Net.values()) {
            item.add(n.alias, n.value());
        }
        return item;
    }


    /**
     * 通过JVM获取的OS信息
     */
    public static Item jvmOS() {
        Item item = new Item("OS(from JVM)");
        for (OS e : OS.values()) {
            item.add(e.alias, e.value());
        }
        return item;
    }


    /**
     * 全部的系统信息
     */
    public static List<Item> values() {
        List<Item> items = new LinkedList<>();

        items.add(jvmOS());
        items.add(jvmNet());
        items.add(deviceSummary());
        items.add(os());
        items.add(firmware());
        items.add(baseboard());
        items.add(cpu());
        items.add(memory());
        items.addAll(disks());
        items.add(networkParams());
        items.addAll(networkIFs());

        return items;
    }


    private static String formatBytes(long v) {
        long v0 = B.toMB(v);
        return formatLong(v0) + " M";
    }


    private Sys() {
    }

}
