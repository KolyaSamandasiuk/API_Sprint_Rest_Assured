package api;

import api.clients.*;

import static api.Const.BASE_URL;

public class BaseTest {
    protected static BoardRestTestClient boardRestTestClient = new BoardRestTestClient(BASE_URL);
    protected static ListTestRestClient listTestRestClient = new ListTestRestClient(BASE_URL);
    protected static CardTestRestClient cardTestRestClient = new CardTestRestClient(BASE_URL);
    protected static LabelRestTestClient labelRestTestClient = new LabelRestTestClient(BASE_URL);
    protected static ChecklistRestTestClient checklistRestTestClient = new ChecklistRestTestClient(BASE_URL);
}