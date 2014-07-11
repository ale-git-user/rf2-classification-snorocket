/**
 * Copyright (c) 2009 International Health Terminology Standards Development
 * Organisation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ihtsdo.classifier.model;

/**
 * Organisation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

/**
 * The Class SnoGrp.
 * Represents a relationships list.
 */
public class SnoGrp extends ArrayList<SnoRel> {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The Constant debug. */
    private static final boolean debug = false; // :DEBUG:

    /**
     * Instantiates a new sno grp.
     *
     * @param relList the rel list
     * @param needsToBeSorted the needs to be sorted
     */
    public SnoGrp(List<SnoRel> relList, boolean needsToBeSorted) {
        super();
        // set doSort = true if list not pre-sorted to C1-Group-Type-C2 order
        if (needsToBeSorted)
            Collections.sort(relList);
        this.addAll(relList);
       
    }

    /**
     * Instantiates a new sno grp.
     *
     * @param o the o
     */
    public SnoGrp(SnoRel o) {
        super();
        this.add(o); // 
    }

    /**
     * Instantiates a new sno grp.
     */
    public SnoGrp() {
        super();
    }

    /**
     * Adds the all with sort.
     *
     * @param roleGroupB the role group b
     * @return the sno grp
     */
    public SnoGrp addAllWithSort(SnoGrp roleGroupB) {

        this.addAll(roleGroupB);
        // SORT BY [ROLE-C2-GROUP-C2]
        Comparator<SnoRel> comp = new Comparator<SnoRel>() {
            public int compare(SnoRel o1, SnoRel o2) {
                int thisMore = 1;
                int thisLess = -1;
                if (o1.typeId > o2.typeId) {
                    return thisMore;
                } else if (o1.typeId < o2.typeId) {
                    return thisLess;
                } else {
                    if (o1.c2Id > o2.c2Id) {
                        return thisMore;
                    } else if (o1.c2Id < o2.c2Id) {
                        return thisLess;
                    } else {
                        return 0; // EQUAL
                    }
                }
            } // compare()
        };
        Collections.sort(this, comp);

        return this;
    }

    /**
     * Sort by type.
     *
     * @return the sno grp
     */
    public SnoGrp sortByType() {
        // SORT BY [ROLE-C2-GROUP-C2]
        Comparator<SnoRel> comp = new Comparator<SnoRel>() {
            public int compare(SnoRel o1, SnoRel o2) {
                int thisMore = 1;
                int thisLess = -1;
                if (o1.typeId > o2.typeId) {
                    return thisMore;
                } else if (o1.typeId < o2.typeId) {
                    return thisLess;
                } else {
                    if (o1.c2Id > o2.c2Id) {
                        return thisMore;
                    } else if (o1.c2Id < o2.c2Id) {
                        return thisLess;
                    } else {
                        return 0; // EQUAL
                    }
                }
            } // compare()
        };
        Collections.sort(this, comp);
        return this;
    }

    /**
     * Does roleGroupA Role-Value match roleGroupB Role-Values?<br>
     * <br>
     * <font color=#990099> IMPLEMENTATION NOTE: roleGroups MUST be pre-sorted
     * in C1-Group-Type-C2 order for this routine. Pre-sorting is used to
     * provide overall computational efficiency.</font>
     *
     * @param roleGroupB the role group b
     * @return true iff RoleValues match
     */
    public boolean equals(SnoGrp roleGroupB) {
        int sizeA = this.size();
        if (sizeA != roleGroupB.size())
            return false; // trivial case, does not have same number of elements

        if (sizeA == 0)
            return true; // trivial case, both empty

        int i = 0;
        boolean isSame = true;
        while (i < sizeA) {
            if (this.get(i).typeId != roleGroupB.get(i).typeId || this.get(i).c2Id != roleGroupB.get(i).c2Id) {
                isSame = false;
                break;
            }
            i++;
        }

        return isSame;
    }

    /**
     * Find logically equivalent role group from role group list provided. <br>
     * <br>
     * <font color=#990099> IMPLEMENTATION NOTE: roleGroups MUST be pre-sorted
     * in C1-Group-Type-C2 order for this routine. Pre-sorting is used to
     * provide overall computational efficiency.</font>
     *
     * @param groupList_B the group list_ b
     * @return SnoGrp iff logically equivalent role group found
     */
    public SnoGrp findLogicalEquivalent(SnoGrpList groupList_B) {
        for (SnoGrp snoGrp : groupList_B) {
            if (this.equals(snoGrp)) {
                return snoGrp;
            }
        }
        return null;
    }

} // class SnoGrp

