package com.github.rawnly.enums;

public enum Severity {
    INFO("info"),
    WARNING("warning"),
    DEBUG("debug"),
    ERROR("error"),
    FATAL("fatal");

    public final String value;

    Severity(String value) {
        this.value = value;
    }
}
