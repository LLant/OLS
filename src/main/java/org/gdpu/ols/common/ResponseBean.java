package org.gdpu.ols.common;

public class ResponseBean extends BaseBean {

    private static final long serialVersionUID = 4438556717523313833L;
    private String resultCode;
    private String resultMessage;
    private transient Object data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if(data!=null){
            return "\nResponseBean " + "resultCode: "+resultCode+", resultMessage: "
                    +resultMessage+"\ndata: "+data.toString();
        }else{
            return "\nResponseBean " + "resultCode: "+resultCode+", resultMessage: "
                    +resultMessage+"\ndata: ";
        }
    }
}
