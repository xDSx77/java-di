import com.mti.hivers.impl.Hivers;
import com.mti.hivers.impl.provider.Prototype;
import com.mti.hivers.impl.provider.Singleton;

import java.util.stream.IntStream;

public class Main {

    public interface TestService {
        public void ping();
    }
    public static class TestServiceImpl implements TestService {
        @Override public void ping() { System.out.println("ping"); }
    }

    public static class Nested {
        private final TestService testService;

        public Nested(final TestService testService) {
            this.testService = testService;
        }
    }

    public static void main(String[] args) {
        final var hivers = new Hivers();

        hivers.addProvider(new Singleton<>(TestService.class, new TestServiceImpl()));
        hivers.addProvider(new Singleton<>(TestService.class, TestServiceImpl::new));
        hivers.addProvider(new Prototype<>(Nested.class, () -> new Nested(hivers.instanceOfOrThrow(TestService.class))));
        hivers.addProvider(new Singleton<>(Nested.class, new Nested(hivers.instanceOfOrThrow(TestService.class))));

        final var service = hivers.instanceOf(TestService.class).orElseThrow();
        IntStream.of(1, 2, 3, 4, 5).forEach(i ->
                service.ping()
        );
    }
}
