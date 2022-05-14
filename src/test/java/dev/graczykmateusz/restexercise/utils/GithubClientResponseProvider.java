package dev.graczykmateusz.restexercise.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class GithubClientResponseProvider {

    public static String getUserZeroFollowersResponse() throws JSONException {
        return createUserResponseWithoutFollowers()
                .put("followers", 0)
                .toString();
    }

    public static String getUserNonZeroFollowersResponse() throws JSONException {
        return createUserResponseWithoutFollowers()
                .put("followers", 3)
                .toString();
    }

    private static JSONObject createUserResponseWithoutFollowers() throws JSONException {
        return new JSONObject()
                .put("login", "GraczykMateusz")
                .put("id", "43554417")
                .put("node_id", "MDQ6VXNlcjQzNTU0NDE3")
                .put("avatar_url", "https://avatars.githubusercontent.com/u/43554417?v=4")
                .put("gravatar_id", "")
                .put("url", "https://api.github.com/users/GraczykMateusz")
                .put("html_url", "https://github.com/GraczykMateusz")
                .put("followers_url", "https://api.github.com/users/GraczykMateusz/followers")
                .put("following_url", "https://api.github.com/users/GraczykMateusz/following{/other_user}")
                .put("gists_url", "https://api.github.com/users/GraczykMateusz/gists{/gist_id}")
                .put("starred_url", "https://api.github.com/users/GraczykMateusz/starred{/owner}{/repo}")
                .put("subscriptions_url", "https://api.github.com/users/GraczykMateusz/subscriptions")
                .put("organizations_url", "https://api.github.com/users/GraczykMateusz/orgs")
                .put("repos_url", "https://api.github.com/users/GraczykMateusz/repos")
                .put("events_url", "https://api.github.com/users/GraczykMateusz/events{/privacy}")
                .put("received_events_url", "https://api.github.com/users/GraczykMateusz/received_events")
                .put("type", "User")
                .put("site_admin", false)
                .put("name", "Mateusz Graczyk")
                .put("company", null)
                .put("blog", "")
                .put("location", "Poland")
                .put("email", null)
                .put("hireable", null)
                .put("bio", null)
                .put("twitter_username", "")
                .put("public_repos", 17)
                .put("public_gists", 0)
                .put("followers", 3)
                .put("following", 3)
                .put("created_at", "2018-09-24T20:41:09Z")
                .put("updated_at", "2022-03-07T20:39:28Z");
    }
}
