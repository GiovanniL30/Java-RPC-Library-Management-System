package project.server.views.utility;


public enum ServerPanels {
    HOME_PANEL("Home"),
    VIEW_BOOKS_PANEL("View All Books"),
    MANAGE_BOOK_PANEL("Manage Book"),
    MANAGE_ACCOUNTS_PANEL("Manage Accounts"),
    BORROWED_PANEL("Borrowed Books"),
    PENDING_BORROW_PANEL("Pending Borrow Requests"),
    PENDING_RETURN_PANEL("Pending Return Requests"),
    All_BOOKS_PANEL("All Books"),
    AVAILABLE_BOOKS_PANEL("Available Books"),
    UNAVAILABLE_BOOKS_PANEL("Unavailable Books");

    private final String displayName;

    ServerPanels(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}