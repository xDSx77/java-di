package com.mti.hivers;

public interface Provider<PROVIDABLE_T,IMPL_T extends PROVIDABLE_T> {
    public Class<PROVIDABLE_T> getProvidableType();
    public IMPL_T getProvidedObject();
}
