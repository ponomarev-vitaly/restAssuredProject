package test.update;

import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import test.BaseTest;

public class UpdateCardTest extends BaseTest {
    @Test
    public void checkUpdateCard() {
        String updatedName = "Update Name" + LocalDateTime.now();

    }
}
