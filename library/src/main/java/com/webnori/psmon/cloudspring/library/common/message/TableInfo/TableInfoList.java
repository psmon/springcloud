package com.webnori.psmon.cloudspring.library.common.message.TableInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// for ImmutableMessage List
public class TableInfoList implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int sequenceNumber;
    private final List<TableInfo> values;

    public TableInfoList(int sequenceNumber, List<TableInfo> values) {
        this.sequenceNumber = sequenceNumber;
        this.values = Collections.unmodifiableList(new ArrayList<TableInfo>(values));
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<TableInfo> getValues() {
        return values;
    }
}
