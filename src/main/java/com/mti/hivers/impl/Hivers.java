package com.mti.hivers.impl;

import com.mti.hivers.Provider;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Optional;
import java.util.ServiceLoader;

public class Hivers {

    private final HashMap<Class<?>,Provider<?,?>> _container = new HashMap<>();

    public void addProvider(Provider<?,?> provider)  {
            _container.put(provider.getProvidableType(), provider);
    }

    public <PROVIDABLE_T> Optional<PROVIDABLE_T> instanceOf(Class<PROVIDABLE_T> providableClass)
    {
        final Provider<?,?> provider = _container.get(providableClass);
        if (provider == null)
            return Optional.empty();
        return Optional.of((PROVIDABLE_T) provider.getProvidedObject());
    }

    public <PROVIDABLE_T> PROVIDABLE_T instanceOfOrThrow(Class<PROVIDABLE_T> providableClass)
    {
        final Optional<PROVIDABLE_T> optional = this.instanceOf(providableClass);
        if (optional.isEmpty())
            throw new IllegalArgumentException();
        return optional.get();
    }
}
