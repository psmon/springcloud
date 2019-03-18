package com.webnori.psmon.cloudspring.library.common.message.TableInfo;

import java.io.Serializable;

public class TableCMD implements Serializable {

    public static enum TableCMDType{
        SYNC_FIRST,
        SYNC_UPDATE,
        SYNC_AUTO
    }

    public final TableCMDType cmdType;

    private static final long serialVersionUID = 1L;

    public TableCMD(TableCMDType cmdType) {
        this.cmdType = cmdType;
    }
}
