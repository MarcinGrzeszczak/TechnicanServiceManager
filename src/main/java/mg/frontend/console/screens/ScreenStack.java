package mg.frontend.console.screens;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mg.backend.BackendFasade;

public class ScreenStack implements ScreenCallback {
    private final List<Screen> stack;
    private int index = 0;

    private ScreenStack(List<Screen> stack) {
        this.stack = stack;
        for (Screen screen : stack) {
            screen.setCallback(this);
        }

        this.displayScreen();
    }
    
    
    @Override
    public void back() {
        if (this.index > 0) {
            --this.index;
        }
        this.displayScreen();
    }

    @Override
    public void selection(int i) {
        if (this.index < this.stack.size()) {
            ++this.index;
        }
        this.displayScreen();
    }

    public void displayScreen() {
        this.stack.get(this.index).displayMenu();
    }

    public static class Builder {
        private List<Screen> stack = new ArrayList<>();
        private BackendFasade backend;
        private Scanner scanner;

        public Builder(BackendFasade backend, Scanner scanner) {
            this.backend = backend;
            this.scanner = scanner;
        }

        public <T extends Screen> Builder addToStack(Class<T> clazz)
                throws InstantiationException, IllegalAccessException, IllegalArgumentException,
                InvocationTargetException, NoSuchMethodException, SecurityException {

            T screenInstance = clazz
                .getDeclaredConstructor(BackendFasade.class, Scanner.class)
                .newInstance(this.backend, this.scanner);
            
            this.stack.add(screenInstance);

            return this;
        }

        public ScreenStack build() {
            return new ScreenStack(this.stack);
        }
    }
}
