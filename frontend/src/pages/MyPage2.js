import React, { useEffect, useState } from "react";
import Web3 from "web3";
import { ethers } from "ethers";
import { saveAccount } from "../store/WalletReducer";
import { useDispatch } from "react-redux";
import axios from "axios";
import styled from "styled-components";
import Button from "@mui/material/Button";
import SettingsIcon from "@mui/icons-material/Settings";
import PhotoLibraryIcon from "@mui/icons-material/PhotoLibrary";
import LogoutIcon from "@mui/icons-material/Logout";
import { Link } from "react-router-dom";
import { Grid } from "@mui/material";
import TicketCollection from "../components/TicketCollection";
import {
  myTicketContract,
  showScheduleAbi,
  showScheduleManagerContract,
  ticketSaleManagerContract,
  web3,
} from "../utils/web3Config";
import TicketOnSale from "../components/MyPage/TicketOnSale";
import MyTicket from "../components/MyPage/MyTicket";
import MyTicketItem from "../components/MyPage/MyTicketItem";
import Tmp from "../components/MyPage/Tmp";
import MyShow from "../components/MyPage/MyShow";
// import { myTicketContract } from "../utils/web3";

const UnconnectedContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  height: 100%;
  margin-top: 2rem;
`;

const ConnectedContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  margin-bottom: 2rem;
`;

const LogInButton = styled.button`
  background: white;
  color: black;
  border-radius: 4px;
  border: 1px solid #e5e5e5;
  padding: 0.5rem 1rem;
  margin-top: 1rem;
  box-sizing: border-box;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  width: 40vw;
  display: flex;
  align-items: center;
  justify-content: space-between;

  &:hover {
    background: rgba(229, 229, 229, 0.8);
  }
`;

const UserInfo = styled.div`
  border: none;
`;
//
const NavList = styled.div`
  display: flex;
  justify-content: center;
  border-bottom: 1px solid #939393;
`;

const NavListItem = styled.div`
  margin: 12px;
  margin-left: 30px;
  margin-right: 30px;
  cursor: pointer;
`;

const NavListItemSelected = styled.div`
  margin: 12px;
  margin-left: 30px;
  margin-right: 30px;
  cursor: pointer;
  font-weight: bold;
`;
const TitleText = styled.h2`
  margin-left: 20px;
`;

const DescriptionDiv = styled.div`
  margin-left: 20px;
  margin-bottom: 20px;
`;

