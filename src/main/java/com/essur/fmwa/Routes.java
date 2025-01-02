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

    /** API for work with service which using hibernate (Entity manager) **/
    private static final String JDBC_TEMPLATE_PLAYER_API = API + "/jdbc-player";
    public static final String JDBC_GET_ALL_PLAYERS = JDBC_TEMPLATE_PLAYER_API + "/getAll";
    public static final String JDBC_CREATE_PLAYER = JDBC_TEMPLATE_PLAYER_API + "/create";
    public static final String JDBC_GET_PLAYER_BY_ID = JDBC_TEMPLATE_PLAYER_API + "/getById";
    public static final String JDBC_UPDATE_PLAYER_BY_ID = JDBC_TEMPLATE_PLAYER_API + "/update";
    public static final String JDBC_DELETE_PLAYER = JDBC_TEMPLATE_PLAYER_API + "/delete";
}
