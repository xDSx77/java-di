package com.mti.hivers.impl.provider;

import com.mti.hivers.Provider;

import java.util.function.Supplier;

public class Prototype<PROVIDABLE_T,IMPL_T extends PROVIDABLE_T> implements Provider<PROVIDABLE_T,IMPL_T> {

    private final Class<PROVIDABLE_T> _type;
    private final Supplier<IMPL_T> _supplier;

    public Prototype(Class<PROVIDABLE_T> type ,Supplier<IMPL_T> supplier) {
        _supplier = supplier;
        _type = type;
    }

    @Override
    public Class<PROVIDABLE_T> getProvidableType() {
        return _type;
    }

    @Override
    public IMPL_T getProvidedObject() {
        return _supplier.get();
    }
}
