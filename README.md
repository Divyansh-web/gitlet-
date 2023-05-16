# Gitlet Design Document

**Name**: Divyansh Yadav

## Classes and Data Structures

### Commit


#### Fields

1. String message
2. String timeStamp -> stores time in string format 
3. String id -> sha1 hash of this object
4. String parentId -> sha1 hash of the parent
5. HashMap<String,String> blobs -> maps the name of files to their respective git-sh1 hash code

#### Methods
1. getMessage() -> returns message
2. getTimeStam() -> returns timeStamp
3. getParentId() -> retuns parentId
4. getHash() -> returns sha1 hash of the commit object
5. save() -> saves the commit on the drive
6. toString() -> returns commit in string format

### Respository -> Handles persistance and keeps tracks of paths 

#### Fields

1. File CWD -> current working directory
2. File GITLET_DIR -> everything is stored in this path
3. File STAGE_ADD -> path of the data-structure that handles staging area
4. File COMMITS_DIR -> path of commits
5. File BLOB_DIR -> path of blobs
6. File HEAD_FILE -> path of HEAD file 
7. File MASTER_FILE -> master file path

#### Methods

1. setUpPersistance() -> makes the necessary directories
2. fileToHash(String filename) -> returns the sh1 hash of the contents of the file
3. saveBlob(String filename)











### StageArea -> data structure for keeping track of staged files 


### Fields 
1. ArrayList<String> stageRemove -> stores sha1 hash of blobs that are staged to be removed
2. HashMap<String,String> stageAdd -> map[NameofFile] -> sha1HashOfTheBlob

## Algorithms

## Persistence

