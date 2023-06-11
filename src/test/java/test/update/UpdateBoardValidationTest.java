package test.update;

import arguments.holders.BoardIdValidationArgumentsHolder;
import arguments.providers.BoardIdValidationArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import test.BaseTest;

public class UpdateBoardValidationTest extends BaseTest {
    @ParameterizedTest
    @ArgumentsSource(BoardIdValidationArgumentsProvider.class)

    public void checkUpdateBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder){

    }
}
