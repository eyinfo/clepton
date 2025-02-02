package com.eyinfo.foundation;

import com.eyinfo.foundation.utils.ConvertUtils;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

/// 雪花工具管理类
public class Butterfly {

    private static Butterfly butterfly;

    public static Butterfly getInstance() {
        if (butterfly == null) {
            synchronized (Butterfly.class) {
                if (butterfly == null) {
                    butterfly = new Butterfly();
                }
            }
        }
        return butterfly;
    }

    private SequenceSnowflakeGenerator idGen;

    private Butterfly() {
        init();
    }

    public void init() {
        IdGeneratorOptions options = new IdGeneratorOptions();
        // 雪花计算方法 （1-漂移算法|2-传统算法），默认1
        options.Method = 1;
        // 机器码 必须由外部设定，最大值 2^WorkerIdBitLength-1
        // 默认值6，限定 WorkerId 最大值为2^6-1，即默认最多支持64个节点。
        options.WorkerId = 6;
        // 基础时间（ms单位） 不能超过当前系统时间
        // 如果要兼容老系统的雪花算法，此处应设置为老系统的BaseTime。
        // options.BaseTime = 1582206693000L;
        // 机器码位长 默认值6，取值范围 [1, 15]（要求：序列数位长+机器码位长不超过22）
        options.WorkerIdBitLength = 6;
        // 序列数位长 默认值6，取值范围 [3, 21]（要求：序列数位长+机器码位长不超过22）
        options.SeqBitLength = 6;
        YitIdHelper.setIdGenerator(options);

        idGen = new SequenceSnowflakeGenerator(0, 0);
    }

    public long nextId() {
        return YitIdHelper.nextId();
    }

    public String nextIdWith() {
        return String.valueOf(nextId());
    }

    public long nextSequenceId() {
        return idGen.nextId();
    }

    public long nextShortBitId(int numberOfBits) {
        String id = nextIdWith();
        String shortId = id.substring(id.length() - numberOfBits);
        return ConvertUtils.toLong(shortId);
    }

    /**
     * 默认8位
     */
    public long nextShortBitId() {
        return nextShortBitId(8);
    }
}
