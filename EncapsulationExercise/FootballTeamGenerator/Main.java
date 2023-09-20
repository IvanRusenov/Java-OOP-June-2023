package EncapsulationExercises05FootballTeamGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        Map<String, Team> teams = new HashMap<>();

        while (!"END".equals(input)) {

            String[] commandData = input.split(";");

            String command = commandData[0];
            String teamName = commandData[1];

            try {

                switch (command) {
                    case "Team":

                        Team team = new Team(teamName);
                        teams.put(teamName, team);
                        break;

                    case "Add":

                        if (teams.containsKey(teamName)) {

                            String playerName = commandData[2];
                            int endurance = Integer.parseInt(commandData[3]);
                            int sprint = Integer.parseInt(commandData[4]);
                            int dribble = Integer.parseInt(commandData[5]);
                            int passing = Integer.parseInt(commandData[6]);
                            int shooting = Integer.parseInt(commandData[7]);

                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);

                            teams.get(teamName).addPlayer(player);

                        } else {

                            System.out.printf("Team %s does not exist.\n", teamName);

                        }

                        break;

                    case "Remove":

                        String playerName = commandData[2];
                        if (teams.containsKey(teamName)) {
                            teams.get(teamName).removePlayer(playerName);
                        } else {
                            System.out.printf("Team %s does not exist.\n", teamName);
                        }

                        break;

                    case "Rating":

                        if (teams.containsKey(teamName)) {
                            System.out.printf("%s - %.0f\n", teamName, teams.get(teamName).getRating());
                        } else {
                            System.out.printf("Team %s does not exist.\n", teamName);
                        }

                        break;

                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            input = scan.nextLine();

        }

    }
}
