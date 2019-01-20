package com.webnori.psmon.cloudspring.library.common.message;

import java.io.Serializable;

public class CMD_REMOTE implements Serializable {

    private int messageType;
    private String message;
    private String debugInfo;

    public CMD_REMOTE(int messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    public int getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugInfo() {
        return debugInfo;
    }
}
