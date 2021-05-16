package com.thebestlab6.server.commands;

import com.thebestlab6.common.data.HumanBeing;
import com.thebestlab6.common.exceptions.WrongAmountOfElementsException;
import com.thebestlab6.server.utils.CollectionManager;
import com.thebestlab6.server.utils.ResponseBuilder;

import javax.xml.stream.XMLStreamException;

public class AddIfMax implements Executable{
    private CollectionManager collectionManager;

    public AddIfMax(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object humanObj) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            HumanBeing human = (HumanBeing) humanObj;
            if (Float.compare(human.getImpactSpeed(), collectionManager.getMaxImpactSpeed()) == 1) {
                collectionManager.add(human);
                ResponseBuilder.append("Элемент успешно добавлен в коллекцию!");
            }
            else ResponseBuilder.append("Элемент не был добавлен в коллекцию, так как его значение поля ImpactSpeed меньше максимального");
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }
}
