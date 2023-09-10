package recruitment.task.application.exceptions;

public enum ErrorType {
    E404(404,"USER DOES NOT EXIST"),
    E406(406,"THE REQUESTED XML FORMAT IS NOT SUPPORTED");


    private Integer statusCode;
    private String statusMassage;


    ErrorType(int statusCode,String statusMassage) {
        this.statusCode = statusCode;
        this.statusMassage = statusMassage;
    }

    public String getStatusMassage(){
        return this.statusMassage;
    }
    public int getStatusCode(){
        return this.statusCode;
    }

}
