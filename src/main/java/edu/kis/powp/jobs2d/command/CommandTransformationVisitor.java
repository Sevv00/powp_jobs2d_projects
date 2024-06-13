package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.command.builder.CompoundCommandBuilder;
import edu.kis.powp.jobs2d.transformations.Transformation;

import java.awt.Point;

public class CommandTransformationVisitor implements CommandVisitor {

    private final CompoundCommandBuilder compoundCommandBuilder;
    private final Transformation transformation;

    public ImmutableCompoundCommand getTransformedCommand() {
        return compoundCommandBuilder.build();
    }

    public CommandTransformationVisitor(String commandName, Transformation transformation) {
        String newName = commandName + "_" + transformation.getName();
        this.compoundCommandBuilder = new CompoundCommandBuilder();
        compoundCommandBuilder.setName(newName);
        this.transformation = transformation;
    }

    @Override
    public void visit(OperateToCommand operateToCommand){
        Point point = new Point(operateToCommand.getX(), operateToCommand.getY());
        applyTransformation(point, OperateToCommand::new);
    }

    @Override
    public void visit(SetPositionCommand setPositionCommand){
        Point point = new Point(setPositionCommand.getX(), setPositionCommand.getY());
        applyTransformation(point, SetPositionCommand::new);
    }

    @Override
    public void visit(ICompoundCommand compoundCommand) {
        compoundCommand.iterator().forEachRemaining(command -> command.accept(this));
    }

    private void applyTransformation(Point point, CommandCreator commandCreator) {
        Point transformedPoint = transformation.transform(point);
        add(commandCreator.create(transformedPoint.x, transformedPoint.y));
    }

    private void add(DriverCommand command) {
        compoundCommandBuilder.addCommand(command);
    }

    @Override
    public String toString() {
        return compoundCommandBuilder.toString();
    }

    /**
     * Functional interface for OperationToCommand and SetPositionCommand constructors.
     */
    @FunctionalInterface
    private interface CommandCreator {
        DriverCommand create(int x, int y);
    }
}
