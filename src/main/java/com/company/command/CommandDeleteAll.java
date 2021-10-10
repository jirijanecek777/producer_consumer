package com.company.command;

import com.company.repository.DbUserRepository;

import java.sql.SQLException;

public class CommandDeleteAll implements Command {

    @Override
    public void execute() throws SQLException {
        new DbUserRepository().deleteAll();
    }

    @Override
    public String toString() {
        return "CommandDeleteAll";
    }

}
