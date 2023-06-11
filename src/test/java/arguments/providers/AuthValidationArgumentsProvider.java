package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import consts.UrlParamValues;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class AuthValidationArgumentsProvider implements ArgumentsProvider {

//    @Override
//    public Stream provideArguments(ExtensionContext context){
//        return Stream.of(
//                new AuthValidationArgumentsHolder(
//                        Collections.emptyMap(),
//                        "invalid key"),
//                new AuthValidationArgumentsHolder(
//                        Map.of(
//                                "key", UrlParamValues.VALID_KEY
//                        ),
//                        "unauthorized permission requested"),
//                new AuthValidationArgumentsHolder( // It was important to write not only token and token value, but key and key value. The key token pair must be complementary.
//                        Map.of(
//                                "key", UrlParamValues.VALID_KEY_SECOND,
//                                "token", UrlParamValues.VALID_TOKEN_SECOND
//                        ),
//                        "invalid key")
//        ).map(Arguments::of);
//    }

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new AuthValidationArgumentsHolder(
                        Collections.emptyMap(),
                        "invalid key"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of("key", UrlParamValues.VALID_KEY_SECOND),
                        "unauthorized permission requested"
                ),
                new AuthValidationArgumentsHolder(
                        Map.of("token", UrlParamValues.VALID_TOKEN_SECOND),
                        "invalid key"
                )
        ).map(Arguments::of);
    }
}
