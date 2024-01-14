package me.igrom_.calculator_commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class Calculator_commands implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(
            ClientCommandManager.literal("calculate")
            .then(argument("expression", StringArgumentType.greedyString())
            .executes(context -> {
                final String expression = StringArgumentType.getString(context, "expression");
//                final int result = kjlasdf;
                context.getSource().sendFeedback(Text.literal(expression));
                return 1;
        }))));
    }
}
