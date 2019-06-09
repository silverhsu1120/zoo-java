package com.silver.zoo.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class NavBarViewModel extends ViewModel {

    private MutableLiveData<State> state = new MutableLiveData<>();
    private List<State> stack = new ArrayList<>();

    public MutableLiveData<State> getState() {
        return state;
    }

    public List<State> getStack() {
        return stack;
    }

    public void pushState(int icon, String title) {
        State state = new State(icon, title);
        stack.add(state);
        this.state.setValue(state);
    }

    public void popState() {
        if (stack.size() == 1) return;
        stack.remove(stack.size() - 1);
        State state = stack.get(stack.size() - 1);
        this.state.setValue(state);
    }

    public class State {

        private int icon;
        private String title;

        State(int icon, String title) {
            this.icon = icon;
            this.title = title;
        }

        public int getIcon() {
            return icon;
        }

        public String getTitle() {
            return title;
        }
    }
}
