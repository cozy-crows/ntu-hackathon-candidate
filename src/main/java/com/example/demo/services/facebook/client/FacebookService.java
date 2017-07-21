package com.example.demo.services.facebook.client;

import com.example.demo.dtos.facebook.FacebookUser;
import com.example.demo.dtos.facebook.PageInfo;
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


//    @GET("{pageId}?fields=posts.limit(10){application,backdated_time,call_to_action,child_attachments,coordinates," +
//            "created_time,description,feed_targeting,expanded_width,from,expanded_height,icon,height," +
//            "full_picture,id,event,comments_mirroring_domain,caption,allowed_advertising_objectives," +
//            "actions,is_published,is_spherical,is_popular,link,message,multi_share_optimized,name," +
//            "object_id,parent_id,picture,place,privacy,promotable_id,promotion_status,properties," +
//            "scheduled_publish_time,shares,source,status_type,story,story_tags,subscribed,target," +
//            "targeting,timeline_visibility,type,updated_time,via,width,attachments,comments}")
//    Call<PageInfo> getPagePosts(@Path("pageId") String pageId,
//                                @Query("access_token") String accessToken,
//                                @Query("after") String after,
//                                @Query("before") String before);

    @GET("{pageId}?fields=posts")
    Call<NodeList<PageInfo>> getPagePosts(@Path("pageId") String pageId,
                                @Query("access_token") String accessToken,
                                @Query("limit") String limit,
                                @Query("after") String after,
                                @Query("before") String before);
}
