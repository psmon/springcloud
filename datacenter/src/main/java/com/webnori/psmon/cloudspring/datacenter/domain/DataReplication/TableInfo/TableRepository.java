package com.webnori.psmon.cloudspring.datacenter.domain.DataReplication.TableInfo;

import java.util.List;

interface TableRepository {
    List<TableInfoEntity> findAllTableInfos();
}
