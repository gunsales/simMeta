simMeta
=======

University of Leipzig, Chair for Information-Systems, June 2014.
This is a basic-version of the prototype belonging to a dissertation.

This project transforms BPMN2-Processes, EPC-Processes, Petri-Net-Processes and others into a EMF-Based meta-model in order 
to enrich the processes with simulation- and logistic-specific information. Therefor, a Graphiti-based editor allows the visual customization.
Final step is the transformation from the enriched processes into simulation-specific software (ARENA, Enterprise Dynamics and Anylogic).
The processes are now ready to be simulated of the shelf.

1)Please install the current version of graphiti via 
http://archive.eclipse.org/graphiti/updates/0.10.0/ (SDK ONLY)
2)also, this project uses Xtend 2.4.3 for M2T- and M2M-Projects available on 
http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/ (SDK Only)
3)include bpmn2-modeler available here:
http://download.eclipse.org/bpmn2-modeler/updates/kepler/0.7.0/
4)Lastly, a version of KIELER-Framework for layout is needed. Use this update site:
http://rtsys.informatik.uni-kiel.de/~kieler/updatesite/nightly/pragmatics/
and install KIELER Layout for Graphiti"

In workspace, first step is to generate the model-code in project .metamodel/model/simMeta.genmodel.
Second, clean up the workspace and start the product via de.lsem.simulation/simulator.product (Might fail. Check Run-Configurations-->Plugin-Dependencies-->Auto-Add Dependencies)

Comments are always welcome!
Cheers, Lewin Boehlke