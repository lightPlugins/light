package io.lightplugins.light.api;

public interface LightModule {

    void enable();

    void disable();

    void reload();

    boolean isEnabled();

    String getName();
}
