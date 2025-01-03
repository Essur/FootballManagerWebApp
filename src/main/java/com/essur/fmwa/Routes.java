package com.essur.fmwa;

public class Routes {
    private static final String API = "/api/v1";

    /**API for work with service which using hibernate (Entity manager) **/
    private static final String HIBERNATE_PLAYER_API = API + "/hi-player";
    public static final String H_GET_ALL_PLAYERS = HIBERNATE_PLAYER_API + "/getAll";
    public static final String H_CREATE_PLAYER = HIBERNATE_PLAYER_API + "/create";
    public static final String H_GET_PLAYER_BY_ID = HIBERNATE_PLAYER_API + "/getById";
    public static final String H_UPDATE_PLAYER_BY_ID = HIBERNATE_PLAYER_API + "/update";
    public static final String H_DELETE_PLAYER = HIBERNATE_PLAYER_API + "/delete";

    public static final String H_PLAYER_TRANSFER = HIBERNATE_PLAYER_API + "/playerTransfer";

    private static final String HIBERNATE_TEAM_API = API + "/hi-team";
    public static final String H_GET_ALL_TEAMS = HIBERNATE_TEAM_API + "/getAll";
    public static final String H_CREATE_TEAM = HIBERNATE_TEAM_API + "/create";
    public static final String H_GET_TEAM_BY_ID = HIBERNATE_TEAM_API + "/getById";
    public static final String H_UPDATE_TEAM_BY_ID = HIBERNATE_TEAM_API + "/update";
    public static final String H_DELETE_TEAM = HIBERNATE_TEAM_API + "/delete";

    /** API for work with service which using JDBC Template **/
    private static final String JDBC_TEMPLATE_PLAYER_API = API + "/jdbc-player";
    public static final String JDBC_GET_ALL_PLAYERS = JDBC_TEMPLATE_PLAYER_API + "/getAll";
    public static final String JDBC_CREATE_PLAYER = JDBC_TEMPLATE_PLAYER_API + "/create";
    public static final String JDBC_GET_PLAYER_BY_ID = JDBC_TEMPLATE_PLAYER_API + "/getById";
    public static final String JDBC_UPDATE_PLAYER_BY_ID = JDBC_TEMPLATE_PLAYER_API + "/update";
    public static final String JDBC_DELETE_PLAYER = JDBC_TEMPLATE_PLAYER_API + "/delete";

    private static final String JDBC_TEMPLATE_TEAM_API = API + "/jdbc-team";
    public static final String JDBC_GET_ALL_TEAMS = JDBC_TEMPLATE_TEAM_API + "/getAll";
    public static final String JDBC_CREATE_TEAM = JDBC_TEMPLATE_TEAM_API + "/create";
    public static final String JDBC_GET_TEAM_BY_ID = JDBC_TEMPLATE_TEAM_API + "/getById";
    public static final String JDBC_UPDATE_TEAM_BY_ID = JDBC_TEMPLATE_TEAM_API + "/update";
    public static final String JDBC_DELETE_TEAM = JDBC_TEMPLATE_TEAM_API + "/delete";

    public static final String JDBC_PLAYER_TRANSFER = JDBC_TEMPLATE_PLAYER_API + "/playerTransfer";
}
