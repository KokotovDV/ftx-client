package com.company.ftx.client;

/**
 * @author Dmitry Kokotov
 */
public class FtxRequest {
    private String op;
    private String channel;
    private String market;

    public FtxRequest(String operation, String channel, String market) {
        this.op = operation;
        this.channel = channel;
        this.market = market;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
