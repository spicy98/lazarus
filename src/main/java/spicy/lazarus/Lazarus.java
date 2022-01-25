package spicy.lazarus;

import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spicy.lazarus.command.CommandManager;
import spicy.lazarus.module.ModuleManager;
import spicy.lazarus.util.client.ConfigUtil;

import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;

public class Lazarus implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("lazarus");
	public static final IEventBus EVENT_BUS = new EventBus();

	@Override
	public void onInitialize() {
		EVENT_BUS.registerLambdaFactory("spicy.lazarus", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
		LOGGER.info("Welcome to Lazarus.");

		ModuleManager.init();
		CommandManager.init();
		ConfigUtil.setupConfig();
		try {
			ConfigUtil.loadConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		EVENT_BUS.subscribe(this);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOGGER.info("Thank you for running Neon Client.");
			ConfigUtil.saveConfig();
		}));
	}
}
