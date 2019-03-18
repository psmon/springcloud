package com.webnori.psmon.cloudspring.datacenter.domain.DataReplication.TableInfo;

import java.util.ArrayList;
import java.util.List;

public class TableMemoryRepository implements TableRepository {

    private List<TableInfoEntity> tableList = new ArrayList<>();

    public List<TableInfoEntity> findAllTableInfos() {
        return tableList;
    }

    public void createDummyData() {
        for(int i=0;i<100;i++){
            TableInfoEntity addData = new TableInfoEntity(i,"table:"+i,1);
            tableList.add(addData);
        }
    }

}
