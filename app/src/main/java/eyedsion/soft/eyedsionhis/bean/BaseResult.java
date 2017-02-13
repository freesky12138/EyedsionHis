package eyedsion.soft.eyedsionhis.bean;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface BaseResult {


    public int getResult() ;

    public void setResult(int result) ;

    public int getErrcode() ;

    public void setErrcode(int errcode);

    public String getErrormsg();

    public void setErrormsg(String errormsg) ;
}
