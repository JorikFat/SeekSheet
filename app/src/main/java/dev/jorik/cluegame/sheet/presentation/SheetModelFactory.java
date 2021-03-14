package dev.jorik.cluegame.sheet.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dev.jorik.cluegame.sheet.domain.SheetDomain;

public class SheetModelFactory implements ViewModelProvider.Factory{
    private final SheetDomain domain;

    public SheetModelFactory(SheetDomain domain) {
        this.domain = domain;
    }

    @NonNull @Override @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SheetViewModel(domain);
    }
}
