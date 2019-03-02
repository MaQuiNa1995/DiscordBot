package es.maquina.discord.bot.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import es.maquina.discord.bot.main.servicio.BotService;
import es.maquina.discord.config.Configuracion;

public class Main {
	public static void main(String[] args) {

		BotService bot;

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuracion.class)) {
			bot = context.getBean("botService", BotService.class);
		}

	}
}
