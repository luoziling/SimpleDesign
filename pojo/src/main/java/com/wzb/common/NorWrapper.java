package com.wzb.common;

/**
 * @author Satsuki
 * @time 2020/4/16 13:49
 * @description:
 */
public class NorWrapper {
    private ConcalWrapper concalWrapper;
    private String nowValue;
    private String nextValue;

    public NorWrapper() {
    }

    public NorWrapper(ConcalWrapper concalWrapper, String nowValue, String nextValue) {
        this.concalWrapper = concalWrapper;
        this.nowValue = nowValue;
        this.nextValue = nextValue;
    }

    public ConcalWrapper getConcalWrapper() {
        return concalWrapper;
    }

    public void setConcalWrapper(ConcalWrapper concalWrapper) {
        this.concalWrapper = concalWrapper;
    }

    public String getNextValue() {
        return nextValue;
    }

    public void setNextValue(String nextValue) {
        this.nextValue = nextValue;
    }

    public String getNowValue() {
        return nowValue;
    }

    public void setNowValue(String nowValue) {
        this.nowValue = nowValue;
    }

    @Override
    public String toString() {
        return "NorWrapper{" +
                "concalWrapper=" + concalWrapper +
                ", nowValue='" + nowValue + '\'' +
                ", nextValue='" + nextValue + '\'' +
                '}';
    }
}
