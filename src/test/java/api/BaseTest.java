package api;

import api.clients.BoardRestTestClient;
import api.clients.ActionsRestTestClient;
import api.clients.ListTestRestClient;

import static api.Const.BASE_URL;

public class BaseTest {
    protected static BoardRestTestClient boardRestTestClient = new BoardRestTestClient(BASE_URL);
    protected static ListTestRestClient listTestRestClient = new ListTestRestClient(BASE_URL);
    protected static ActionsRestTestClient actionsRestTestClient = new ActionsRestTestClient(BASE_URL);

}
