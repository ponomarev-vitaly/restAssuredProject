package arguments.providers;

import arguments.holders.AuthValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class AuthValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context){
        return Stream.of(
                new AuthValidationArgumentsHolder(
                        Collections.emptyMap()
                ),
                new AuthValidationArgumentsHolder(
                        Map.of(
                                "key", "36a241085b4ad3d2267dbd22fc544c5d"
                        )
                ),
                new AuthValidationArgumentsHolder( // It was important to write not only token and token value, but key and key value. The key token pair must be complementary.
                        Map.of(
                                "key", "fb04999a731923c2e3137153b1ad5de0",
                                "token", "b73120fb537fceb444050a2a4c08e2f96f47389931bd80253d2440708f2a57e1"
                        )
                )
        ).map(Arguments::of);
    }
}
