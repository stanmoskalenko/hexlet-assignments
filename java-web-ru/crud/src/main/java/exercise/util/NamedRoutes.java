package exercise.util;

public class NamedRoutes {

    private static final String ROOT_PATH = "/";
    private static final String POSTS_PATH_WITH_PARAMS = "/posts/";
    private static final String POSTS_PATH = "/posts";

    public static String rootPath() {
        return ROOT_PATH;
    }

    public static String postsPath(String id) {
        return POSTS_PATH_WITH_PARAMS + id;
    }

    public static String postsPath() {
        return  POSTS_PATH;
    }

}
