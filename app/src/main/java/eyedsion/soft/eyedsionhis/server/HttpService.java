package eyedsion.soft.eyedsionhis.server;

import eyedsion.soft.eyedsionhis.entity.MovieEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
/**
 * Created by Administrator on 2017/2/7.
 */

public interface HttpService {

    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
