package es.maquina.discord.bot.main.dominio;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import es.maquina.discord.bot.main.servicio.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

@Service("bot")
public class Bot {

	@Resource(name = "botListener")
	private BotListener botListener;

	private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

	@Value("${bot.token}")
	private String token;

	private JDABuilder builder;

	public void iniciarBot() {

		if (builder == null) {
			builder = new JDABuilder(AccountType.BOT);
			builder.setToken(token);
			builder.addEventListener(botListener);

			try {
				builder.build();
			} catch (LoginException exception) {
				builder = null;
				LOGGER.error("Se ha producdo una excepcion al crear el bot mas info: " + exception.getMessage());
			}
		}
	}

}
