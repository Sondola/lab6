package com.thebestlab6.common.objects;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private String commandArg;
    private Object obj;

    public Request(String command, String commandArg, Object obj) {
        this.command = command;
        this.commandArg = commandArg;
        this.obj = obj;
    }

    public Request(String command, String commandArr) {
        this(command, commandArr, null);
    }

    public String getCommandString() {
        return command;
    }

    public String getCommandArg() {return commandArg;}

    public Object getObject() {return obj;}

    public boolean isEmpty() {
        return command.isEmpty();
    }

    @Override
    public String toString() {
        return "Request[" + command + "]";
    }
}
