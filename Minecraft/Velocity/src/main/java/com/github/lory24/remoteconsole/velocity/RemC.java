package com.github.lory24.remoteconsole.velocity;

public enum RemC {
    INSTANCE,
    ;

    private Entry entry;

    public boolean enable(final Entry entry) {
        this.entry = entry;

        return true;
    }

    public void disable() {

    }
}
