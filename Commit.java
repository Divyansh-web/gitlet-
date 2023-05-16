package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Calendar;
import java.text.*;


/** Represents a gitlet commit object.
 *
 *
 *  @author Divyansh
 */
public class Commit implements Serializable,Cloneable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    private String timeStamp;
    private String parentId;

    private HashMap<String,String> blobs=new HashMap<>();

    public Commit(){
        message="initial commit";
        Date defTime=new Date(0L);
        timeStamp=defTime.toString();
    }

    public void commit(StageArea obj,String msg1,String parentId1){
        try{
            addCommit(obj.retAddIterator(), msg1,parentId1);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }

    }

    /*sets files that were staged for add to the commit*/
    private void addCommit(Iterator<Map.Entry<String,String>> stageRemItr,String msg1,String parentId1){
        while(stageRemItr.hasNext()){
            Map.Entry<String,String> blob=stageRemItr.next();
            String filename=blob.getKey();
            String fileHash=blob.getValue();
            blobs.put(filename,fileHash);
        }
        setMessage(msg1);
        Date currentDateTime=Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate=dateFormat.format(currentDateTime);
        setTimeStamp(strDate);
        setParentId(parentId1);

    }
    /*removes the files that were staged for removal*/
    private void remCommit(){

    }
    public String getMessage(){
        return message;
    }
    public String getTimeStamp(){
        return timeStamp;
    }
    public String getParentId(){
        return parentId;
    }

    private void setMessage(String msg){
        message=msg;
    }
    private void setTimeStamp(String timeString){
        timeStamp=timeString;
    }
    private void setParentId(String parentHash){
        parentId=parentHash;
    }
    public String getHash(){
        return Utils.sha1(Utils.serialize(this));
    }


    @Override
    public String toString(){
        return "--------------------------\n"+message+"\n"+timeStamp+"\n"+getHash()+"\n";
    }


    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public void printTrackedFiles(){
        Iterator<Map.Entry<String,String>> itr=blobs.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<String,String> blob= itr.next();
            System.out.println(blob.getKey()+"-->"+blob.getValue());
        }
    }

    public Iterator<Map.Entry<String,String>> getTrackedFilesItr(){
        return blobs.entrySet().iterator();
    }


    /* TODO: fill in the rest of this class. */
}
