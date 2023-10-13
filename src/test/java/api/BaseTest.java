package api;

import api.clients.BoardClient;
import api.clients.CardClient;
import api.clients.ChecklistClient;
import api.clients.ListClient;

import static api.Const.BASE_URL;

public class BaseTest {
    protected static BoardClient boardClient = new BoardClient(BASE_URL);
    protected static ListClient listClient = new ListClient(BASE_URL);
    protected static CardClient cardClient = new CardClient(BASE_URL);
    protected static ChecklistClient checklistClient = new ChecklistClient(BASE_URL);
}
