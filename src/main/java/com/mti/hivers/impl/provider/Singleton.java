package com.mti.hivers.impl.provider;

import com.mti.hivers.Provider;

public class Singleton<PROVIDABLE_T,IMPL_T extends PROVIDABLE_T> implements Provider<PROVIDABLE_T,IMPL_T> {

    private final Class<PROVIDABLE_T> _type;
    private final IMPL_T _singleton;

    public Singleton(Class<PROVIDABLE_T> type, IMPL_T singleton) {
        _type = type;
        _singleton = singleton;
    }

    @Override
    public Class<PROVIDABLE_T> getProvidableType() {
        return _type;
    }

    @Override
    public IMPL_T getProvidedObject() {
        return _singleton;
    }
}
