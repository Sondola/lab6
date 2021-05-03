package com.thebestlab6.server.utils;

import com.thebestlab6.common.objects.Request;
import com.thebestlab6.common.objects.Response;
import com.thebestlab6.common.objects.ResponseAnswer;

public class Analyzer {
    private CommandManager commandManager;

    public Analyzer(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response handle(Request request) {
        ResponseAnswer responseAnswer = analyze(request.getCommandString(), request.getCommandArg(), request.getObject());
        Response response = new Response(responseAnswer, ResponseBuilder.getBuilder());
        ResponseBuilder.delete();
        return response;
    }

    public ResponseAnswer analyze(String commandName, String commandArg, Object commandObject) {
        switch(commandName) {
            case "HELP":
                if(!commandManager.help(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "INFO":
                if(!commandManager.info(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "SHOW":
                if(!commandManager.show(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "ADD":
                if(!commandManager.add(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "ADD_IF_MAX":
                if(!commandManager.add_if_max(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "ADD_IF_MIN":
                if(!commandManager.add_if_min(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "UPDATE":
                if(!commandManager.update(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "REMOVE_BY_ID":
                if(!commandManager.remove_by_id(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "REMOVE_ALL_BY_CAR":
                if(!commandManager.remove_all_by_car(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "CLEAR":
                if(!commandManager.clear(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            /*case "EXECUTE_SCRIPT":
                if(!commandManager.execute_script(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "EXIT":
                if(!commandManager.exit(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;*/
            case "MAX_BY_CREATION_DATE":
                if (!commandManager.max_by_creation_date(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "REMOVE_GREATER":
                if(!commandManager.remove_greater(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            case "FILTER_GREATER_THAN_SOUNDTRACK_NAME":
                if(!commandManager.filter_greater_than_soundtrack_name(commandArg, commandObject)) return ResponseAnswer.ERROR;
                else return ResponseAnswer.OK;
            default:
                ResponseBuilder.appendError("Команда " + commandName + " не входит в список доступных");
                return ResponseAnswer.ERROR;
        }
    }
}
