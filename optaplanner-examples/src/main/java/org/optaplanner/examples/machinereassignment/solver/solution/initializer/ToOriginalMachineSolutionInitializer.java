/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.machinereassignment.solver.solution.initializer;

import org.optaplanner.core.impl.phase.custom.CustomPhaseCommand;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.optaplanner.examples.machinereassignment.domain.MachineReassignment;
import org.optaplanner.examples.machinereassignment.domain.MrProcessAssignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToOriginalMachineSolutionInitializer implements CustomPhaseCommand {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    public void changeWorkingSolution(ScoreDirector scoreDirector) {
        MachineReassignment machineReassignment = (MachineReassignment) scoreDirector.getWorkingSolution();
        initializeProcessAssignmentList(scoreDirector, machineReassignment);
    }

    private void initializeProcessAssignmentList(ScoreDirector scoreDirector,
            MachineReassignment machineReassignment) {
        for (MrProcessAssignment processAssignment : machineReassignment.getProcessAssignmentList()) {
            scoreDirector.beforeVariableChanged(processAssignment, "machine");
            processAssignment.setMachine(processAssignment.getOriginalMachine());
            scoreDirector.afterVariableChanged(processAssignment, "machine");
        }
    }

}
