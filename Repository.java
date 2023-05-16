package gitlet;

import java.io.File;
import static gitlet.Utils.*;


/** Represents a gitlet repository.
 *  stores necessary paths and serialises and stores blobs
 *
 *  @author Divyansh Yadav
 */
public class Repository {
    /**
     * static final File CWD -> abstract path of current working directory
     * static final File GITLET_DIR -> .gitlet folder path
     * static final File STAGE_AREA -> path of serialised stagearea data structure
     * static final File COMMITS_DIR -> path of serialised commits
     * static final File BLOB_DIR => path of directory that stores blobs
     *
     *
     *
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File STAGE_AREA=join(GITLET_DIR,"STAGEAREA");
    public static final File COMMITS_DIR=join(GITLET_DIR,"commits");

    public static final File BLOB_DIR=join(GITLET_DIR,"blobs");

    public static final File HEAD_FILE=join(GITLET_DIR,"HEAD");

    public static final File MASTER_FILE=join(GITLET_DIR,"MASTER");
    public static void setUpPersistance() throws Exception{
        if(GITLET_DIR.exists()){
            throw new Exception("A Gitlet version-control system already exists in the current directory");

        }
        else{
            GITLET_DIR.mkdir();
            COMMITS_DIR.mkdir();
            BLOB_DIR.mkdir();
        }
    }
    private  static String fileToString(String filename){
        return Utils.readContentsAsString(join(CWD,filename));
    }

    private static byte[] fileToByteStream(String filename){
        return Utils.readContents(join(Repository.CWD, filename));
    }
    public static String fileToHash(String filename){
        return Utils.sha1(fileToString(filename));
    }

    public static void saveBlob(String filename){
        byte[] fileStream=fileToByteStream(filename);
        String hash=Utils.sha1(fileStream);
        Utils.writeObject(join(BLOB_DIR,hash),fileStream);
    }

    /*setHEAD("dfds2q4234werewr1234") -> puts "dfds2q4234werewr1234" in the MASTER file*/
    public static void setHEAD(String hash){
        Utils.writeObject(HEAD_FILE,hash);
    }
    public static String getHEAD(){
       return Utils.readObject(HEAD_FILE,String.class);
    }

    public static void checkFile(String filename) throws Exception{
        if(!(join(CWD,filename).exists())){
            throw new Exception("File does not exist.");
        }

    }

    public static StageArea deserStageArea(){
        StageArea obj;
        if(STAGE_AREA.exists()){
            obj=Utils.readObject(STAGE_AREA,StageArea.class);
        }
        else {
            obj=new StageArea();
        }


        return obj;
    }

    public static void saveStageArea(StageArea obj){
        Utils.writeObject(STAGE_AREA,obj);
    }

    public static Commit getCommit(String hash){
        return Utils.readObject(join(COMMITS_DIR,hash), Commit.class);
    }

    public static void saveCommit(Commit obj){
        Utils.writeObject(join(COMMITS_DIR,obj.getHash()),obj);
    }



    /* TODO: fill in the rest of this class. */
}
