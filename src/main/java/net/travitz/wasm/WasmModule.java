package net.travitz.wasm;

public class WasmModule {

    private long module;

    native private static long getModule();

    public WasmModule() {
        module = getModule();
    }
}
