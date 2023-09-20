package EncapsulationExercises05FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    private final List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    public void setName(String name) {

        if (name.trim().isEmpty()) {

            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {

        boolean isPlayerRemoved = this.players.removeIf(p -> p.getName().equals(playerName));

        if (!isPlayerRemoved) {
            String errorMessage = String.format("Player %s is not in %s team.", playerName, this.getName());
            throw new IllegalArgumentException(errorMessage);
        }

    }

    public double getRating() {
        return players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0);
    }
}
