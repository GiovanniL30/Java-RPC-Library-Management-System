package project.utilities.utilityClasses;

import java.io.Serializable;

public enum ServerActions implements Serializable {

    BROADCAST_MESSAGE,
    EDIT_BOOK,
    DELETE_BOOK,
    ADDED_NEW_BOOK,
    CANCEL_BOOK_PENDING,
    ACCEPT_BOOK_PENDING,
    RETRIEVES_BOOK,
    RETRIVE_PENDING_BOOK,
    CHANGE_PASSWORD,
    BAN_ACCOUNT,
    UNBAN_ACCOUNT,
    DELETE_ACCOUNT,
}
