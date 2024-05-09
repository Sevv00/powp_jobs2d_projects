package edu.kis.powp.jobs2d.command.factory;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.builder.CompoundCommandBuilder;


public class CompoundCommandFactory {
    public static CompoundCommand getRectangleAction(String name) {
        CompoundCommandBuilder builder = new CompoundCommandBuilder();
        builder.setName(name)
                .addSetPosition(0, 0)
                .addOperateTo(0, 100)
                .addOperateTo(200, 100)
                .addOperateTo(200, 0)
                .addOperateTo(0, 0);
        return builder.build();
    }

    public static CompoundCommand getTriangleAction(String name) {
        CompoundCommandBuilder builder = new CompoundCommandBuilder();
        builder.setName(name)
                .addSetPosition(0, 100)
                .addOperateTo(200, 50)
                .addOperateTo(0, 0)
                .addOperateTo(0, 100);
        return builder.build();
    }

    public static CompoundCommand getSecretAction(String name) {
        CompoundCommandBuilder builder = new CompoundCommandBuilder();
        builder.setName(name)
                .addSetPosition(-20, -50)
                .addOperateTo(-20, -50)
                .addSetPosition(-20, -40)
                .addOperateTo(-20, 50)
                .addSetPosition(0, -50)
                .addOperateTo(0, -50)
                .addSetPosition(0, -40)
                .addOperateTo(0, 50)
                .addSetPosition(70, -50)
                .addOperateTo(20, -50)
                .addOperateTo(20, 0)
                .addOperateTo(70, 0)
                .addOperateTo(70, 50)
                .addOperateTo(20, 50);
        return builder.build();
    }

    public static CompoundCommand getDeeplyComplexAction(String name) {
        CompoundCommand rectangleCompoundCommand = CompoundCommandFactory.getRectangleAction("Rectangle command");
        CompoundCommand triangleCompoundCommand = CompoundCommandFactory.getTriangleAction("Triangle command");

        CompoundCommandBuilder builder = new CompoundCommandBuilder();
        builder.setName(name)
                .addCommand(rectangleCompoundCommand)
                .addCommand(triangleCompoundCommand);
        return builder.build();
    }
}
