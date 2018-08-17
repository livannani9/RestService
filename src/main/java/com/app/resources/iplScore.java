package com.app.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.models.Team;

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
		System.out.println("winPercentage="+winPercentage);
		if (winPercentage<=65) {
			System.out.println("Team is Not Eligible to Play ");
			team.setReason("Your winning Persontage is <65");
			team.setStatus("NoT Eligible");
			
			return Response.status(200).entity(team).build();
			
		}
		else {
			System.out.println("Team Is Selected To Play......");
			team.setStatus("Your Eligible");
			team.setReason("winning Percentage is "+winPercentage);
			return Response.status(200).entity(team).build();
		}
		
	}
}
