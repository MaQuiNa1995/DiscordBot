package es.maquina.discord.bot.main.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

@Component("botListener")
public class BotListener extends ListenerAdapter {

	private List<String> listaJugadores = new ArrayList<>();

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {

		String mensajeUsuario = event.getMessage().getContentRaw();
		MessageChannel canal = event.getChannel();

		if (mensajeUsuario.startsWith("!Añadir Jugador")) {

			String jugadorAnnadir = mensajeUsuario.split(":")[1];
			listaJugadores.add(jugadorAnnadir);
			canal.sendMessage("Jugador Añadido: " + jugadorAnnadir).queue();

		} else if (mensajeUsuario.startsWith("!Listar Jugadores") && listaJugadores.isEmpty() == Boolean.FALSE) {

			StringBuilder participantes = new StringBuilder("Lista De Jugadores Del Torneo: \n");

			for (String jugador : listaJugadores) {
				participantes.append(jugador + "\n");
			}

			canal.sendMessage(participantes.toString());

		} else if (mensajeUsuario.startsWith("!Reiniciar")) {
			listaJugadores.clear();
		}
	}

}
