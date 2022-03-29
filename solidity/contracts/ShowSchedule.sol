// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/utils/Counters.sol";
import "./MyTicket.sol";
import "./IResellPolicy.sol";
import "./ITicketClass.sol";

// 공연 정보 생성과 연관된 티켓 발매 역할
contract ShowSchedule is Ownable, IResellPolicy, ITicketClass {
    using Counters for Counters.Counter;

    // 공연 관리자(기획자) 주소
    address private _admin;
    uint64 private _showId;
    string private _stageName;
    uint256 private _startedAt;
    uint256 private _endedAt;
    bool private _isCancelled;
    Counters.Counter private _mintCount;
    mapping(uint256 => Counters.Counter) private _mintCountByClassId;
    uint256 private _maxMintCount;
    mapping(uint256 => mapping(uint256 => uint256)) private _ticketIdsBySeat;
    ResellPolicy private _resellPolicy;
    TicketClassInfo[] private _ticketClasses;

    IERC20 private _currencyContract;
    MyTicket private _ticketContract;
    
    constructor(
            address admin,
            uint64 showId,
            string memory stageName, 
            uint256 startedAt, 
            uint256 endedAt, 
            uint256 maxMintCount, 
            TicketClassInfo[] memory ticketClasses,
            ResellPolicy memory resellPolicy,
            address currencyContractAddress,
            address ticketContractAddress
        ) public {

        _setAdmin(admin);
        _setShowId(showId);
        _setStageName(stageName);
        _setStartedAt(block.timestamp + startedAt);
        _setEndedAt(block.timestamp + endedAt);
        _setMaxMintCount(maxMintCount);
        _setResellPolicy(resellPolicy);
        _setTicketClasses(ticketClasses);

        _isCancelled = false;
        _currencyContract = IERC20(currencyContractAddress);
        _ticketContract = MyTicket(ticketContractAddress);
    }

    function cancel() public notEnded onlyAdmin {
        _isCancelled = true;
    }

    // 티켓 등록
    function registerTicket(uint256 classId, uint256 seatIndex, uint256 ticketId) public payable notFull notClassFull(ticketId) notCanceled notStarted {
        // 먼저 해당 자리가 비어있는지 확인
        require(_ticketIdsBySeat[classId][seatIndex] == 0, "This seat is already registered");
        
        uint256 classId = _ticketContract.getClassId(ticketId);
        uint256 classPrice = getTicketClassPrice(classId);

        // 등록자에게 충분한 잔고가 있는지 확인
        require(_currencyContract.balanceOf(msg.sender) >= classPrice);

        // 등록자에게서 금액만큼 토큰을 받음
        _currencyContract.transferFrom(msg.sender, address(this), classPrice);

        // 해당 자리에 티켓 ID를 등록
        _ticketIdsBySeat[classId][seatIndex] = ticketId;

        // 전체 및 해당 등급의 발행 티켓 수를 증가
        _mintCount.increment();
        _mintCountByClassId[classId].increment();
    }

    // 티켓 등록 취소
    function revokeTicket(uint256 classId, uint256 seatIndex) public payable notStarted {
         // 먼저 해당 자리에 해당 티켓이 등록되어 있는지 확인
        require(_ticketIdsBySeat[classId][seatIndex] > 0);

        // 해당 자리에 등록된 티켓 ID 가져오기
        uint256 ticketId = _ticketIdsBySeat[classId][seatIndex];

        // 티켓의 class와 취소할 class가 일치하는지 확인
        require(classId == _ticketContract.getClassId(ticketId));

        // 해당 클래스의 등록 가격 가져오기
        uint256 classPrice = getTicketClassPrice(classId);

        // 티켓 주인인지 확인
        require(_ticketContract.ownerOf(ticketId) == msg.sender);

        // Contract에 충분한 잔고가 있는지 확인
        require(_currencyContract.balanceOf(address(this)) >= classPrice);
        
        // 등록자에게 금액만큼 토큰을 환불
        _currencyContract.transfer(msg.sender, classPrice);

        // 해당 자리에 티켓 ID를 등록 취소
        _ticketIdsBySeat[classId][seatIndex] = 0;

        // 전체 및 해당 등급의 발행 티켓 수를 감소
        _mintCount.decrement();
        _mintCountByClassId[classId].decrement();
    }

    // 티켓 환불
    function refundTicket(uint16 row, uint16 col) public payable Canceled {
        revokeTicket(row, col);
    }

    // 모금액 회수
        // 등록자에게 금액만큼 토큰을 전액 전송
    function withdraw() public payable Ended onlyAdmin {
        uint256 contractBalance = _currencyContract.balanceOf(address(this));
        _currencyContract.transfer(_admin, contractBalance);
    }

    function _setAdmin(address admin) private onlyOwner {
        _admin = admin;
    }

    function _setShowId(uint64 showId) private onlyOwner {
        _showId = showId;
    }

    function _setStageName(string memory stageName) private onlyOwner {
        _stageName = stageName;
    }

    function _setStartedAt(uint256 startedAt) private onlyOwner {
        _startedAt = startedAt;
    }

    function _setEndedAt(uint256 endedAt) private onlyOwner {
        _endedAt = endedAt;
    }

    function _setMaxMintCount(uint256 maxMintCount) private onlyOwner {
        _maxMintCount = maxMintCount;
    }

    function _setResellPolicy(ResellPolicy memory resellPolicy) private {
        _resellPolicy = resellPolicy;
    }

    function _setTicketClasses(TicketClassInfo[] memory ticketClasses) private onlyOwner {
        for (uint256 i = 0; i < ticketClasses.length; i++)
        {
            _ticketClasses.push(ticketClasses[i]);
        }
    }

    function getShowId() public view returns(uint64) {
        return _showId;
    }

    function getStageName() public view returns(string memory) {
        return _stageName;
    }

    function getStartedAt() public view returns(uint256) {
        return _startedAt;
    }

    function getEndedAt() public view returns(uint256) {
        return _endedAt;
    }

    function getMaxMintCount() public view returns(uint256) {
        return _maxMintCount;
    }

    function getResellPolicy() public view returns (bool, uint8, uint256) {
        return (_resellPolicy.isAvailable, _resellPolicy.royaltyRatePercent, _resellPolicy.priceLimit);
    }

    function getTicketClassCount() public view returns(uint256) {
        return _ticketClasses.length;
    }

    function getTicketClassName(uint256 ticketClassId) public view returns(string memory) {
        return _ticketClasses[ticketClassId].name;
    }

    function getTicketClassPrice(uint256 ticketClassId) public view returns(uint256) {
        return _ticketClasses[ticketClassId].price;
    }

    function getTicketClassMaxMintCount(uint256 ticketClassId) public view returns(uint256) {
        return _ticketClasses[ticketClassId].maxMintCount;
    }

    modifier onlyAdmin() {
        require(msg.sender == _admin, "You're not admin");
        _;
    }

    modifier notEmpty() {
        require(_mintCount.current() > 0, "You can't revoke the ticket yet");
        _;
    }

    modifier notFull() {
        require(_mintCount.current() < _maxMintCount, "You can't make a ticket at this schedule");
        _;
    }

    modifier notClassFull(uint256 ticketId) {
        uint256 classId = _ticketContract.getClassId(ticketId);
        uint256 classMaxMintCount = getTicketClassMaxMintCount(classId);

        // 먼저 해당 등급이 비어있는지 확인
        require(_mintCountByClassId[classId].current() < classMaxMintCount, "You can't make a ticket at this schedule");
        _;
    }

    modifier Started() {
        require(_startedAt < block.timestamp, "This schedule is not started yet");
        _;
    }

    modifier notStarted() {
        require(_startedAt >= block.timestamp, "This schedule is already started");
        _;
    }

    modifier Ended() {
        require(_endedAt < block.timestamp, "This schedule is not ended yet");
        _;
    }

    modifier notEnded() {
        require(_endedAt >= block.timestamp, "This schedule is already ended");
        _;
    }

    modifier Canceled() {
        require(_isCancelled, "This show is not cancelled");
        _;
    }

    modifier notCanceled() {
        require(!_isCancelled, "This show is cancelled");
        _;
    }
}