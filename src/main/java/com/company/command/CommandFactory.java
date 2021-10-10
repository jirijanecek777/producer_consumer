package com.company.command;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommandFactory {

    public static Command getCommand(String data) {
        if (data == null) {
            return null;
        }
        if (data.startsWith("Add")) {
            var parameters = extractParameters(data);
            if (parameters.size() != 3) {
                return null;
            }
            Long id = getAsLong(parameters.get(0));
            if (id == null) {
                return null;
            }

            return new CommandAdd(
                    id,
                    removeQuotations(parameters.get(1)),
                    removeQuotations(parameters.get(2))
            );

        } else if (data.equals("PrintAll")) {
            return new CommandPrintAll();

        } else if (data.equals("DeleteAll")) {
            return new CommandDeleteAll();
        }

        return null;
    }

    private static Long getAsLong(String data) {
        try {
            return Long.parseLong(data);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String removeQuotations(String data) {
        return data.replaceAll("^\"|\"$", "");
    }

    private static List<String> extractParameters(String data) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(data);

        if (matcher.find()) {
            return Arrays.stream(matcher.group(1).split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }

        return List.of();
    }
}
