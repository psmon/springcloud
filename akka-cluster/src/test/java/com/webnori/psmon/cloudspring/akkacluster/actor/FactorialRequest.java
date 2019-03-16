package com.webnori.psmon.cloudspring.akkacluster.actor;

import java.io.Serializable;

public class FactorialRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    public final Integer upToN;

    public FactorialRequest(int upToN) {
        this.upToN = upToN;
    }
}
