package api;

import api.clients.ActionsClient;
import api.clients.BoardClient;
import api.clients.ListClient;

import static api.Const.BASE_URL;

public class BaseTest {
    protected static BoardClient boardClient = new BoardClient(BASE_URL);
    protected static ListClient listClient = new ListClient(BASE_URL);
    protected static ActionsClient apiClient = new ActionsClient(BASE_URL);
}
