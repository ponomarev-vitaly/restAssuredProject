package arguments.providers;

import arguments.holders.CardIdValidationArgumentsHolder;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class CardIdValidationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream provideArguments(ExtensionContext context) {
        return Stream.of(
                new CardIdValidationArgumentsHolder(
                        Map.of("card_id", "646748eaef222a0de8dfb59d"),
                        "The requested resource was not found.",
                        404
                ),

                new CardIdValidationArgumentsHolder(
                        Map.of("card_id", "invalid"),
                        "invalid id",
                        400
                )
        ).map(Arguments::of);
    }
}
