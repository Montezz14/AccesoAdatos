public class TestEnv {
    public static void main(String[] args) {
        String env = System.getenv("DB_PASSWORD");
        String prop = System.getProperty("DB_PASSWORD");
        System.out.println("ENV_DB_PASSWORD=" + (env == null ? "<null>" : env));
        System.out.println("PROP_DB_PASSWORD=" + (prop == null ? "<null>" : prop));
    }
}
