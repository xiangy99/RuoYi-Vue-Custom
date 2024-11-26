package com.ruoyi.common.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Twitter_Snowflake<br> SnowFlake的结构如下(每部分用-分开):<br> 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000
 * - 000000000000 <br> 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间戳(毫秒级)，注意，41位时间戳不是存储当前时间的时间戳，而是存储时间戳的差值（当前时间戳 - 开始时间戳)
 * 得到的值），这里的的开始时间戳，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间戳，可以使用69年，年T = (1L << 41) / (1000L *
 * 60 * 60 * 24 * 365) = 69<br> 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间戳)产生4096个ID序号<br> 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 */
public class IdUtil {
    
    // ==============================Fields===========================================
    
    /**
     * 开始时间戳
     */
    private final static long twepoch = 879955200000L;
    
    /**
     * 能容忍的系统时钟回拨时间（2秒）
     */
    private final static long timeOffset = 2000L;
    
    /**
     * 机器id所占的位数
     */
    private final static long workerIdBits = 5L;
    
    /**
     * 数据标识id所占的位数
     */
    private final static long datacenterIdBits = 5L;
    
    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    
    /**
     * 支持的最大数据标识id，结果是31
     */
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    
    /**
     * 序列在id中占的位数
     */
    private final static long sequenceBits = 12L;
    
    /**
     * 机器ID向左移12位
     */
    private final static long workerIdShift = sequenceBits;
    
    /**
     * 数据标识id向左移17位(12+5)
     */
    private final static long datacenterIdShift = sequenceBits + workerIdBits;
    
    /**
     * 时间戳向左移22位(5+5+12)
     */
    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);
    
    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    
    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;
    
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    
    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;
    
    // ==============================Constructors=====================================
    
    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public IdUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
    
    // ==============================Methods==========================================
    
    private static final IdUtil idWorker = new IdUtil(1, 1);
    
    /**
     * 生成数据库主键ID
     *
     * @return 数据库主键ID
     */
    public static Long getId() {
        return idWorker.nextId();
    }
    
    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        
        // 当前时间戳小于上一次ID生成的时间戳
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            // 系统时钟回退在容忍范围内，例如出现过闰秒
            if (offset < timeOffset) {
                timestamp = lastTimestamp;
            }
            // 系统时钟回退超出容忍范围，抛出异常
            else {
                throw new RuntimeException(
                        String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", offset));
            }
        }
        
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        
        // 上次生成ID的时间戳
        lastTimestamp = timestamp;
        
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }
    
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    
    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
    
    /**
     * 解析主键ID，获取工作ID，数据中心ID，ID对应时间
     *
     * @param id 主键ID
     * @return 工作ID，数据中心ID，ID对应时间
     */
    public static JSONObject parseInfo(long id) {
        String sonwFlakeId = Long.toBinaryString(id);
        int len = sonwFlakeId.length();
        JSONObject jsonObject = new JSONObject();
        int sequenceStart = (int) (len < workerIdShift ? 0 : len - workerIdShift);
        long dataCenterIdShift = sequenceBits + workerIdBits;
        int workerStart = (int) (len < dataCenterIdShift ? 0 : len - dataCenterIdShift);
        int timeStart = (int) (len < timestampLeftShift ? 0 : len - timestampLeftShift);
        String sequence = sonwFlakeId.substring(sequenceStart, len);
        String workerId = sequenceStart == 0 ? "0" : sonwFlakeId.substring(workerStart, sequenceStart);
        String dataCenterId = workerStart == 0 ? "0" : sonwFlakeId.substring(timeStart, workerStart);
        String time = timeStart == 0 ? "0" : sonwFlakeId.substring(0, timeStart);
        int sequenceInt = Integer.valueOf(sequence, 2);
        jsonObject.put("sequence", sequenceInt);
        int workerIdInt = Integer.valueOf(workerId, 2);
        jsonObject.put("workerId", workerIdInt);
        int dataCenterIdInt = Integer.valueOf(dataCenterId, 2);
        jsonObject.put("dataCenter", dataCenterIdInt);
        long diffTime = Long.parseLong(time, 2);
        long timeLong = diffTime + twepoch;
        Date date = formatTime(timeLong);
        jsonObject.put("date", date);
        jsonObject.put("dateTime", DateUtil.formatDateTime(date));
        return jsonObject;
    }
    
    private static Date formatTime(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.getTime();
    }
    
    // ==============================Test=============================================
    
    /**
     * 测试
     */
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(IdUtil.getId());
        }
    }
}