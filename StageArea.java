package gitlet;

import java.util.*;
import java.io.Serializable;

public class StageArea implements Serializable{
    private HashMap<String,String> stageadd;
    private ArrayList<String> stagerem;

    public StageArea(){
        stageadd=new HashMap<>();
        stagerem=new ArrayList<>();
    }
    
    public void add(String filename){
        Repository.saveBlob(filename);
        stageadd.put(filename,Repository.fileToHash(filename));
    }

    private boolean stageAddRem(String filename){
        return stageadd.remove(filename,Repository.fileToHash(filename));
    }

    public void rem(String filename){
        String HEAD=Repository.getHEAD();
        Commit commitObj=Repository.getCommit(HEAD);
        Iterator<Map.Entry<String,String>> itr=commitObj.getTrackedFilesItr();
        while(itr.hasNext()){
            Map.Entry<String,String> mapping=itr.next();
            mapping.
        }
    }
    
    public void printFiles()
    {
        for (Map.Entry<String, String> mapElement :
                stageadd.entrySet()) {
            System.out.println(mapElement.getKey()+"-->"+mapElement.getValue());
        }
    }

    public boolean isEmpty(){
        if(stageadd.isEmpty() && stagerem.isEmpty())
        {
            return true;
        }
        return false;

    }

    public void flush(){
            stageadd.clear();
            stagerem.clear();
    }

    public Iterator<Map.Entry<String,String>>  retAddIterator() throws Exception{
        if(isEmpty()){
            throw new Exception("No changes added to the commit");
        }
        else{
            return stageadd.entrySet().iterator();
        }
    }
}