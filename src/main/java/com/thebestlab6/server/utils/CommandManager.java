package com.thebestlab6.server.utils;

import com.thebestlab6.server.commands.Executable;

public class CommandManager {
    private Executable info;
    private Executable help;
    private Executable show;
    private Executable add;
    private Executable update;
    private Executable remove_by_id;
    private Executable clear;
    private Executable execute_script;
    private Executable exit;
    private Executable add_if_max;
    private Executable add_if_min;
    private Executable remove_greater;
    private Executable remove_all_by_car;
    private Executable max_by_creation_date;
    private Executable filter_greater_than_soundtrack_name;

    public CommandManager(Executable info,
                          Executable help,
                          Executable show,
                          Executable add,
                          Executable update,
                          Executable remove_by_id,
                          Executable clear,
                          Executable execute_script,
                          Executable exit,
                          Executable add_if_max,
                          Executable add_if_min,
                          Executable remove_greater,
                          Executable remove_all_by_car,
                          Executable max_by_creation_date,
                          Executable filter_greater_than_soundtrack_name) {
        this.info = info;
        this.help = help;
        this.show = show;
        this.add = add;
        this.update = update;
        this.remove_by_id = remove_by_id;
        this.clear = clear;
        this.execute_script = execute_script;
        this.exit = exit;
        this.add_if_max = add_if_max;
        this.add_if_min = add_if_min;
        this.remove_greater = remove_greater;
        this.remove_all_by_car = remove_all_by_car;
        this.max_by_creation_date = max_by_creation_date;
        this.filter_greater_than_soundtrack_name = filter_greater_than_soundtrack_name;
    }

    public boolean info(String str, Object obj) {
        return info.execute(str, obj);
    }

    public boolean help(String str, Object obj) {
        return help.execute(str, obj);
    }

    public boolean show(String str, Object obj) {
        return show.execute(str, obj);
    }

    public boolean add(String str, Object obj) {
        return add.execute(str, obj);
    }

    public boolean update(String str, Object obj) {
        return update.execute(str, obj);
    }

    public boolean remove_by_id(String str, Object obj) {
        return remove_by_id.execute(str, obj);
    }

    public boolean clear(String str, Object obj) {
        return clear.execute(str, obj);
    }

    public boolean execute_script(String str, Object obj) {
        return execute_script.execute(str, obj);
    }
    public boolean exit(String str, Object obj) {
        return exit.execute(str, obj);
    }
    public boolean add_if_max(String str, Object obj) {
        return add_if_max.execute(str, obj);
    }
    public boolean add_if_min(String str, Object obj) {
        return add_if_min.execute(str, obj);
    }
    public boolean remove_greater(String str, Object obj) {
        return remove_greater.execute(str, obj);
    }
    public boolean remove_all_by_car(String str, Object obj) {
        return remove_all_by_car.execute(str, obj);
    }
    public boolean max_by_creation_date(String str, Object obj) {
        return max_by_creation_date.execute(str, obj);
    }
    public boolean filter_greater_than_soundtrack_name(String str, Object obj) {
        return filter_greater_than_soundtrack_name.execute(str, obj);
    }
}
