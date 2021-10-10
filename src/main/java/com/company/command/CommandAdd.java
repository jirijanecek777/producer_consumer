package com.company.command;

import com.company.repository.DbUserRepository;

import java.sql.SQLException;
import java.util.Objects;

public class CommandAdd implements Command {

    private final Long id;
    private final String guid;
    private final String name;

    public CommandAdd(Long id, String guid, String name) {
        this.id = id;
        this.guid = guid;
        this.name = name;
    }

    @Override
    public void execute() throws SQLException {
        new DbUserRepository().save(this.id, this.guid, this.name);
    }

    @Override
    public String toString() {
        return "CommandAdd {" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandAdd that = (CommandAdd) o;
        return id.equals(that.id) && guid.equals(that.guid) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guid, name);
    }
}
