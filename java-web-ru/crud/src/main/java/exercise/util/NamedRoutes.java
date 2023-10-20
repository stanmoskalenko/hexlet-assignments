package exercise.util;

public class NamedRoutes {

    private static final String ROOT_PATH = "/";
    private static final String POSTS_PATH = "/posts";

    public static String rootPath() {
        return ROOT_PATH;
    }

    public static String postsPath(String id) {
        return POSTS_PATH + "/" + id;
    }

    public static String postsPath() {
        return  POSTS_PATH;
    }

}
