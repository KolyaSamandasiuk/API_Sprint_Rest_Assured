package api;

import api.clients.BoardRestTestClient;
import api.clients.LabelRestTestClient;
import api.clients.ListTestRestClient;
import api.clients.OrganizationRestTestClient;

import static api.Const.BASE_URL;

public class BaseTest {
    protected static BoardRestTestClient boardRestTestClient = new BoardRestTestClient(BASE_URL);
    protected static ListTestRestClient listTestRestClient = new ListTestRestClient(BASE_URL);
    protected static LabelRestTestClient labelRestTestClient = new LabelRestTestClient(BASE_URL);
    protected static OrganizationRestTestClient organizationRestTestClient = new OrganizationRestTestClient(BASE_URL);
}
