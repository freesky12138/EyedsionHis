package eyedsion.soft.eyedsionhis.server;

import eyedsion.soft.eyedsionhis.entity.ImgSubEntity;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2017/2/8.
 */

public interface FileService
{
    @Multipart
    @POST("PostFormData")
    Call<ImgSubEntity> upload(@Part MultipartBody.Part file);
}
