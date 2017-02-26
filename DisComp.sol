pragma solidity ^0.4.0; //selects solidity version

contract DisComp {
	uint startTime;
    uint private endtime;
    bool ended;
    uint maxCost;
    struct Proposal { //Proposal type carries the bid and sender
        uint cost;
        address contractor;
    }
    Proposal top; //top is the highest bidder 
    address public requestee; //stores the address of the person who wants stuff done
    mapping(address => Proposal) public proposals; //every proposal is stored

    event newTopProposal(Proposal prop);
    event Complete(Proposal winner);

    function startProposal(
        uint _biddingTime;
        address _requestee;
    ){
        requestee = _requestee;
        startTime = now;
        endtime = startTime + _biddingTime;
    }

    function propose payable () {
        if(now > endtime){
            throw;
        }
        if(msg.value <= maxCost){
            throw;
        }
        top.cost = msg.value;
        top.contractor = msg.sender;
        newTopProposal(top);
    }
    function end(){
        if(now <= endtime){
            throw;
        }
        if(ended){
            throw;
        }
        Complete(top); //ends it
        if(!requestee.send(top.cost)){
            throw;
        }
    }
}