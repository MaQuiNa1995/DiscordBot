package es.maquina.discord.bot.main.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

			canal.sendMessage(annadirJugador(mensajeUsuario)).queue();

		} else if (mensajeUsuario.startsWith("!Listar Jugadores") && listaJugadores.isEmpty() == Boolean.FALSE) {

			canal.sendMessage(listarJugadores()).queue();

		} else if (mensajeUsuario.startsWith("!Reiniciar")) {

			listaJugadores.clear();

		} else if (mensajeUsuario.startsWith("!Montar Equipos")) {

			canal.sendMessage(listarJugadores()).queue();
			canal.sendMessage(montarEquipos()).queue();

		}

	}

	private CharSequence annadirJugador(String mensajeUsuario) {
		String jugadorAnnadir = mensajeUsuario.split(":")[1];
		listaJugadores.add(jugadorAnnadir);
		return "Jugador Añadido: " + jugadorAnnadir;
	}

	private CharSequence montarEquipos() {
		List<String> equipo1 = new ArrayList<>();
		List<String> equipo2 = new ArrayList<>();

		Random random = new Random();

		Boolean annadirEquipo1 = Boolean.TRUE;

		while (listaJugadores.isEmpty() == Boolean.FALSE) {

			int numAleatorio = random.nextInt(listaJugadores.size());

			String jugadorElegido = listaJugadores.get(numAleatorio);

			if (annadirEquipo1) {

				equipo1.add(jugadorElegido);

			} else {

				equipo2.add(jugadorElegido);

			}
			listaJugadores.remove(numAleatorio);

			annadirEquipo1 = !annadirEquipo1;
		}

		StringBuilder equipos = new StringBuilder();
		equipos.append("Jugadores del equipo Ñuñu: \n\t");
		equipos.append(equipo1.toString());
		equipos.append("\nJugadores del equipo Cucky: \n\t");
		equipos.append(equipo2.toString());

		return equipos.toString();
	}

	private String listarJugadores() {
		StringBuilder participantes = new StringBuilder("Lista De Jugadores Del Torneo: \n");

		for (String jugador : listaJugadores) {
			participantes.append(jugador + "\n");
		}

		return participantes.toString();
	}

}
