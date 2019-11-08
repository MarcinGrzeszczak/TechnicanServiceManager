package mg.frontend.console.screens;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import mg.backend.BackendFasade;
import mg.frontend.console.Menu;

public abstract class Screen {

    private Menu menu;
    protected int selectedId;
    protected Scanner scanner;
    protected ScreenCallback callback;
    protected BackendFasade backend;


    public Screen(BackendFasade backend, Scanner scanner) {
        this.scanner = scanner;
        this.backend = backend;
    }

    public void setCallback(ScreenCallback callback) {
        this.callback = callback;
    }

    protected void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void displayMenu() {
        this.menu.show();

    }

    public void back() {
        this.callback.back();
    }

    public void select() {
        System.out.print("\tWybierz ID: ");
        this.selectedId = scanner.nextInt();
        System.out.println();
    }

    public int getSelectedId() {
        return selectedId;
    }

    abstract void displayTable();

    protected String showTable(List<List<String>> table) {

        List<List<String>> transposedArray = this.transpose().apply(table);
        
        List<List<String>> editedArray = transposedArray.stream()
            .map(column -> {
                int longestValue = column.stream()
                    .reduce("",(prev, curr) -> {
                        if (curr == null) {
                            return prev;
                        }
                        return prev.length() < curr.length() ? curr : prev;
                    })
                    .length();
                
                return column.stream()
                    .map(element -> {
                        if (element == null) {
                            element = "null";
                        }
                        return tableColumnPadding(2) 
                            + element 
                            + tableColumnPadding(longestValue - element.length() + 2);
                    })
                    .collect(Collectors.toList());
            })
            .collect(Collectors.toList());

       
        List<List<String>> listToFlat = this.transpose().apply(editedArray);

        String flattenArray = listToFlat.stream()
            .map(column -> column.stream()
                .collect(Collectors.joining("|")))
            .collect(Collectors.joining("\n"));

        System.out.println(flattenArray);
        return flattenArray; 
    }

    private UnaryOperator<List<List<String>>> transpose() {
        return list -> {
            return IntStream.range(0, list.get(0).size())
                .mapToObj(row -> {
                    return IntStream.range(0, list.size())
                        .mapToObj(column -> {
                            return list.get(column).get(row);
                        })
                        .collect(Collectors.toList());
                })
                .collect(Collectors.toList());
        };
                
    }

    public Menu getMenu() {
        return menu;
    }

    private String tableColumnPadding(int length) {
        String padding = "";
        for (int iter = 0; iter < length; ++iter) {
            padding += " ";
        }
        return padding;
    }

    protected Map<String, String> add(Map<String, String> data) {
        this.scanner.nextLine();
        for (String key : data.keySet()) {
            System.out.print(key + "(" + data.get(key) + ") : ");
            String value = this.scanner.nextLine();
            if (!value.equals("")) {
                data.replace(key, value);
            }
        }
        return data;
    }
}