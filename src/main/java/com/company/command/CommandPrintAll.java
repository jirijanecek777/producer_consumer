package com.company.command;

import com.company.domain.User;
import com.company.repository.DbUserRepository;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class CommandPrintAll implements Command {

    @Override
    public void execute() throws SQLException {
        var users = new DbUserRepository().findAll();
        String formattedUsers = users.isEmpty()
                ? "no users"
                : users.stream().map(User::toString).collect(Collectors.joining("\n"));

        System.out.println(formattedUsers);
    }

    @Override
    public String toString() {
        return "CommandPrintAll";
    }

}
