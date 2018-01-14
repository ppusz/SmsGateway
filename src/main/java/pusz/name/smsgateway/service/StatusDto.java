package pusz.name.smsgateway.service;

class StatusDto {

    private final String status;
    private final boolean isFinal;

    StatusDto(final String status, final boolean isFinal) {
        this.status = status;
        this.isFinal = isFinal;
    }

    String getStatus() {
        return status;
    }

    boolean isFinal() {
        return isFinal;
    }

    @Override
    public String toString() {
        return "StatusDto{" +
                "status='" + status + '\'' +
                ", isFinal=" + isFinal +
                '}';
    }
}
