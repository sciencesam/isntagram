 solidity ^0.4.0;

contract SimpleStorage {
    address owner;
    
    uint storedData;
    
    function set(uint x){
        owner = msg.sender;
        storedData = x;
    }
    function get() constant returns (uint){
        return storedData;
    }
    function deletey() {
        suicide(owner);
    }
}
