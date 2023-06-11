package consts;

import java.util.Map;

public class UrlParamValues {
    public static final String VALID_KEY = "36a241085b4ad3d2267dbd22fc544c5d";
    public static final String VALID_TOKEN = "ATTAefa2c1bf12159579a4153dc7d77b4ca9055612b58ba8c673c1b0a04d6ad834c3EB4CCA0D";
    public static final String VALID_KEY_SECOND = "fb04999a731923c2e3137153b1ad5de0";
    public static final String VALID_TOKEN_SECOND = "b73120fb537fceb444050a2a4c08e2f96f47389931bd80253d2440708f2a57e1";

    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
            );

    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", "8b32218e6887516d17c84253faf967b6",
            "token", "492343b8106e7df3ebb7f01e219cbf32827c852a5f9e2b8f9ca296b1cc604955"
    );
    public static final String EXISTING_BOARD_ID = "646746aecb24dbfdcd185380";
    public static final String BOARD_ID_TO_UPDATE = "645ea05814221943e36343bf";
    public static final String USER_NAME = "vitalyponomarev3";

    public static final String EXISTING_LIST_ID = "646746aecb24dbfdcd185387";

    public static final String EXISTING_CARD_ID = "646748eaef222a0de8dfb52c";
    public static final String CARD_ID_TO_UPDATE = "646748eaef222a0de8dfb52c";
}
