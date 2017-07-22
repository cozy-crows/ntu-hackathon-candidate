package com.example.demo.services.facebook.client;

import com.example.demo.dtos.facebook.FacebookUser;
import com.example.demo.dtos.facebook.post.PostInfo;
import com.example.demo.dtos.facebook.unit.NodeList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jerry on 2017/7/21.
 */
public interface FacebookService {

    /**
     * 取得 Facebook User 資訊
     *
     * @param userId
     * @param accessToken
     * @return
     */
    @GET("{userId}?fields=id,name")
    Call<FacebookUser> getUser(@Path("userId") String userId,
                               @Query("access_token") String accessToken);

    @GET("{pageId}/posts?fields=fields=id,created_time,message,actions,place,message_tags,targeting,attachments," +
            "child_attachments,story,story_tags,comments.limit(1).summary(true),likes.limit(1).summary(true)")
    Call<NodeList<PostInfo>> getPagePosts(@Path("pageId") String pageId,
                                          @Query("access_token") String accessToken,
                                          @Query("limit") String limit,
                                          @Query("after") String after,
                                          @Query("before") String before);
}
