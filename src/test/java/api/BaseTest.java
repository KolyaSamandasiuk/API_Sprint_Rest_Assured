package api;

import static api.Const.BASE_URL;

public class BaseTest {
    protected static BoardClient boardClient = new BoardClient(BASE_URL);
    protected static ListClient listClient = new ListClient(BASE_URL);
}
