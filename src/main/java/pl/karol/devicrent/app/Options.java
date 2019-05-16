package pl.karol.devicrent.app;

public enum Options {
    ADD_DEVICE(1, "Add device"),
    ADD_CATEGORY(2, "Add category"),
    ADD_CUSTOMER(3, "Add client"),
    RENT_DEVICE(4, "Rent device"),
    DELETE_DEVICE(5, "Delete device"),
    DELETE_CATEGORY(6, "Delete category"),
    DELETE_CUSTOMER(7, "Delete client"),
    SEARCH_DEVICES(8, "Search products"),
    END(9, "End");

    private int id;
    private String description;

    Options(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Options getOption(int userInput) {
        if(userInput < 1 || userInput > values().length)
            throw new InvalidOptionException();
        return values()[userInput-1];
    }

    @Override
    public String toString() {
        return id + " - " + description;
    }


}
