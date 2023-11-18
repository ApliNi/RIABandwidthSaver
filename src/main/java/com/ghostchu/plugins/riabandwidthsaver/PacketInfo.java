package com.ghostchu.plugins.riabandwidthsaver;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.LongAdder;

public class PacketInfo implements Comparable<PacketInfo>{
    private LongAdder pktCounter;
    private LongAdder pktSize;

    public PacketInfo(){
        this.pktCounter = new LongAdder();
        this.pktSize = new LongAdder();
    }

    public LongAdder getPktCounter() {
        return pktCounter;
    }

    public LongAdder getPktSize() {
        return pktSize;
    }

    @Override
    public int compareTo(@NotNull PacketInfo o) {
        return Long.compare(this.pktSize.longValue(), o.pktSize.longValue());
    }
}
