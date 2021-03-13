package dev.jorik.cluegame.application;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    protected DestroyUseCase destroyUseCase;

    public BaseViewModel(DestroyUseCase destroyUseCase) {
        this.destroyUseCase = destroyUseCase;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        destroyUseCase.destroy();
    }
}
