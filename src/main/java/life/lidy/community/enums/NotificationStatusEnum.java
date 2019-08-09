package life.lidy.community.enums;

/**
 * 用户是否得知了通知
 */
public enum  NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
