package mg.frontend.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mg.frontend.console.MenuCallback;

public class Menu {
    private String header;
    private List<String> options;
    private List<MenuCallback> callbacks;
    private Scanner scanner;

    public static MenuBuilder builder() {
        return new Menu.MenuBuilder();
    }

    private Menu(Scanner scanner, String header, 
        List<String> options, List<MenuCallback> callbacks) {

        this.header = header;
        this.options = options;
        this.callbacks = callbacks;
        this.scanner = scanner;
    }

    public void show() throws IndexOutOfBoundsException {

        System.out.println("\t" + this.header + "\n");
        for (int iter = 0; iter < options.size(); ++iter) {
            System.out.println(iter + ". " + options.get(iter));
        }

        System.out.print("Wybor > ");
        int choice = this.scanner.nextInt();
        System.out.println();

        if (choice < 0 || choice > callbacks.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        callbacks.get(choice).run();

    }

    public int selectFromList(String text, int bound) {
        return 0;
    }

    public static class MenuBuilder {
        private String header;
        private List<String> options = new ArrayList<>();
        private List<MenuCallback> callbacks = new ArrayList<>();
        private Scanner scanner;

        public MenuBuilder setScanner(Scanner scanner) {
            this.scanner = scanner;
            return this;
        }

        public MenuBuilder setHeader(String header) {
            this.header = header;
            return this;
        }

        public MenuBuilder addOption(String name, MenuCallback callback) {
            this.options.add(name);
            this.callbacks.add(callback);

            return this;
        }

        public Menu build() {
            return new Menu(this.scanner, this.header, this.options, this.callbacks);
        }
    }
}