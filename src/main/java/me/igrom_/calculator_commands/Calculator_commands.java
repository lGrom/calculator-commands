package me.igrom_.calculator_commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.text.Text;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class Calculator_commands implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            final LiteralCommandNode<FabricClientCommandSource> calculateNode = dispatcher.register(ClientCommandManager.literal("calculate")
            .then(argument("expression", StringArgumentType.greedyString())
            .executes(context -> {
                final String expression = StringArgumentType.getString(context, "expression");

                // evaluate
                Expression parsedExpression = new ExpressionBuilder(expression).build();
                final double result = parsedExpression.evaluate();

                // convert to string, round to 10 decimals, and remove trailing zeros
                DecimalFormat decimalFormat = new DecimalFormat("0");
                decimalFormat.setMaximumFractionDigits(6);

                context.getSource().sendFeedback(Text.literal("Result: " + decimalFormat.format(result)));
                return 1;
            })));

            dispatcher.register(ClientCommandManager.literal("calc").redirect(calculateNode));
        });
    }
}
