package es.maquina.discord.bot.main.servicio;

import java.util.ArrayList;
import java.util.Arrays;
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

		} else if (mensajeUsuario.startsWith("!Reglas")) {
			canal.sendMessage(crearAviso(canal)).queue();
		} else if (mensajeUsuario.startsWith("!RifarMapa")) {
			canal.sendMessage("Mapa Elegido: " + mapaAleatorio()).queue();
		}

	}

	private CharSequence mapaAleatorio() {
		List<String> listaMapas = Arrays.asList("Castillo",

				"Dorado", "Ecopunto: Antártica", "Eichenwalde", "Hanamura", "Hollywood", "Ilios", "Industrias Volskaya",
				"King's Row", "Necrópolis", "Nepal", "Numbani", "Observatorio: Gibraltar", "Oasis", "Ruta 66",
				"Selva Negra", "Templo de Anubis", "Torre Lijiang");

		return listaMapas.get(new Random().nextInt(listaMapas.size()));
	}

	private String crearAviso(MessageChannel canal) {

		StringBuilder aviso = new StringBuilder();

		aviso.append("@everyone\n");
		aviso.append("\tEstás serán las reglas:\n");
		aviso.append(
				"\t-En Lobby se elegirá l@s lideres de 'Ñuñu Team' y 'CuckyTeam' (Se intentará equilibrar por habilidad);.\n");
		aviso.append(
				"\t- Será 5vs5 si estamos todos este día, depende como varie los equipos, cambiará el tiempo de reaparición.\n");
		aviso.append(
				"\t- Si hay jugadores impares (ejemplo 5vs6);, el equipo con desventaja puede Optar o no por un bot con el personaje que deseen (Con dificultad Normal);.\n");
		aviso.append("\t- Se jugará un total de 4 partidos que compondrán los siguientes mapas:\n");
		aviso.append("\t   · Asalto → París\n");
		aviso.append("\t   · Asalto/Escolta → Blizzard World\n");
		aviso.append("\t   · Control → Busan\n");
		aviso.append("\t   · Escolta → Rialto\n");
		aviso.append("\t(En caso de empate se decidirá si hacer un 5º mapa por votación popular);.\n");
		aviso.append("\t- Empezará el torneo 'Ñuñus Cup Edición 2' dia 02/03/2019 a las 23:59.\n");
		aviso.append("\t-------------------------------------------------------------------------\n");
		aviso.append("\t¡MUCHA SUERTE Y PASADLO BIEN!");

		return aviso.toString();

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

		listaJugadores.addAll(Arrays.asList("Maquina1995", "Ericmeca98", "Carlos", "MagonxESP", "Helena",
				"HizoPOOMyaestaaquilaguerra", "Goliath", "Oliver", "Mikel", "Bir3venge"));

		for (String jugador : listaJugadores) {
			participantes.append(jugador + "\n");
		}

		return participantes.toString();
	}

}