function MyPage() {
  const userData = JSON.parse(localStorage.getItem("userAccount"));
  const [pageNum, setPageNum] = useState(1);
  const handlePageNum = (page) => {
    setPageNum(page);
  };
  //
  const [userInfo, setUserInfo] = useState();
  const [isConnected, setIsConnected] = useState(false);
  const [walletInfo, setWalletInfo] = useState({
    nickname: "Unnamed",
    description: "Please Write Description",
  });
    ////
  const [ticketArray, setTicketArray] = useState([]);
  const [myTicketArray, setMyTicketArray] = useState([]);
  const [saleStatus, setSaleStatus] = useState(false);

  // Redux
  const dispatch = useDispatch();

  const detectCurrentProvider = () => {
    let provider;
    if (window.ethereum) {
      provider = window.ethereum;
      // ethereum ?????? ?????? ???
    } else if (window.web3) {
      provider = window.web3.currentProvider;
      // metamask??? ???????????? ?????? ??? -> ??????????????? ?????? ???????????? ??????
    } else {
      alert("Install Metamask!");
      window.location =
        "https://chrome.google.com/webstore/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn/";
    }
    return provider;
  };

  // ????????? ?????? ??????, ?????? ?????? ???????????? (+ ?????? ??????????????? ?????? ??????)
  const onConnect = async () => {
    try {
      const currentProvider = detectCurrentProvider();
      if (currentProvider) {
        await currentProvider.request({ method: "eth_requestAccounts" });
        const web3 = new Web3(currentProvider);

        // ?????? ?????? ???????????? - ?????? ??????, ???????????????, ??????
        const userAccount = await web3.eth.getAccounts();
        const chainId = await web3.eth.getChainId(); // ChainId
        const account = userAccount[0]; // ?????? ??????
        dispatch(saveAccount(account)); // Redux ??????
        let ethBalance = await web3.eth.getBalance(account); // ??????
        ethBalance = web3.utils.fromWei(ethBalance, "ether"); //wei??? ??????

        // userInfo??? ?????? (localStorage)
        saveUserInfo(ethBalance, account, chainId);

        // post
        axios
          .post(`https://nfticket.plus/api/v1/account/${account}`)
          .then((res) => {
            // console.log(res);
            if (res.status === 200) {
              checkConnectedWallet();
            }
          })
          .catch((err) => {
            console.error(err);
          });
      }
    } catch (err) {
      console.error(err);
    }
  };

  const saveUserInfo = (ethBalance, account, chainId) => {
    const userAccount = {
      account: account,
      balance: ethBalance,
      connectionid: chainId,
    };
    window.localStorage.setItem("userAccount", JSON.stringify(userAccount)); //user persisted data
    const userData = JSON.parse(localStorage.getItem("userAccount"));
    setUserInfo(userData);
    setIsConnected(true);
  };
  // ????????????
  const onDisconnect = () => {
    window.localStorage.removeItem("userAccount");
    setUserInfo({});
    setIsConnected(false);
    setWalletInfo([]);
  };

  function checkConnectedWallet() {
    const userData = JSON.parse(localStorage.getItem("userAccount"));
    if (userData != null) {
      setUserInfo(userData);
      setIsConnected(true);
      // api ?????? ?????? ?????? ????????????, walletInfo??? ?????? ??????
      // .get
      axios
        .get(`https://nfticket.plus/api/v1/profile/${userData.account}`)
        .then((res) => {
          setWalletInfo(res.data);
          // console.log(res);
        })
        .catch((err) => console.error(err));
    }
  }

  // ?????? ??????
  const getMyTickets = async () => {
    try {
      const userData = JSON.parse(localStorage.getItem("userAccount"));
      // ?????? ?????? ?????? ???????????? ??????????????? ?????? ???
      const balanceLength = await myTicketContract.methods
        .balanceOf(userData.account)
        .call();

      const tempArray = [];
      for (let i = 0; i < parseInt(balanceLength, 10); i++) {
        // ticketId: 1?????? ??????
        const ticketId = await myTicketContract.methods
          .tokenOfOwnerByIndex(userData.account, i)
          .call();
        // showScheduleId: 1?????? ??????
        const showScheduleId = await myTicketContract.methods
          .getShowScheduleId(ticketId)
          .call();
        // clasId: 1?????? ?????? => className(?????? ???????????? ??????)
        const classId = await myTicketContract.methods
          .getClassId(ticketId)
          .call();
        const showScheduleAddress = await showScheduleManagerContract.methods
          .getShowSchedule(showScheduleId)
          .call();
        const showScheduleContract = new web3.eth.Contract(
          showScheduleAbi,
          showScheduleAddress,
        );
        const className = await showScheduleContract.methods
          .getTicketClassName(classId)
          .call();
        // ?????? ?????? ??????????????????
        const showId = await showScheduleContract.methods.getShowId().call();
        const showInfo = await axios.get(`https://nfticket.plus/api/v1/show/${showId}`);
        // ?????? ????????? ??????
        const ticketUri = await myTicketContract.methods.getTokenURI(ticketId).call();

        tempArray.push({
          ticketId,
          showScheduleId,
          ticketUri,
          className,
          name: showInfo.data.name,
        });
      }
      setTicketArray(tempArray);
    } catch (err) {
      console.error(err);
    }
  };

  // ?????? ??????
  const getMyTicketsOnSale = async () => {
    try {
      const cnt = await ticketSaleManagerContract.methods
        .getSaleIdsByWallet(userData.account)
        .call();

      const tempAddress = [];
      for (let i = 0; i < parseInt(cnt.length); i++) {
        const saleAddr = await ticketSaleManagerContract.methods.getSale(parseInt(cnt[i])).call();
        tempAddress.push({ saleAddr });
      }
      setMyTicketArray(tempAddress);
    } catch (err) {
      console.error(err);
    }
  };
 

  useEffect(() => {
    checkConnectedWallet();
    getMyTicketsOnSale();
    getMyShows();
    getMyTickets();
  }, []);

  useEffect(() => {
    getMyTicketsOnSale();
    getMyShows();
    getMyTickets();
  }, [pageNum])
  
  // ????????? ??????
  const wtRoyalty = async () => {
    try {
      const withdrawR = await ticketSaleManagerContract.methods
        .withdrawRoyalty()
        .send({ from: userData.account });
      if (withdrawR.status) {
        alert("SSF ????????? ?????????????????????.");
      }
    } catch (err) {
      console.error(err);
    }
  };

  const [showArray, setShowArray] = useState([]);
  // show
  const getMyShows = async () => {
    try {
      const myShowSchedules = await showScheduleManagerContract.methods
        .getShowSchedulesOfOwner(userData.account)
        .call();
      const tmp = [];
      for (let i = 0; i < myShowSchedules.length; i++) {
        const getShowSchedule = await showScheduleManagerContract.methods
          .getShowSchedule(parseInt(myShowSchedules[i]))
          .call();
        tmp.push({ getShowSchedule });
      }
      setShowArray(tmp);
    } catch (err) {
      console.error(err);
    }
  };

  // ????????? S -> M
  const onChangeGallery = async () => {
    try {
      const data = {
        gallery: "galleryM",
        timestamp: new Date().getTime(),
      };
      const sign = await signMessage(JSON.stringify(data));
      const sendData = { info: data, hash_sign: sign };
      const res = await axios.patch(
        `https://nfticket.plus/api/v1/account/${userInfo.account}/gallery`,
        sendData,
      );
      // console.log(res);
    } catch (err) {
      console.error(err);
    }
  };
  // console.log("????????????", userInfo);
  const signMessage = async (message) => {
    // ?????????????????? ????????? ??????
    if (!window.ethereum)
      throw new Error("No crypto wallet found. Please install it.");

    await window.ethereum.send("eth_requestAccounts");
    const provider = new ethers.providers.Web3Provider(window.ethereum);
    const signer = provider.getSigner();
    const signature = await signer.signMessage(message);

    return signature;
  };

  return (
    <div style={{ paddingBottom: "100px" }}>
      {isConnected ? (
        <>
          <ConnectedContainer>
            {/* ?????? */}
            <img
              src='images/profile_img2.png'
              alt=''
              style={{
                height: "300px",
                width: "100%",
                objectFit: "cover",
              }}
            />
            {/* ????????? ?????? */}
            <Grid container spacing={2} style={{ marginTop: "0.5rem" }}>
              <Grid item xs={4}>
                <div></div>
              </Grid>
              <Grid item xs={4}>
                <div
                  style={{
                    display: "flex",
                    position: "absolute",
                    top: "390px",
                    left: "50%",
                    transform: "translate(-50%, -50%)",
                  }}
                >
                  <img
                    src={`https://nfticket.plus/showipfs/ipfs/${walletInfo.image_uri}`}
                    onError={({ currentTarget }) => {
                      currentTarget.onerror = null; // prevents looping
                      currentTarget.src = "images/MetaMask_Fox.svg.png";
                    }}
                    alt=''
                    style={{
                      width: "150px",
                      height: "150px",
                      borderRadius: "50%",
                      border: "3px solid white",
                      objectFit: "cover",
                      background: "grey",
                    }}
                  />
                </div>
              </Grid>
              <Grid
                item
                xs={4}
                style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "end",
                  paddingRight: "4rem",
                }}
              >
                <Link to='/MyPage/Settings'>
                  <SettingsIcon
                    style={{
                      display: "flex",
                      alignItems: "center",
                      color: "black",
                      height: "30px",
                      width: "30px",
                    }}
                  />
                </Link>
                <PhotoLibraryIcon
                  onClick={onChangeGallery}
                  style={{
                    display: "flex",
                    alignItems: "center",
                    color: "black",
                    height: "30px",
                    width: "30px",
                    marginLeft: "0.4rem",
                    cursor: "pointer",
                  }}
                ></PhotoLibraryIcon>
                <LogoutIcon
                  onClick={onDisconnect}
                  style={{
                    color: "black",
                    height: "30px",
                    width: "30px",
                    marginLeft: "0.5rem",
                    cursor: "pointer",
                  }}
                />
              </Grid>
            </Grid>
            {/* ?????? ?????? */}
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "center",
                marginTop: "2rem",
              }}
            >
              {/* ????????? */}
              <UserInfo>
                <h1>{walletInfo.nickname}</h1>
              </UserInfo>
              {/* ?????? ?????? */}
              <p
                style={{
                  width: "200px",
                  whiteSpace: "nowrap",
                  overflow: "hidden",
                  textOverflow: "ellipsis",
                  border: "1px solid grey",
                  borderRadius: "20px",
                  padding: "0.5rem",
                }}
              >
                <img
                  src='images/ethereum.png'
                  alt='eth'
                  style={{ width: "20px", height: "20px" }}
                />
                {userInfo.account}
              </p>
              {/* ?????? */}
              <UserInfo>
                <p>{walletInfo.description}</p>
              </UserInfo>
              <Button onClick={wtRoyalty}>Withdraw</Button>
            </div>
          </ConnectedContainer>

          <div>
            <NavList>
              {pageNum === 1 ? (
                <NavListItemSelected onClick={() => handlePageNum(1)}>
                  ?????? ??????
                </NavListItemSelected>
              ) : (
                <NavListItem onClick={() => handlePageNum(1)}>
                  ?????? ??????
                </NavListItem>
              )}
              {pageNum === 2 ? (
                <NavListItemSelected onClick={() => handlePageNum(2)}>
                  ?????? ??????
                </NavListItemSelected>
              ) : (
                <NavListItem onClick={() => handlePageNum(2)}>
                  ?????? ??????
                </NavListItem>
              )}
              {pageNum === 3 ? (
                <NavListItemSelected onClick={() => handlePageNum(2)}>
                  ?????? ??????
                </NavListItemSelected>
              ) : (
                <NavListItem onClick={() => handlePageNum(3)}>?????? ??????</NavListItem>
              )}
            </NavList>

            {pageNum === 1 && (
              <div>
                <TitleText>?????? ??????</TitleText>

                <DescriptionDiv>
                  <Grid container>
                    {ticketArray &&
                      ticketArray.map((v, i) => {
                        return (
                          <Grid item xs={3}>
                            <MyTicketItem key={i} {...v} />
                          </Grid>
                        );
                      })}
                  </Grid>
                </DescriptionDiv>
              </div>
            )}

            {pageNum === 2 && (
              <div>
                <TitleText>?????? ???????????? ??????</TitleText>

                <DescriptionDiv>
                  <Grid container>
                    {myTicketArray &&
                      myTicketArray.map((v, i) => {
                        return (
                          <Grid item xs={3}>
                            <Tmp key={i} {...v} />
                          </Grid>
                        );
                      })}
                  </Grid>
                </DescriptionDiv>
              </div>
            )}

            {pageNum === 3 && (
              <div>
                <TitleText>?????? ????????? ??????</TitleText>
                <DescriptionDiv>
                  <Grid container>
                    {showArray &&
                      showArray.map((v, i) => {
                        return (
                          <Grid item xz={3}>
                            <MyShow key={i} {...v} />
                          </Grid>
                        );
                      })}
                  </Grid>
                </DescriptionDiv>
              </div>
            )}
          </div>

        </>
      ) : (
        <UnconnectedContainer>
          <h1>Connect Your Wallet</h1>
          <LogInButton variant='contained' onClick={onConnect}>
            <img
              src='images/MetaMask_Fox.svg.png'
              alt='foxie'
              style={{ width: "50px", height: "50px" }}
            />
            Metamask
          </LogInButton>
        </UnconnectedContainer>
      )}
    </div>
  );
}

export default MyPage;
