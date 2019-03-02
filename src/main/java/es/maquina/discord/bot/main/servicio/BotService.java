package es.maquina.discord.bot.main.servicio;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

@Component("botService")
public class BotService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BotService.class);

	@Value("${bot.token}")
	private String token;

	private JDABuilder builder;

	private BotService() {
		builder = new JDABuilder(AccountType.BOT);
		builder.setToken(token);
		try {
			builder.build();
		} catch (LoginException exception) {
			builder = null;
			LOGGER.error("Se ha producdo una excepcion al crear el bot mas info: " + exception.getMessage());
		}
	}

}
