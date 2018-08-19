package com.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.DaoImp.IplScoreDao;
import com.app.Exceptions.UserDaoExpection;
import com.app.models.IplResponce;
import com.app.models.Player;
import com.app.models.Team;
import com.google.gson.Gson;

@Path("/iplscore")
public class iplScore {

	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerTeam(Team team) {

		int matchWon = team.getMatchWon();
		int matchtotal = team.getMatchtotal();

		double winPercentage = ((double) matchWon / matchtotal) * 100;
		System.out.println("winPercentage=" + winPercentage);
		if (winPercentage <= 65) {
			System.out.println("Team is Not Eligible to Play ");
			team.setReason("Your winning Persontage is <65");
			team.setStatus("NoT Eligible");

			return Response.status(200).entity(team).build();

		} else {
			System.out.println("Team Is Selected To Play......");
			team.setStatus("Your Eligible");
			team.setReason("winning Percentage is " + winPercentage);
			return Response.status(200).entity(team).build();
		}

	}

	@POST
	@Path("/saveplayer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePlayer(Player player) {
		System.out.println("Validation string.........");
		IplResponce iplResponce = new IplResponce();

		if (player.getName() == null || player.getName().isEmpty()) {
			iplResponce.setErrorCode("121");
			iplResponce.setErrorMessage("Name Should not be Empty...");
			iplResponce.setResponceDate(null);
			System.out.println(
					"Responce at server= " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());

			return Response.status(500).entity(iplResponce).build();

		} else if (player.getMobile() == null || player.getMobile().isEmpty()) {
			iplResponce.setErrorCode("122");
			iplResponce.setErrorMessage("Mobile Number In Valid");
			iplResponce.setResponceDate(null);
			System.out.println(
					"Responce at server= " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());
			return Response.status(500).entity(iplResponce).build();

		} else if (player.getEmail() == null || player.getEmail().isEmpty()) {
			iplResponce.setErrorCode("123");
			iplResponce.setErrorMessage("eMail not valid");
			iplResponce.setResponceDate(null);
			System.out.println(
					"Responce at server= " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());

			return Response.status(500).entity(iplResponce).build();

		} else if (player.getTeam() == null || player.getTeam().isEmpty()) {
			iplResponce.setErrorCode("124");
			iplResponce.setErrorMessage("team is should not be null & Empty");
			iplResponce.setResponceDate(null);
			System.out
					.println("Responce at server=" + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());

			return Response.status(500).entity(iplResponce).build();

		} else {
			try {
				IplScoreDao dao = new IplScoreDao();
				dao.registerPlayer();

			} catch (UserDaoExpection e) {
				iplResponce.setErrorCode(e.getReasonCode() + "");
				iplResponce.setErrorMessage(e.getMessage());
				iplResponce.setResponceDate(null);

				return Response.status(500).entity(iplResponce).build();
			}

			iplResponce.setErrorCode("000");
			iplResponce.setErrorMessage("Success");
			Gson gson = new Gson();
			String jsonPlayer = gson.toJson(player);
			iplResponce.setResponceDate(jsonPlayer);
			System.out.println("Player Json  is Done");
			System.out.println("Responce set=  " + iplResponce.toString());

			return Response.status(200).entity(iplResponce).build();
		}

	}

	public static void main(String[] args) {
		Player player = new Player();
		player.setName("livan");
		player.setMobile("8008961423");
		player.setEmail("email@gmail.com");
		player.setTeam("india");

		System.out.println("entering in Regiser Player.......");
		IplResponce iplResponce = new IplResponce();

		System.out.println("Validation string.........");
		if (player.getName() == null || player.getName().isEmpty()) {
			iplResponce.setErrorCode("121");
			iplResponce.setErrorMessage("Name Should not be Empty...");
			iplResponce.setResponceDate(null);
			System.out.println("Responce set=  " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());
		} else if (player.getMobile() == null || player.getMobile().isEmpty()) {
			iplResponce.setErrorCode("122");
			iplResponce.setErrorMessage("Mobile Number In Valid");
			iplResponce.setResponceDate(null);
			System.out.println("Responce set=  " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());
		} else if (player.getEmail() == null || player.getEmail().isEmpty()) {
			iplResponce.setErrorCode("123");
			iplResponce.setErrorMessage("eMail not valid");
			iplResponce.setResponceDate(null);
			System.out.println("Responce set=  " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());
		} else if (player.getTeam() == null || player.getTeam().isEmpty()) {
			iplResponce.setErrorCode("124");
			iplResponce.setErrorMessage("team is should not be null & Empty");
			iplResponce.setResponceDate(null);
			System.out.println("Responce set=  " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage()
					+ iplResponce.getResponceDate());
		} else {

			try {
				IplScoreDao dao = new IplScoreDao();
				dao.registerPlayer();

			} catch (UserDaoExpection e) {
				iplResponce.setErrorCode(e.getReasonCode() + "");
				iplResponce.setErrorMessage(e.getMessage());

				// return Response.status(500).entity(iplResponce).build();
			}

			iplResponce.setErrorCode("000");
			iplResponce.setErrorMessage("Success");
			Gson gson = new Gson();
			String jsonPlayer = gson.toJson(player);
			iplResponce.setResponceDate(jsonPlayer);
			System.out.println("Player Json  is Done");
			System.out.println("Responce set=  " + iplResponce.getErrorCode() + "  " + iplResponce.getErrorMessage());
		}
	}
}
