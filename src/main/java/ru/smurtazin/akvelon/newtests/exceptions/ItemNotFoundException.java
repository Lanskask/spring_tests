package ru.smurtazin.akvelon.newtests.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemName, Long id) {
        super(String.format("Could not find %s %s", itemName, id));
    }
}
