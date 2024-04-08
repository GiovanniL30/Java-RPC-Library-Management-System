package midproject.server.views.utility;

/**
 * Enum representing different panels in the server GUI.
 */

public enum ServerPanels {
    HOME_PANEL("Home"),
    VIEW_BOOKS_PANEL("View All Books"),
    MANAGE_BOOK_PANEL("Manage Book"),
    MANAGE_ACCOUNTS_PANEL("Manage Accounts"),
    BORROWED_PANEL("Borrowed"),
    PENDING_BORROW_PANEL("Pending Borrow"),
    PENDING_RETURN_PANEL("Pending Return"),
    All_BOOKS_PANEL("All Books"),
    AVAILABLE_BOOKS_PANEL("Available Books"),
    UNAVAILABLE_BOOKS_PANEL("Unavailable Books"),
    ADD_BOOKS_PANEL("Add Books");

    private final String displayName;

    /**
     * Constructs a ServerPanels enum with the specified display name.
     * @param displayName The display name of the panel.
     */
    ServerPanels(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the panel.
     */
    public String getDisplayName() {
        return displayName;
    }
} // end of ServerPanels enum