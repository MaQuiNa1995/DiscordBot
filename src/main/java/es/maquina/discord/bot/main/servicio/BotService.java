package es.maquina.discord.bot.main.servicio;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

@Component
public class BotService {

	JDABuilder builder;

	private BotService() {
		builder = new JDABuilder(AccountType.BOT);

	}

}
