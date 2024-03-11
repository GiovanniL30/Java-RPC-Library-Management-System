package project.utilities.utilityClasses;

import java.io.Serializable;

public enum NotifyType implements Serializable {


    SERVER_RETRIEVES_BOOK,
    SERVER_ACCEPT_PENDING_BOOK,
    SERVER_CANCEL_BOOK_PENDING,
    SERVER_ADDED_A_NEW_BOOK,
    SERVER_DELETED_A_BOOK,
    SERVER_EDITED_A_BOOK

}
