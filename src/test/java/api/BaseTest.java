package api;

import api.clients.BoardRestTestClient;
import api.clients.CardTestRestClient;
import api.clients.LabelRestTestClient;
import api.clients.ChecklistTestRestClient;
import api.clients.ListTestRestClient;

import static api.Const.BASE_URL;

public class BaseTest {
    protected static BoardRestTestClient boardRestTestClient = new BoardRestTestClient(BASE_URL);
    protected static ListTestRestClient listTestRestClient = new ListTestRestClient(BASE_URL);
    protected static LabelRestTestClient labelRestTestClient = new LabelRestTestClient(BASE_URL);
    protected static CardTestRestClient cardTestRestClient = new CardTestRestClient(BASE_URL);
    protected static ChecklistTestRestClient checklistTestRestClient = new ChecklistTestRestClient(BASE_URL);
}