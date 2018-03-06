package by.tc.task05.parser.iface;

import by.tc.task05.model.Entity;

import java.util.List;

public interface Parser {
    void setFileSource();

    List<Entity> parse();
}
