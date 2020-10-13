package net.timfoil.wasm;

public class WasmModule {

    // load wasm_module_impl.so or wasm_module_imple.dll (unix vs windows)
    static { System.loadLibrary("wasm_module_impl"); }

    private long module;

    native private static long getModule();

    public WasmModule() {
        module = getModule();
    }
}
