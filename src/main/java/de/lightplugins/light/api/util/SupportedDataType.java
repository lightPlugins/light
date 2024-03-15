package de.lightplugins.light.api.util;

public enum SupportedDataType {
    INTEGER,
    REAL,
    TEXT,
    BLOB,
    DATE,
    TIME,
    TIMESTAMP;

    /**
     * Check if the object is of a supported data type.
     *
     * @param obj the object to check
     * @return true if the object is of a supported data type, false otherwise.
     */
    public static boolean isSupported(Object obj) {
        // Loop through the supported data types
        for (SupportedDataType type : SupportedDataType.values()) {
            // Check if the object's class simple name matches the supported data type.
            if (obj.getClass().getSimpleName().equals(type.name())) {
                return true;
            }
        }
        return false;
    }
}
