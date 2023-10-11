package api;

import api.clients.BoardTestRestClient;
import api.clients.ListTestRestClient;

public class BaseTest {
    protected static BoardTestRestClient boardClient = new BoardTestRestClient(TestProperties.getBaseUrl());
    protected static ListTestRestClient listClient = new ListTestRestClient(TestProperties.getBaseUrl());
}
