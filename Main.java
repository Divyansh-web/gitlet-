package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) throws Exception {
            String firstArg = args[0];
            switch(firstArg) {
                case "init":
                    Repository.setUpPersistance();
                    Commit initCommit=new Commit();
                    Repository.saveCommit(initCommit);
                    Repository.setHEAD(initCommit.getHash());
                    break;
                case "add":
                    Repository.checkFile(args[1]);
                    StageArea stageArea=Repository.deserStageArea();
                    stageArea.add(args[1]);
                    Repository.saveStageArea(stageArea);
                    break;
                case "commit":
                    Commit parentCommit=Repository.getCommit(Repository.getHEAD());
                    Commit newCommit= (Commit) parentCommit.clone();
                    StageArea stageAreaObj=Repository.deserStageArea();
                    newCommit.commit(stageAreaObj,args[1],parentCommit.getHash());
                    Repository.saveCommit(newCommit);
                    Repository.setHEAD(newCommit.getHash());
                    stageAreaObj.flush();
                    Repository.saveStageArea(stageAreaObj);
                    break;
                case "rm":
                    Repository.checkFile(args[1]);
                    StageArea stageArea1=Repository.deserStageArea();
                    stageArea1.rem(args[1]);
                    Repository.saveStageArea(stageArea1);
                    break;
            }
        }


    }

