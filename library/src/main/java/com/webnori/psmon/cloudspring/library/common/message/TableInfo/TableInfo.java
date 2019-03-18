package com.webnori.psmon.cloudspring.library.common.message.TableInfo;

import java.io.Serializable;

public class TableInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public final int tableId;

    public final String tableName;

    public final int tableType;

    public TableInfo(int tableId, String tableName, int tableType) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.tableType = tableType;
    }
}
