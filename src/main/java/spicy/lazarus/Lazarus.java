package spicy.lazarus;

import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spicy.lazarus.commands.CommandManager;
import spicy.lazarus.modules.ModuleManager;

import java.lang.invoke.MethodHandles;

public class Lazarus implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Lazarus");
	public static final IEventBus EVENT_BUS = new EventBus();
	public static ModuleManager moduleManager = new ModuleManager();

	@Override
	public void onInitialize() {
		EVENT_BUS.registerLambdaFactory("spicy.lazarus", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));

		moduleManager.init();

		CommandManager.init();

		EVENT_BUS.subscribe(this);
		LOGGER.info("Welcome to Lazarus.");
	}
}
