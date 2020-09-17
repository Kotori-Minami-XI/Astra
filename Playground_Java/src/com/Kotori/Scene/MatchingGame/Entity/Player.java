package com.Kotori.Scene.MatchingGame.Entity;

public class Player {
    private Integer playerId;
    private Integer playerName;
    private Boolean online;
    private Preference preference;

    public Player(Integer playerId, Integer playerName, Preference preference) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.online = false;
        this.preference = preference;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerName() {
        return playerName;
    }

    public void setPlayerName(Integer playerName) {
        this.playerName = playerName;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
}
