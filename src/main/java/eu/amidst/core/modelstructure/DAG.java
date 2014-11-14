package eu.amidst.core.modelstructure;

import eu.amidst.core.header.StaticModelHeader;
import eu.amidst.core.header.Variable;

/**
 * Created by Hanen on 13/11/14.
 */
public class DAG {

    private StaticModelHeader modelHeader;
    private ParentSet[] parents;
    private boolean[] m_bits;
    /* bit representation of parent sets m_bits[parentNode + childNode * totalNbrOfNodes]
    represents the directed arc from the parentNode to the childNode */

    public DAG(StaticModelHeader modelHeader) {
        this.modelHeader = modelHeader;
        this.parents = new ParentSet[modelHeader.getNumberOfVars()];

        for (int i=0;i<modelHeader.getNumberOfVars();i++) {
            parents[i] = ParentSet.newParentSet();
        }
        this.m_bits = new boolean [modelHeader.getNumberOfVars() * modelHeader.getNumberOfVars()];
    }

    public ParentSet getParentSet(Variable var) {
        return parents[var.getVarID()];
    }


    public boolean containCycles(){
       /* check whether there are cycles in the BN */

        int nbrNodes = modelHeader.getNumberOfVars();

        boolean[] checked = new boolean[nbrNodes];

        for(int i= 0; i<nbrNodes; i++){

            boolean isCycle = false;

            for( int j =0; (!isCycle && j < nbrNodes); j++){

                if (!checked[j]){
                    boolean hasNoParents = true;
                    for (int par = 0; par < nbrNodes; par++){
                        if (m_bits[par + j * nbrNodes] && !checked[par]){
                            hasNoParents = false;
                        }
                    }
                    if(hasNoParents){
                        checked[j] = true;
                        isCycle = true;
                    }
                }
            }

            if(!isCycle){
                return true;
            }
        }
        return false;
    }

    public StaticModelHeader getModelHeader(){
        return this.modelHeader;
    }




}
