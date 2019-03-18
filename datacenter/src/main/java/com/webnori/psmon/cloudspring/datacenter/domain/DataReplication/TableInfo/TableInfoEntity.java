package com.webnori.psmon.cloudspring.datacenter.domain.DataReplication.TableInfo;

import com.webnori.psmon.cloudspring.library.common.message.TableInfo.TableInfo;

public class TableInfoEntity {

    private static final long serialVersionUID = 1L;

    public final int tableId;

    public final String tableName;

    public final int tableType;

    public TableInfoEntity(int tableId, String tableName, int tableType) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.tableType = tableType;
    }

    public TableInfo toTableInfo() {
        return new TableInfo(tableId,tableName,tableType);
    }

}
