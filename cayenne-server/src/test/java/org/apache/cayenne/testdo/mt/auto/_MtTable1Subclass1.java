package org.apache.cayenne.testdo.mt.auto;

import org.apache.cayenne.exp.Property;
import org.apache.cayenne.testdo.mt.MtTable1;

/**
 * Class _MtTable1Subclass1 was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _MtTable1Subclass1 extends MtTable1 {

    @Deprecated
    public static final String SUBCLASS1ATTRIBUTE1_PROPERTY = "subclass1Attribute1";

    public static final String TABLE1_ID_PK_COLUMN = "TABLE1_ID";

    public static final Property<String> SUBCLASS1ATTRIBUTE1 = new Property<String>("subclass1Attribute1");

    public void setSubclass1Attribute1(String subclass1Attribute1) {
        writeProperty("subclass1Attribute1", subclass1Attribute1);
    }
    public String getSubclass1Attribute1() {
        return (String)readProperty("subclass1Attribute1");
    }

}
