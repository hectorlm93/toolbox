package eu.amidst.core.variables.stateSpaceTypes;

import eu.amidst.core.variables.StateSpaceType;
import eu.amidst.core.variables.StateSpaceTypeEnum;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by andresmasegosa on 25/11/14.
 */
public class FiniteStateSpace extends StateSpaceType implements Iterable<String> {

    private int numberOfStates;
    private final List<String> statesNames;
    private final Map<String,Integer> mapStatesNames;



    public FiniteStateSpace(int numberOfStates1) {
        super(StateSpaceTypeEnum.FINITE_SET);
        this.numberOfStates=numberOfStates1;
        this.statesNames = new ArrayList<>();
        this.mapStatesNames = new ConcurrentHashMap<>();
        for (int i=0; i<numberOfStates1; i++){
            //this.statesNames.add("State_"+i);
            //this.mapStatesNames.put("State_"+i, i);
            this.statesNames.add(i+".0");
            this.mapStatesNames.put(i+".0", i);
        }
    }

    public FiniteStateSpace(List<String> statesNames1) {
        super(StateSpaceTypeEnum.FINITE_SET);
        this.numberOfStates=statesNames1.size();
        this.statesNames = new ArrayList<>();
        this.mapStatesNames = new ConcurrentHashMap<>();
        for (int i = 0; i < statesNames1.size(); i++) {
            this.statesNames.add(statesNames1.get(i));
            this.mapStatesNames.put(statesNames1.get(i),i);
        }
    }

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public String getStatesName(int state) {
        return statesNames.get(state);
    }

    public int getIndexOfState(String stateName) { return this.mapStatesNames.get(stateName);}

    @Override
    public Iterator<String> iterator() {
        return statesNames.iterator();
    }

    public List<String> getStatesNames(){
        return this.statesNames;
    }
}